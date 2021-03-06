package coffee_machine.storage.transformers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffee_machine.model.Drink;
import coffee_machine.model.DrinksContainer;
import coffee_machine.storages.transformers.DTOToDrinksContainerTransformer;
import coffee_machine.storages.transformers.dto.CoffeeMachineDTO;
import coffee_machine.xml_io.XMLDocumentMetaData;
import coffee_machine.xml_io.XMLIO;
import coffee_machine.xml_io.exceptions.XMLIOException;

/**
 * @author Galina
 * 
 */

public class DTOToDrinksContainerTransformerTests {
	private XMLIO xmlIO;

	@Before
	public void setupObject() {
		xmlIO = new XMLIO();
	}

	@Test
	public void testDTOToMoneyTransformer() throws XMLIOException {
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(new XMLDocumentMetaData(CoffeeMachineDTO.class,
						"resources/TestXMLs/DTOToDrinksContainerXML/CoffeeMachineDTO.xml",
						"src/CoffeeMachineDTO.xsd"));
		DTOToDrinksContainerTransformer dtoToDrinksContainerTransformer = new DTOToDrinksContainerTransformer();
		DrinksContainer expectedDrinksContainer = dtoToDrinksContainerTransformer
				.transform(coffeeMachineDTO);
		DrinksContainer actualDrinksContainer = new DrinksContainer();
		actualDrinksContainer.add(new Drink("Coffee", 30), 100).add(
				new Drink("Tea", 40), 100);

		assertEquals(expectedDrinksContainer, actualDrinksContainer);
	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithDuplicatedDrinks() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToDrinksContainerXML/DuplicatedDrinks.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithNegativeDrinkPrice() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToDrinksContainerXML/NegativeDrinkPrice.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithoutDrinks() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/DTOToDrinksContainerXML/WithoutDrinks.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithZeroPrice() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/DTOToDrinksContainerXML/ZeroPrice.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}
	
	@Test
	public void testTransformWithZeroAmount() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/DTOToDrinksContainerXML/ZeroAmount.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithNegativeAmount() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/DTOToDrinksContainerXML/NegativeAmount.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}
	
	@After
	public void tearDownObject() {
		xmlIO = null;
	}
}
