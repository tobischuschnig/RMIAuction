package model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class wich contains the Constructor and all getters and setters for the specific EventType for Autions
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public class AuctionEvent extends Event  {

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
	/**
	 * Returns the specific type of a AuctionEvent in a well formatted way 
	 * @return the formatted String
	 */
	@Override
	public String toString() {
		String hilf = "";
		Date date = new Date(this.getTimestamp());
		Format format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:ms");
		if(this.getType().equals(EventType.AUCTION_STARTED)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - an auction with the ID "+auctionID+" has been created";
		} else if(this.getType().equals(EventType.AUCTION_ENDED)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - the auction with the ID "+auctionID+" has ended";
		}
		return hilf;
	}
	
}
