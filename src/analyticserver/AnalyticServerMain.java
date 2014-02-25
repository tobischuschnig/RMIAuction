package analyticserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import model.Properties;


/**
 * Main to start the AnalyticServer 
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-02-25
 */
public class AnalyticServerMain {
	private static Registry r;
	/**
	 * Main Mathod starts the Analytic server with the correct properties
	 * @param args isnt used
	 */
	public static void main(String args[]){
		if (args.length != 1) {
			System.err.println("Wrong Arguments!\nCorrect: AnalyticServer Name");
			System.exit(0);
		}
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
			System.out.println(args[0]);
			//LocateRegistry.createRegistry(1099);
			AnalyticServer analyticServer = new AnalyticServer();
			AnalyticServerInterface analyticServerInterface = (AnalyticServerInterface)UnicastRemoteObject.exportObject(analyticServer, 0);
			r.rebind(args[0], analyticServerInterface);
			System.out.println("AnalyticServer bound");
		} 
		catch (Exception e) {
			System.err.println("Wrong Arguments!\nCorrect: AnalyticServer Name");
			//e.printStackTrace();
		}
	}
}
