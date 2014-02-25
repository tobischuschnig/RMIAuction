package billingServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.util.Scanner;

import model.Properties;
import exceptions.UserInputException;

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
	private static BillingServer acc;
	private static BillingServerSecure secure;
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
	public BillingServerSecureInterface login(String username, String password) throws UserInputException{
		FileInputStream fis = null;
		BufferedReader br = null;
		boolean login = false;
		Properties p=new Properties("user.properties");
		
		String pw=p.getProperty(username);
		
		if(pw.equals("")){
			System.err.println("Username not found in user.properties");
		}else{
			if(pw.equals(createMD5(password))){
				login=true;
			}
		}
		//Rueckgabe der referenz
		if(login){
			try {
				return (BillingServerSecureInterface)r.lookup("BillingServerSecure");
			} catch (NotBoundException e) {
				System.out.println("Service not bound");
			} catch (AccessException e) {
				
			} catch (RemoteException e) {
				
			}
		}else{
			throw new UserInputException();	
		}
		return null;
	}

	/**
	 * Creates the md5 hash from a string
	 * @param pass password to be hashed
	 * @return md5 hash from password
	 */
	public String createMD5(String pass){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());

			byte byteData[] = md.digest();

			//convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			// System.out.println("Digest(in hex format):: " + sb.toString());

			return sb.toString();
		}catch (Exception e) {
			return "";
		}
	}

	public static void main(String args[]){
		if(args.length != 1) {
			System.err.println("Wrong Arguments!\nCorrect: BillingServerName");
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
			p.addProperty("billing.bindingName", args[0]);
			//LocateRegistry.createRegistry(1099);
			acc = new BillingServer();
			secure=new BillingServerSecure();
			r.rebind(args[0]+"Secure", (BillingServerSecureInterface)UnicastRemoteObject.exportObject(secure, 0));
			System.out.println("BillingServerSecure bound");
			r.rebind(args[0], (BillingServerInterface)UnicastRemoteObject.exportObject(acc, 0));
			System.out.println("BillingServer bound");
			Scanner in;
            in = new Scanner(System.in);
            while (true) {
            	if(in.nextLine().equals("!exit")){
            		break;
            	}
            }
            r.unbind("BillingServerSecure");
            r.unbind("BillingServer");
            secure.save();
            System.exit(1);
		} 
		catch (Exception e) {
			System.err.println("Wrong Arguments!\nCorrect: BillingServerName");
//			System.out.println("Err: " + e.getMessage());
		}
	}
	
}


