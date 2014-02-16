package billingServer.junit;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import Exceptions.UserInputException;
import billingServer.BillingServer;

/**
 * TDD Testclass for BillingServer
 * @author Alexander Rieppel
 * @email <arieppel@student.tgm.ac.at>
 */
public class BillingServerTest {
private BillingServer bs;
	
	@Before
	public void setUp(){
		bs = new BillingServer();
	}
	/**
	 * Testing of login Method, when all should do fine and Method returns the 
	 * BillingServerSecure Reference
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
	@Test(expected=UserInputException.class)
	public void testLoginFail() throws RemoteException, UserInputException{
		assertNull(bs.login("test", "123454"));
	}
	
	/**
	 * Testing of FileNotFoundException
	 * @throws RemoteException
	 * @throws UserInputException
	 */
	@Test(expected=FileNotFoundException.class)
	public void testFileNotFound() throws RemoteException, UserInputException{
		assertNull(bs.login("admin", "admin"));
	}
	
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
