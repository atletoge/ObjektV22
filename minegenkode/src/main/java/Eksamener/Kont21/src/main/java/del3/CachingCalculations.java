package del3;

import java.util.HashMap;

public class CachingCalculations implements Calculations {

	private HashMap<Integer, Integer> oversikt = new HashMap<>();
	private Calculations delegate;
	private HashMap<Integer, Integer> oversikt2 = new HashMap<>();

	public CachingCalculations(Calculations delegate) {
		this.delegate = delegate;
	}

	@Override
	/**
	 * Delegates the job of calculating the square root to the delegate If the
	 * argument has been previously seen, the delegate should not be used and a
	 * local cached version of the result should be returned
	 * 
	 * @returns the calculation applied to the argument
	 */
	public int getCalculation1(int number) {
		if(oversikt.containsKey(number)) {
			return oversikt.get(number);
		} else {
			oversikt.put(number, delegate.getCalculation1(number));
			return oversikt.get(number);
		}
	}

	@Override
	/**
	 * Delegates the job of calculating the square to the delegate If the argument
	 * has been previously seen, the delegate should not be used and a local cached
	 * version of the result should be returned
	 * 
	 * @returns the calculation applied to the argument
	 */
	public int getCalculation2(int number) {
		if(oversikt2.containsKey(number)) {
			return oversikt2.get(number);
		} else {
			oversikt2.put(number, delegate.getCalculation2(number));
			return oversikt2.get(number);
		}

	}

	public static void main(String[] args) {
		CachingCalculations calc = new CachingCalculations(new CalculationsImpl());
		// Should print 81
		System.out.println(calc.getCalculation2(9));
		// Should print 81 again, should not use the delegate
		System.out.println(calc.getCalculation2(9));
		// Should print 3
		System.out.println(calc.getCalculation1(9));
	}

}
