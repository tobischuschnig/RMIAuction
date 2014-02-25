package analyticserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import model.Properties;



public class AnalyticServerMain {
	private static Registry r;

	public static void main(String args[]){
		// Ein SecurityManager ist nur erforderlich, wenn der RMI-Class-Loader Code laden muss
		//	    if (System.getSecurityManager() == null)
		//	      System.setSecurityManager(new RMISecurityManager());
		Properties p=new Properties("registry.properties");
		int port=Integer.parseInt(p.getProperty("registry.port"));
		try {
			try{
				r=LocateRegistry.createRegistry(port);
			}catch (Exception e) {
				r=LocateRegistry.getRegistry(port);
			}
			
			//LocateRegistry.createRegistry(1099);
			AnalyticServer analyticServer = new AnalyticServer();
			AnalyticServerInterface analyticServerInterface = (AnalyticServerInterface)UnicastRemoteObject.exportObject(analyticServer, 0);
			r.rebind("AnalyticServer", analyticServerInterface);
			System.out.println("AnalyticServer bound");
		} 
		catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
