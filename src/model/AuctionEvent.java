package model;

public class AuctionEvent extends Event {

	private int auctionID;
	
	public AuctionEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
	}


	public AuctionEvent(String iD, EventType type, long timestamp,
			int auctionID) {
		super(iD, type, timestamp);
		this.auctionID = auctionID;
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

	
	
}
