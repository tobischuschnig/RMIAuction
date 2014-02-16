package model;

public class BidEvent extends Event {

	private String userName;

	private int auctionID;

	private double price;

	
	
	public BidEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
		// TODO Auto-generated constructor stub
	}

	
	
	public BidEvent(String iD, EventType type, long timestamp, String userName,
			int auctionID, double price) {
		super(iD, type, timestamp);
		this.userName = userName;
		this.auctionID = auctionID;
		this.price = price;
	}



	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the auctionID
	 */
	public int getAuctionID() {
		return auctionID;
	}

	/**
	 * @param auctionID the auctionID to set
	 */
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	
	
}
