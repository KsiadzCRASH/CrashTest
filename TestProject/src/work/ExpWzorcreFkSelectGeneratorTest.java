/**
 * 
 */
package work;

import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.TestCase;

import org.junit.Test;

import work.GmExpPozTO.SchemaParser;
import work.GmExpPozTO.SelectGenerator;
import work.marshalling.WMarshallingException;


public class ExpWzorcreFkSelectGeneratorTest extends TestCase {

	private static ExpFkQueryDataMarshalling expFkQueryMarshalling = new ExpFkQueryDataMarshalling("work");

	private static ApExpServiceMock apServiceMock = new ApExpServiceMock();
	
	@Test
	public void testXmlMarshallingTest() 
	{
	  
		ExpFkXmlQueryXO root = new ExpFkXmlQueryXO();
		ExpFkXmlQueryElemXO elem = new ExpFkXmlQueryElemXO();
		ExpFkXmlQueryElemPartXO elemPart = new ExpFkXmlQueryElemPartXO();
		StrExpFkXO strXO = new StrExpFkXO();
		StrExpFkXO strwXO = new StrExpFkXO();
		StrExpFkXO strhXO = new StrExpFkXO();
		StrExpFkXO stroXO = new StrExpFkXO();
		
		strXO.setValue("Select");

		strwXO.setValue("where");
		strhXO.setValue("having");
		stroXO.setValue("order");
		
		root.getElemBud().add(elem);
		elem.setId("id");
		elem.setSelect(strXO);
		elem.setWhere(strwXO);
		elem.setHaving(strhXO);
		elem.setOrdered(stroXO);
		elem.setGroup(stroXO);
		
		elemPart.setType("part");
		elemPart.setVariable("variable");
		elemPart.setStr(strhXO);
		elem.getFrom().add(elemPart);
		try {
			String generatedXml = expFkQueryMarshalling.marshall(root);
			assertNotNull(generatedXml);	
			
		} catch (WMarshallingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	@Test
	public void testPath()
	{
		String filName1 = "services\\apteka\\schema\\data\\ExpFkQueryData.xml";
		String filName2 = "work/schema/data/ExpFkQueryData.xml";
		
	
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	URL url = classLoader.getResource(filName1);
    	url = classLoader.getResource(filName2);
		
    	assertNotNull(url);
	}
	@Test
	public void testXmlUnmarshallingTest() 
	{
	  
		ExpFkXmlQueryXO root;
		try {
			//
			root = expFkQueryMarshalling.loadAndUnmarshall("work/schema/data/ExpFkQueryData.xml");
			assertNotNull(root);	
			
		} catch (WMarshallingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}	
	
	@Test
	public void testTEst()
	{
		int a = 4/2/2;
		
		assertEquals(a, 1);
		
	}
	// GMEXPPOZTO TO SCHEMA
	@Test
	public void testConvertDataToSchemaTest()
	{
		GmExpPozTO expPoz = new GmExpPozTO();
		String testSchema = "@~K~#-#~M~SW@NETTO;ALL_RW;@1;^1;@~M~#4444#~SW@~#000#@0@~K~M~#999#~SW@";
		SchemaParser parser = new SchemaParser();
		
		parser.convertSchemaToData(expPoz, testSchema);
		
		String newSchema = parser.convertDataToSchema(expPoz);
		
		
		
		assertEquals(testSchema, newSchema);
	}	

	// SCHEMA TO GMEXPPOZTO 
	@Test
	public void testQueryGeneratorTest() 
	{
		GmExpPozTO expPoz = new GmExpPozTO();
		String testSchema = "@~A4~O~#666#~SW@VAT;0;5;DARY_NIE;@84;^0;@@~K~F~#777#@0@";
		SchemaParser parser = new SchemaParser();
		String result;
		
		parser.convertSchemaToData(expPoz, testSchema);
	
		result = apServiceMock.generateQuery(expPoz);
		
		assertEquals("", result.toString());
	
	}
	
	
	@Test
	public void testParserOpisTest()
	{
		GmExpPozTO expPoz = new GmExpPozTO();
		String testSchema = "@~A4~O~#666#~SW@VAT;0;5;DARY_NIE;@84;^0;@@~K~F~#777#@0@";
		SchemaParser parser = new SchemaParser();
		SelectGenerator generator  = new SelectGenerator();
		String result; 
		
		parser.convertSchemaToData(expPoz, testSchema);
	
		assertNotNull(expPoz.getOpisList());
		assertTrue(expPoz.getOpisList().size() == 3);

		result = generator.genrate(expPoz);
		
		assertEquals(result, "#");
		
	}	
	
	

}
