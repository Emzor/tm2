package scripts.terazMatura.pageObjects.teacherZone;

import core.Constants;
import core.selenium.Engine;
import core.selenium.EngineException;
import core.selenium.PageObject;

public class TeacherZoneMethods extends PageObject{
	
	public TeacherZoneMethods() throws EngineException {
		super();
		super.goToPage(Constants.TERAZMATURA_STUDENT_URL);
	}
	
	public TeacherZoneMethods(Engine engine) throws EngineException {
		super(engine);
	}
}
