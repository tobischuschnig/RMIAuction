package analyticserver;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import model.*;

public class AnalyticServer implements AnalyticServerInterface{
	
	private ConcurrentHashMap<Integer,AuctionEvent> auctionEventsStarted;
	private ArrayList<AuctionEvent> auctionEventsEnded;
	
	private ConcurrentHashMap<String,ArrayList<UserEvent>> userEventsLogin;
	private ConcurrentHashMap<String,ArrayList<UserEvent>> userEventsLogout;
	
	private ConcurrentHashMap<Integer,ArrayList<BidEvent>> bidEvents;
	
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
		statisticsEvents.put(EventType.AUCTION_TIME_AVG,new StatisticsEvent()); // fertig
		statisticsEvents.put(EventType.ACUTION_SUCCESS_RATIO,new StatisticsEvent()); // fertig		
	}
	
	@Override
	public String suscribe(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processEvent(Event event) {
		// TODO Auto-generated method stub
		if(event instanceof AuctionEvent) { //fertig
			if (event.getType().equals(EventType.AUCTION_STARTED)) {
				auctionEventsStarted.put(((AuctionEvent) event).getAuctionID(), (AuctionEvent) event);
			} else {
				auctionEventsEnded.add((AuctionEvent) event);
				this.claculateAuctionStatistic();
			}
			
		}
		else if(event instanceof UserEvent) {
			String key;
			if (event.getType().equals(EventType.USER_LOGIN)) {
				if (userEventsLogin.get(((UserEvent) event).getUserName()) == null) {
					ArrayList<UserEvent> wert = new ArrayList();
					wert.add((UserEvent) event);
					userEventsLogin.put(((UserEvent) event).getUserName(),wert);
					key = ((UserEvent) event).getUserName();
				} else {
					userEventsLogin.get(((UserEvent) event).getUserName()).add((UserEvent) event);
					key = ((UserEvent) event).getUserName();
				}
			} else {
				if (userEventsLogout.get(((UserEvent) event).getUserName()) == null) {
					ArrayList<UserEvent> wert = new ArrayList();
					wert.add((UserEvent) event);
					userEventsLogout.put(((UserEvent) event).getUserName(),wert);
					key = ((UserEvent) event).getUserName();
				} else {
					userEventsLogout.get(((UserEvent) event).getUserName()).add((UserEvent) event);
					key = ((UserEvent) event).getUserName();
				}
			}
		
			this.claculateUserStatistic(key);

		}else if(event instanceof BidEvent) { //fertig
			if (bidEvents.get(((BidEvent) event).getAuctionID()) == null) {
				ArrayList<BidEvent> wert = new ArrayList();
				wert.add((BidEvent) event);
				bidEvents.put(((BidEvent) event).getAuctionID(), wert);
			}
			else {
				bidEvents.get(((BidEvent) event).getAuctionID()).add((BidEvent) event);
			}
			
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
		
		////////////////////////////////////////////////////////////////////////
		//Auction Time Average
		
		double newtime = auctionEventsStarted.get( auctionEventsEnded.get(auctionEventsEnded.size()-1).getAuctionID() ).getTimestamp()
				- auctionEventsEnded.get(auctionEventsEnded.size()-1).getTimestamp();
		//Berechnen der neuen Zeit
		
		double value =  ( statisticsEvents.get(EventType.AUCTION_TIME_AVG).getValue() * (auctionEventsEnded.size()-1) 
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
		
		
		///////////////////////////////////////////////////////////////////////////////
		//Auction Succes Ratio
		double alt =  statisticsEvents.get(EventType.ACUTION_SUCCESS_RATIO).getValue() * (auctionEventsEnded.size()-1);
		double newwert;
		if (bidEvents.get(auctionEventsEnded.get(auctionEventsEnded.size()-1).getAuctionID()) == null) {
			newwert = (alt + 0) /  (auctionEventsEnded.size());
		}
		else {
			newwert = (alt + 100) /  (auctionEventsEnded.size());
		}
		StatisticsEvent newEventRadio = new StatisticsEvent("1", EventType.ACUTION_SUCCESS_RATIO,
				System.currentTimeMillis(),newwert);
		
		statisticsEvents.put(EventType.ACUTION_SUCCESS_RATIO, newEventRadio);
	}
	
	private void claculateUserStatistic(String key) {
		// TODO Auto-generated method stub
		
	}
	
	private void claculateBidStatistic() {
		// TODO Auto-generated method stub

		
	}
}
