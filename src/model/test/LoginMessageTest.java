package model.test;

import static org.junit.Assert.*;
import model.LoginMessage;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for LoginMessage 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class LoginMessageTest {
	private LoginMessage linm, linm1;

	@Before
	public void setUp() throws Exception {
		linm = new LoginMessage();
		linm1 = new LoginMessage(null, null, 0, 0);
	}

	/**
	 * Testing of getName Method in LoginMessage
	 */
	@Test
	public void testGetName() {
		linm.setName("asd");
		assertEquals(linm.getName(), "asd");
	}
	
	/**
	 * Testing of getAdresse Method in LoginMessage
	 */
	@Test
	public void testGetAdresse() {
		linm.setAdresse("asd");
		assertEquals(linm.getAdresse(), "asd");
	}
	
	/**
	 * Testing of getTcpPort Method in LoginMessage
	 */
	@Test
	public void testGetTcpPort() {
		linm.setTcpPort(1000);
		assertEquals(linm.getTcpPort(),1000);
	}
	
	/**
	 * Testing of getUdpPort Method in LoginMessage
	 */
	@Test
	public void testGetUdpPort() {
		linm.setUdpPort(100);
		assertEquals(linm.getUdpPort(), 100);
	}
}
