package main.alg.weekone;

import org.junit.Test;

import junit.framework.TestCase;

public class BinarySearch extends TestCase
{
	public int search(int[] numberArray, int number)
	{
		int cIndex, startPointer = 0, endPointer = numberArray.length-1;
		
		while(startPointer <= endPointer)
		{
			cIndex = (endPointer - startPointer)/2 + startPointer;	
			
			if(numberArray[cIndex] > number) endPointer = cIndex - 1;
			else if(numberArray[cIndex] < number) startPointer = cIndex + 1;
			else return cIndex;
			
		}
		
		return -1;
		
	}
	
	public int threeSumProblem(int[] data)
	{
		int tSumCount = 0, tmpSum, searchIndex = -1;
		
		for(int i=0; i<data.length; i++)
		{
			for(int j=i; j<data.length; j++)
			{
				tmpSum = -(data[i]+data[j]);
				
				searchIndex = search(data, tmpSum);
				
				if(searchIndex >= 0 && (data[i] < data[j] && data[j] < data[searchIndex])) 
					tSumCount++;
				
			}	
		}
		
		return tSumCount;
	}
	
	public int threeSumProblemSI(int[] data)
	{
		int tSumCount = 0, tmpSum, searchIndex = -1;
		
		for(int i=0; i<data.length; i++)
		{
			for(int j=i; j<data.length; j++)
			{
				tmpSum = -(data[i]+data[j]);
				
				if(data[i] < data[j] && data[j] < tmpSum)
				{
					searchIndex = search(data, tmpSum);
				
					if(searchIndex >= 0) 
						tSumCount++;
				}
			}	
		}
		
		return tSumCount;
	}
	
	@Test 
	public void testBinarySearch()
	{
		int[] data = {1,23,44,51,63,78,99};
		
		assertEquals(search(data, 1), 0);
		assertEquals(search(data, 99), 6);
		assertEquals(search(data, 78), 5);
		assertEquals(search(data, 51), 3);
		assertEquals(search(data, 63), 4);
		assertEquals(search(data, 23), 1);

		assertEquals(search(data, 0), -1);
		assertEquals(search(data, 123), -1);
		assertEquals(search(data, 58), -1);
		assertEquals(search(data, 98), -1);
	}
	
	@Test 
	public void testTSumBinarySearch()
	{
		int[] data = {-40, -20, -10, 0, 5, 10, 30, 40};
		
		assertEquals(4, threeSumProblem(data));
		assertEquals(4, threeSumProblemSI(data));
		
	}
}
