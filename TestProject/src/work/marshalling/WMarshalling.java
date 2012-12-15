package work.marshalling;

public interface WMarshalling <T extends ContentXMLAbstract> {

	/**
	 * Zamienia xml-a w postaci String-a na reprezentację obiektową
	 * @param content (xml)
	 * @return
	 */
	public T unmarshall(String content) throws WMarshallingException;
	
	/**
	 * Zamienia klasy obiektów na zawartość xml-a w postaci String-a    
	 * @param komunikat
	 * @return
	 */
	public String marshall(T komunikat) throws WMarshallingException;

    String getContextPath();
	
	
}
