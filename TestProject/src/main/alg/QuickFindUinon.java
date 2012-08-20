package main.alg;

import junit.framework.TestCase;

import org.junit.Test;


public class QuickFindUinon  extends TestCase {

	static class Alg 
	{
		
		int [] array;
		
		public Alg(int elemCount)
		{
			array = new int[elemCount];
			
			for (int i = 0; i < elemCount; i++) {
				array[i] = i;
			}
		}
		
		public boolean find(int p, int q)
		{
			return array[p] == array[q]; // ? array[p]  : -1 ;
		}
		public int union(int p, int q)
		{
			int tmpQ = -1, tmpP;
			int count = 0;
		
			if((!find(p, q)))
			{
				tmpP = array[p];
				tmpQ = array[q];
				
				for (int i = 0; i < array.length; i++) {
					
					if(array[i] == tmpQ)
					{ 
						count++;
						array[i] = tmpP;
					}
				}
				
			}
			
			return count;
		}
	}
	
	public static void main(String[] args)
	{
		QuickFindUinon uion = new QuickFindUinon();
		
		uion.testMain();
	}
	@Test
	public void testMain() {

		QuickFindUinon.Alg alg = new QuickFindUinon.Alg(10);
		
		alg.union(1, 2);
		alg.union(3, 4);
		alg.union(5, 6);
		alg.union(7, 8);
		alg.union(7, 9);
		alg.union(9, 1);
		alg.union(0, 5);
		
		assertTrue(alg.find(7, 2));
		assertTrue(alg.find(0, 6));
		assertFalse(alg.find(1, 4));

	}
	
	

}
