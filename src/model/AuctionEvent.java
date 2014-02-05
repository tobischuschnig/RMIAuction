package model;

public class AuctionEvent extends Event {

	private long auctionID;
	private boolean succes;

	/**
	 * @return the auctionID
	 */
	public long getAuctionID() {
		return auctionID;
	}

	/**
	 * @param auctionID the auctionID to set
	 */
	public void setAuctionID(long auctionID) {
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
