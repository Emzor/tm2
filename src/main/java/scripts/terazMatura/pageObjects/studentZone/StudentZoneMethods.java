package scripts.terazMatura.pageObjects.studentZone;

import core.Constants;
import core.selenium.Engine;
import core.selenium.EngineException;
import core.selenium.PageObject;
import core.util.Assert;
import core.util.internal.logger.Logger;

public class StudentZoneMethods extends PageObject {

	Logger log = Logger.getInstance();
	StudentZoneFields fields = new StudentZoneFields();

	public StudentZoneMethods() throws EngineException {
		super();
		super.goToPage(Constants.TERAZMATURA_STUDENT_URL);
	}

	public StudentZoneMethods(Engine engine) throws EngineException {
		super(engine);
	}

	public StudentZoneMethods doNavigateToMojaMatura() throws EngineException {

		try {
			engine.get(fields.MOJAMATURA_BUTTON).waitForElementClickable().click();
			log.log("Clicked Moja Matura element from main nav bar", true);
		} catch (Exception e) {
			log.log("Couldn't click Moja Matura element from main nav bar", false);
			throw e;
		}
		return this;
	}

	public StudentZoneMethods doVerifyMojaMaturaMainElement() throws EngineException {

		int elem = engine.get(fields.MOJAMATURA_MAIN_ELEMENT).size();

		if (elem > 0) {
			log.log("Found main element: Twoje przedmioty", true);
		} else {
			log.log("Couldn't find main element: Twoje przedmioty", false);
			throw new EngineException();
		}

		return this;
	}

	public StudentZoneMethods doNavigateToMaturaAZ() throws EngineException {

		try {
			engine.get(fields.MATURAAZ_BUTTON).waitForElementClickable().click();
			log.log("Clicked link: Matura od A do Z", true);
		} catch (Exception e) {
			log.log("Couldn't click link: Matura od A do Z", true);
			throw e;
		}

		return this;
	}

	public StudentZoneMethods doVerifyMaturaAZMainElement() throws EngineException {

		int elem = engine.get(fields.MATURAAZ_MAIN_ELEMENT).size();

		if (elem > 0) {
			log.log("Found main element: Matura od A do Z", true);
		} else {
			log.log("Couldn't find main element: Matura od A do Z, i might be on wrong page: " + engine.getCurrentURL(),
					false);
			throw new EngineException();
		}

		return this;
	}

	public StudentZoneMethods doNavigateToTesty() throws EngineException {

		try {
			engine.get(fields.TESTY_BUTTON).waitForElementClickable().click();
			log.log("Clicked Testy", true);
		} catch (Exception e) {
			log.log("Couldn't click Testy", false);
			throw e;
		}
		return this;
	}

	public StudentZoneMethods doVerifyTesty() throws EngineException {

		int elem = engine.get(fields.TESTY_NOT_LOGGED_ELEMENT).size();

		if (elem > 0) {
			log.log("Found main element, Formatka logowania", true);
		} else {
			log.log("Couldn't find main element: Formatka logowania", false);
			throw new EngineException();
		}

		return this;
	}
	
	public StudentZoneMethods doNavigateToProbnaMatura() throws EngineException{
		try {
			engine.get(fields.PROBNA_MATURA_BUTTON).waitForElementClickable().click();
			log.log("Clicked Probna matura z Nowa era", true);
		} catch (Exception e) {
			log.log("Couldn't click probna matura z Nowa era", false);
			throw e;
		}
		
		return this;
	}
	
	public StudentZoneMethods doVerifyProbnaMatura() throws EngineException{
		int elem = engine.get(fields.PROBNA_MATURA_ARTICLE).size();

		if (elem > 0) {
			log.log("Found main element, Article", true);
		} else {
			log.log("Couldn't find main element: Article", false);
			throw new EngineException();
		}

		return this;
	}
	
	public StudentZoneMethods doNavigateToJakZycZebyZdac(){
		
		try {
			engine.get(fields.JAK_ZYC_BUTTON).waitForElementClickable().click();
			log.log("Clicked: jak zyc zeby zdac", true);
		} catch (Exception e) {
			log.log("Couldn't click: jak zyc zeby zdac", false);
		}
		
		return this;
	}
	
	public StudentZoneMethods doVerifyJakZycZebyZdac() throws EngineException{
		
		String headerText = engine.get(fields.JAK_ZYC_ZEBY_ZDAC_HEADER).waitForElementVisible().getText();
		
		try {
			Assert.assertText("Jak żyć, żeby zdać?", headerText);
			log.log("Header element asserted successfully", true);
		} catch (Exception e) {
			log.log("Couldn't assert header element", false);
		}
		
		
		return this;
	}
}
