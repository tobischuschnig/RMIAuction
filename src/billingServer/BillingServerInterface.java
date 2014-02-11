package billingServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Exceptions.UserInputException;

public interface BillingServerInterface extends Remote {
		  public BillingServerSecureInterface login(String username, String password) throws RemoteException,UserInputException;
}
