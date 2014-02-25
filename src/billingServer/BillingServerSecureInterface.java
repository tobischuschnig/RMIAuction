package billingServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import exceptions.InvalidInputException;
import exceptions.InvalidParameterException;
import exceptions.OverlappedPricestepException;

import model.Bill;
import model.PriceSteps;
/**
 * This Interface provides the necessary method heads for the class BillingServerSecure.
 * The Remote interface serves to identify interfaces whose methods may be invoked from a non-local virtual machine
 * @author Tobias Lins
 * @email tlins@tgm.ac.at
 * @version 1.0
 */
public interface BillingServerSecureInterface extends Remote{
	public PriceSteps getPriceSteps() throws RemoteException;
	public boolean createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) throws RemoteException,InvalidParameterException,InvalidInputException,OverlappedPricestepException;
	public boolean deletePriceStep(double startPrice, double endPrice) throws RemoteException;
	public boolean billAuction(String user, long auctionID, double price) throws RemoteException;
	public ArrayList<Bill> getBill(String user) throws RemoteException;
}
