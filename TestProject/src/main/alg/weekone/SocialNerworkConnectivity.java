package main.alg.weekone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

public class SocialNerworkConnectivity extends TestCase {

	private static class XMLReader 
	{
		private File file;
		
		public XMLReader(String name) {
			
			 file = new File(name);
		}

		private Document loadFile() throws ParserConfigurationException, SAXException, IOException
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  
			DocumentBuilder db;
				
				db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(file);
			
				doc.getDocumentElement().normalize();
			 	
				return doc;
		}
		public List<LogData> readData()
		{
			Document  doc;
			List<LogData> resultList;

			try 
			{
				doc = loadFile();
				resultList = new ArrayList<LogData>();

				NodeList logList = doc.getElementsByTagName("log");
				
				for(int i=0; i< logList.getLength(); i++)
				{
					Node node = logList.item(i);
					
					if(node.getNodeType() == Node.ELEMENT_NODE)
					{
						Element logElem = (Element)node;

						//-------
						NodeList logElemList = logElem.getElementsByTagName("first");
						Element firstNameElement = (Element)logElemList.item(0);

						NodeList textFNList = firstNameElement.getChildNodes();
						System.out.println("First Name : " + 
								((Node)textFNList.item(0)).getNodeValue().trim());

						//-------
						NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
						Element lastNameElement = (Element)lastNameList.item(0);

						NodeList textLNList = lastNameElement.getChildNodes();
						System.out.println("Last Name : " + 
								((Node)textLNList.item(0)).getNodeValue().trim());

						//----
						NodeList ageList = firstPersonElement.getElementsByTagName("age");
						Element ageElement = (Element)ageList.item(0);

						NodeList textAgeList = ageElement.getChildNodes();
						System.out.println("Age : " + 
								((Node)textAgeList.item(0)).getNodeValue().trim());

						//------


					}//end of if clause


				}//end of for loop with s var

					
			}
			catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return resultList;
		}
		public int readN()
		{
			
		}
		
	}
	private static class LogData
	{
		private int time;
		private int pPeerId;
		private int qPeerId;
		
		public LogData(int time, int pPeerId, int qPeerId) {
			super();
			this.time = time;
			this.pPeerId = pPeerId;
			this.qPeerId = qPeerId;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public int getpPeerId() {
			return pPeerId;
		}

		public void setpPeerId(int pPeerId) {
			this.pPeerId = pPeerId;
		}

		public int getqPeerId() {
			return qPeerId;
		}

		public void setqPeerId(int qPeerId) {
			this.qPeerId = qPeerId;
		}
		
		
		
	}
	private static class SNCQuickUnionFind extends QuickFindUinon.AlgQuickUnionPathCommpresion
	{
		public SNCQuickUnionFind(int elemCount)
		{
			super(elemCount, true);
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Test-s
	@Test
	private void testMain()
	{
		XMLReader dataReader = new XMLReader("snc.xml");
			
		List<SocialNerworkConnectivity.LogData> data =  dataReader.readData();
		
		SNCQuickUnionFind alg = 
		
		
	}
}
