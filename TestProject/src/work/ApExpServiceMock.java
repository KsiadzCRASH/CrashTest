package work;

import java.net.URISyntaxException;

import work.marshalling.WMarshallingException;

public class ApExpServiceMock {
	
	ExpFkQueryDataMarshalling expFkQueryMarshalling = new ExpFkQueryDataMarshalling("work");
	
	public String generateQuery(GmExpPozTO data)
	{
		ExpFkQueryGenerator generator = ExpFkQueryGenerator.getInstance();
		
		if(!generator.isQueryTemplateLoaded())
		{
			try
			{
				ExpFkXmlQueryXO xo = expFkQueryMarshalling.loadAndUnmarshall("work/schema/data/ExpFkQueryData.xml");
				generator.setTemplate(xo);
			}
			catch (WMarshallingException | URISyntaxException e) 
			{
				e.printStackTrace();
				throw new RuntimeException(); // Nasz wyj¹te
			}
		}
		
		return generator.genereateQuery(data);
	}

	public ExpFkQueryDataMarshalling getExpFkQueryMarshalling() {
		return expFkQueryMarshalling;
	}

	public void setExpFkQueryMarshalling(
			ExpFkQueryDataMarshalling expFkQueryMarshalling) {
		this.expFkQueryMarshalling = expFkQueryMarshalling;
	}

}
