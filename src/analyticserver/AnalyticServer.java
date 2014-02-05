package analyticserver;

import java.util.concurrent.ConcurrentHashMap;

import model.*;

public class AnalyticServer implements AnalyticServerInterface{
	
	private ConcurrentHashMap<Integer,AuctionEvent> auctionEvents;
	private ConcurrentHashMap<Integer,UserEvent> userEvents;
	private ConcurrentHashMap<Integer,BidEvent> bidEvents;
	private ConcurrentHashMap<EventType,StatisticsEvent> statisticsEvents;
	private ConcurrentHashMap<String,String> managementClients; //incorect 
	//Important: first String is an ID for the Management Client
	// 	private ConcurrentHashMap<String,String> managementClients; //correct but not implemented now

	public AnalyticServer() {
		auctionEvents = new ConcurrentHashMap();
		userEvents = new ConcurrentHashMap();
		bidEvents = new ConcurrentHashMap();
		statisticsEvents = new ConcurrentHashMap();
		statisticsEvents.put(EventType.USER_SESSIONTIME_MIN,new StatisticsEvent());
		statisticsEvents.put(EventType.USER_SESSIONTIME_MAX,new StatisticsEvent());
		statisticsEvents.put(EventType.USER_SESSIONTIME_AVG,new StatisticsEvent());
		statisticsEvents.put(EventType.BID_PRICE_MAX,new StatisticsEvent());
		statisticsEvents.put(EventType.BID_COUNT_PER_MINUTE,new StatisticsEvent());
		statisticsEvents.put(EventType.AUCTION_TYPE_AVG,new StatisticsEvent());
		statisticsEvents.put(EventType.ACUTION_SUCCESS_RATIO,new StatisticsEvent());		
	}
	
	@Override
	public String suscribe(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processEvent(Event event) {
		// TODO Auto-generated method stub
		if(event instanceof AuctionEvent) {
			auctionEvents.put(auctionEvents.size(), (AuctionEvent) event);
			this.claculateAuctionStatistic();
		}
		else if(event instanceof UserEvent) {
			userEvents.put(userEvents.size(), (UserEvent) event);
			this.claculateUserStatistic();
		}else if(event instanceof BidEvent) {
			bidEvents.put(bidEvents.size(), (BidEvent) event);
			this.claculateBidStatistic();
		}
	}

	@Override
	public void unsuscribe(String uid) {
		// TODO Auto-generated method stub
		
	}
	
	public void claculate() {
		
	}
	

	private void claculateAuctionStatistic() {
		// TODO Auto-generated method stub
		auctionEvents.
	}
	
	private void claculateUserStatistic() {
		// TODO Auto-generated method stub
		
	}
	
	private void claculateBidStatistic() {
		
		
	}
}
