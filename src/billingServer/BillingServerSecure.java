package billingServer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

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
	private ConcurrentHashMap<String, Bill> bills;

	/**
	 * Konstructor wich sets the concurrenthashmap for the pricesteps
	 */
	public BillingServerSecure() {
		this.priceSteps = new PriceSteps();
		this.bills = new ConcurrentHashMap<String, Bill>();
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
			throws RemoteException {
		boolean overlaped = false;
		ConcurrentHashMap<Integer, PriceStep> psTemp = priceSteps
				.getPriceSteps();
		if (startPrice < 0 || endPrice < 0 || fixedPrice < 0
				|| variablePricePercent < 0)
			throw new RemoteException();

		Iterator<Integer> it = psTemp.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			PriceStep temp = psTemp.get(key);
			if (temp.getStartPrice() < startPrice
					&& startPrice < temp.getEndPrice())
				overlaped = true;

			if (temp.getStartPrice() < endPrice
					&& endPrice < temp.getEndPrice())
				overlaped = true;

		}

		if (!overlaped) {
			psTemp.put(psTemp.size(), new PriceStep(startPrice, endPrice,
					fixedPrice, variablePricePercent));
			priceSteps.setPriceSteps(psTemp);
			return true;
		} else {
			System.out
					.println("Es konnte kein neuer PriceStep angelgegt werden, da er sich mit einem vorhandnen ueberschneidet");
			throw new RemoteException();
		}

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
		ConcurrentHashMap<Integer, PriceStep> psTemp = priceSteps
				.getPriceSteps();
		Iterator<Integer> it = psTemp.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			PriceStep temp = psTemp.get(key);
			if (temp.getStartPrice() == startPrice
					&& temp.getEndPrice() == endPrice) {
				psTemp.remove(key);
				priceSteps.setPriceSteps(psTemp);
				// priceSteps.getPriceSteps().remove(
				return true;
			}
		}
		return false;
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

		Double feeVariable = 0.0;
		Double feeFixed = 0.0;

		ConcurrentHashMap<Integer, PriceStep> psTemp = priceSteps
				.getPriceSteps();
		Iterator<Integer> it = psTemp.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			PriceStep temp = psTemp.get(key);
			if (price >= temp.getStartPrice()
					&& (price <= temp.getEndPrice() || temp.getEndPrice() == 0)) {
				feeVariable = price * temp.getVariablePricePercent() / 100;
				feeFixed = temp.getFixedPrice();
			}
		}

		bills.put(user, new Bill(user, auctionID, price, feeFixed, feeVariable,
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
	public Bill getBill(String user) {
		if (bills.containsKey(user))
			return bills.get(user);
		else
			return null;
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
	public ConcurrentHashMap<String, Bill> getBills() {
		return bills;
	}

	/**
	 * For testing Purposes only
	 * 
	 * @param bills
	 *            the bills to set
	 */
	public void setBills(ConcurrentHashMap<String, Bill> bills) {
		this.bills = bills;
	}
}
