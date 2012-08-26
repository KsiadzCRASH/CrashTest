package main.alg.weekone;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SocialNerworkConnectivity extends TestCase {

	private static class XMLReader 
	{
		private File file;
		
		public XMLReader(String name) throws URISyntaxException 
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL url = classLoader.getResource(name);
			
			file = new File(url.toURI());
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
		public List<LogData> readData() throws AssertionError
		{
			Document  doc;
			List<LogData> resultList = null;

			try 
			{
				doc = loadFile();
				resultList = new ArrayList<LogData>();

				NodeList rootList = doc.getElementsByTagName("root");
				Node rootNode = rootList.item(0);

				
				NodeList logList = ((Element)rootNode).getChildNodes();
				
				for(int i=0; i< logList.getLength(); i++)
				{
					Node node = logList.item(i);
					
					if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("log"))
					{
						
						// TIME
						NodeList timeNodeList = ((Element)node).getElementsByTagName("time");
						Element timeElement = (Element)timeNodeList.item(0);
						NodeList timeList = timeElement.getChildNodes();
					
						System.out.println("Time : " + ((Node)timeList.item(0)).getNodeValue().trim());
						int time = Integer.parseInt(((Node)timeList.item(0)).getNodeValue());
						// Peer P
						NodeList peerNodeList = ((Element)node).getElementsByTagName("peer");
						
						Element pPeerElement = (Element)peerNodeList.item(0);
						NodeList peerElementList = pPeerElement.getChildNodes();
						
						System.out.println("P : " + ((Node)peerElementList.item(0)).getNodeValue().trim());
						
						int pPeerId = Integer.parseInt(((Node)peerElementList.item(0)).getNodeValue());
						
						Element qPeerElement = (Element)peerNodeList.item(1);
					
						peerElementList = qPeerElement.getChildNodes();
						
						int qPeerId = Integer.parseInt(((Node)peerElementList.item(0)).getNodeValue());
						
						System.out.println("Q : " + ((Node)peerElementList.item(0)).getNodeValue().trim());
						
						resultList.add(new LogData(time, pPeerId, qPeerId));

					}
				}
				
			}
			catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AssertionError();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AssertionError();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AssertionError();
			}
			
			return resultList;
		}
		public int readN()
		{
			Document  doc;
			int result = 0;

			try 
			{
				doc = loadFile();
			
				NodeList rootList = doc.getElementsByTagName("root");
				Node rootNode = rootList.item(0);
				
				NodeList rootNodesList = ((Element)rootNode).getChildNodes();
				
				for(int i=0; i< rootNodesList.getLength(); i++)
				{
					Node node = rootNodesList.item(i);
					
					if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("n"))
					{
						
						NodeList nElementList = ((Element) node).getChildNodes();
					
						System.out.println("Time : " + ((Node)nElementList.item(0)).getNodeValue().trim());
						result = Integer.parseInt(((Node)nElementList.item(0)).getNodeValue());
						
						break;
					}
				}
				
			}
			catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AssertionError();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AssertionError();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AssertionError();
			}
			
			return result;
		}
		
	}
	
	@SuppressWarnings("unused")
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
		
		public int findSolution(List<LogData> data)
		{
			int result = 0; 
			
			for (LogData logData : data) {
				
				connect(logData.getpPeerId(), logData.getqPeerId());
				if(allPeersConnected())
				{
					result = logData.time;
					break;
				}
			}
			
			return result;
			
		}
		
		private boolean allPeersConnected()
		{
			for (int i = 0; i < objectCountArray.length; i++) {
				
				if(objectCountArray[i] == elemtCount) 
					return true;
			}
			
			return false;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Test-s
	@Test
	public void testMain()
	{
		XMLReader dataReader;
		
		try 
		{
		
			dataReader = new XMLReader("resources/weekone/snc.xml");
		
		} catch (URISyntaxException e) 
		{
			throw new AssertionError();
		}
		
		int elemCount = dataReader.readN();
		int result=0;
		List<SocialNerworkConnectivity.LogData> data =  dataReader.readData();
		
		SNCQuickUnionFind alg = new SNCQuickUnionFind(elemCount);
		
		result = alg.findSolution(data);
		System.out.println("Result :" + result);
		
		assertEquals(result, 134);
		
	}
}
