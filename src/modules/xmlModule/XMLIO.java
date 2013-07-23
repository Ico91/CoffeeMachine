package modules.xmlModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class XMLIO {

	public Object read(XMLDocumentMetaData xmlMeta) throws JAXBException,
			FileNotFoundException, SAXException {
		JAXBContext context = JAXBContext.newInstance(xmlMeta.getDtoClass());
		Unmarshaller unmarshaller = context.createUnmarshaller();

		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new File(xmlMeta.getPathToSchema()));
		unmarshaller.setSchema(schema);
		
		FileReader file = new FileReader(xmlMeta.getPathToFile());
		
		Object objectToReturn = xmlMeta.getDtoClass().cast(unmarshaller
				.unmarshal(file));

		return objectToReturn;
	}

	public void write(XMLDocumentMetaData xmlMeta, Object objectToWrite)
			throws JAXBException {
		if (!objectToWrite.getClass().equals(xmlMeta.getDtoClass()))
			throw new IllegalArgumentException(
					"Provided object is not of the same class as specified in xml meta file");
		
		JAXBContext context = JAXBContext.newInstance(xmlMeta.getClass());
		Marshaller marshaller = context.createMarshaller();

		File file = new File(xmlMeta.getPathToFile());

		marshaller.marshal(objectToWrite, System.out);
		marshaller.marshal(objectToWrite, file);

	}

}
