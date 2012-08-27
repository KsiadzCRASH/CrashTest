package main.alg.weekone;

import junit.framework.TestCase;

import org.junit.Test;

public class QuickFindUnionIE extends TestCase {

	public static class QuickUnionFindLargestNumber extends QuickFindUinon.AlgQuickUnionLazy
	{
		protected int [] largestValue;
			
		public QuickUnionFindLargestNumber(int elemCount) 
		{
			super(elemCount);
			
			largestValue = new int[elemCount];
			for (int i = 0; i < elemCount; i++) {
				largestValue[i] = i;
			}
		}
		
		
		public int findLargest(int p, int q)
		{
			int rootP = root(p);
			int rootQ = root(q);
			
			return rootP == rootQ ? largestValue[rootP] : -1;
		
		}
		public int connect(int p, int q)
		{
			int rootQ = root(q);
			int rootP = root(p);
			
			array[rootQ] = array[rootP];
			if(largestValue[rootQ] > largestValue[rootP])
			{
				largestValue[rootP] = largestValue[rootQ]; 
			}
			
			return array[rootQ];
		}
	}
	public static class QuickUnionFindSuccessor extends QuickFindUinon.AlgQuickUnionLazy
	{
		protected int [] successorValue;
			
		public QuickUnionFindSuccessor(int elemCount) 
		{
			super(elemCount);
			
			successorValue = new int[elemCount];
			for (int i = 0; i < elemCount; i++) {
				successorValue[i] = i + 1;
			}
			successorValue[elemCount-1] = 0;
		}
		@Override
		protected int root(int p)
		{
			while(array[p] == -1) p = successorValue[p];
			
			return p;
		}
		
		public int findSuccessor(int p)
		{
			int rootP = root(successorValue[p]);
			
			return array[rootP];
		}
		public void remove(int p)
		{
			array[p] = -1;
		}
		
	}

	public static class QuickUnionFindByDepth extends QuickFindUinon.Alg
	{
		protected int depthArray[];
		
		public QuickUnionFindByDepth(int elemCount) {
			super(elemCount);
			
			depthArray = new int[elemCount];
			
			for (int i = 0; i < depthArray.length; i++) {
				depthArray[i] = 0;
			}
		}
		protected int root(int p)
		{
			while(p != array[p]) p = array[p];
			
			return p;
		}
		public boolean find(int p, int q)
		{
			return root(p) == root(q); // ? array[p]  : -1 ;
		}
		public int connect(int p, int q)
		{
			int rootQ = root(q);
			int rootP = root(p);
			
			if(rootP != rootQ)
			{
				if(depthArray[rootQ] < depthArray[rootP])
				{
					array[rootQ] = rootP;
					
					if(depthArray[rootP] == depthArray[rootQ])
					{
						depthArray[rootP] += 1;
					}
				}
				else 
				{
					array[rootP] = rootQ;
					
					if(depthArray[rootQ] == depthArray[rootP])
					{
						depthArray[rootQ] += 1;
					}
				
				}
			}
			return array[rootQ];
		}

	}
	
	// Tree structure
	private int maxDepth(int [] array)
	{
		int maxDepth = 1, depth = 0;
		int index = 0;
		
		for (int i = 0; i < array.length; i++) 
		{
			index = i;
			depth = 0;
			
			while(index != array[index])
			{
				depth++;
				index = array[index];
			}
			
			if(depth > maxDepth) maxDepth = depth;
		}
		return maxDepth;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Test
	public void testQuickUnionFindLargestNumber() {

		QuickUnionFindLargestNumber alg = new QuickUnionFindLargestNumber(10);
		
		alg.connect(1, 2);
		alg.connect(1, 3);
		alg.connect(3, 4);
		alg.connect(4, 6);
		
		alg.connect(7, 9);
		alg.connect(9, 8);
		alg.connect(0, 8);
		alg.connect(0, 5);
		
		assertEquals(alg.findLargest(1, 6), 6);
		assertEquals(alg.findLargest(7, 0), 9);
		
	}
	@Test
	public void testQuickUnionFindSuccessor() {

		QuickUnionFindSuccessor alg = new QuickUnionFindSuccessor(10);
		
		assertEquals(alg.findSuccessor(0), 1);
		alg.remove(1);
		assertEquals(alg.findSuccessor(0), 2);
		alg.remove(2);
		assertEquals(alg.findSuccessor(0), 3);
		alg.remove(3);
		assertEquals(alg.findSuccessor(0), 4);
		alg.remove(4);
		assertEquals(alg.findSuccessor(0), 5);
		alg.remove(5);
		assertEquals(alg.findSuccessor(0), 6);
		alg.remove(6);
		assertEquals(alg.findSuccessor(0), 7);
		alg.remove(7);
		assertEquals(alg.findSuccessor(0), 8);
		alg.remove(8);
		assertEquals(alg.findSuccessor(0), 9);
		alg.remove(9);
		assertEquals(alg.findSuccessor(0), 0);
		
		alg = new QuickUnionFindSuccessor(10);
		
		assertEquals(alg.findSuccessor(3), 4);
		alg.remove(4);
		assertEquals(alg.findSuccessor(0), 1);
		alg.remove(3);
		assertEquals(alg.findSuccessor(2), 5);
		alg.remove(8);
		assertEquals(alg.findSuccessor(7), 9);
	}

	@Test
	public void testQuickUnionFindDepth() {

		QuickUnionFindByDepth alg = new QuickUnionFindByDepth(10);
		
		alg.connect(1, 2);
		alg.connect(1, 3);
		alg.connect(3, 4);
		alg.connect(4, 6);
		
		alg.connect(7, 9);
		alg.connect(9, 8);
		alg.connect(0, 8);
		alg.connect(0, 5);
		
		assertEquals(maxDepth(alg.array), 1);
		
		alg.connect(1, 7);
		
		assertEquals(maxDepth(alg.array), 2);
		
	}
}
