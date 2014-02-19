package model.test;

import static org.junit.Assert.assertEquals;
import model.AuctionEvent;
import model.EventType;

import org.junit.Before;
import org.junit.Test;

/**
 * Testclass for AuctionEvent 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class AuctionEventTest {

	private AuctionEvent ae, ae1, ae2;

	@Before
	public void setUp() {
		ae = new AuctionEvent(null, null, 0);
		ae1 = new AuctionEvent("asd", EventType.AUCTION_STARTED, 100, 5);
		ae2 = new AuctionEvent("asd", EventType.AUCTION_ENDED, 100, 5);
	}

	/**
	 * Testing of getAuctionID Method in AuctionEvent
	 */
	@Test
	public void testGetAuctionID() {
		ae1.setAuctionID(1);
		assertEquals(ae1.getAuctionID(), 1);
	}
	
	/**
	 * Testing of toString Method in AuctionEvent with AUCTION_STARTED Event
	 */
	@Test
	public void testToStringAS() {
		System.out.println(ae1.toString());
		assertEquals(ae1.toString(), "AUCTION_STARTED: 01.01.1970 01:00:00:00 - an auction with the ID 5 has been created");
	}
	
	/**
	 * Testing of toString Method in AuctionEvent with AUCTION_STARTED Event
	 */
	@Test
	public void testToStringAE() {
		System.out.println(ae2.toString());
		assertEquals(ae2.toString(), "AUCTION_ENDED: 01.01.1970 01:00:00:00 - the auction with the ID 5 has ended");
	}
}
