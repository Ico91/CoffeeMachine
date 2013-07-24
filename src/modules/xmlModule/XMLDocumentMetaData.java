package modules.xmlModule;

/**
 * Holds meta information used by the XMLIO class for writing and reading XML
 * files. This information consists of the path to the XML file, the path to the
 * Schema file, used for validation of the data, and the class of the object
 * which will be read/written.
 * 
 * @author Hristo
 * 
 */
public class XMLDocumentMetaData {
	private String pathToFile;
	private String pathToSchema;
	private Class<?> dtoClass;

	public XMLDocumentMetaData(Class<?> dtoClass, String pathToFile,
			String pathToSchema) {
		this.pathToFile = pathToFile;
		this.pathToSchema = pathToSchema;
		this.dtoClass = dtoClass;
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public String getPathToSchema() {
		return pathToSchema;
	}

	public void setPathToSchema(String pathToSchema) {
		this.pathToSchema = pathToSchema;
	}

	public Class<?> getDtoClass() {
		return dtoClass;
	}

	public void setDtoClass(Class<?> dtoClass) {
		this.dtoClass = dtoClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtoClass == null) ? 0 : dtoClass.hashCode());
		result = prime * result
				+ ((pathToFile == null) ? 0 : pathToFile.hashCode());
		result = prime * result
				+ ((pathToSchema == null) ? 0 : pathToSchema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof XMLDocumentMetaData))
			return false;
		XMLDocumentMetaData other = (XMLDocumentMetaData) obj;
		if (dtoClass == null) {
			if (other.dtoClass != null)
				return false;
		} else if (!dtoClass.equals(other.dtoClass))
			return false;
		if (pathToFile == null) {
			if (other.pathToFile != null)
				return false;
		} else if (!pathToFile.equals(other.pathToFile))
			return false;
		if (pathToSchema == null) {
			if (other.pathToSchema != null)
				return false;
		} else if (!pathToSchema.equals(other.pathToSchema))
			return false;
		return true;
	}
}
