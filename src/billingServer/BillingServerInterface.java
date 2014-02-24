package billingServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Exceptions.UserInputException;
/**
 * This Interface provides the necessary method heads fpr BillingServer.
 * The Remote interface serves to identify interfaces whose methods may be invoked from a non-local virtual machine
 * @author Tobias Lins
 *@email tlins@tgm.ac.at
 *@version 1.0
 */
public interface BillingServerInterface extends Remote {
		  public BillingServerSecureInterface login(String username, String password) throws RemoteException,UserInputException;
}
