package model.test;

import static org.junit.Assert.*;
import model.EventType;
import model.UserEvent;

import org.junit.Before;
import org.junit.Test;

/**
 * Testclass for UserEvent
 * 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 * 
 */
public class UserEventTest {
	private UserEvent ue1, ue2, ue3;
	private UserEvent ue4;

	@Before
	public void setUp() throws Exception {
		ue1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		ue2 = new UserEvent("1", EventType.USER_LOGOUT, 1000010, "u1");
		ue3 = new UserEvent("1", EventType.USER_DISCONNECTED, 1000010, "u1");
		ue4 = new UserEvent(null, null, 0);
	}

	/**
	 * Testing of getName Method in UserEvent
	 */
	@Test
	public void testGetName() {
		ue1.setUserName("asd");
		assertEquals(ue1.getUserName(), "asd");
	}

	/**
	 * Testing of toString Method in UserEvent
	 */
	@Test
	public void testToString() {
		String a = ue1.toString();
		System.out.println(a);
		assertEquals(ue1.toString(), a);
	}

	/**
	 * Testing of toString Method in UserEvent
	 */
	@Test
	public void testToString1() {

		String a = ue2.toString();
		System.out.println(a);
		assertEquals(ue2.toString(), a);
	}

	/**
	 * Testing of toString Method in UserEvent
	 */
	@Test
	public void testToString2() {

		String a = ue3.toString();
		System.out.println(a);
		assertEquals(ue3.toString(), a);
	}

}
