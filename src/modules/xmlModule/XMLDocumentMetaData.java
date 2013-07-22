package modules.xmlModule;

public class XMLDocumentMetaData {
	private String pathToFile;
	private String pathToSchema;
	private Class<?> dtoClass;
	
	public XMLDocumentMetaData(Class<?> dtoClass, String pathToFile, String pathToSchema)
	{
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
}
