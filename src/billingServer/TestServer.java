package billingServer;

import java.rmi.Naming;
import java.rmi.RemoteException;

import Exceptions.UserInputException;

import model.PriceStep;

public class TestServer {
	public static void main (String [] args){
		BillingServerInterface acc = null;
		try {      
			acc = (BillingServerInterface)Naming.lookup("BillingServer");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BillingServerSecureInterface secure=(BillingServerSecureInterface)acc.login("admin", "admin");
			secure.createPriceStep(1.0, 5.0, 2.0, 5.0);
			System.out.println(secure.getPriceSteps().toString());
		} catch (RemoteException e) {
		}catch (UserInputException e1) {
			System.err.println(e1.getMessage());
		}
	}

}