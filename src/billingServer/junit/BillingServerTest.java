package billingServer.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.rmi.Naming;
import java.rmi.RemoteException;

import model.Properties;

import org.junit.Before;
import org.junit.Test;

import Exceptions.UserInputException;
import billingServer.BillingServer;
import billingServer.BillingServerInterface;

/**
 * Testclass for BillingServer
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @version 17/02/2014
 */
public class BillingServerTest {
	private BillingServer bs;
	private BillingServerInterface acc = null;
	
	@Before
	public void setUp(){
		bs=new BillingServer();
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
	}
	/**
	 * Testing of login Method with right user credentials
	 * 
	 * @throws UserInputException 
	 * @throws RemoteException 
	 */
	@Test
	public void testLogin() throws UserInputException, RemoteException{
		assertNotNull(bs.login("admin", "admin"));
	}
	/**
	 * Testing of login Method, when login should fail and Method returns null
	 * @throws UserInputException 
	 * @throws RemoteException 
	 */
	@Test(expected=NullPointerException.class)
	public void testLoginFail() throws RemoteException, UserInputException{
		assertNull(bs.login("test", "123454"));
	}
	
//	/**
//	 * For testing purposes, here is a version of the testcase, which is called correctly with the Reference
//	 * @throws UserInputException
//	 * @throws RemoteException
//	 * 
//	 */
//	@Test
//	public void testLoginCorrectCall() throws UserInputException, RemoteException{
//		assertNotNull(acc.login("admin", "admin"));
//	}
	
	/**
	 * Testing of Create MD5 Method, where the MD5 should match
	 */
	@Test
	public void testCreateMD5(){
		String asd = bs.createMD5("asd");
		String asd1 = bs.createMD5("asd");
		assertEquals(asd,asd1);
	}
}
