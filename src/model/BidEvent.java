package model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class wich contains the Constructor and all getters and setters for the specific EventType for Bids
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public class BidEvent extends Event {

	private String userName;

	private int auctionID;

	private double price;

	
	
	public BidEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
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
	/**
	 * Returns the specific type of a Bid Event in a well formatted way 
	 * @return the formatted String
	 */
	@Override
	public String toString() {
		String hilf = "";
		Date date = new Date(this.getTimestamp());
		Format format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:ms");
		if(this.getType().equals(EventType.BID_PLACED)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - user "+userName+" placed bid "+price+" on auction "+auctionID;
		} else if(this.getType().equals(EventType.BID_OVERBID)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - user "+userName+" got overbid with bid "+price+" on auction "+auctionID;
		} else if(this.getType().equals(EventType.BID_WON)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - user "+userName+" won auction "+auctionID+" with bid price "+price;
		}
		return hilf;
	}
	
}
