package main;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class FinalizerTest extends TestCase {

	public static class OrderedFinalizer
	{
		private static int lastId;
		private static int counterId;
		
		private static int faillId;
		
		
		public static OrderedFinalizer last;
		public static OrderedFinalizer first;
		private static OrderedFinalizer newInstance()
		{
			if(last == null)
			{
				last = new OrderedFinalizer(lastId++);
				counterId++;
				first = last;
			}
			counterId++;
			return last = new OrderedFinalizer(last, lastId++);
		}
		private OrderedFinalizer next;
		public int id;
		public OrderedFinalizer(int id)
		{
			this.id = id;
		}
		public OrderedFinalizer(OrderedFinalizer next, int id)
		{
			this(id);
			this.next = next;
		}
		
		@Override 
		protected void finalize() throws InterruptedException
		{
			counterId--;
			//System.out.println("Object "+ id);
			System.out.println(new Date().toString());
			
			Thread.sleep(10000);
			System.out.println((new Date()).toString());
			
			if(counterId != id) 
			{
				faillId++;
				
				System.out.println("Counter "+counterId + " " + id);
			}
			next = null;
		}
	
	}
	public static class FinalReborn
	{
		public static FinalReborn Inst;
		
		
		@Override
		protected void finalize() throws InterruptedException
		{
			System.out.println("Dead");
			Inst = this;
		}
	
	}
	@Test
	public void testFinalReborn() throws InterruptedException
	{
		new FinalReborn();
		
		Runtime.getRuntime().gc();
		Thread.sleep(400);
		System.out.println(FinalReborn.Inst);
		
		FinalReborn.Inst = null;
		Runtime.getRuntime().gc();
		Thread.sleep(400);
		System.out.println(FinalReborn.Inst);
			
	}
	@Test
	public void testMassiveFinalize() throws InterruptedException
	{
		for(int i=100000; i > 0; i--)
		{
			System.out.println(OrderedFinalizer.newInstance().id);
		}

		
		//OrderedFinalizer.first = null;
		OrderedFinalizer.last = null;
		Runtime.getRuntime().gc();
		
		Thread.sleep(2000);
		System.out.println("Faill :" + OrderedFinalizer.faillId);
		
	}
	@Test
	public void testFinalize() throws InterruptedException
	{
		System.out.println(OrderedFinalizer.newInstance().id);
		System.out.println(OrderedFinalizer.newInstance().id);
		System.out.println(OrderedFinalizer.newInstance().id);
		System.out.println(OrderedFinalizer.newInstance().id);

		
		//OrderedFinalizer.first = null;
		OrderedFinalizer.last = null;
		Runtime.getRuntime().gc();
		Thread.sleep(4000);
		System.out.println("Terminate");
	}

}
