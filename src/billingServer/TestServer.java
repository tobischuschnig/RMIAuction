package billingServer;

import java.rmi.Naming;
import java.rmi.RemoteException;

import exceptions.InvalidInputException;
import exceptions.InvalidParameterException;
import exceptions.OverlappedPricestepException;
import exceptions.UserInputException;


import model.Bill;
import model.PriceStep;
import model.Properties;

public class TestServer {
	public static void main (String [] args){
		Properties p=new Properties("registry.properties");
		String host=p.getProperty("registry.host");
		int port=Integer.parseInt(p.getProperty("registry.port"));
		//BillingServer Objekt wird erstellt, und durch Naming Lookup abgerufen
		BillingServerInterface acc = null;
		try {     
			acc = (BillingServerInterface)Naming.lookup("rmi://"+host+":"+port+"/BillingServer");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		BillingServerSecureInterface secure = null;
		try {
			// BillingServerSecure objekt wird erstellt. 
			//Login hat zwei parameter (username, password) aber unverschluesselt.
			secure=(BillingServerSecureInterface)acc.login("admin", "admin");
			secure.createPriceStep(1000.0, 2000.0, 2.0, 5.0);
			secure.createPriceStep(1.0, 10.0, 2.0, 5.0);
			secure.createPriceStep(11.0, 20.0, 2.0, 5.0);
			secure.createPriceStep(21.0, 30.0, 2.0, 5.0);
			secure.createPriceStep(31.0, 40.0, 2.0, 5.0);
			
			System.out.println(secure.getPriceSteps().toString());
			//secure.billAuction("admin", 1, 1.2);

			//	b.
		}catch (UserInputException e1) {
			System.err.println(e1.getCause());
		} catch (InvalidInputException e) {
			System.err.println(e.getCause());
		} catch (InvalidParameterException e) {
			System.err.println(e.getCause());
		} catch (OverlappedPricestepException e) {
			System.err.println(e.getCause());
		} catch (RemoteException e) {
			System.err.println(e.getCause());
		}
	}

}
