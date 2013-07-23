package coffeeMachine;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import modules.xmlModule.XMLDocumentMetaData;
import modules.xmlModule.XMLIO;
import modules.xmlModule.exceptions.XMLIOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.transformers.fromDto.DTOToDrinksContainerTransformer;
import coffeeMachine.transformers.fromDto.exceptions.DTOToMoneyAmountException;

/** 
* @author Galina
*
*/

public class DTOToDrinksContainerTransformerTests {

	@Test
	public void testDTOToMoneyTransformer() throws XMLIOException {
		XMLIO xmlIO = new XMLIO();
		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO.read(new XMLDocumentMetaData(CoffeeMachineDTO.class,
					"resources\\TestXMLs\\CoffeeMachineDTO.xml",
					"src\\CoffeeMachineDTO.xsd"));
		DTOToDrinksContainerTransformer dtoToDrinksContainerTransformer = new DTOToDrinksContainerTransformer();
		DrinksContainer expectedDrinksContainer = dtoToDrinksContainerTransformer.transform(coffeeMachineDTO);
		DrinksContainer actualDrinksContainer = new DrinksContainer();
		actualDrinksContainer.add(new Drink("Coffee", 30), 100);
		
		assertEquals(expectedDrinksContainer, actualDrinksContainer);
	}

}
