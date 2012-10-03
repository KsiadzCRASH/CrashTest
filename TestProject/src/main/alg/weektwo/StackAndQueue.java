package main.alg.weektwo;

import java.util.Iterator;

import org.junit.Test;

import junit.framework.TestCase;

public class StackAndQueue extends TestCase {

	public static class Item implements IItem
	{
		private int nr;
		
		public Item(int nr)
		{
			this.nr = nr;
		}
		
		public String toString()
		{
			return "Item : " + nr;
		}
		
		public int nr()
		{
			return nr;
		}
	}
	public static interface IItem
	{
		int  nr();
	}
	
	public static class StackLL<T>
	{
		private class Node
		{
			T elem;
			Node next;
		}
	
		private Object syncObect;
		private Node head;
		
		public StackLL()
		{
			syncObect = new Object();
		}
		
		public boolean isEmpty()
		{
			boolean flag = true;
			
			synchronized (syncObect)
			{
				flag = (head == null);
			}
			
			return flag;
		}
		public void push(T elem)
		{
			synchronized(syncObect)
			{
				Node nHead = new Node();
				
				nHead.elem = elem;
				nHead.next = head;
				head = nHead;
			}
		}
		public T pop()
		{
			T elem = null;
			
			synchronized(syncObect)
			{	
				Node oHead = head;
				
				if(oHead != null) head = oHead.next;
				
				elem = oHead.elem;
			}
			
			return elem;
		}
	} 
	//
	public static class StackArr<T>
	{
		private Object syncObect;
		private T[] data;
		private int headIndex = 0;
		private int initialSize = 0;
		
		@SuppressWarnings("unchecked")
		public StackArr(int initialSize)
		{
			if(initialSize <= 0) throw new IllegalArgumentException();
			
			syncObect = new Object();
			
			this.initialSize = initialSize;
			
			data = (T[]) new Object[initialSize];
		}
		
		public int size()
		{
			return data.length;
		}
		
		public boolean isEmpty()
		{
			boolean flag = true;
			
			synchronized (syncObect)
			{
				flag = (headIndex < 0 || data[headIndex] == null);
			}
			
			return flag;
		}
		public void push(T elem)
		{
			synchronized(syncObect)
			{
				manageUpSize();
				data[++headIndex] = elem;
			}
		}
		
		public T pop()
		{
			T elem = null;
			
			synchronized(syncObect)
			{	
				elem = data[headIndex--];

				manageDownSize();
			}
			
			return elem;
		}
		@SuppressWarnings("unchecked")
		private void manageUpSize()
		{
			if(headIndex >= data.length / 2) 
			{
				T[] nData = (T[]) new Object[data.length * 2];
				
				for (int i = 0; i <= headIndex; i++) {
					nData[i] = data[i];
				}
				
				data = nData;
			}
		}
		@SuppressWarnings("unchecked")
		private void manageDownSize()
		{
			if(headIndex <= data.length / 4 && data.length / 2 >= initialSize)  
			{
				T[] nData = (T[]) new Object[data.length / 2];
				
				for (int i = 0; i <= headIndex; i++) {
					nData[i] = data[i];
				}
				
				data = nData;
			}
		}
	} 
	// Queue 
	public static class QueueLL<T>
	{
		private class Node
		{
			T elem;
			Node next;
		}
	
		private Object syncObect;
		private Node head;
		private Node tail;
		
		
		public QueueLL()
		{
			syncObect = new Object();
		}
		
		public boolean isEmpty()
		{
			boolean flag = true;
			
			synchronized (syncObect)
			{
				flag = (head == null);
			}
			
			return flag;
		}
		public void enqueue(T elem)
		{
			synchronized(syncObect)
			{
				Node node = new Node();
				
				node.elem = elem;
				
				if(isEmpty())
				{
					tail = node;
					head = tail;
				}
				else 
				{	
					tail.next = node;
					tail = node;
				}

			}
		}
		public T dequeue()
		{
			T elem = null;
			
			synchronized(syncObect)
			{	
				if(!isEmpty())
				{
					elem = head.elem;
				
					head = head.next;
				}
			}
			
			return elem;
		}
	} 
	//
	public static class QueueArr<T> implements Iterable<T>
	{
		private Object syncObect;
		private T[] data;
		
		private int headIndex = 0;
		private int tailIndex = 0;
		private int elemCount = 0;
		
		private int initialSize = 0;
		
		@SuppressWarnings("unchecked")
		public QueueArr(int initialSize)
		{
			if(initialSize <= 0) throw new IllegalArgumentException();
			
			syncObect = new Object();
			
			this.initialSize = initialSize;
			
			data = (T[]) new Object[initialSize];
		}
		
		public int size()
		{
			return data.length;
		}
		
		public int elemCount()
		{
			return elemCount;
		}
	
