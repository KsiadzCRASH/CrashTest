package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestMain {

	private static final int NUMPTS = 100;
	private int[] data = new int[NUMPTS];
	
	{ for(int i = 0; i < NUMPTS; i++) data[i] = i; }
	
	public static class Dupa
	{
		
	}

	class Dupa1
	{
		
	}
	
	public static void bla()
	{
		try
		{
		
			return;
		}finally 
		{
			System.out.println("dad");
		}

	}
	
	public void shortCircuit() {
		int i = 9;
		int a = 6; // Why did we use a variable here?
		// What happens if you replace 'a' with '6' below?
		// Try this with an IDE like Eclipse...
		if ( (6 < 9 ) || (++i < 8)  )  i = i + 1;
	}	
	

	class A {
		public String doStuff(int i) { return "int"; }
		public String doStuff(Integer i) { return "Integer"; }
		public String doStuff(Object i) { return "Object"; }
		public String doStuff(int...i) { return "int vararg"; }
	}
	
	
	public void methodPreferenceMore() {
		// What happens if you change 'Integer' to 'Double'
		// Does thidddddsdasds explain 'methodPreferenceDouble'?

		// Think about why this happens?
	//	new A().doStuff(1,Double.valueOf(2));
	}
	/**
	 * @param args

	 *
	 */

	@SuppressWarnings("serial")
	static class ParentException extends Exception {}  
	@SuppressWarnings("serial")
	static class ChildException extends ParentException {}
	
	private void throwIt() throws ParentException {
		throw new ChildException();
	}
	
	public void catchOrder() {
		 String s = "";
		try {
			//throwIt();
//			throw new Error();
			
		} catch(RuntimeException e) {
			s = "ChildException";
		}/* catch(ParentException e) {
			s = "ParentException";
		}*/
	}
	enum Colors { 
		Red, Blue, Green, Yellow; // what happens if you add a ; here?
		// What happens if you type Red() instead?
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		
			bla();
		
			try(FileInputStream file = new FileInputStream("ds"))
			{
				
			}
			int leftShift = 0x80000000; // Is this number positive or negative?
			
			int asss = 42;
			long aacc = 42;
			
			boolean dda =  aacc == 42;
			
			int neg = 7; // Is this number positive or negative?
			int pos = -7; // Is this number positive or negative?
			int max = Integer.MAX_VALUE; // Is this number positive or negative?
			int min = Integer.MIN_VALUE; // Is this number positive or negative?
			int zero = 0;
		
			String as = Integer.toBinaryString(leftShift);
			String asNeg = Integer.toBinaryString(neg);
			String asPos = Integer.toBinaryString(pos);
			
			String asMax = Integer.toBinaryString(max);
			String asMin = Integer.toBinaryString(min);
			String asZero = Integer.toBinaryString(zero);
			
//			leftShift = leftShift << 1;
		//	leftShift =
					leftShift >>>= 4;
			
			as = Integer.toBinaryString(leftShift);
		
			TestMain aa = new TestMain();
		
		aa.catchOrder();
		// TODO Auto-generated method stub

		long b = 2147483648L;
		int a = (int) b;
		
		System.out.println(a);
		
		final String stringaa = "ha";
		Object object = new Object(){
			@Override public String toString(){
				return stringaa;
			}
		};
		int i = 5;
		// What happens if you remove the 'final' modifier?
		// What does this mean for case values?
		final int caseOne = 1;
		String result = "Basic ";
		switch(i) {
			case caseOne:
				result += "One";
				break;
			default:
				result += "Nothing";
		}
		
		System.out.println(stringaa + null);
		
		
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
	
		BigDecimal a1 = new BigDecimal(4.0);
		BigDecimal a2 = new BigDecimal(4.00, new MathContext(3));
		
		BigDecimal[] aadss= new BigDecimal[]{ a1, a2, a2, a1};
		Arrays.sort(aadss, 0, 4);
		
		System.out.println("");
		
		
		 Date date = new Date(100010001000L);
		
		System.out.println(date.toString());
		
	}
	abstract class ParentTwo {
		abstract public List<?> doStuff();
	}		
	
	class ChildTwo extends ParentTwo {
		public List<?> doStuff() { return Collections.emptyList(); };
	}

	public void overridenMethodsMayReturnSubtype() {
		// What do you need to change in order to get rid of the type cast?
		// Why does this work?
		List<?> list = new ChildTwo().doStuff();
	}
	
	

}
