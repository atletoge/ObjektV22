package del4;

public class Temperature {

	private double degrees;
	private char scale;

	// Add any needed fields
	/**
	 * 
	 * @param degrees an arbitrary number
	 * @param scale   a character declaring the scale
	 * 
	 * @throws IllegalArgumentException if scale is not 'C' or 'F'
	 */
	public Temperature(double degrees, char scale) {
		this.degrees = degrees;
		if(String.valueOf(scale).equals("C") || String.valueOf(scale).equals("F")) {
			this.scale = scale;
		} else {
			throw new IllegalArgumentException("Må være enten celsius eller fahrenheit");
		}
		
		

	}

	/**
	 * 
	 * @return The current scale
	 */
	public char getScale() {
		return scale;
	}

	/**
	 * 
	 * @return the current degree of this object
	 */
	public double getDegrees() {
		return degrees;
	}

	/**
	 * Converts this temperature object to be the opposite scale of what it
	 * currently is
	 * 
	 * @return this temperature object, converted with value in Celsius if the scale
	 *         of this temperature object is Fahrenheit, and value in Fahrenheit if
	 *         this the scale of this temperature object is Celsius
	 */
	public Temperature toOther() {
		if(String.valueOf(scale).equals("C")) {
			this.scale = 'F';
			this.degrees = convertCelsisusToFahrenheit(this.degrees);
		} else {
			this.scale = 'C';
			this.degrees = convertFahrenheitToCelsius(this.degrees);
		}
		return this;
	}

	/**
	 * Creates a new temperature object with values in the other scale of this
	 * temperature object
	 * 
	 * @return a new Temperature object, with value in Celsius if the scale of this
	 *         temperature object is Fahrenheit, and value in Fahrenheit if the
	 *         scale of this temperature object is Celsius
	 */
	public Temperature inOther() {
		Temperature temperature;
		if(String.valueOf(scale).equals("C")) {
			temperature = new Temperature(convertCelsisusToFahrenheit(this.degrees), 'F');
		} else {
			temperature = new Temperature(convertFahrenheitToCelsius(this.degrees), 'C');
		} return temperature;
	}

	/**
	 * Lowers the temperature
	 * 
	 * @param amount the amount to lower by
	 */
	public void lower(double amount) {
		this.degrees -= amount;
	}

	/**
	 * 
	 * @param celsius
	 * @return the temperature in Fahrenheit
	 */
	public static double convertCelsisusToFahrenheit(double celsius) {
		return (celsius * 1.8 + 32.0);
	}

	/**
	 * 
	 * @param fahrenheit
	 * @return the temperature in Celsius
	 */
	public static double convertFahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit - 32.0) / 1.8;
	}

	public static void main(String[] args) {
		Temperature t = new Temperature(20, 'C');
		// Should print 20
		System.out.println(t.getDegrees());
		t.lower(10);
		// Should now print 10
		System.out.println(t.getDegrees());
		t.toOther();
		// Should now print 50
		System.out.println(t.getDegrees());
		t.toOther();

		Temperature t2 = t.inOther();
		// Should be 50;
		System.out.println(t2.getDegrees());

		// Should be 10
		System.out.println(t.getDegrees());
	}

}