		public boolean isEmpty()
		{
			boolean flag = true;
			
			synchronized (syncObect)
			{
				flag = headIndex < 0 || data[headIndex] == null;
			}
			
			return flag;
		}
		public void enqueue(T elem)
		{
			synchronized(syncObect)
			{
				elemCount++;
				tailIndex = (++tailIndex)%data.length;
				
				data[tailIndex] = elem;	
				
				manageUpSize();
			}
		}
		
		public T dequeue()
		{
			T elem = null;
			
			synchronized(syncObect)
			{	
				elemCount--;
				elem = data[(headIndex++)%data.length];
				
				manageDownSize();
			}
			
			return elem;
		}
		@SuppressWarnings("unchecked")
		private void manageUpSize()
		{
			if(elemCount >= data.length / 2)
			{
				T[] nData = (T[]) new Object[data.length * 2];
				
				for (int i = headIndex, j = 0; i <= tailIndex; i++, j++) 
				{
					nData[j] = data[i];
				}
				headIndex = 0;
				tailIndex = elemCount - 1;
				
				data = nData;
			}
		}
		@SuppressWarnings("unchecked")
		private void manageDownSize()
		{
			if(elemCount <= data.length / 4 && data.length / 2 >= initialSize)  
			{
				T[] nData = (T[]) new Object[data.length / 2];
				
				for (int i = headIndex, j = 0; i <= tailIndex; i++, j++) 
				{
					nData[j] = data[i];
				}
				headIndex = 0;
				tailIndex = elemCount - 1;
				
				data = nData;
			}
		}
		private class QueueIterator<T> implements Iterator<T>
		{

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		} 

		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
	} 
	// Stacks	
	@Test
	public void testStackLL()
	{
		StackLL<IItem> stack = new StackLL<IItem>();
		
		IItem elem;
		
		elem = new Item(1);
		stack.push(elem);
		
		elem = new Item(2);
		stack.push(elem);
		
		elem = new Item(3);
		stack.push(elem);
		
		elem = new Item(4);
		stack.push(elem);
		
		elem = stack.pop();
		assertEquals(4, elem.nr());
		
		elem = stack.pop();
		assertEquals(3, elem.nr());
		
		elem = stack.pop();
		assertEquals(2, elem.nr());
	
		elem = stack.pop();
		assertEquals(1, elem.nr());
		
		assertEquals(true, stack.isEmpty());
	} 

	@Test
	public void testStackArr()
	{
		StackArr<IItem> stack = new StackArr<IItem>(1);
		
		IItem elem;
		
		elem = new Item(1);
		stack.push(elem);
		
		assertEquals(2, stack.size());
		
		elem = new Item(2);
		stack.push(elem);
		
		assertEquals(4, stack.size());
		
		elem = new Item(3);
		stack.push(elem);
		
		assertEquals(8, stack.size());

		elem = new Item(4);
		stack.push(elem);
		
		elem = stack.pop();
		assertEquals(4, elem.nr());
		
		elem = stack.pop();
		assertEquals(3, elem.nr());
		
		assertEquals(4, stack.size());
		
		elem = stack.pop();
		assertEquals(2, elem.nr());
		
		assertEquals(2, stack.size());
		
		elem = stack.pop();
		assertEquals(1, elem.nr());
		
		assertEquals(1, stack.size());
		
		assertEquals(true, stack.isEmpty());
	} 

	/// Queues
	@Test
	public void testQueueLL()
	{
		QueueLL<IItem> queue = new QueueLL<IItem>();
		
		IItem elem;
		
		elem = new Item(1);
		queue.enqueue(elem);
		
		elem = new Item(2);
		queue.enqueue(elem);
		
		elem = new Item(3);
		queue.enqueue(elem);
		
		elem = new Item(4);
		queue.enqueue(elem);
		
		elem = queue.dequeue();
		assertEquals(1, elem.nr());
		
		elem = queue.dequeue();
		assertEquals(2, elem.nr());
		
		elem = queue.dequeue();
		assertEquals(3, elem.nr());
	
		elem = queue.dequeue();
		assertEquals(4, elem.nr());
		
		assertEquals(true, queue.isEmpty());
	} 
	
	@Test
	public void testQueueArr()
	{
		QueueArr<IItem> queue = new QueueArr<IItem>(1);
		
		IItem elem;
		
		elem = new Item(1);
		queue.enqueue(elem);
		
		assertEquals(2, queue.size());
		
		elem = new Item(2);
		queue.enqueue(elem);
		
		assertEquals(4, queue.size());
		
		elem = new Item(3);
		queue.enqueue(elem);
		
		assertEquals(8, queue.size());

		elem = new Item(4);
		queue.enqueue(elem);
		
		elem = queue.dequeue();
		assertEquals(1, elem.nr());
		
		elem = queue.dequeue();
		assertEquals(2, elem.nr());
		
		assertEquals(4, queue.size());
		
		elem = queue.dequeue();
		assertEquals(3, elem.nr());
		
		assertEquals(2, queue.size());
		
		elem = queue.dequeue();
		assertEquals(4, elem.nr());
		
		assertEquals(1, queue.size());
		
		assertEquals(true, queue.isEmpty());
	} 
}
