package analyticserver;

import java.util.ArrayList;
import java.util.Enumeration;
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
		statisticsEvents.put(EventType.AUCTION_TIME_AVG,new StatisticsEvent());
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
			if (event.getType().equals(EventType.AUCTION_STARTED)) {
				auctionEvents.put((int) ((AuctionEvent) event).getAuctionID(), (AuctionEvent) event);
			} else {
				auctionEvents.put((int) -((AuctionEvent) event).getAuctionID(), (AuctionEvent) event);

			}
			
			this.claculateAuctionStatistic((int) ((AuctionEvent) event).getAuctionID());
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
	

	private void claculateAuctionStatistic(Integer newkey) {
		Enumeration<Integer> keys = auctionEvents.keys();
		while(keys.hasMoreElements()) {
			int current = keys.nextElement();
			auctionEvents.get(keys.nextElement());
		}
		Double past = statisticsEvents.get(EventType.AUCTION_TIME_AVG).getValue();
		past *= auctionEvents.size()/2;
	}
	
	private void claculateUserStatistic() {
		// TODO Auto-generated method stub
		
	}
	
	private void claculateBidStatistic() {
		
		
	}
}
