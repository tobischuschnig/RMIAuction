package billingServer.junit;

import static org.junit.Assert.*;

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
	public void testLogin() throws RemoteException, UserInputException{
		assertNotNull(bs.login("test", "1234"));
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
}
