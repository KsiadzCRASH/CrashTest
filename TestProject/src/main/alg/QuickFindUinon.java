package main.alg;

import junit.framework.TestCase;

import org.junit.Test;


public class QuickFindUinon  extends TestCase {

	static class Alg 
	{
		protected int [] array;
		
		public int[] getArray() {
			return array;
		}

		public void setArray(int[] array) {
			this.array = array;
		}

		public Alg(int elemCount)
		{
			array = new int[elemCount];
			
			for (int i = 0; i < elemCount; i++) {
				array[i] = i;
			}
		}
		
		
	}
	//Quick Find
	static class AlgQuickFind extends Alg 
	{
		public AlgQuickFind(int elemCount) {
			super(elemCount);
		}

		public boolean find(int p, int q)
		{
			return array[p] == array[q]; // ? array[p]  : -1 ;
		}
		public int connect(int p, int q)
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
	// Tree like str.
	private int maxDepth(int [] array)
	{
		int maxDepth = 0, depth = 0;
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
	// Quick Union Lazy
	static class AlgQuickUnionLazy extends Alg 
	{
		public AlgQuickUnionLazy(int elemCount) {
			super(elemCount);
		}

		private int root(int p)
		{
			while(p != array[p]) p = array[p];
			
			return p;
			
			/*wersja rekurencyjna - mimo wszystko gorsza 
			 * if(p == array[p]) return p;
			else 
			{
				return root(array[p]);
			}*/
		}
		public boolean find(int p, int q)
		{
			return root(p) == root(q); // ? array[p]  : -1 ;
		}
		public int connect(int p, int q)
		{
			int rootQ = root(q);
			int rootP = root(p);
			
			array[rootQ] = array[rootP];
			
			return array[rootQ];
		}
	}
	// Quick Union Weighted
	static class AlgQuickUnionWeighted extends Alg 
	{
		private int objectCountArray[];
		
		public AlgQuickUnionWeighted(int elemCount) {
			super(elemCount);
			
			objectCountArray = new int[elemCount];
			
			for (int i = 0; i < objectCountArray.length; i++) {
				objectCountArray[i] = 1;
			}
		}

		private int root(int p)
		{
			while(p != array[p]) p = array[p];
			
			return p;
			
			/*wersja rekurencyjna - mimo wszystko gorsza 
			 * if(p == array[p]) return p;
			else 
			{
				return root(array[p]);
			}*/
		}
		public boolean find(int p, int q)
		{
			return root(p) == root(q); // ? array[p]  : -1 ;
		}
		public int connect(int p, int q)
		{
			int rootQ = root(q);
			int rootP = root(p);
			
			if(objectCountArray[rootQ] < objectCountArray[rootP])
			{
				array[rootQ] = rootP;
				objectCountArray[rootP] += objectCountArray[rootQ];
			}
			else 
			{
				array[rootP] = rootQ;
				objectCountArray[rootQ] += objectCountArray[rootP];
			
			}
			return array[rootQ];
		}
	}
	public static void main(String[] args)
	{
		QuickFindUinon uion = new QuickFindUinon();
		
		uion.testQuickFind();
	}
	@Test
	public void testQuickFind() {

		QuickFindUinon.AlgQuickFind alg = new QuickFindUinon.AlgQuickFind(10);
		
		alg.connect(1, 2);
		alg.connect(3, 4);
		alg.connect(5, 6);
		alg.connect(7, 8);
		alg.connect(7, 9);
		alg.connect(9, 1);
		alg.connect(0, 5);
		
		assertTrue(alg.find(7, 2));
		assertTrue(alg.find(0, 6));
		assertTrue(alg.find(3, 4));
		assertFalse(alg.find(1, 4));
		assertFalse(alg.find(5, 7));
		assertFalse(alg.find(6, 3));		

	}
	
	@Test
	public void testQuickunionLazy() {

		QuickFindUinon.AlgQuickUnionLazy alg = new QuickFindUinon.AlgQuickUnionLazy(10);
		
		alg.connect(1, 2);
		alg.connect(3, 4);
		alg.connect(5, 6);
		alg.connect(7, 8);
		alg.connect(7, 9);
		alg.connect(9, 1);
		alg.connect(0, 5);
		
		assertTrue(alg.find(7, 2));
		assertTrue(alg.find(0, 6));
		assertTrue(alg.find(3, 4));
		assertFalse(alg.find(1, 4));
		assertFalse(alg.find(5, 7));
		assertFalse(alg.find(6, 3));		

		assertEquals(maxDepth(alg.getArray()), 2);
	}
	@Test
	public void testQuickUnionWeighted() {

		QuickFindUinon.AlgQuickUnionWeighted algWeighted = new QuickFindUinon.AlgQuickUnionWeighted(10);
		QuickFindUinon.AlgQuickUnionLazy algLazy = new QuickFindUinon.AlgQuickUnionLazy(10);
		
		algWeighted.connect(1, 2);
		algWeighted.connect(3, 4);
		algWeighted.connect(5, 6);
		algWeighted.connect(7, 8);
		algWeighted.connect(7, 9);
		algWeighted.connect(9, 1);
		algWeighted.connect(0, 5);
		algWeighted.connect(4, 1);
		algWeighted.connect(2, 6);
		

		algLazy.connect(1, 2);
		algLazy.connect(3, 4);
		algLazy.connect(5, 6);
		algLazy.connect(7, 8);
		algLazy.connect(7, 9);
		algLazy.connect(9, 1);
		algLazy.connect(0, 5);
		algLazy.connect(4, 1);
		algLazy.connect(2, 6);
		
		assertTrue(maxDepth(algWeighted.array) <= maxDepth(algLazy.array));
	
	}
}
