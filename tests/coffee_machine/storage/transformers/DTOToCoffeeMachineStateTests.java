package coffee_machine.storage.transformers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffee_machine.model.CoffeeMachineState;
import coffee_machine.model.Coin;
import coffee_machine.model.Drink;
import coffee_machine.model.DrinksContainer;
import coffee_machine.model.MoneyAmount;
import coffee_machine.storages.transformers.DTOToCoffeeMachineState;
import coffee_machine.storages.transformers.dto.CoffeeMachineDTO;
import coffee_machine.storages.transformers.exceptions.DTOToCoffeeMachineException;
import coffee_machine.xml_io.XMLDocumentMetaData;
import coffee_machine.xml_io.XMLIO;
import coffee_machine.xml_io.exceptions.XMLIOException;

/**
 * @author Galina
 * 
 */

public class DTOToCoffeeMachineStateTests {
	private XMLIO xmlIO;

	@Before
	public void setupObject() {
		xmlIO = new XMLIO();
	}

	@Test
	public void testDTOToMoneyTransformer() throws XMLIOException,
			DTOToCoffeeMachineException {
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
				.read(new XMLDocumentMetaData(
						CoffeeMachineDTO.class,
						"resources/TestXMLs/DTOToCoffeeMachineState/CoffeeMachineDTO.xml",
						"src/CoffeeMachineDTO.xsd"));
		DTOToCoffeeMachineState dtoToCoffeeMachineState = new DTOToCoffeeMachineState();
		CoffeeMachineState expectedCoffeeMachineState = dtoToCoffeeMachineState
				.transform(coffeeMachineDTO);

		DrinksContainer actualDrinksContainer = new DrinksContainer();
		actualDrinksContainer.add(new Drink("Coffee", 30), 100).add(new Drink("Tea", 40), 100);
		MoneyAmount actualMoneyAmount = new MoneyAmount();
		actualMoneyAmount.add(Coin.FIVE, 100).add(Coin.TEN, 100)
				.add(Coin.TWENTY, 100).add(Coin.FIFTY, 100).add(Coin.LEV, 100);

		CoffeeMachineState actualCoffeeMachineState = new CoffeeMachineState(
				actualMoneyAmount, actualDrinksContainer);
		assertEquals(expectedCoffeeMachineState, actualCoffeeMachineState);
	}
	
	@After
	public void tearDownObject() {
		xmlIO = null;
	}
}
