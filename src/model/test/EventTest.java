package model.test;

import static org.junit.Assert.*;
import model.Event;
import model.EventType;
import model.StatisticsEvent;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for Event
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class EventTest {
	private StatisticsEvent e;

	@Before
	public void setUp() throws Exception {
		e=new StatisticsEvent("asd",EventType.AUCTION_TIME_AVG,12310293);
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
