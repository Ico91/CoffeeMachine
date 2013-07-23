package coffeeMachine;

import static org.junit.Assert.assertEquals;

import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;
import modules.xmlModule.exceptions.XMLIOException;

import org.junit.Test;

import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.transformers.fromDto.DTOToMoneyAmountTransformer;
import coffeeMachine.transformers.fromDto.exceptions.DTOToMoneyAmountException;

public class DTOToMoneyAmountTransformerTests {

	@Test
	public void testDTOToMoneyTransformer() throws XMLIOException,
			DTOToMoneyAmountException {
		XMLIO xmlIO = new XMLIO();
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(new XMLDocumentMetaData(CoffeeMachineDTO.class,
						"resources\\TestXMLs\\CoffeeMachineDTO.xml",
						"src\\CoffeeMachineDTO.xsd"));
		DTOToMoneyAmountTransformer dtoToMoneyAmountTransformer = new DTOToMoneyAmountTransformer();
		MoneyAmount expectedmoneyAmount = dtoToMoneyAmountTransformer
				.transform(coffeeMachineDTO);
		MoneyAmount actualMoneyAmount = new MoneyAmount();
		actualMoneyAmount.add(Coin.FIVE, 100).add(Coin.TEN, 100)
				.add(Coin.TWENTY, 100).add(Coin.FIFTY, 100).add(Coin.LEV, 100);
		assertEquals(expectedmoneyAmount, actualMoneyAmount);
	}

}
