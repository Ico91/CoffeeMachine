package modules.xmlModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import coffeeMachine.CoffeeMachineDTO;

public class XMLIO {

	public Object read(XMLDocumentMetaData xmlMeta) throws JAXBException,
			FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(xmlMeta.getClass());
		Unmarshaller unmarshaller = context.createUnmarshaller();

		FileReader file = new FileReader(xmlMeta.getPathToFile());

		CoffeeMachineDTO coffeeMachineDTO = (CoffeeMachineDTO) unmarshaller
				.unmarshal(file);

		return coffeeMachineDTO;
	}

	public void write(XMLDocumentMetaData xmlMeta, Object objectToWrite)
			throws JAXBException {
		if (!objectToWrite.getClass().equals(xmlMeta.getClass()))
			throw new IllegalArgumentException(
					"Provided object is not of the same class as specified in xml meta file");
		
		JAXBContext context = JAXBContext.newInstance(xmlMeta.getClass());
		Marshaller marshaller = context.createMarshaller();

		File file = new File(xmlMeta.getPathToFile());

		marshaller.marshal(objectToWrite, System.out);
		marshaller.marshal(objectToWrite, file);

	}

}
