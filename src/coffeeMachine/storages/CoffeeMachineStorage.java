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
public class CoffeeMachineStorage {
	// TODO: Need changes!!!
	public CoffeeMachineStorage() {

	}

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