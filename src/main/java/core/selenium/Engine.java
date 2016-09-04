package core.selenium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Constants;
import core.util.Assert;

public class Engine {

	private WebDriver driver;
	private List<WebElement> elements;
	private WebElement element;
	private By query;
	String driverName = Constants.SELENIUM_DRIVER;

	public Engine() {
		try {
			switch (driverName) {
			case "firefox":
				driver = getFireFoxDriver();
				break;
			case "mobile":
				driver = getMobileDriver();
			default:
				break;
			}

		} catch (Exception exc) {
			System.out.println("EngineFailure");
			exc.printStackTrace();
		}
	}

	public Engine sleep(long millis) throws EngineException {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new EngineException("InterruptedException");
		}
		return this;
	}

	private static WebDriver getFireFoxDriver() {

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("pdfjs.disabled", true);
		profile.setPreference("plugin.state.npctrl", 2);
		WebDriver driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		return driver;
	}

	private static WebDriver getMobileDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Google Nexus 5");

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		WebDriver driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		return driver;
	}

	public Engine goToPage(String url) throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't go to Page because driver instance not exist");
		}
		driver.get(url);
		return this;
	}

	public String getCurrentURL() throws EngineException {
		sleep(50);

		if (driver == null) {
			throw new EngineException("Can't getCurrentURL because driver instance not exist");
		}
		return driver.getCurrentUrl();
	}

	public String getPageTitle() throws EngineException {
		sleep(50);
		if (driver == null) {
			throw new EngineException("Can't getPageTitle because driver instance not exist");
		}
		return driver.getTitle();
	}

	public Engine get(String xpath) throws EngineException {
		query = By.xpath(xpath);
		get(driver.findElements(query));
		return this;
	}

	public Engine get(By by) throws EngineException {
		query = by;
		get(driver.findElements(by));
		return this;
	}

	public String getEachText() throws EngineException {
		if (elements == null) {
			throw new EngineException("Can't get text of elements because they are not selected");
		}
		String print = "";
		for (WebElement e : elements) {
			print += e.getText() + ", ";
		}
		return print.substring(0, print.length() - 2);
	}

	public String getAttribute(String attribute) throws EngineException {
		if (element == null) {
			throw new EngineException("Can't getAttribute of element because it's not selected");
		}
		return this.element.getAttribute(attribute);
	}

	public String getEachAttribute(String attribute) throws EngineException {
		if (elements == null) {
			throw new EngineException("Can't get attrubute of elements because they are not selected");
		}
		String print = "";
		for (WebElement e : elements) {
			print += e.getAttribute(attribute) + ", ";
		}
		return print.substring(0, print.length() - 2);
	}

	public Engine waitForElementVisible(int timeout) throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't waitForElementVisible because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("waitForElementVisible - element is not selected");
		}

		sleep(100);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(query)));
		get(driver.findElements(query));
		return this;
	}

	public Engine waitForElementVisible() throws EngineException {
		return waitForElementVisible(30);
	}

	public Engine waitForElementClickable(int timeout) throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't waitForElementClickable because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("waitForElementClickable - element is not selected");
		}
		sleep(100);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(query)));
		get(driver.findElements(query));
		return this;
	}

	public Engine waitForElementClickable() throws EngineException {
		return waitForElementClickable(30);
	}

	public Engine selectByValue(String value) throws EngineException {
		if (this.element == null) {
			throw new EngineException("selectByValue - element is not selected");
		}
		new Select(this.element).selectByValue(value);
		return this;
	}

	public Engine selectByText(String text) throws EngineException {
		if (this.element == null) {
			throw new EngineException("selectByText - element is not selected");
		}
		new Select(this.element).selectByVisibleText(text);
		return this;
	}

	public Engine selectByIndex(int index) throws EngineException {
		if (this.element == null) {
			throw new EngineException("selectByIndex - element is not selected");
		}
		new Select(this.element).selectByIndex(index);
		;
		return this;
	}

	public Engine click() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't click because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("click - element is not selected");
		}
		sleep(50);
		waitForElementClickable();
		this.element.click();
		return this;
	}

	public Engine jsClick() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't jsClick because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("jsClick - element is not selected");
		}

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		return this;
	}

	public Engine hover() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't hover because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("hover - element is not selected");
		}
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.perform();
		sleep(500);
		return this;
	}

	public Engine clear() throws EngineException {
		if (this.element == null) {
			throw new EngineException("clear - element is not selected");
		}
		this.element.clear();
		return this;
	}

	public Engine sendKeys(String key) throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't sendKeys because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("sendKeys - element is not selected");
		}
		sleep(50).clear().sleep(50);
		this.element.sendKeys(key);
		return this;
	}

	public Engine sendKeysWithVerification(String key) throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't sendKeysWithVerification because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("sendKeysWithVerification - element is not selected");
		}
		sleep(50).clear().sleep(50);
		for (int i = 0; i < key.length(); i++) {
			String actualChar = "" + key.charAt(i);
			this.element.sendKeys(actualChar);
			char[] inputChars = this.element.getAttribute("value").toCharArray();
			String inputLastChar = inputChars[inputChars.length - 1] + "";
			if (!actualChar.equals(inputLastChar) && actualChar.length() != inputLastChar.length()) {
				throw new EngineException(
						"sendKeysWithVerification - characters not match " + actualChar + " != " + inputLastChar);
			}
		}
		return this;
	}

	public Engine scrollToBottom() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't scrollToBottom because driver instance doesn't exist");
		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		return this;
	}

	public Engine scrollToElement() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't scrollToElement because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("scrollToElement - element is not selected");
		}
		Point p = element.getLocation();
		Dimension d = element.getSize();
		int x = p.getX() + d.getWidth();
		int y = p.getY() + d.getHeight();
		((JavascriptExecutor) driver).executeScript("javascript:window.scrollTo(" + x + "," + y + ")");
		sleep(10);
		return this;
	}

	public String getNodeSource() throws EngineException {
		JavascriptExecutor js;

		if (driver == null) {
			throw new EngineException("Can't get element because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("getNodeSource - element is not selected");
		}
		sleep(50);
		js = ((JavascriptExecutor) driver);
		String abcd = js.executeScript("return arguments[0].innerHTML.toString();", element).toString();
		return abcd;
	}

	public Engine openNewTab(String url) throws EngineException {

		if (driver == null) {
			throw new EngineException("Can't openNewTab because driver instance doesn't exist");
		}

		JavascriptExecutor js;
		js = ((JavascriptExecutor) driver);
		js.executeScript("javascript:window.open('" + url + "', '_blank');");

		return this;
	}

	public Engine switchToPopUp() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't switchToPopUp because driver instance doesn't exist");
		}
		ArrayList<String> activeWindows = new ArrayList<>(driver.getWindowHandles());
		if (activeWindows.size() > 1) {
			driver.switchTo().window(activeWindows.get(1));
		}
		return this;
	}

	public Engine switchToMainWindow() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't switchToMainWindow because driver instance doesn't exist");
		}
		ArrayList<String> activeWindows = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(activeWindows.get(0));
		return this;
	}

	public Engine switchTabChrome() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't switchTabChrome because driver instance doesn't exist");
		}
		new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).build().perform();
		return this;
	}

	public Engine scrollToTop() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't scrollToTop because driver instance doesn't exist");
		}
		new Actions(driver).sendKeys(Keys.HOME).build().perform();
		return this;
	}

	public Assert assertElement() throws EngineException {
		if (this.elements == null) {
			throw new EngineException("assertElement - element is not selected");
		}
		return new Assert(elements);
	}

	public Engine getVisible(String xpath) throws EngineException {
		query = By.xpath(xpath);
		get(driver.findElements(query));
		waitForElementVisible();
		return this;
	}

	public Engine getVisible(String xpath, int timeout) throws EngineException {
		query = By.xpath(xpath);
		get(driver.findElements(query));
		waitForElementVisible(timeout);
		return this;
	}

	public Engine getVisible(By by) throws EngineException {
		query = by;
		get(driver.findElements(query));
		waitForElementVisible();
		return this;
	}

	public Engine getVisible(By by, int timeout) throws EngineException {
		query = by;
		get(driver.findElements(query));
		waitForElementVisible(timeout);
		return this;
	}

	private void get(List<WebElement> elements) throws EngineException {
		sleep(500);
		if (driver == null) {
			throw new EngineException("Can't get because driver instance not exist");
		}
		this.elements = elements;
		if (elements.size() > 0) {
			this.element = elements.get(0);
		} else {
			this.element = null;
		}
	}

	public Engine point(int index) throws EngineException {
		if (elements == null) {
			throw new EngineException("Can't point element because elements are not selected");
		}
		if (elements.size() < index + 1) {
			throw new EngineException("Can't point " + (index + 1) + " element because only " + elements.size()
					+ " elements is selected");
		}
		this.element = elements.get(index);
		return this;
	}

	public int size() throws EngineException {
		if (elements == null) {
			throw new EngineException("Can't count elements because they are not selected");
		}
		return elements.size();
	}

	public String getText() throws EngineException {
		if (element == null) {
			throw new EngineException("Can't getText of element because doesn't have any element");
		}
		return this.element.getText();
	}

	public Engine rightClick() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't rightClick because driver instance not exist");
		}
		Actions act = new Actions(driver);
		act.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		return this;
	}

	public Engine refresh() throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't refresh because driver instance not exist");
		}
		driver.navigate().refresh();
		return this;
	}

	public Engine dragAndDropHorizontal(int i) throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't dragAndDropHorizontal because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("dragAndDropHorizontal - element is not selected");
		}
		Actions move = new Actions(driver);
		org.openqa.selenium.interactions.Action dragAndDrop;

		int width = element.getSize().getWidth();

		dragAndDrop = move.clickAndHold(element).moveByOffset(-(width / 5), 0)
				.moveByOffset((int) ((width / 200) * i), 0).release().build();
		dragAndDrop.perform();

		return this;
	}

	public Engine dragAndDropVertical(int i) throws EngineException {
		if (driver == null) {
			throw new EngineException("Can't dragAndDropVertical because driver instance doesn't exist");
		}
		if (this.element == null) {
			throw new EngineException("dragAndDropVertical - element is not selected");
		}
		Actions move = new Actions(driver);
		org.openqa.selenium.interactions.Action dragAndDrop;

		int height = element.getSize().getHeight();

		dragAndDrop = move.clickAndHold(element).moveByOffset(0, -(height / 5))
				.moveByOffset(0, (int) ((height / 200) * i)).release().build();
		dragAndDrop.perform();

		return this;
	}

	public Engine setValueAttributeByID(String id, String newValue) throws EngineException {

		if (driver == null) {
			throw new EngineException("Can't setValueAttributeByID because driver instance doesn't exist");
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').setAttribute('value', '" + newValue + "')");
		return this;
	}

	public Engine switchFrame(String iFrameID) throws EngineException {

		if (driver == null) {
			throw new EngineException("Can't switchFrame because driver instance doesn't exist");
		}

		WebElement fr = driver.findElement(By.id(iFrameID));
		driver.switchTo().frame(fr);
		return this;
	}

	public Engine leaveFrame() throws EngineException {

		if (driver == null) {
			throw new EngineException("Can't leaveFrame because driver instance doesn't exist");
		}

		driver.switchTo().defaultContent();
		return this;
	}

	public void quit() throws EngineException {
		try {
			driver.quit();
			driver = null;
			sleep(2000);
		} catch (Exception e) {
			System.out.println("Exception while closing browser");
		}
	}
}
