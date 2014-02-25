package analyticserver.junit;

import model.AuctionEvent;
import model.EventType;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;
import analyticserver.CalculateAuctionEvents;

public class CalculateAuctionEventTest {
	private AnalyticServer as;
	private CalculateAuctionEvents cae;
	@Before
	public void setUp() {
		as = new AnalyticServer();
		cae = new CalculateAuctionEvents(as);
	}
	
	@Test
	public void calculateTest(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		cae.calculate(a1);
		//assertEquals(cae.calculate(a1).size(),1);
	}
	
//	@Test
//	public void calculateTest1(){
//		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
//		assertEquals(cae.calculate(b1).size(),1);
//	}
//	
//	@Test
//	public void calculateTest2(){
//		Event e = new Event(null, null, 0);
//		assertEquals(cae.calculate(e).size(),1);
//	}
//	
//	@Test
//	public void calculateTest3(){
//		Event e = new Event(null, null, 0);
//		assertEquals(cae.calculate(e).size(),1);
//	} 
}
