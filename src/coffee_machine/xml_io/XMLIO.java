package coffee_machine.xml_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;


import org.xml.sax.SAXException;

import coffee_machine.xml_io.exceptions.XMLIOException;

/**
 * This class gives the tools to do I/O operations with XML files. This includes
 * reading the contents of an XML files into a Java object type and writing
 * information to an XML file. The path to the XML file is specified in an
 * XMLDocumentMetaData object, as well as the XML schema file, used to validate
 * data, and the class of the read/written object.
 * 
 * @author Hristo
 * 
 */
public class XMLIO {

	/**
	 * Reads the, specified in an XMLDocumentMetaData object, XML file and
	 * returns its content.
	 * 
	 * @param xmlMeta
	 *            - XMLDocumentMetaData object, which specifies the class of the
	 *            object to write the information into and points the path to
	 *            the XML file, and the schema file, which is used to validate
	 *            the information in the XML file.
	 * @return the content of an XML file in a Java Object class object
	 * @throws XMLIOException
	 */
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

	/**
	 * Writes the contents of a given object to an XML file. The object should
	 * be in suitable format to write, specified in the schema file, pointed by
	 * the XMLDocumentMetaData object.
	 * 
	 * @param xmlMeta
	 *            - XMLDocumentMetaData object, which specifies the class of the
	 *            object to read the information from and points the path to the
	 *            XML file, and the schema file, which is used to validate the
	 *            information in the XML file.
	 * @param objectToWrite
	 *            - formatted, according to the schema file in the
	 *            XMLDocumentMetaData, object which will be written to an XML
	 *            file
	 * @throws XMLIOException
	 */
	public void write(XMLDocumentMetaData xmlMeta, Object objectToWrite)
			throws XMLIOException {
		try {
			Objects.requireNonNull(xmlMeta, "Meta data object is null!");
			Objects.requireNonNull(objectToWrite,
					"Specified object to write is null!");
			if (!objectToWrite.getClass().equals(xmlMeta.getDtoClass()))
				throw new IllegalArgumentException(
						"Provided object is not of the same class as specified in xml meta file");
			File file = new File(xmlMeta.getPathToFile());
			
			prepareStorage(file);

			JAXBContext context = JAXBContext
					.newInstance(xmlMeta.getDtoClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			marshaller.marshal(objectToWrite, file);
		} catch (JAXBException | IOException e) {
			throw new XMLIOException(e.getMessage());
		}
	}

	private static void prepareStorage(File file) throws XMLIOException, IOException {
		if (file.exists()) {
			return ;
		}
		
		File parent = file.getParentFile(); 
		if ( !parent.exists() && !parent.mkdirs() ) {
			throw new XMLIOException( "Unable to create directories for: " + parent.getPath() );
		}
		
		if ( !file.createNewFile() ) {
			throw new XMLIOException( "Unable to create file: " + file.getName() );
		}
	}

}
