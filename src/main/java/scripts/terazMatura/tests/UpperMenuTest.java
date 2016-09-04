package scripts.terazMatura.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.selenium.EngineException;
import core.util.internal.logger.Logger;
import scripts.terazMatura.pageObjects.homePage.HomePageMethods;
import scripts.terazMatura.pageObjects.studentZone.StudentZoneMethods;

public class UpperMenuTest {

	protected Logger log = Logger.getInstance();
	
	@Before
	public void beforeTest(){
		log.newLog("Upper Menu Test");
	}
	
	@Test
	public void upperMenuTest() throws EngineException {
		HomePageMethods hp = new HomePageMethods();
		StudentZoneMethods sz = hp.doNavigateToStudentZone();
		sz.doVerifyMojaMaturaMainElement();
		sz.doNavigateToMaturaAZ();
		sz.doVerifyMaturaAZMainElement();
		sz.doNavigateToTesty();
		sz.doVerifyTesty();
		sz.doNavigateToJakZycZebyZdac();
		sz.doVerifyJakZycZebyZdac();
		
	}
	
	@After
	public void afterTest(){
		log.printLog();
	}
}
