package main.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class Sorting extends TestCase{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@SuppressWarnings("unused")
	private static class A 
	{
		private Integer primId;
		private Integer secondId;
		private Integer tertiaryFlag;
		
		public A(Integer pId, Integer sId, Integer tFlag) {
			super();
			this.primId = pId;
			this.secondId = sId;
			this.tertiaryFlag = tFlag;
		}

		public Integer getPrimId() {
			return primId;
		}

		public void setPrimId(Integer pId) {
			this.primId = pId;
		}

		public Integer getSecondId() {
			return secondId;
		}

		public void setSecondId(Integer sId) {
			this.secondId = sId;
		}

		public Integer getTertiaryFlag() {
			return tertiaryFlag;
		}

		public void setTertiaryFlag(Integer tertiaryFlag) {
			this.tertiaryFlag = tertiaryFlag;
		}
		
		@Override
		public String toString()
		{
			return "Prim : " + primId + " Second : " + secondId + " Tertiary : " + tertiaryFlag;
		}
	}
	private List<A> sortData(List<A> data)
	{
		Collections.sort(data, new Comparator<A>(){
			public int compare(A o1, A o2) {

				int priComp = 0, 
						result = 0;

				if (o1.getPrimId() == null && o2.getPrimId() == null)
					priComp = 0; // dead code
				else if (o1.getPrimId() == null) 
					priComp = -1; // dead code
				else if (o2.getPrimId() == null) 
					priComp = 1; // dead code
				else priComp = o1.getPrimId().compareTo(o2.getPrimId());

				result = priComp;

				if (o1.getSecondId() != null && o2.getSecondId() != null)
				{
					if(o1.getSecondId().compareTo(o2.getSecondId()) == 0)
					{
						if (o1.getTertiaryFlag() != null && o2.getTertiaryFlag() != null)
						{
							result = -o1.getTertiaryFlag().compareTo(o2.getTertiaryFlag());
						}           
					}
				}

				return result;
			}
		});

		return data;
	}

	/**
	 * Ordered data
	 * @return data without modification
	 */
	@Test
	public void testSortingOrdered()
	{
		List<A> data = new ArrayList<A>();
		
		data.add(new A(1,1,1));
		data.add(new A(2,1,-1));
		
		data.add(new A(3,2,1));
		data.add(new A(4,2,-1));

		data.add(new A(5,3,1));
		data.add(new A(6,3,-1));

		data.add(new A(7,4,1));
		data.add(new A(8,4,-1));

		data.add(new A(9,5,1));
		data.add(new A(10,5,-1));
	
		data = sortData(data);
		
		for (int i = 0; i < data.size(); i++) {
			assertEquals((int)data.get(i).getPrimId(), i+1);
		}
	}
	/**
	 * Reverse ordered data
	 * @return ordered data
	 */
	@Test
	public void testSortingReverse()
	{
		List<A> data = new ArrayList<A>();
		
		data.add(new A(10,5,-1));
		data.add(new A(9,5,1));
		
		data.add(new A(8,4,-1));
		data.add(new A(7,4,1));

		data.add(new A(6,3,-1));
		data.add(new A(5,3,1));

		data.add(new A(4,2,-1));
		data.add(new A(3,2,1));

		data.add(new A(2,1,-1));
		data.add(new A(1,1,1));
	
		data = sortData(data);
		
		for (int i = 0; i < data.size(); i++) {
			assertEquals((int)data.get(i).getPrimId(), i+1);
		}
	}
	
	/**
	 * Randomly ordered data
	 * @return ordered data
	 */
	@Test
	public void testSortingRandom()
	{
		List<A> data = new ArrayList<A>();
		
		data.add(new A(6,3,-1));
		data.add(new A(7,4,1));
		data.add(new A(1,1,1));
		data.add(new A(4,2,-1));
		data.add(new A(8,4,-1));
		data.add(new A(9,5,1));
		data.add(new A(5,3,1));
		data.add(new A(10,5,-1));
		data.add(new A(3,2,1));
		data.add(new A(2,1,-1));
		
		data = sortData(data);
		
		for (int i = 0; i < data.size(); i++) {
			assertEquals((int)data.get(i).getPrimId(), i+1);
		}
	}

	/**
	 * Randomly ordered with missing data
	 * @return ordered data
	 */
	@Test
	public void testSortingRandomMissingData()
	{
		List<A> data = new ArrayList<A>();
		
		data.add(new A(6,3,-1));
		data.add(new A(7,null,null));
		data.add(new A(1,1,1));
		data.add(new A(4,null,-1));
		data.add(new A(8,null,null));
		data.add(new A(9,5,1));
		data.add(new A(5,3,null));
		data.add(new A(10,null,-1));
		data.add(new A(3,2,1));
		data.add(new A(2,1,-1));
		
		data = sortData(data);
		
		for (int i = 0; i < data.size(); i++) {
			assertEquals((int)data.get(i).getPrimId(), i+1);
		}
	}

}
