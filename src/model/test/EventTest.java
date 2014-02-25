package model.test;

import static org.junit.Assert.*;
import model.Event;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for Event
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class EventTest {
	private Event e;

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Testing of getID Method in Event
	 */
	@Test
	public void testGetID() {
		e.setID("asd");
		assertEquals(e.getID(), "asd");
	}
	
	/**
	 * Testing of getType Method in Event
	 */
	@Test
	public void testGetType() {
		e.setType(null);
		assertEquals(e.getType(), null);
	}
	
	/**
	 * Testing of getTimestamp Method in Event
	 */
	@Test
	public void testGetTimestamp() {
		e.setTimestamp(100L);
		assertEquals(e.getTimestamp(), 100L);
	}
}
