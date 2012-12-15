package work.marshalling;

/**
 * Bład wyrzucany w przypadku problemów z wczytaniem schematu XML
 * @author mp
 *
 */
public class SchemaXMLException extends WMarshallingException {
	
	private static final long serialVersionUID = 1L;

	public SchemaXMLException() {
	}

	public SchemaXMLException(String message) {
		super(message);
	}

	public SchemaXMLException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SchemaXMLException(Throwable throwable) {
		super(throwable);
	}
}
