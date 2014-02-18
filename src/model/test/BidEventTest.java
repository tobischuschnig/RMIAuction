package model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.AuctionEvent;
import model.BidEvent;


/**
 * Testclass for BidEvent
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class BidEventTest {
	private BidEvent be,be1;
	
	@Before
	public void setUp() {
		be = new BidEvent(null, null, 0);
		be1 = new BidEvent(null, null, 0, null, 0, 0);
	}
	
	/**
	 * Testing of getAuctionID Method in BidEvent
	 */
	@Test
	public void testGetAuctionID() {
		be.setAuctionID(10);
		assertEquals(be.getAuctionID(),10);
	}
	
	/**
	 * Testing of getUsername Method in BidEvent
	 */
	@Test
	public void testGetUsername(){
		be.setUserName("asd");
		assertEquals(be.getUserName(),"asd");
	}
	
	/**
	 * Testing of getPrice Method in BidEvent
	 */
	@Test
	public void testGetPrice(){
		be.setPrice(1.0);
		assertEquals(1.0, be.getPrice(), 0);
	}
}
