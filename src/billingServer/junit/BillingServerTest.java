package billingServer.junit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
	 */
	@Test
	public void testLogin(){
		assertNotNull(bs.login("test", "1234"));
	}
	/**
	 * Testing of login Method, when login should fail and Method returns null
	 */
	@Test
	public void testLoginFail(){
		assertNull(bs.login("test", "123454"));
	}
}
