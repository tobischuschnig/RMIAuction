package billingServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
/**
 * This is the class for the user login.
 * If the login is successfully, you will get
 * an reference to the BillingServerSecure Object
 * which provides you other methods
 * @author Klune Alexander, Tobias Lins
 * @version 1.1
 * @email = aklune@student.tgm.ac.at
 * @email tobias.lins@student.tgm.ac.at
 *
 */
public class BillingServer implements BillingServerInterface,Serializable {
	
	
	
	private static Registry r;
	
	/**
	 * The access to the billing server is secured by user authentication. To keep things simple, 
	 * the username/password combinations can be configured statically in a config file user.properties. 
	 * Each line in this file contains an entry "<username> = <password>", e.g., "john = f23c5f9779a3804d586f4e73178e4ef0". 
	 * Do not put plain-text passwords into the config file, but store the MD5 hash 
	 * (not very safe either, but sufficient for this assignment) of the passwords. 
	 * Use the java.security.MessageDigest class to obtain the MD5 hash of a given password. 
	 * If and only if the login information is correct, the management client obtains 
	 * a reference to a SecureBillingServer remote object, which performs the actual tasks.
	 * @param username	username of the user login
	 * @param password	password from this user
	 * @return	reference von BillingServerSecure
	 */
	public BillingServerSecureInterface login(String username, String password) throws RemoteException{
		FileInputStream fis = null;
		BufferedReader br = null;
		boolean login = false;
		
		try {
			fis = new FileInputStream("user.properties");
			br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			while(line != null){
				String[] parts = line.split("=");
				if(parts[0].equals(username) && parts[1].equals(createMD5(password)))
					login = true;
				line = br.readLine();
			}
			fis.close();
			br.close();
		}catch (FileNotFoundException e) {
			System.err.println("Error loading file : user.properties");
		}catch (IOException e) {
			System.err.println("Error loading file : user.properties");
		}
		//Rueckgabe der referenz
		if(login)
			try {
				return (BillingServerSecureInterface)r.lookup("BillingServerSecure");
			} catch (NotBoundException e) {
				System.out.println("Service not bound");
			}
		try {
			//throw new invalidinputexception
			//throw new RemoteException();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//return null;
		return null;
	}
	
	public String createMD5(String pass){
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
	        md5.update(pass.getBytes());
	        byte[] result = md5.digest();

	        /* Ausgabe */
	        StringBuffer hexString = new StringBuffer();
	        for (int i=0; i<result.length; i++) {
	            hexString.append(Integer.toHexString(0xFF & result[i]));
	        }
	        return hexString.toString();
		}catch (Exception e) {
			return "";
		}
	}

	public static void main(String args[]){
	    // Ein SecurityManager ist nur erforderlich, wenn der RMI-Class-Loader Code laden muss
//	    if (System.getSecurityManager() == null)
//	      System.setSecurityManager(new RMISecurityManager());
	    try {
	    	r=LocateRegistry.createRegistry(1099);
	    	//LocateRegistry.createRegistry(1099);
	    	BillingServer acc = new BillingServer();
	    	BillingServerSecure secure=new BillingServerSecure();
	    	r.rebind("BillingServerSecure", (BillingServerSecureInterface)UnicastRemoteObject.exportObject(secure, 0));
	    	System.out.println("BillingServerSecure bound");
	    	r.rebind("BillingServer", (BillingServerInterface)UnicastRemoteObject.exportObject(acc, 0));
	    	System.out.println("BillingServer bound");
	    } 
	    catch (Exception e) {
	      System.out.println("Err: " + e.getMessage());
	    }
	  }


}


