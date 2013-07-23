package coffeeMachine;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import coffeeMachine.DTO.coffeeMachineDTO.CoffeeMachineDTO;
import coffeeMachine.exceptions.CoffeeMachineStateException;
import coffeeMachine.exceptions.DTOToCoffeeMachineStateException;
import modules.xmlModule.*;

/***
 * 
 * @author Krasimir Nikolov Atanasov
 * 
 */
public class CoffeeMachineStorage {
	// TODO: Need changes!!!
	public CoffeeMachineStorage() {

	}

	public CoffeeMachineState load(String xmlFilePath, String xsdSchemePath) throws CoffeeMachineStateException {
		XMLDocumentMetaData xmlDocumentMetadata = new XMLDocumentMetaData(
				CoffeeMachineDTO.class, xmlFilePath, xsdSchemePath);
		XMLIO xmlIO = new XMLIO();

		try {
			CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) xmlIO
					.read(xmlDocumentMetadata);
			DTOToCoffeeMachineState transformer = new DTOToCoffeeMachineState();
			return transformer.transform(coffeeMachineDTO);

		} catch (FileNotFoundException e) {
			throw new CoffeeMachineStateException("XML file not found!");
		} catch (JAXBException e) {
			throw new CoffeeMachineStateException(
					"Cannot create marshal object");
		} catch (SAXException e) {
			throw new CoffeeMachineStateException(e.getMessage());
		} catch (DTOToCoffeeMachineStateException e) {
			throw new CoffeeMachineStateException(e.getMessage());
		}
	}
}