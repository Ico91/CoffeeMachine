package coffee_machine.xml_io;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffee_machine.reports.transformers.dto.DrinksReportDTO;
import coffee_machine.storages.transformers.dto.CoffeeMachineDTO;
import coffee_machine.xml_io.XMLDocumentMetaData;
import coffee_machine.xml_io.XMLIO;
import coffee_machine.xml_io.exceptions.XMLIOException;

public class XMLIOTests {

	private XMLIO xml;
	private XMLDocumentMetaData xmlMeta;

	@Before
	public void setUp() {
		xml = new XMLIO();
	}

	@Test(expected = XMLIOException.class)
	public void testReadWithWrongSchemaFile() throws XMLIOException {
		xmlMeta = new XMLDocumentMetaData(CoffeeMachineDTO.class,
				"resources/coffeeMachine.xml", "coffeeMachineSchema.xsd");
		xml.read(xmlMeta);
	}

	@Test(expected = XMLIOException.class)
	public void testReadWithWrongXMLFile() throws XMLIOException {
		xmlMeta = new XMLDocumentMetaData(CoffeeMachineDTO.class,
				"coffeeMachineFile.xml", "src/CoffeeMachineDTO.xsd");
		xml.read(xmlMeta);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWriteWithWrongObjectClass() throws XMLIOException {
		xmlMeta = new XMLDocumentMetaData(DrinksReportDTO.class, "report.xml",
				"src/DrinksReport.xsd");
		xml.write(xmlMeta,
				new coffee_machine.storages.transformers.dto.CoffeeMachineDTO());
	}

	@Test
	public void testWriteAndRead() throws XMLIOException {
		xmlMeta = new XMLDocumentMetaData(DrinksReportDTO.class,
				"resources/TestXMLs/XMLIO/testWriteReport.xml",
				"resources/TestXMLs/XMLIO/testWriteReportSchema.xsd");

		DrinksReportDTO objectToWrite = new DrinksReportDTO();
		DrinksReportDTO.OrderedDrinks reportOrderedDrinks = new DrinksReportDTO.OrderedDrinks();

		DrinksReportDTO.OrderedDrinks.Drink drink = new DrinksReportDTO.OrderedDrinks.Drink();
		drink.setAmount(7);
		drink.setName("Coffee");
		reportOrderedDrinks.getDrink().add(drink);
		drink = new DrinksReportDTO.OrderedDrinks.Drink();
		drink.setAmount(5);
		drink.setName("Hot Chocolate");
		reportOrderedDrinks.getDrink().add(drink);
		drink = new DrinksReportDTO.OrderedDrinks.Drink();
		drink.setAmount(3);
		drink.setName("Tea");
		reportOrderedDrinks.getDrink().add(drink);

		objectToWrite.setOrderedDrinks(reportOrderedDrinks);
		objectToWrite.setTotal(15);

		xml.write(xmlMeta, objectToWrite);

		DrinksReportDTO readObject = (DrinksReportDTO) xml.read(xmlMeta);

		assertEquals("Written and read objects should be equal", objectToWrite,
				readObject);
	}

	@After
	public void tearDown() {
		xml = null;
		xmlMeta = null;
	}

}
