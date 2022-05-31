package del5_8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Property {

	// Add any needed fields here

	protected boolean isSold;
	protected String name;
	protected int price;
	protected List<BidListener> observers = new ArrayList<BidListener>();
	protected List<BidListener> observersHigh = new ArrayList<BidListener>();
	protected List<Bid> bids = new ArrayList<Bid>();

	/**
	 * 
	 * @param name  the name of the property to be sold
	 * @param price the listing price of the property
	 */
	public Property(String name, int price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * 
	 * @return the name of the property
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the price of the property
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 
	 * @return whether the property is sold, default value is false
	 */
	public boolean isSold() {
		return isSold;
	}

	/**
	 * Sets the property as sold
	 * 
	 * @throws IllegalStateException if no bids have been received
	 */
	public void setIsSold() {
		if(getNumberOfBids() < 1) {
			throw new IllegalStateException("Kan ikke sette som solgt uten å få bud");
		} this.isSold =true;
	}

	/**
	 * 
	 * @return the number of bids received on this property
	 */
	public int getNumberOfBids() {
		return bids.size();
	}

	/**
	 * 
	 * @param listener register listener to be notified of any bids to this property
	 */
	public void addListenerForAllBids(BidListener listener) {
		if(observers.contains(listener)) {
			throw new IllegalArgumentException("Er allerede lagt til i listen");
		}
		observers.add(listener);
	}

	/**
	 * 
	 * @param listener register listener to be notified of only bids that are new
	 *                 highest bids You do not need to handle the case where a
	 *                 listener gets registered both for highest bid and for all
	 *                 bids
	 */
	public void addListenerForHighestBids(BidListener listener) {
		if(observersHigh.contains(listener)) {
			throw new IllegalArgumentException("Er allerede lagt til i listen");
		}
		observersHigh.add(listener);
	}

	/**
	 * 
	 * @param listener removes listener from this property, registered with any of
	 *                 the above methods
	 */
	public void removeListener(BidListener listener) {
		if(observers.contains(listener)) {
			observers.remove(listener);
		}else if(observersHigh.contains(listener)) {
			observersHigh.remove(listener);
		} else{
			throw new IllegalArgumentException("Finnes ikke i listene");
		}
	}

	/**
	 * Creates a new bid object and notifies all listeners that a bid has been given
	 * 
	 * @param bidder the name of the bidder
	 * @param bid    the amount of the bid
	 * 
	 * @throws IllegalStateException - if the property is already sold
	 */
	public void bidReceived(String bidder, int bid) {
		if(this.isSold) {
			throw new IllegalStateException("Property is already sold.");
		} Bid bid1 = new Bid(bidder, this, bid);
		if(!bids.contains(bid1)) {
			bids.add(bid1);
			notifyListeners(bid1);
		}
	}

	/**
	 * Notifies listeners that a bid has been received. There are currently no
	 * listeners implemented in the main method, but this is used for test purposes
	 * by us after the exam.
	 * 
	 * This is package private for testing purposes
	 * 
	 * @param bid the most recent bid
	 */
	void notifyListeners(Bid bid) {
		if(bid.getBid() > getHighestBid()) {
			for (BidListener bidListener : observersHigh) {
				bidListener.propertyBid(bid);
			}
			for (BidListener bidListener : observers) {
				bidListener.propertyBid(bid);
			}
		} else {
			for (BidListener bidListener : observers) {
				bidListener.propertyBid(bid);
			}
		}
	}
	/**
	 * 
	 * @return the current highest bid. If the property has no bids, return 0
	 */
	public int getHighestBid() {
		int highest = 0;
		for (Bid bid : bids) {
			if(bid.getBid() > highest) {
				highest = bid.getBid();
			}
		} return highest;
	}

	public static void main(String[] args) {
		Property p = new Property("name", 1000);
		p.bidReceived("BIDDER", 500);
		// 500
		System.out.println(p.getHighestBid());
		p.bidReceived("BIDDER2", 1100);
		// 1100
		System.out.println(p.getHighestBid());
		// false
		System.out.println(p.isSold());
		p.setIsSold();
		// true
		System.out.println(p.isSold());

	}
}
