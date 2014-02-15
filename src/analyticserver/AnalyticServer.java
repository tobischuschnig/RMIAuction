package analyticserver;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import model.*;

public class AnalyticServer implements AnalyticServerInterface{
	
	private ConcurrentHashMap<Long,AuctionEvent> auctionEventsStarted;
	private ArrayList<AuctionEvent> auctionEventsEnded;
	
	private ConcurrentHashMap<Integer,UserEvent> userEvents;
	private ConcurrentHashMap<Integer,BidEvent> bidEvents;
	private ConcurrentHashMap<EventType,StatisticsEvent> statisticsEvents;
	private ConcurrentHashMap<String,String> managementClients; //incorect 
	//Important: first String is an ID for the Management Client
	// 	private ConcurrentHashMap<String,String> managementClients; //correct but not implemented now

	public AnalyticServer() {
		auctionEventsStarted = new ConcurrentHashMap();
		auctionEventsEnded = new ArrayList();
		
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
				auctionEventsStarted.put(((AuctionEvent) event).getAuctionID(), (AuctionEvent) event);
			} else {
				auctionEventsEnded.add((AuctionEvent) event);
				this.claculateAuctionStatistic();
			}
			
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
		//Ansatz mit schleife immer neu durchrechnen
//		long gesamtzeit;
//		for (int i = 0; i < auctionEventsEnded.size(); i++) {
//			auctionEventsStarted.get(auctionEventsEnded.get(i).getAuctionID());
//			
//		}
		long newtime = auctionEventsStarted.get( auctionEventsEnded.get(auctionEventsEnded.size()-1).getAuctionID() ).getTimestamp()
				- auctionEventsEnded.get(auctionEventsEnded.size()-1).getTimestamp();
		//Berechnen der neuen Zeit
		
		long value = (long) ( statisticsEvents.get(EventType.AUCTION_TIME_AVG).getValue() * (auctionEventsEnded.size()-1) 
				+ newtime) 
				/ auctionEventsEnded.size(); 
		//Berechnen der durchschnitts Zeit:
		// (AlterDurchschnitt * auctionEventsEnded.size()-1 + newtime) : auctionEventsEnded.size()
		// (AtlerDurchschnitt * alterDivident + NeueZeit) : NeuerDivident
		
		StatisticsEvent newEvent = new StatisticsEvent("1",EventType.AUCTION_TIME_AVG, 
				System.currentTimeMillis(),value);
		//TODO Wie mach ich das mit der ID;
		//Neues Event erstellen
		
		statisticsEvents.put(EventType.AUCTION_TIME_AVG, newEvent);
		//Ersetzten (da gleicher Key)
	}
	
	private void claculateUserStatistic() {
		// TODO Auto-generated method stub
		
	}
	
	private void claculateBidStatistic() {
		
		
	}
}
