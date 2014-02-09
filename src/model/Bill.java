package model;

public class Bill {
	private String username;
	private long auctionID;
	private double price;

	public Bill(String username, long auctionID, double price) {
		this.username = username;
		this.auctionID = auctionID;
		this.price=price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(long auctionID) {
		this.auctionID = auctionID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	
	

}
