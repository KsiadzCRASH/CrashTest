package work.marshalling;

/**
 * Bład wyrzucany w przypadku braku zgodności pliku XML ze schematem XML
 * @author mp
 *
 */
public class ValidationXMLException extends WMarshallingException
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	// linia w której wystąpił błąd
	private int line;
	// kolumna w której wystąpił błąd
	private int column;

	/**
	 * @return linia w której wystąpił błąd
	 */
	public int getLine()
	{
		return line;
	}

	/**
	 * @return kolumna w której wystąpił błąd
	 */
	public int getColumn()
	{
		return column;
	}

	public ValidationXMLException()
	{
	}

	public ValidationXMLException(String message)
	{
		super(message);
	}
	
	public ValidationXMLException(String message, int line, int column)
	{
		super(message);
		this.line = line;
		this.column = column;
	}

	public ValidationXMLException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	public ValidationXMLException(Throwable throwable)
	{
		super(throwable);
	}
}
