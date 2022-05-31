package del5_8;

public class BusinessProperty extends Property {


	/**
	 * BusinessProperty should implement the following extensions from Property. As
	 * soon as a bid is received that is equal to or higher than the price, the
	 * property should be marked as sold
	 */

	public BusinessProperty(String name, int price) {
		super(name, price);
	}
	@Override
	public void bidReceived(String bidder, int bid) {
		if(this.isSold) {
			throw new IllegalStateException("Property is already sold.");
		} Bid bid1 = new Bid(bidder, this, bid);
		if(!bids.contains(bid1)) {
			bids.add(bid1);
			notifyListeners(bid1);
		} if(bid1.getBid() > this.price) {
			this.setIsSold();
		}
	}

	public static void main(String[] args) {
		Property p = new BusinessProperty("name", 1000);
		p.bidReceived("BIDDER", 500);
		// 500
		System.out.println(p.getHighestBid());
		p.bidReceived("BIDDER2", 1100);
		// 1100
		System.out.println(p.getHighestBid());
		// true
		System.out.println(p.isSold());

	}
}
