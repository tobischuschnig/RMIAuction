package model;

public class Bill {
	private String user;
	private long auctionID ;
	private double feeFixed,strikePrice, feeVariable, feeTotal;

	public Bill(String user, long auctionID, double strikePrice) {
		this.user = user;
		this.auctionID = auctionID;
		this.feeFixed = feeFixed;
		this.strikePrice = strikePrice;
		this.feeVariable = feeVariable;
		this.feeTotal = feeTotal;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the auctionID
	 */
	public long getAuctionID() {
		return auctionID;
	}

	/**
	 * @param auctionID the auctionID to set
	 */
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	/**
	 * @return the feeFixed
	 */
	public double getFeeFixed() {
		return feeFixed;
	}

	/**
	 * @param feeFixed the feeFixed to set
	 */
	public void setFeeFixed(int feeFixed) {
		this.feeFixed = feeFixed;
	}

	/**
	 * @return the strikePrice
	 */
	public double getStrikePrice() {
		return strikePrice;
	}

	/**
	 * @param strikePrice the strikePrice to set
	 */
	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}

	/**
	 * @return the feeVariable
	 */
	public double getFeeVariable() {
		return feeVariable;
	}

	/**
	 * @param feeVariable the feeVariable to set
	 */
	public void setFeeVariable(double feeVariable) {
		this.feeVariable = feeVariable;
	}

	/**
	 * @return the feeTotal
	 */
	public double getFeeTotal() {
		return feeTotal;
	}

	/**
	 * @param feeTotal the feeTotal to set
	 */
	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}
	
	public String toString(){
		return this.auctionID+"\t"+this.strikePrice+"\t"+this.feeFixed+"\t"+this.feeVariable+"\t"+this.feeTotal;
	}
	
	

}
