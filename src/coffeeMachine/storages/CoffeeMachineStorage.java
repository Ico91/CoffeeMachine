package coffeeMachine.storages;

import coffeeMachine.CoffeeMachineState;
import coffeeMachine.dto.coffeeMachine.CoffeeMachineDTO;
import coffeeMachine.exceptions.CoffeeMachineStateException;
import coffeeMachine.transformers.fromDto.DTOToCoffeeMachineState;
import coffeeMachine.transformers.fromDto.exceptions.DTOToCoffeeMachineException;
import modules.xmlModule.*;
import modules.xmlModule.exceptions.XMLIOException;

/***
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
/***
 * Class used to load CoffeeMachineState from xml file.
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class CoffeeMachineStorage {

	/***
	 * Creates new CoffeeMAchineStorage object
	 */
	public CoffeeMachineStorage() {

	}

	/***
	 * Load CoffeeMachineState from specified in xmlFilePath xml file
	 * 
	 * @param xmlFilePath
	 *            path to XML file containing CoffeeMachineState data
	 * @param xsdSchemePath
	 *            XSD schema used to validate xml file
	 * @return new CoffeeMachineState builded from specified xml file
	 * @throws CoffeeMachineStateException
	 */
	public CoffeeMachineState load(String xmlFilePath, String xsdSchemePath)
			throws CoffeeMachineStateException {
		XMLDocumentMetaData xmlDocumentMetadata = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, xmlFilePath, xsdSchemePath);
		XMLIO xmlIO = new XMLIO();

		try {
			CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
					.read(xmlDocumentMetadata);
			DTOToCoffeeMachineState transformer = new DTOToCoffeeMachineState();
			return transformer.transform(coffeeMachineDTO);

		} catch (XMLIOException e) {
			throw new CoffeeMachineStateException(e.getMessage());
		} catch (DTOToCoffeeMachineException e) {
			throw new CoffeeMachineStateException(
					"Cannot transform CoffeeMachineDTO to CoffeeMachineState");
		}
	}
}