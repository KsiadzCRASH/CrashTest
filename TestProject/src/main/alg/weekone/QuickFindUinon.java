package main.alg.weekone;

import junit.framework.TestCase;

import org.junit.Test;


public class QuickFindUinon  extends TestCase {

	static class Alg 
	{
		protected int elemtCount;
		protected int [] array;
		
		public int[] getArray() {
			return array;
		}

		public void setArray(int[] array) {
			this.array = array;
		}

		public Alg(int elemCount)
		{
			this.elemtCount = elemCount;
			
			array = new int[this.elemtCount];
			
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
		protected int objectCountArray[];
		
		public AlgQuickUnionWeighted(int elemCount) {
			super(elemCount);
			
			objectCountArray = new int[elemCount];
			
			for (int i = 0; i < objectCountArray.length; i++) {
				objectCountArray[i] = 1;
			}
		}

		protected int root(int p)
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
			
			if(rootP != rootQ)
			{
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
			}
			return array[rootQ];
		}
	}
	// Quick Union Path Compression
	/**
	 * @author Tomasz.Switek
	 *
	 */
	static class AlgQuickUnionPathCommpresion extends QuickFindUinon.AlgQuickUnionWeighted
	{
		
		private boolean flatType = true;
		
		/**
		 * @param elemCount
		 * @param flatType : if true then TotalFlat else OneLevelFlat
		 */
		public AlgQuickUnionPathCommpresion(int elemCount, boolean flatType) {
			super(elemCount);
			this.flatType = flatType;
		}
		
		@Override
		protected int root(int p)
		{
			return flatType ? rootTotalFlat(p) : rootOneLevelFlat(p);
		}
		
		private int rootTotalFlat(int p)
		{
			int root = super.root(p);
			int tmpP;
			
			while(p != array[p])
			{
				tmpP = p;
				p = array[p];
				array[tmpP] = root; 
			}
			
			return root;
		}
		private int rootOneLevelFlat(int p)
		{

			while(p != array[p])
			{
				array[p] = array[array[p]];
				p = array[p];
			}
			return p;
		}
	}
	
	//
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
		algWeighted.connect(2, 5);
		

		algLazy.connect(1, 2);
		algLazy.connect(3, 4);
		algLazy.connect(5, 6);
		algLazy.connect(7, 8);
		algLazy.connect(7, 9);
		algLazy.connect(9, 1);
		algLazy.connect(0, 5);
		algLazy.connect(4, 1);
		algLazy.connect(2, 5);
		
		assertTrue(maxDepth(algWeighted.array) <= maxDepth(algLazy.array));
	
	}
	@Test
	public void testQuickUnionTotalFlat() {

		QuickFindUinon.AlgQuickUnionPathCommpresion algTotalFlat = new QuickFindUinon.AlgQuickUnionPathCommpresion(10, true);
		QuickFindUinon.AlgQuickUnionLazy algLazy = new QuickFindUinon.AlgQuickUnionLazy(10);
		
		algTotalFlat.connect(1, 2);
		algTotalFlat.connect(3, 4);
		algTotalFlat.connect(5, 6);
		algTotalFlat.connect(7, 8);
		algTotalFlat.connect(7, 9);
		algTotalFlat.connect(9, 1);
		algTotalFlat.connect(0, 5);
		algTotalFlat.connect(4, 1);
		algTotalFlat.connect(2, 5);
		
		algTotalFlat.find(1, 5);
		algTotalFlat.find(3, 0);
		
		algLazy.connect(1, 2);
		algLazy.connect(3, 4);
		algLazy.connect(5, 6);
		algLazy.connect(7, 8);
		algLazy.connect(7, 9);
		algLazy.connect(9, 1);
		algLazy.connect(0, 5);
		algLazy.connect(4, 1);
		algLazy.connect(2, 5);
		
		assertTrue(maxDepth(algTotalFlat.array) <= maxDepth(algLazy.array));
	
	}
	@Test
	public void testQuickUnionOneLevelFlat() {

		QuickFindUinon.AlgQuickUnionPathCommpresion algOneLevelFlat = new QuickFindUinon.AlgQuickUnionPathCommpresion(10, false);
		QuickFindUinon.AlgQuickUnionLazy algLazy = new QuickFindUinon.AlgQuickUnionLazy(10);
		
		algOneLevelFlat.connect(1, 2);
		algOneLevelFlat.connect(3, 4);
		algOneLevelFlat.connect(5, 6);
		algOneLevelFlat.connect(7, 8);
		algOneLevelFlat.connect(7, 9);
		algOneLevelFlat.connect(9, 1);
		algOneLevelFlat.connect(0, 5);
		algOneLevelFlat.connect(4, 1);
		algOneLevelFlat.connect(2, 5);
		
		algOneLevelFlat.find(1, 5);
		algOneLevelFlat.find(3, 0);
		

		algLazy.connect(1, 2);
		algLazy.connect(3, 4);
		algLazy.connect(5, 6);
		algLazy.connect(7, 8);
		algLazy.connect(7, 9);
		algLazy.connect(9, 1);
		algLazy.connect(0, 5);
		algLazy.connect(4, 1);
		algLazy.connect(2, 6);
		
		assertTrue(maxDepth(algOneLevelFlat.array) <= maxDepth(algLazy.array));
	
	}
}
