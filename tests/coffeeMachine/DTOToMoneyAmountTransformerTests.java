package coffeeMachine;

import static org.junit.Assert.assertEquals;
import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;
import modules.xmlModule.exceptions.XMLIOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.transformers.fromDto.DTOToMoneyAmountTransformer;
import coffeeMachine.transformers.fromDto.exceptions.DTOToMoneyAmountException;

public class DTOToMoneyAmountTransformerTests {
	private XMLIO xmlIO;

	@Before
	public void setupObject() {
		xmlIO = new XMLIO();
	}

	@Test
	public void testDTOToMoneyTransformer() throws XMLIOException,
			DTOToMoneyAmountException {
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(new XMLDocumentMetaData(
						CoffeeMachineDTO.class,
						"resources/TestXMLs/DTOToMoneyAmountXML/CoffeeMachineDTO.xml",
						"src/CoffeeMachineDTO.xsd"));
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		MoneyAmount expectedmoneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
		MoneyAmount actualMoneyAmount = new MoneyAmount();
		actualMoneyAmount.add(Coin.FIVE, 100).add(Coin.TEN, 100)
				.add(Coin.TWENTY, 100).add(Coin.FIFTY, 100).add(Coin.LEV, 100);
		assertEquals(expectedmoneyAmount, actualMoneyAmount);
	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithDuplicatedCoins() throws XMLIOException,
			DTOToMoneyAmountException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToMoneyAmountXML/DuplicatedValues.xml",
				"src/CoffeeMachineDTO.xsd");
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		@SuppressWarnings("unused")
		MoneyAmount moneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
	}

	@Test
	public void testTransformWithZeroAmount() throws XMLIOException,
			DTOToMoneyAmountException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToMoneyAmountXML/ZeroAmount.xml",
				"src/CoffeeMachineDTO.xsd");
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		@SuppressWarnings("unused")
		MoneyAmount moneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithNegativeAmount() throws XMLIOException,
			DTOToMoneyAmountException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToMoneyAmountXML/NegativeAmount.xml",
				"src/CoffeeMachineDTO.xsd");
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		@SuppressWarnings("unused")
		MoneyAmount moneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithWrongCoinType() throws XMLIOException,
			DTOToMoneyAmountException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToMoneyAmountXML/WrongCoinType.xml",
				"src/CoffeeMachineDTO.xsd");
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		@SuppressWarnings("unused")
		MoneyAmount moneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
	}

	@Test(expected = XMLIOException.class)
	public void testTransformWithoutCoins() throws XMLIOException,
			DTOToMoneyAmountException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToMoneyAmountXML/WithoutCoins.xml",
				"src/CoffeeMachineDTO.xsd");
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		@SuppressWarnings("unused")
		MoneyAmount moneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
	}
	
	@Test
	public void testTransformWhitMissingCoinType() throws XMLIOException,
			DTOToMoneyAmountException {
		XMLDocumentMetaData xmlDocumentMetaData = new XMLDocumentMetaData(
				CoffeeMachineDTO.class,
				"resources/TestXMLs/DTOToMoneyAmountXML/MissingCoinType.xml",
				"src/CoffeeMachineDTO.xsd");
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(xmlDocumentMetaData);
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		@SuppressWarnings("unused")
		MoneyAmount moneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
	}
	
	@After
	public void tearDownObject() {
		xmlIO = null;
	}
}
