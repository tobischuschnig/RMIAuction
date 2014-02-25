package client.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.Client;


/**
 * Testclass for Client
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 *
 */
public class ClientTest {

	private Client c;
	@Before
	public void setUp() throws Exception {
		c = new Client(null, 0);
	}

	/**
	 * Tests the TestingOutPut in Client
	 */
	@Test
	public void testGetTestingoutput() {
		c.setTestingoutput("asd");
		
		c.setLoggedIn(true);
		c.getCli();
		c.getTcpPort();
		c.isActive();
		assertEquals(c.getTestingoutput(),"asd");
	}
	
	
	/**
	 * Tests the Username in Client
	 */
	@Test
	public void testGetUsername() {
		c.setUsername("asd");
		assertEquals(c.getUsername(),"asd");
	}
}
