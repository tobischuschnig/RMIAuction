package billingServer;

import java.rmi.Naming;
import java.rmi.RemoteException;

import Exceptions.UserInputException;

import model.Bill;
import model.PriceStep;

public class TestServer {
	public static void main (String [] args){
		
		//BillingServer Objekt wird erstellt, und durch Naming Lookup abgerufen
		BillingServerInterface acc = null;
		try {      
			acc = (BillingServerInterface)Naming.lookup("BillingServer");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// BillingServerSecure objekt wird erstellt. 
			
			//Login hat zwei parameter (username, password) aber unverschluesselt.
			BillingServerSecureInterface secure=(BillingServerSecureInterface)acc.login("admin", "admin");
			secure.createPriceStep(1.0, 5.0, 2.0, 5.0);
			secure.billAuction("admin", 1, 1.2);
			System.out.println(secure.getPriceSteps().toString());
			
			secure.deletePriceStep(1.0, 5.0);
			System.out.println(secure.getPriceSteps().toString());
			
			Bill b=secure.getBill("admin");
		//	b.
		} catch (RemoteException e) {
		}catch (UserInputException e1) {
			System.err.println(e1.getMessage());
		}
	}

}
