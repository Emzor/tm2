package scripts.terazMatura.pageObjects.homePage;

import core.Constants;
import core.selenium.Engine;
import core.selenium.EngineException;
import core.selenium.PageObject;
import core.util.internal.logger.Logger;
import scripts.terazMatura.pageObjects.studentZone.StudentZoneMethods;
import scripts.terazMatura.pageObjects.homePage.HomePageFields;

public class HomePageMethods extends PageObject{

	Logger log = Logger.getInstance();
	HomePageFields fields = new HomePageFields();

	public HomePageMethods() throws EngineException {
		super();
		super.goToPage(Constants.TERAZMATURA_MAIN_URL);
	}
	
	public HomePageMethods(Engine engine) throws EngineException {
		super(engine);
		super.goToPage(Constants.TERAZMATURA_MAIN_URL);
	}
	
	public StudentZoneMethods doNavigateToStudentZone() throws EngineException{
		
		try {
			engine.get(fields.STUDENT_ZONE_BUTTON).waitForElementClickable().click();
			log.log("Clicked student zone Button", true);
		} catch (Exception e) {
			log.log("Couldn't click student zone button", false);
			throw e;
		}
		
		return new StudentZoneMethods(engine);
	}
	

	public HomePageMethods doClickTeacherZone(){
		
		return this;
	}
}
