package coffeeMachine;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;

import org.junit.Test;
import org.xml.sax.SAXException;

import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.transformers.fromDto.DTOToCoffeeMachineState;
import coffeeMachine.transformers.fromDto.exceptions.DTOToCoffeeMachineException;

/** 
* @author Galina
*
*/

public class DTOToCoffeeMachineStateTests {

	@Test
	public void testDTOToMoneyTransformer() throws FileNotFoundException, JAXBException, SAXException, DTOToCoffeeMachineException {
		XMLIO xmlIO = new XMLIO();
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO.read(new XMLDocumentMetaData(CoffeeMachineDTO.class,
					"resources\\TestXMLs\\CoffeeMachineDTO.xml",
					"src\\CoffeeMachineDTO.xsd"));
		DTOToCoffeeMachineState dtoToCoffeeMachineState = new DTOToCoffeeMachineState();
		CoffeeMachineState expectedCoffeeMachineState = dtoToCoffeeMachineState.transform(coffeeMachineDTO);
		
		DrinksContainer actualDrinksContainer = new DrinksContainer();
		actualDrinksContainer.add(new Drink("Coffee", 30), 100);
		MoneyAmount actualMoneyAmount = new MoneyAmount();
		actualMoneyAmount.add(Coin.FIVE, 100).add(Coin.TEN, 100).add(Coin.TWENTY, 100).add(Coin.FIFTY, 100).add(Coin.LEV, 100);
		
		CoffeeMachineState actualCoffeeMachineState = new CoffeeMachineState(actualMoneyAmount, actualDrinksContainer);
		assertEquals(expectedCoffeeMachineState, actualCoffeeMachineState);
	}

}
