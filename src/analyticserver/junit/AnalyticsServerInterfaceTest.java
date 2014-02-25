package analyticserver.junit;

import static org.junit.Assert.assertNull;

import java.rmi.RemoteException;

import model.AuctionEvent;

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
	 * @throws RemoteException 
	 */
	@Test(expected=NullPointerException.class)
	public void testProcessEvent() throws RemoteException {
		asi.processEvent(new AuctionEvent(null, null, 0));	
		assertNull(asi);
	}
	/**
	 * Testing null initializeing with unsubscribe
	 * @throws RemoteException 
	 */
	@Test(expected=NullPointerException.class)
	public void testInsuscribe() throws RemoteException {
		asi.unsuscribe("id");
		assertNull(asi);
	}

}
