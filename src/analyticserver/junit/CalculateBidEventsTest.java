package analyticserver.junit;

import static org.junit.Assert.assertEquals;
import model.AuctionEvent;
import model.BidEvent;
import model.EventType;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;
import analyticserver.CalculateAuctionEvents;
import analyticserver.CalculateBidEvents;

public class CalculateBidEventsTest {
	private AnalyticServer as;
	private CalculateBidEvents cbe;
	@Before
	public void setUp() {
		as = new AnalyticServer();
		cbe = new CalculateBidEvents(as);
	}
	
	@Test
	public void calculateTest(){
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		assertEquals(cbe.calculate(b1).size(),1);
	}
}
