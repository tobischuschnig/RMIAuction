package model.test;

import static org.junit.Assert.assertEquals;
import model.AuctionEvent;

import org.junit.Before;
import org.junit.Test;

public class AuctionEventTest {

	private AuctionEvent ae, ae1;

	@Before
	public void setUp() {
		ae = new AuctionEvent(null, null, 0);
		ae1 = new AuctionEvent(null, null, 0, 0);
	}

	/**
	 * Testing of getAuctionID Method in AuctionEvent
	 */
	@Test
	public void testGetAuctionID() {
		ae1.setAuctionID(1);
		assertEquals(ae1.getAuctionID(), 1);
	}
}
