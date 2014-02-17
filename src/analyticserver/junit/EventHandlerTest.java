package analyticserver.junit;

import static org.junit.Assert.assertEquals;
import model.AuctionEvent;
import model.BidEvent;
import model.EventType;
import model.UserEvent;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;
import analyticserver.EventHandler;

public class EventHandlerTest {
	private AnalyticServer as;
	private EventHandler eh;
	@Before
	public void setUp() {
		as = new AnalyticServer();
		eh = new EventHandler(as);
	}
	
	@Test
	public void calculateTestAuctionStarted(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		assertEquals(eh.execute(a1).size(),1);
	}
	
	@Test
	public void calculateTestAuctionEnded(){
		AuctionEvent a2 = new AuctionEvent("1", EventType.AUCTION_ENDED,   1000010,1);
		assertEquals(eh.execute(a2).size(),1);
	}
	
	@Test
	public void calculateTestBidPlaced(){
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		assertEquals(eh.execute(b1).size(),1);
	}
	
	@Test
	public void calculateTestUserLogin(){
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		assertEquals(eh.execute(u1).size(),1);
	}
	
	@Test
	public void calculateTestUserLogout(){
		UserEvent u2 = new UserEvent("1", EventType.USER_LOGOUT,1000010, "u1");
		assertEquals(eh.execute(u2).size(),1);
	}
	
	@Test
	public void calculateTestUserDisconnected(){
		UserEvent u2 = new UserEvent("1", EventType.USER_LOGOUT,1000010, "u1");
		assertEquals(eh.execute(u2).size(),1);
	}
	
	@Test
	public void calculateTestBidOverbid(){
		BidEvent u2 = new BidEvent("1", EventType.BID_OVERBID,1000010, "b1", 1, 20);
		assertEquals(eh.execute(u2).size(),1);
	}
	
	@Test
	public void calculateTestBidWON(){
		BidEvent u2 = new BidEvent("1", EventType.BID_WON,1000010, "b1", 1, 20);
		assertEquals(eh.execute(u2).size(),1);
	}
}