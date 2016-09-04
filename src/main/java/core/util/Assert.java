package core.util;

import java.util.List;

import org.openqa.selenium.WebElement;

public class Assert {
	private List<WebElement> elements;

	public Assert(List<WebElement> elements) {
		this.elements = elements;
	}

	public static void assertTrue(boolean condition) {
		org.junit.Assert.assertTrue(condition);
	}

	public static void assertFalse(boolean condition) {
		org.junit.Assert.assertFalse(condition);
	}

	public static void assertText(String expected, String string) {
		org.junit.Assert.assertEquals(expected, string);
	}

	public static void assertText(Double expected, Double doubled) {
		org.junit.Assert.assertEquals(expected, doubled);
	}
	
	public void assertAmountOfElementsIsGraterThan(int presence) {
		assertTrue(this.elements.size() >= presence);
	}

	public void assertAmountOfElementsIsEqualsTo(int presence) {
		assertTrue(this.elements.size() == presence);
	}
}
