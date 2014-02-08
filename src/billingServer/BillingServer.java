package billingServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class BillingServer {
	
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
	public BillingServerSecure login(String username, String password){
		FileInputStream fis = null;
		BufferedReader br = null;
		boolean login = false;
		
		try {
			fis = new FileInputStream("user.properties");
			br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			while(line != null){
				String[] parts = line.split("=");
				if(parts[0].equals(username) && parts[1].equals(password))
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
			return new BillingServerSecure();
		return null;
	}

}


