package model;

public class AuctionEvent extends Event {

	private int auctionID;
	private boolean succes;
	
	public AuctionEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
	}


	public AuctionEvent(String iD, EventType type, long timestamp,
			int auctionID, boolean succes) {
		super(iD, type, timestamp);
		this.auctionID = auctionID;
		this.succes = succes;
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
	 * @return the succes
	 */
	public boolean isSucces() {
		return succes;
	}

	/**
	 * @param succes the succes to set
	 */
	public void setSucces(boolean succes) {
		this.succes = succes;
	}	
	
	
}
