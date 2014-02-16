package analyticserver.junit;

import static org.junit.Assert.*;

import model.Event;

import org.junit.Before;
import org.junit.Test;


import analyticserver.AnalyticServerInterface;

/**
 * Junit Tests for the Rmi Remote Interface AnalyricsServerInterface.
 * @author Alexander Auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 08/02/2014
 *
 */
public class AnalyticsServerInterfaceTest {

	public  AnalyticServerInterface asi;
	
	/**
	 * Settup which is started for every single test.
	 */
	@Before
	public void setUp() {
		AnalyticServerInterface asi;
	}

	
	/**
	 * Test of calling not initalized Interface.
	 */
	@Test 
	public void testNullInit() {
		assertNull(asi);
	}
	
	/**
	 * Testing null initializeing
	 */
	@Test(expected=NullPointerException.class)
	public void testEqualityObject() {
			asi.getClass();
			assertNull(asi);
	}
	/**
	 * Testing null initializeing with process event
	 */
	@Test(expected=NullPointerException.class)
	public void testProcessEvent() {
		asi.processEvent(new Event(null, null, 0));	
		assertNull(asi);
	}
	/**
	 * Testing null initializeing with unsubscribe
	 */
	@Test(expected=NullPointerException.class)
	public void testInsuscribe() {
		asi.unsuscribe("id");
		assertNull(asi);
	}

}
