package del5_8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Realtor implements Iterable<Property> {


	private String name;
	private double commission;
	private List<Property> properties;

	/**
	 * Creates a Realtor object
	 * 
	 * @param name       the name of the realtor
	 * @param commission the commission the realtor takes for a sale
	 */
	public Realtor(String name, double commission) {
		this.name = name;
		setCommission(commission);
		this.properties = new ArrayList<Property>();
	}

	/**
	 * 
	 * @return the name of the realtor
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param commission the new commission of the realtor
	 * 
	 * @throws IllegalArgumentException if the commission not between (excluding) 0
	 *                                  and (including) 100.
	 */
	public void setCommission(double commission) {
		if(commission > 0 && commission <101) {
			this.commission = commission;
		} else {
			throw new IllegalArgumentException("Commission has to be a number between 1-100");
		}
	}

	/**
	 * Adds a property to the realtor's sale collection
	 * 
	 * @param property a property
	 */
	public void addProperty(Property property) {
		if(!properties.contains(property)) {
			properties.add(property);
		} else {
			throw new IllegalArgumentException("Eiendommen er allerede lagt til");
		}
	}

	/**
	 * The total commission is calculated as the sum of the highest bid of each sold
	 * property times the commission rate. The commission rate is calculated based
	 * on the realtor's current commission rate and does not need to consider
	 * historical commission rates
	 * 
	 * A realtor with commission of 10 %, and two sold properties sold at 1000 each,
	 * would have a total commission value of 200
	 * 
	 * @return the calculated commission of the realtor
	 */
	public double calculateTotalCommission() {
		double sum = 0;
		for (Property property : properties) {
			if(property.isSold()) {
				sum += property.getHighestBid();
			}
		}
		return (sum*commission)/100;
	}

	@Override
	public Iterator<Property> iterator() {
		return this.properties.iterator();
	}

	@Override
	public String toString() {
		return name;
	}


	public int getHighestSale() {
		int sum = 0;
		for (Property property : properties) {
			if(property.isSold()) {
				if(property.getHighestBid() > sum) {
					sum = property.getHighestBid();
				}
			}
		} return sum;
	}	

	/**
	 * 
	 * @return an iterator to be able to iterate through all the properties of this
	 *         realtor
	 */
	public Iterator<Property> iterable() {
		while(iterator().hasNext()) {
			return iterator();
		}
		return null;
	}

	public static void main(String[] args) {
		Realtor realtor = new Realtor("test", 10);
		// The following will only work if BusinessProperty and Property has the correct
		// implementation
		Property p = new Property("name", 1500);
		p.bidReceived("BIDDER", 2000);
		p.setIsSold();
		realtor.addProperty(p);
		// Should be 200
		System.out.println(realtor.calculateTotalCommission());
	}

}
