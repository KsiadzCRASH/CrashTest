package main.alg.weektwo;

import junit.framework.TestCase;

public class StackAndQueue extends TestCase {

	public static interface IItem
	{}
	
	public static class StackLL<T extends IItem>
	{
		private class Node
		{
			T elem;
			Node next;
		}
	} 
}
