package billingServer;

import Exceptions.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import model.PriceStep;
import model.PriceSteps;
import model.Bill;

/**
 * This class provides methodes to delete, create and get the current
 * Pricesteps. To that, there can be created a bill, when the auction is over.
 * This bill can be lookuped by the specific username.
 * 
 * @author Klune Alexander
 * @version 1.0
 * @email aklune@student.tgm.ac.at
 */
public class BillingServerSecure implements Serializable,
		BillingServerSecureInterface {

	private PriceSteps priceSteps;
	private CopyOnWriteArrayList<Bill> bills;

	/**
	 * Konstructor wich sets the concurrenthashmap for the pricesteps
	 */
	public BillingServerSecure() {
		this.priceSteps = new PriceSteps();
		this.bills = new CopyOnWriteArrayList<Bill>();
	}

	/**
	 * This method returns the current pricesteps
	 * 
	 * @return current priceSteps
	 */
	public PriceSteps getPriceSteps() {
		return priceSteps;
	}

	/**
	 * This method creates a new pricestep, if the new pricestep doesnt overlap
	 * with existing ones. If the new pricestep overlaps, you have to delete the
	 * old one first. All parameters have to be > 0
	 * 
	 * @param startPrice
	 *            startprice of the new pricestep
	 * @param endPrice
	 *            endprice of the new pricestep
	 * @param fixedPrice
	 *            fixedprice of the new pricestep
	 * @param variablePricePercent
	 *            variablePricePercent of the new pricestep
	 * @throws RemoteException
	 *             thrown when parameter < 0 or overlaping with existing
	 *             pricestep
	 * @return boolean true, if the new pricestep has been successfully set.
	 *         otherwise throw RemoteException
	 */
	public boolean createPriceStep(double startPrice, double endPrice,
			double fixedPrice, double variablePricePercent)
			throws  RemoteException,InvalidParameterException,InvalidInputException,OverlappedPricestepException{
		return priceSteps.addPricestep(startPrice, endPrice, fixedPrice,
				variablePricePercent);
	}

	/**
	 * This Method deletes the pricestep with the specific startprice and
	 * endprice
	 * 
	 * @param startPrice
	 *            startprice of the pricestep to be deleted
	 * @param endPrice
	 *            endprice of the pricestep to be deleted
	 */
	public boolean deletePriceStep(double startPrice, double endPrice) {
		return priceSteps.removePricestep(startPrice, endPrice);
	}

	/**
	 * This Method creates a bill with the given user, auctionID and price
	 * 
	 * @param user
	 *            username who won the auction
	 * @param auctionID
	 *            ended auctionID
	 * @param price
	 *            endprice of the auction
	 * @return if bill was sucessfully created
	 */
	public boolean billAuction(String user, long auctionID, double price) {
		System.out.println("bill will be added");
		Double feeVariable = 0.0;
		Double feeFixed = 0.0;

		CopyOnWriteArrayList<PriceStep> psTemp = priceSteps.getPriceSteps();

		for (int x = 0; x < psTemp.size(); x++) {
			PriceStep temp = psTemp.get(x);
			if (price >= temp.getStartPrice()
					&& (price <= temp.getEndPrice() || temp.getEndPrice() == 0)) {
				feeVariable = price * temp.getVariablePricePercent() / 100;
				feeFixed = temp.getFixedPrice();
			}
		}
		System.out.println(user);
		bills.add(new Bill(user, auctionID, price, feeFixed, feeVariable,
				feeFixed + feeVariable));
		return true;
	}

	/**
	 * This Method returns the bill of a specific user
	 * 
	 * @param user
	 *            username of the bill to be returned
	 * @return Bill of the username
	 */
	public ArrayList<Bill> getBill(String user) {
		ArrayList bil=new ArrayList<Bill>();
		for (int x = 0; x < bills.size(); x++) {
			if (bills.get(x).getUser().equals(user)) {
				bil.add(bills.get(x));
			}
		}
		return bil;
	}

	/**
	 * For testing Purposes only
	 * 
	 * @param priceSteps
	 *            the priceSteps to set
	 */
	public void setPriceSteps(PriceSteps priceSteps) {
		this.priceSteps = priceSteps;
	}

	/**
	 * For testing Purposes only
	 * 
	 * @return the bills
	 */
	public CopyOnWriteArrayList<Bill> getBills() {
		return bills;
	}

	/**
	 * For testing Purposes only
	 * 
	 * @param bills
	 *            the bills to set
	 */
	public void setBills(CopyOnWriteArrayList<Bill> bills) {
		this.bills = bills;
	}
}
