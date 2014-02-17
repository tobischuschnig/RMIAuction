package model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.AuctionEvent;
import model.BidEvent;

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
	public void testGetAuctionIDBidEvent() {
		be.setAuctionID(1);
		assertEquals(be1.getAuctionID(), 1);
	}
	
	/**
	 * Testing of getUsername Method in BidEvent
	 */
	@Test
	public void testGetUsernameBidEvent(){
		be.setUserName("asd");
		assertEquals(be.getUserName(),"asd");
	}
	
	/**
	 * Testing of getPrice Method in BidEvent
	 */
	@Test
	public void testGetPriceBidEvent(){
		be.setPrice(1.0);
		assertEquals(1.0, be.getPrice(), 0);
	}
}
