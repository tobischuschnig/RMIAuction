package analyticserver.junit;

import static org.junit.Assert.*;
import model.AuctionEvent;
import model.BidEvent;
import model.EventType;
import model.UserEvent;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;
import analyticserver.EventHandler;

/**
 * Testclass for EventHandler of AnalyticsServer
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @version 17/02/2014
 */
public class EventHandlerTest {
	private AnalyticServer as;
	private EventHandler eh;
	@Before
	public void setUp() {
		as = new AnalyticServer();
		eh = new EventHandler(as);
	}
	
	/**
	 * Testing of execute Method with Event AUCTION_STARTED
	 */
	@Test
	public void ExecuteTestAuctionStarted(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		assertNull(eh.execute(a1));
	}
	
	/**
	 * Testing of execute Method with Event AUCTION_ENDED after Event AUCTION_STARTED
	 */
	@Test
	public void ExecuteTestAuctionEnded(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		AuctionEvent a2 = new AuctionEvent("1", EventType.AUCTION_ENDED,   1000010,1);
		eh.execute(a1);
		assertEquals(eh.execute(a2).size(),2);
	}
	
	/**
	 * Testing of execute Method with Event BID_PLACED after Event AUCTION_STARTED
	 */
	@Test
	public void ExcuteTestBidPlaced(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		eh.execute(a1);
		assertEquals(eh.execute(b1).size(),1);
	}
	
	/**
	 * Testing of execute Method with Event BID_PLACED two times on same Auction
	 * after Event AUCTION_STARTED
	 */
	@Test
	public void ExcuteTestBidPlacedTwoTimes(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		BidEvent b2 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		eh.execute(a1);
		eh.execute(b1);
		assertEquals(eh.execute(b2).size(),1);
	}
	
	/**
	 * Testing of execute Method with Event USER_LOGIN two times on same User
	 */
	@Test
	public void ExecuteTestUserLogin(){
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		assertNull(eh.execute(u1));
	}
	
	/**
	 * Testing of execute Method with Event USER_LOGIN two times on same User
	 */
	@Test
	public void ExecuteTestUserLoginTwoTimes(){
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		UserEvent u2 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		eh.execute(u1);
		assertNull(eh.execute(u2));
	}
	
	/**
	 * Testing of execute Method with Event USER_LOGOUT after Event USER_LOGIN
	 */
	@Test
	public void ExecuteTestUserLogout(){
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		UserEvent u2 = new UserEvent("1", EventType.USER_LOGOUT,1000010, "u1");
		eh.execute(u1);
		assertEquals(eh.execute(u2).size(),2);
	}
	
	/**
	 * Testing of execute Method with Event USER_LOGOUT two times on same User
	 * after Event USER_LOGIN
	 */
	@Test
	public void ExecuteTestUserLogoutTwoTimes(){
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		UserEvent u2 = new UserEvent("1", EventType.USER_LOGOUT,1000010, "u1");
		UserEvent u3 = new UserEvent("1", EventType.USER_LOGOUT,1000010, "u1");
		eh.execute(u1);
		eh.execute(u2);
		assertEquals(eh.execute(u3).size(),2);
	}
	
	/**
	 * Testing of execute Method with Event USER_DISCONNECTED after Event USER_LOGIN
	 */
	@Test
	public void ExecuteTestUserDisconnected(){
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		UserEvent u2 = new UserEvent("1", EventType.USER_DISCONNECTED,1000010, "u1");
		eh.execute(u1);
		assertEquals(eh.execute(u2).size(),2);
	}
	
	/**
	 * Testing of execute Method with Event BID_OVERBID after Event AUCTION_STARTED and Event
	 * BID_PLACED
	 */
	@Test
	public void ExecuteTestBidOverbid(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		eh.execute(a1);
		eh.execute(b1);
		BidEvent b2 = new BidEvent("1", EventType.BID_OVERBID,1000010, "b2", 1, 20);
		assertEquals(eh.execute(b2).size(),1);
	}
	
	/**
	 * Testing of execute Method with Event BID_OVERBID two times on same Auction after Event AUCTION_STARTED and Event
	 * BID_PLACED
	 */
	@Test
	public void ExecuteTestBidOverbidTwoTimes(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		eh.execute(a1);
		eh.execute(b1);
		BidEvent b2 = new BidEvent("1", EventType.BID_OVERBID,1000010, "b2", 1, 20);
		BidEvent b3 = new BidEvent("1", EventType.BID_OVERBID,1000010, "b2", 1, 20);
		eh.execute(b2);
		assertEquals(eh.execute(b3).size(),1);
	}
	
	/**
	 * Testing of execute Method with Event BID_WON after Event AUCTION_STARTED, EVENT BID_PLACED,
	 * Event BID_OVERBID and Event BID_WON
	 */
	@Test
	public void ExecuteTestBidWON(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		BidEvent b2 = new BidEvent("1", EventType.BID_OVERBID,1000010, "b2", 1, 20);
		eh.execute(a1);
		eh.execute(b1);
		eh.execute(b2);
		BidEvent b3 = new BidEvent("1", EventType.BID_WON,1000010, "b3", 1, 20);
		assertEquals(eh.execute(b3).size(),1);
	}
	
	/**
	 * Testing of execute Method with Event BID_WON two times on same Auction after Event AUCTION_STARTED, EVENT BID_PLACED,
	 * Event BID_OVERBID and Event BID_WON
	 */
	@Test
	public void ExecuteTestBidWONTwoTimes(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		BidEvent b2 = new BidEvent("1", EventType.BID_OVERBID,1000010, "b2", 1, 20);
		eh.execute(a1);
		eh.execute(b1);
		eh.execute(b2);
		BidEvent b3 = new BidEvent("1", EventType.BID_WON,1000010, "b3", 1, 20);
		BidEvent b4 = new BidEvent("1", EventType.BID_WON,1000010, "b3", 1, 20);
		eh.execute(b3);
		assertEquals(eh.execute(b4).size(),1);
	}
}