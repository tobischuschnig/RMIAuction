package billingServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Diese Klasse dient der anmeldung des users. Wenn er sich erfolgreich mit
 * username und password an BillingServer angemeldet hat, bekommt dieser
 * eine Referenz auf BillineServerSecure, welcher weitere methoden bereitstellt.
 * @author Klune Alexander
 * @version 1.0
 * @email = aklune@student.tgm.ac.at
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
	 * @param username	username fuer den login
	 * @param password	password fuer den login
	 * @return	referenz von BillingServerSecure
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
			System.err.println("Fehler mein Laden / lesen des Files: user.properties");
		}catch (IOException e) {
			System.err.println("Fehler mein Laden / lesen des Files: user.properties");
		}
		//Rueckgabe der referenz
		if(login)
			return new BillingServerSecure();
		return null;
	}

}


