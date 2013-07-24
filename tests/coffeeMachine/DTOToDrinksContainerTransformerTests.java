package coffeeMachine;

import static org.junit.Assert.assertEquals;
import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;
import modules.xmlModule.exceptions.XMLIOException;

import org.junit.Before;
import org.junit.Test;

import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.transformers.fromDto.DTOToDrinksContainerTransformer;

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
						"resources/TestXMLs/CoffeeMachineDTO.xml",
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
				"resources/TestXMLs/DuplicatedDrinks.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithNegativeDrinkPrice() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/NegativeDrinkPrice.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithoutDrinks() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/WithoutDrinks.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithZeroPrice() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/ZeroPrice.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}
	
	@Test
	public void testTransformWithZeroAmount() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/ZeroAmount.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithNegativeAmount() throws XMLIOException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, "resources/TestXMLs/NegativeAmount.xml",
				"src/CoffeeMachineDTO.xsd");
		@SuppressWarnings("unused")
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);

	}
}
