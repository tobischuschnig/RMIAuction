package model;

/**
 * Provides the Bills for the BillingServer
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public class Bill {

    private String user;
    private long auctionID;
    private double feeFixed, strikePrice, feeVariable, feeTotal;

    public Bill(String user, long auctionID, double strikePrice, double feeFixed, double feeVariable, double feeTotal) {
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
    public void setFeeFixed(double feeFixed) {
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

    public String toString() {
        String returnment = String.format("%10s%15s%15s%15s%15s", "auction_ID",
                "strike_Price", "fee_Fixed", "fee_Variable", "fee_Total");
        returnment += String.format("\n%10s%15s%15s%15s", this.auctionID,
                this.strikePrice, this.feeFixed, this.feeVariable, this.feeTotal);

        return returnment;
    }
}
