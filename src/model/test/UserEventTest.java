package model.test;

import static org.junit.Assert.*;
import model.UserEvent;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for UserEvent
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class UserEventTest {
	private UserEvent ue, ue1;

	@Before
	public void setUp() throws Exception {
		ue = new UserEvent(null, null, 0);
		ue1 = new UserEvent(null, null, 0, null);
	}
	
	/**
	 * Testing of getName Method in UserEvent
	 */
	@Test
	public void testGetName() {
		ue.setUserName("asd");
		assertEquals(ue.getUserName(), "asd");
	}
}
