package model.test;

import static org.junit.Assert.*;
import model.LogoutMessage;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for LogoutMessage
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class LogoutMessageTest {
	private LogoutMessage loum, loum1;

	@Before
	public void setUp() throws Exception {
		loum = new LogoutMessage();
		loum1 = new LogoutMessage(null);
	}
	
	/**
	 * Testing of getName Method in LogoutMessage
	 */
	@Test
	public void testGetName() {
		loum.setName("asd");
		assertEquals(loum.getName(), "asd");
	}

}
