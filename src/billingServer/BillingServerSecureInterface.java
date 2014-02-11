package billingServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Bill;
import model.PriceSteps;

public interface BillingServerSecureInterface extends Remote{
	public PriceSteps getPriceSteps() throws RemoteException;
	public void createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) throws RemoteException;
	public void deletePriceStep(double startPrice, double endPrice) throws RemoteException;
	public void billAuction(String user, long auctionID, double price) throws RemoteException;
	public Bill getBill(String user) throws RemoteException;
}
