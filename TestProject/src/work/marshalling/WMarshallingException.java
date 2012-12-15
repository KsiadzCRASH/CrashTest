package work.marshalling;

/**
 * @author mp
 * 
 */
public class WMarshallingException extends Exception {

	private static final long serialVersionUID	= 1L;

	public WMarshallingException() {
	}

	public WMarshallingException(String message) {
		super(message);
	}

	public WMarshallingException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public WMarshallingException(Throwable throwable)
	{
		super(throwable);
	}
}
