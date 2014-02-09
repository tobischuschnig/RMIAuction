package managmentclient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author j
 */
public interface RMI_Billing extends Remote {
    
    BillingSeverSecure login(String username,String password)throws RemoteException;
    void logout(String username)throws RemoteException;
    PriceSteps getPriceStep() throws RemoteException;
    void createPriceSteps(double startPrice,double endPrice,
            double fixedPrice,double variablePricePercent)throws RemoteException;
    void deletePriceStep(double startPrice,double endPrice)throws RemoteException;
    void billAuction(String user, long auctionID,double price)throws RemoteException;
    Bill getBill(String user)throws RemoteException;
}
