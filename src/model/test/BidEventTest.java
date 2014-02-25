package model.test;

import static org.junit.Assert.assertEquals;
import model.BidEvent;
import model.EventType;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for BidEvent
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class BidEventTest {
	private BidEvent be1,be2,be3;
	private BidEvent be4;
	
	@Before
	public void setUp() {
		be1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		be2 = new BidEvent("1", EventType.BID_OVERBID,1000001 , "b1", 1,  10);
		be3 = new BidEvent("1", EventType.BID_WON,1000001 , "b1", 1,  10);
		be4 = new BidEvent(null, null, 0);
	}
	
	/**
	 * Testing of getAuctionID Method in BidEvent
	 */
	@Test
	public void testGetAuctionID() {
		be1.setAuctionID(10);
		assertEquals(be1.getAuctionID(),10);
	}
	
	/**
	 * Testing of getUsername Method in BidEvent
	 */
	@Test
	public void testGetUsername(){
		be1.setUserName("asd");
		assertEquals(be1.getUserName(),"asd");
	}
	
	/**
	 * Testing of getPrice Method in BidEvent
	 */
	@Test
	public void testGetPrice(){
		be1.setPrice(1.0);
		assertEquals(1.0, be1.getPrice(), 0);
	}
	
	/**
	 * Testing of toString Method in BidEvent
	 */
	@Test
	public void testToString() {
		String a = be1.toString();
		System.out.println(a);
		assertEquals(be1.toString(), a);
	}
	
	/**
	 * Testing of toString Method in BidEvent
	 */
	@Test
	public void testToString1() {
		String a = be2.toString();
		System.out.println(a);
		assertEquals(be2.toString(), a);
	}
	
	/**
	 * Testing of toString Method in BidEvent
	 */
	@Test
	public void testToString2() {
		String a = be3.toString();
		System.out.println(a);
		assertEquals(be3.toString(), a);
	}
}
