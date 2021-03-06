package main.effectivejava;

import org.junit.Test;

import junit.framework.TestCase;

public class EffectiveJavaInheritanceTest extends  TestCase{

	public static class SubClass extends SuperClass
	{
		public static int myVarInit(int val, String name)
		{
			System.out.println(name);
			return val;
		}
		private int var = myVarInit(2, "SubClassVar");
		static 
		{
			//new SuperClass();
			System.out.println("INIT SubClass");
		}
		public static final int bla = myVarInit(2, "SubClassSTATICVar");
		
		public SubClass()
		{
			super();
			
			method();
			System.out.println(var+ "SubClassVar");
			System.out.println(getVar() + "SubClassGetVar");
			
		}
		@Override
		public int getVar()
		{
			return var;
		}
		@Override
		public void method()
		{
			System.out.println("SubClass");
		}
	}
	public static class SuperClass extends SuperClass1
	{
		public static final int bla = SubClass.myVarInit(2, "SuperClassSTATICVar");
		
		private int var = SubClass.myVarInit(12, "SuperClassVar");

		private int var1 = SubClass.myVarInit(23, "SuperClassVar1");
		
		public SuperClass()
		{
			super();
			method();
			System.out.println(var+ "SuperClassVar");
			System.out.println(getVar() + "SuperClassGetVar");		
		}
		
		public int getVar()
		{
			return var;
		}
		public void method()
		{
			System.out.println("Super1Class" );
		}
	}
	public static class SuperClass1
	{
		public static final int bla = SubClass.myVarInit(2, "SuperClass1STATICVar");
		
		private int var = SubClass.myVarInit(12, "SuperClass1Var");

		private int var1 = SubClass.myVarInit(23, "SuperClass1Var1");
		
		public SuperClass1()
		{
			method();
			System.out.println(var+ "SuperClass1Var");
			System.out.println(getVar() + "SuperClass1GetVar");		
		}
		
		public int getVar()
		{
			return var;
		}
		public void method()
		{
			System.out.println("SuperClass1" );
		}
	}

	@Test
	public void testBLA()
	{
		new SubClass();
	}
}
