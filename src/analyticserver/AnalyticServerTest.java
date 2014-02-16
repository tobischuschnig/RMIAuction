package analyticserver;

import model.AuctionEvent;
import model.BidEvent;
import model.EventType;

public class AnalyticServerTest {
	public static void main(String [] args) {
		AnalyticServer a = new AnalyticServer();
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		AuctionEvent a2 = new AuctionEvent("1", EventType.AUCTION_ENDED,   1000010,1);
		AuctionEvent a3 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,2);
		AuctionEvent a4 = new AuctionEvent("1", EventType.AUCTION_ENDED,   1000020,2);
		AuctionEvent a5 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,3);
		AuctionEvent a6 = new AuctionEvent("1", EventType.AUCTION_ENDED,   1000030,3);
		AuctionEvent a7 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,4);
		AuctionEvent a8 = new AuctionEvent("1", EventType.AUCTION_ENDED,   1000040,4);
		//AuctionTimeAverage = 25
		//AuctionSucessRadio = 50 percent
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1, 1000); //max
		BidEvent b2 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,   10); //min
		BidEvent b3 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1, 100);
		BidEvent b4 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 2, 100);
		BidEvent b5 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 2, 100);
		BidEvent b6 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 2, 100);
		//max = 1000
		//min =   10
		
		a.processEvent(a1);
		a.processEvent(a2);
		a.processEvent(a3);
		a.processEvent(a4);
		a.processEvent(a5);
		a.processEvent(a6);
		a.processEvent(a7);
		a.processEvent(a8);
	}
}
