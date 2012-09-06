package main.alg.concurrency.examples;

import org.junit.Test;

import junit.framework.TestCase;

public class SyncTest extends TestCase {

	public static class SyncA implements Runnable
	{
		private SyncObject syncObj;
		
		public SyncA(SyncObject obj) {
			syncObj = obj;
		}
		public void run()
		{
			System.out.println("Sync A");
			try {
				syncObj.methodA("Thread A");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("End sync A");
		
		}
	}
	public static class SyncB implements Runnable
	{
		private SyncObject syncObj;
		
		public SyncB(SyncObject obj) {
			syncObj = obj;
		}
		public void run()
		{
			System.out.println("Sync B");
			try {
				syncObj.methodB("Thread B");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("End sync B");
			
		}
	}

	public static class SyncStaticA implements Runnable
	{
		public void run()
		{
			System.out.println("Static Sync A");
			try {
				SyncObject.staticMethodA("Thread A");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Static End sync A");
		}
	} 

	public static class SyncStaticB implements Runnable
	{
		public void run()
		{
			System.out.println("Static Sync B");
			try {
				SyncObject.staticMethodB("Thread B");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Static End sync B");
		}
	} 
	
	
	public static class SyncObject 
	{
		public synchronized void methodA(String tName) throws InterruptedException
		{
			System.out.println("Method A : " + tName);
			Thread.sleep(1000);
		}
	
		public synchronized void methodB(String tName) throws InterruptedException
		{
			System.out.println("Method B : " + tName);
//			Thread.sleep(1000);
		}
	
		public static synchronized void staticMethodA(String tName) throws InterruptedException
		{
			System.out.println("Static method A : " + tName);
			Thread.sleep(1000);
		}
	
		public static synchronized void staticMethodB(String tName) throws InterruptedException
		{
			System.out.println("Static method B : " + tName);
//			Thread.sleep(1000);
		}
	
	}

	
	// 
	@Test
	public void testSyncMethodTest() throws InterruptedException
	{
		SyncObject obj = new SyncObject();
		
		Thread t1 = new Thread(new SyncA(obj));
		Thread t2 = new Thread(new SyncB(obj));
	
		t1.start();
		t2.start();
		
		t2.join();
		t1.join();
		
		assertTrue(true);
	} 

	@Test
	public void testSyncStaticMethodTest() throws InterruptedException
	{
		Thread t1 = new Thread(new SyncStaticA());
		Thread t2 = new Thread(new SyncStaticB());
	
		t1.start();
		t2.start();
		

		t2.join();
		t1.join();

		assertTrue(true);
	} 

}
