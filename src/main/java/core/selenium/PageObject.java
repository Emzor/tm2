package core.selenium;

import core.util.internal.logger.Logger;

public class PageObject {
	protected static Engine engine;
	Logger log = Logger.getInstance();

	public PageObject() {
		engine = new Engine();
	}

	public PageObject(Engine engine) {
		PageObject.engine = engine;
	}

	public void goToPage(String url) throws EngineException {
		try {
			engine.goToPage(url);
			log.log("Opened page: "+url, true);
		} catch (Exception e) {
			log.log("Couldn't open page: "+url, false);
		}
	}

	public void endTest() throws EngineException {
		try {
			engine.quit();
			log.log("Closed browser", true);
		} catch (Exception e) {
			log.log("Couldn't close browser", false);
		}
		
	}
	
	public void openNewTab(String url) throws EngineException{
		try {
			engine.openNewTab(url);
			log.log("Opened new tab: "+url, true);
		} catch (Exception e) {
			log.log("Couldn't open new tab: "+url, false);
		}
		
	}
	
	public void switchToPopUp() throws EngineException {
		try {
			engine.switchToPopUp();
			log.log("Switched to pop up", true);
		} catch (Exception e) {
			log.log("Couldn't switch to pop up", false);
		}
	}
	
	public void switchToMainWindow() throws EngineException {
		try {
			engine.switchToMainWindow();
			log.log("Switched to main window", true);
		} catch (Exception e) {
			log.log("Couldn't switch to main window", false);
		}
	}
	
	public void switchTabChrome() {
		try {
			engine.switchTabChrome();
			log.log("Swtiched tab", true);
		}
		catch (Exception e) {
			log.log("Failed to switch tab", false);
		}
	}
	

}
