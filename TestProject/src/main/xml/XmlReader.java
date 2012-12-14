package main.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;

import org.junit.Test;

public class XmlReader extends TestCase {
	
	@Test
	public void testReadTest()
	{
		XmlReader dataReader = new XmlReader();
		
		try 
		{
		
			dataReader.loadData(main.xml.ElemData.class,"main/xml/snc.xml");
			
		
		} catch (URISyntaxException e) 
		{
			throw new AssertionError();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	private <T> T  loadData(Class<T> docClass, String name) throws URISyntaxException, FileNotFoundException, JAXBException 
	{	
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	URL url = classLoader.getResource(name);
			
		File file = new File(url.toURI());
	
		return (T) unmarshal(docClass, new FileInputStream(file));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T unmarshal( Class<T> docClass, InputStream inputStream ) throws JAXBException
	{
	    String packageName = docClass.getPackage().getName();
	    JAXBContext jc = JAXBContext.newInstance(packageName);
	    Unmarshaller u = jc.createUnmarshaller();
	    T doc = (T)u.unmarshal( inputStream );
	   
	    return doc;
	}
}
