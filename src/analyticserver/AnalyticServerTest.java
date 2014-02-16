package analyticserver;

import model.AuctionEvent;
import model.BidEvent;
import model.EventType;
import model.UserEvent;

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
		BidEvent b1 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10); //max
		BidEvent b2 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1, 100); 
		BidEvent b3 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1, 1000);//max
		BidEvent b4 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 2, 100);
		BidEvent b5 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 2, 100);
		BidEvent b6 = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 2, 100);
		//max = 1000
		//min =   10
		//Bid Count Per Minute: Minute 1: 6 Minute 2: 3 Minute 3 : 2 Minute 4: 1,5 ......
		
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		UserEvent u2 = new UserEvent("1", EventType.USER_LOGOUT,1000010, "u1");
		UserEvent u3 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u2");
		UserEvent u4 = new UserEvent("1", EventType.USER_LOGOUT,1000020, "u2");
		UserEvent u5 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u3");
		UserEvent u6 = new UserEvent("1", EventType.USER_LOGOUT,1000030, "u3");
		UserEvent u7 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u4");
		UserEvent u8 = new UserEvent("1", EventType.USER_LOGOUT,1000040, "u4");
		
		UserEvent u9 = new UserEvent("1", EventType.USER_LOGIN,  1000000, "u1");
		UserEvent u10 = new UserEvent("1", EventType.USER_LOGOUT,1000010, "u1");
		UserEvent u11 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u2");
		UserEvent u12 = new UserEvent("1", EventType.USER_LOGOUT,1000020, "u2");
		UserEvent u13 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u3");
		UserEvent u14 = new UserEvent("1", EventType.USER_LOGOUT,1000030, "u3");
		UserEvent u15 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u4");
		UserEvent u16 = new UserEvent("1", EventType.USER_LOGOUT,1000040, "u4");
		//UserTimeMin: 10
		//UserTimeMax: 40
		//UserTimeAvg: 25
		
		a.processEvent(a1);
		
		
		System.out.println("\n\n");
		a.processEvent(b1);
		a.processEvent(b2);
		a.processEvent(b3);
		System.out.println("\n\n");

		
		a.processEvent(a2);
		
		System.out.println("\n\n");
		a.processEvent(b4);
		a.processEvent(b5);
		a.processEvent(b6);
		System.out.println("\n\n");
		
		a.processEvent(a3);
		a.processEvent(a4);
		a.processEvent(a5);
		a.processEvent(a6);
		a.processEvent(a7);
		a.processEvent(a8);
	
		System.out.println("--------------------------------------");
		
		a.processEvent(u1);
		a.processEvent(u2);
		a.processEvent(u3);
		a.processEvent(u4);
		a.processEvent(u5);
		a.processEvent(u6);
		a.processEvent(u7);
		a.processEvent(u8);
		a.processEvent(u9);
		a.processEvent(u10);
		a.processEvent(u11);
		a.processEvent(u12);
		a.processEvent(u13);
		a.processEvent(u14);
		a.processEvent(u15);
		a.processEvent(u16);

		System.out.println("--------------------------------------");
		
	}
}
