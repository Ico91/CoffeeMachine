package xmlModule;

import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;
import modules.xmlModule.exceptions.XMLIOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.dto.reports.DrinksReportDTO;

public class XMLIOTests {

	private XMLIO xml;
	private XMLDocumentMetaData xmlMeta;

	@Before
	public void setUp() {
		xml = new XMLIO();
	}

	@Test(expected = XMLIOException.class)
	public void testReadWithWrongSchemaFile() throws XMLIOException {
		xmlMeta = new XMLDocumentMetaData(DrinksReportDTO.class, "report.xml",
				"drinksSchema.xsd");
		xml.read(xmlMeta);
	}

	@Test(expected = XMLIOException.class)
	public void testReadWithWrongXMLFile() throws XMLIOException {
		xmlMeta = new XMLDocumentMetaData(DrinksReportDTO.class, "report2.xml",
				"reportSchema.xsd");
		xml.read(xmlMeta);
	}

	@Test(expected = XMLIOException.class)
	public void testWriteWithWrongObjectClass() throws XMLIOException {
		xmlMeta = new XMLDocumentMetaData(DrinksReportDTO.class, "report.xml",
				"reportSchema.xsd");
		xml.write(xmlMeta, new coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO());
	}


	@After
	public void tearDown() {
		xml = null;
		xmlMeta = null;
	}

}
