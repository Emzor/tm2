package scripts.terazMatura.pageObjects.studentZone;

public class StudentZoneFields {
	
	//BUTTONS
	final String MOJAMATURA_BUTTON = "//div[@id = 'menuglowne']//a[text() = 'Moja matura']";
	final String MATURAAZ_BUTTON = "//div[@id = 'menuglowne']//a[text() = 'Matura od A do Z']";
	final String TESTY_BUTTON = "//div[@id = 'menuglowne']//a[text() = 'Testy']";
	final String PROBNA_MATURA_BUTTON = "//div[@id = 'menuglowne']//a[contains(text(), 'Próbna')]";
	final String JAK_ZYC_BUTTON = "//li[starts-with(@class, 'item-105')]";
	final String SKLEP_BUTTON = "//div[@id = 'menuglowne']//a[text() = 'Sklep']";
	
	//ASSERTION POINTS
	final String MOJAMATURA_MAIN_ELEMENT = "//div[@class = 'pasek przedmioty']//h3[text() = 'Twoje przedmioty']";
	final String MATURAAZ_MAIN_ELEMENT = "//div[@class = 'pasek sprawdzsie']//h2[contains(text(), 'Matura od A do Z')]";
	final String TESTY_NOT_LOGGED_ELEMENT = "//form[@id = 'form-rejestracja']";
	final String PROBNA_MATURA_ARTICLE = "//div[@class = 'tresc artykul']";
	final String JAK_ZYC_ZEBY_ZDAC_HEADER = "//div[starts-with(@class, 'page-header')]/h1";
	final String SKLEP_MAIN_ELEMENT = "";
}
