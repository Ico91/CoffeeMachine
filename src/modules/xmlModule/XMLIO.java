package modules.xmlModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import modules.xmlModule.exceptions.XMLIOException;

import org.xml.sax.SAXException;

public class XMLIO {

	public Object read(XMLDocumentMetaData xmlMeta) throws XMLIOException {
		try {
			File fileToRead = new File(xmlMeta.getPathToFile());
			File schemaFile = new File(xmlMeta.getPathToSchema());

			if (!fileToRead.exists())
				throw new FileNotFoundException(
						"Specified file to read was not found!");
			if (!schemaFile.exists())
				throw new FileNotFoundException("Schema file not found!");

			JAXBContext context = JAXBContext
					.newInstance(xmlMeta.getDtoClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();

			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xmlMeta
					.getPathToSchema()));
			unmarshaller.setSchema(schema);

			FileReader file = new FileReader(xmlMeta.getPathToFile());

			Object objectToReturn = xmlMeta.getDtoClass().cast(
					unmarshaller.unmarshal(file));

			return objectToReturn;
		} catch (SAXException | JAXBException | FileNotFoundException e) {
			throw new XMLIOException(e.getMessage());
		}
	}

	public void write(XMLDocumentMetaData xmlMeta, Object objectToWrite)
			throws XMLIOException {
		try {
			if (!objectToWrite.getClass().equals(xmlMeta.getDtoClass()))
				throw new IllegalArgumentException(
						"Provided object is not of the same class as specified in xml meta file");
			File file = new File(xmlMeta.getPathToFile());
			if (!file.exists())
				file.createNewFile();

			JAXBContext context = JAXBContext
					.newInstance(xmlMeta.getDtoClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			marshaller.marshal(objectToWrite, file);
		} catch (JAXBException | IOException | IllegalArgumentException e) {
			throw new XMLIOException(e.getMessage());
		}
	}

}
