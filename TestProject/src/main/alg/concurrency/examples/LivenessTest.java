package main.alg.concurrency.examples;

import org.junit.Test;

import junit.framework.TestCase;

public class LivenessTest extends TestCase{

	static class Deadlock
	{
		public Boolean reachFlag = false;
		
		public synchronized void methodA(Deadlock inst) throws InterruptedException
		{
			System.out.println("Tries to acquire intrinsic lock of Deadlock inst");
			Thread.sleep(100);
			inst.methodB();
			
			reachFlag = true;
		}
		public synchronized void methodB()
		{
			System.out.println("Do nothing");
		}
	}
	static class Starwation
	{
		public Boolean reachFlag = false;
			
	}
	@Test
	public void testDeadlock() throws InterruptedException
	{
		final Deadlock instA = new Deadlock();
		final Deadlock instB = new Deadlock();
		
		Thread tA = new Thread(new Runnable() {
			@Override
			public void run() {
				try 
				{
					instA.methodA(instB);
				}
				catch (InterruptedException e) 
				{}
				
				System.out.println("End thread A");
			}
		});

		Thread tB = new Thread(new Runnable() {
			@Override
			public void run() {
				try 
				{
					instB.methodA(instA);
				}
				catch (InterruptedException e) 
				{}
				System.out.println("End thread B");
				
			}
		});
		
		tA.setDaemon(true);
		tB.setDaemon(true);
		
		tA.start();
		Thread.sleep(50);
		tB.start();

		tA.join(200);
		
		assertTrue(!instA.reachFlag);
		assertTrue(!instB.reachFlag);
	}
}
