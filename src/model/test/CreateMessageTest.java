package model.test;

import static org.junit.Assert.*;
import model.CreateMessage;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for CreateMessage
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class CreateMessageTest {

	private CreateMessage cm, cm1;

	@Before
	public void setUp() {
		cm = new CreateMessage();
		cm1 = new CreateMessage(null, null, (long) 0);
	}

	/**
	 * Testing of getName Method in CreateMessage
	 */
	@Test
	public void testGetName() {
		cm.setName("asd");
		assertEquals(cm.getName(), "asd");
	}

	/**
	 * Testing of getDesc Method in CreateMessage
	 */
	@Test
	public void testGetDesc() {
		cm.setDesc("asd");
		assertEquals(cm.getDesc(), "asd");
	}

	/**
	 * Testing of getDuration Method in CreateMessage
	 */
	@Test
	public void testGetDuration() {
		cm.setDuration((long)1000);
		assertNotNull(cm.getDuration());
	}
}
