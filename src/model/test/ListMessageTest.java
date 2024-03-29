package model.test;

import static org.junit.Assert.*;
import model.ListMessage;

import org.junit.Before;
import org.junit.Test;

/**
 * Testclass for ListMessage
 * 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 * 
 */
public class ListMessageTest {

	private ListMessage lm, lm1;

	@Before
	public void setUp() throws Exception {
		lm = new ListMessage();
		lm1 = new ListMessage(null);
	}
	
	/**
	 * Testing of getName Method in ListMessage
	 */
	@Test
	public void testGetName() {
		lm.setName("asd");
		assertEquals(lm.getName(), "asd");
	}
}
