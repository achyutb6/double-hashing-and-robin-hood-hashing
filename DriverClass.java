package aab180004;
import java.util.HashSet;
import java.util.Random; 

/**
 * @author Achyut Bhandiwad - aab180004
 * @author Mythri Thippareddy - mxt172530
 */
public class DriverClass<T> {
	
	static Random rand = new Random(); 	
	
	/**
	 * @param arr ;  Return the number of unique elements
	 * @return
	 */
	static<T> int distinctElements(T[ ] arr) {		
		HashSet<T> hashMap2 = new HashSet<>();
		for(int i=0;i<arr.length;i++) {
			hashMap2.add(arr[i]);
		}
		return hashMap2.size();
	}
	
	/**
	 * @return : Creating an array of 1000000 elements
	 */
	public static Integer[] createArray() {
		Integer[] arr = new Integer[1000000];
		for(int i =0;i<arr.length;i++) {
			arr[i]=rand.nextInt();
		}
		return arr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DriverClass<Integer> driver = new DriverClass<Integer>();
		Timer timer = new Timer();		
		Integer[] arr =createArray();		
		
		timer.start();
		int numberOfDistinctElementsUsingHashSet = DriverClass.distinctElements(arr);
		timer.end();
		System.out.println("Number of distinct elements using HashSet:"+numberOfDistinctElementsUsingHashSet);
		System.out.println(timer);

		timer.start();
		int numberOfDistinctElementsUsingRobinHood = RobinHoodHashing.distinctElements(arr);
		timer.end();
		System.out.println("Number of distinct elements using RobinHoodHashing:"+numberOfDistinctElementsUsingRobinHood);
		System.out.println(timer);

		timer.start();
		DoubleHashing<MyInteger,MyInteger> dh = new DoubleHashing<>();
		int numberOfDistinctElementsUsingDoubleHashing = dh.distinctElements(arr);
		timer.end();
		System.out.println("Number of distinct elements using DoubleHashing:"+numberOfDistinctElementsUsingHashSet);
		System.out.println(timer);
		
	}
}
