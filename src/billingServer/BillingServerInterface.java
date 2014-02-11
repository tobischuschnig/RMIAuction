package billingServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BillingServerInterface extends Remote {
		  public BillingServerSecureInterface login(String username, String password) throws RemoteException;
}
