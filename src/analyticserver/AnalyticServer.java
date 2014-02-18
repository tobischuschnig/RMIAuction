package analyticserver;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.undo.StateEdit;

import managmentclient.ManagementClient;
import model.*;
import Exceptions.InvalidFilterException;

public class AnalyticServer implements AnalyticServerInterface{
	
	private ConcurrentHashMap<Integer,AuctionEvent> auctionEventsStarted;
	private ArrayList<AuctionEvent> auctionEventsEnded;
	
	private ConcurrentHashMap<String,ArrayList<UserEvent>> userEventsLogin;
	private ConcurrentHashMap<String,ArrayList<UserEvent>> userEventsLogout;
	
	private ConcurrentHashMap<Integer,ArrayList<BidEvent>> bidEvents;
	private ConcurrentHashMap<Integer,ArrayList<BidEvent>> bidEventsOverbid; //Type = BidOverBid or BidWon
	private ConcurrentHashMap<Integer,ArrayList<BidEvent>> bidEventsWon;
	//Because every bid has and BidPlaced 
	//BidOverBid: Generated when there was an previous Bid so there are two generated
	//BidWon: Is generated when the bid has won so there are two generated (or three)
	
	private ConcurrentHashMap<EventType,StatisticsEvent> statisticsEvents;
	
	private ConcurrentHashMap<String,ConcurrentHashMap<UUID,ManagementClient>> managementClients;
	//String (previously checked with Pattern compile , ArrayList with users with this regex pattern
	
	private ArrayList<String> pattern;
	
	private EventHandler eventHandler;
	
	public AnalyticServer() {
		pattern= new ArrayList();
		pattern.add(EventType.USER_SESSIONTIME_MIN.toString());
		pattern.add(EventType.USER_SESSIONTIME_MAX.toString());
		pattern.add(EventType.USER_SESSIONTIME_AVG.toString());
		pattern.add(EventType.BID_PRICE_MAX.toString());
		pattern.add(EventType.BID_COUNT_PER_MINUTE.toString());
		pattern.add(EventType.AUCTION_TIME_AVG.toString());
		pattern.add(EventType.ACUTION_SUCCESS_RATIO.toString());
		
		pattern.add(EventType.AUCTION_STARTED.toString());
		pattern.add(EventType.AUCTION_ENDED.toString());
		
		pattern.add(EventType.USER_LOGIN.toString());
		pattern.add(EventType.USER_LOGOUT.toString());
		pattern.add(EventType.USER_DISCONNECTED.toString());
		
		pattern.add(EventType.BID_PLACED.toString());
		pattern.add(EventType.BID_OVERBID.toString());
		pattern.add(EventType.BID_WON.toString());
		
		eventHandler = new EventHandler(this);
		
		auctionEventsStarted = new ConcurrentHashMap();
		auctionEventsEnded = new ArrayList();
		
		userEventsLogin = new ConcurrentHashMap();
		userEventsLogout = new ConcurrentHashMap();
		
		bidEvents = new ConcurrentHashMap();
		bidEventsOverbid = new ConcurrentHashMap();
		bidEventsWon = new ConcurrentHashMap();
		
		statisticsEvents = new ConcurrentHashMap();
		
		managementClients = new ConcurrentHashMap();
		
		StatisticsEvent wert1 = new StatisticsEvent("1", EventType.USER_SESSIONTIME_MIN, System.currentTimeMillis(), Double.MAX_VALUE);
		StatisticsEvent wert2 = new StatisticsEvent("1", EventType.USER_SESSIONTIME_MAX, System.currentTimeMillis(), 0);
		StatisticsEvent wert3 = new StatisticsEvent("1", EventType.USER_SESSIONTIME_AVG, System.currentTimeMillis(), 0);
		StatisticsEvent wert4 = new StatisticsEvent("1", EventType.BID_PRICE_MAX, System.currentTimeMillis(), 0);
		StatisticsEvent wert5 = new StatisticsEvent("1", EventType.BID_COUNT_PER_MINUTE, System.currentTimeMillis(), 0);
		StatisticsEvent wert6 = new StatisticsEvent("1", EventType.AUCTION_TIME_AVG, System.currentTimeMillis(), 0);
		StatisticsEvent wert7 = new StatisticsEvent("1", EventType.ACUTION_SUCCESS_RATIO, System.currentTimeMillis(), 0);

		
		
		//TODO weis nicht wie ich die ID's machen soll
		statisticsEvents.put(EventType.USER_SESSIONTIME_MIN,wert1); //fertig		 //funkt
		statisticsEvents.put(EventType.USER_SESSIONTIME_MAX,wert2); //fertig	     //funkt
		statisticsEvents.put(EventType.USER_SESSIONTIME_AVG,wert3); //fertig	     //funkt
		statisticsEvents.put(EventType.BID_PRICE_MAX,wert4); // fertig               //funkt
		statisticsEvents.put(EventType.BID_COUNT_PER_MINUTE,wert5); //fertig         //funkt
		statisticsEvents.put(EventType.AUCTION_TIME_AVG,wert6); // fertig            //funkt
		statisticsEvents.put(EventType.ACUTION_SUCCESS_RATIO,wert7); // fertig		 //funkt

	}
	
	//TODO The method returns a unique subscription identifier string. (ANGABE)
	//TODO Unique ID maybe uuid? (Daniel)
	//TODO dieser dann als identifier fuer Map?
	//TODO Braucht Objekt reference
	//TODO aentweder in ManagementClient auf Regex pruefen oder hier mit Exception
	//TODO Map mit zwei Keys??
	
	//TODO hier eigene Exception werfen wenn Pattern nicht stimmt
	@Override
	public String suscribe(String filter, ManagementClient managementClient) throws PatternSyntaxException, InvalidFilterException {
		UUID uuid = UUID.randomUUID();
		int wert =0;
		for (int i = 0; i < pattern.size();i++) {
			if(Pattern.matches(filter,pattern.get(i))) wert++;
		}
		if (wert==0) {
			throw new InvalidFilterException();
		} else {
			if (managementClients.get(filter) == null) {
				ConcurrentHashMap<UUID, ManagementClient> hilf = new ConcurrentHashMap();
				hilf.put(uuid, managementClient);
				managementClients.put(filter, hilf);
			} else {
				managementClients.get(filter).put(uuid, managementClient);
			}
		}
		return uuid.toString();
	}

	@Override
	public void processEvent(Event event) {
		notify(eventHandler.execute(event),event);
	} 

	
	//TODO bekomme nur meine uuid und schaue diese in der Map nach und loesche diese dann
	@Override
	public void unsuscribe(String uid) {
		Set<String> wert = managementClients.keySet();
		Iterator<String> it = wert.iterator();
		while(it.hasNext()) {
			try {
				managementClients.get(it.next()).remove(uid);
			} catch(NullPointerException ex) {}
		}
		
	}
	
	//TODO An Management Client Schicken
	public void notify(ArrayList<StatisticsEvent> statisticEvent,Event event) {
		//TODO es muessen alle events ausgebbar sein
//		if(events != null) {
//			for(int i = 0; i < events.size();i++) {
//				System.out.println(events.get(i).getType()+"             "+events.get(i).getValue());
//			}
//		} else {
//			System.out.println("null");
//		}
		if(event != null) {
			Set<String> wert = managementClients.keySet();
			Iterator<String> it = wert.iterator();
			while(it.hasNext()) {
				String hilf = it.next();
				if(Pattern.matches(hilf,event.getType().toString())) {
					Set<UUID> wert1 = managementClients.get(hilf).keySet();
					Iterator<UUID> it1 = wert1.iterator();
					while(it1.hasNext()) {
						managementClients.get(hilf).get(it1.next()).processEvent(event.toString());
					}
				}
			}
		}
		if(statisticEvent != null) {
			Set<String> wert = managementClients.keySet();
			Iterator<String> it = wert.iterator();
			while(it.hasNext()) {
				String hilf = it.next();
				for (int i = 0; i < statisticEvent.size();i++) {
					if(Pattern.matches(hilf, statisticEvent.get(i).getType().toString())) { //Weil arrayList
						Set<UUID> wert1 = managementClients.get(hilf).keySet();
						Iterator<UUID> it1 = wert1.iterator();
						while(it1.hasNext()) {
							managementClients.get(hilf).get(it1.next()).processEvent(statisticEvent.toString());
						}
					}
				}
			}
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////	
	//Getters and Setters
	/**
	 * @return the auctionEventsStarted
	 */
	public ConcurrentHashMap<Integer, AuctionEvent> getAuctionEventsStarted() {
		return auctionEventsStarted;
	}

	/**
	 * @param auctionEventsStarted the auctionEventsStarted to set
	 */
	public void setAuctionEventsStarted(
			ConcurrentHashMap<Integer, AuctionEvent> auctionEventsStarted) {
		this.auctionEventsStarted = auctionEventsStarted;
	}

	/**
	 * @return the auctionEventsEnded
	 */
	public ArrayList<AuctionEvent> getAuctionEventsEnded() {
		return auctionEventsEnded;
	}

	/**
	 * @param auctionEventsEnded the auctionEventsEnded to set
	 */
	public void setAuctionEventsEnded(ArrayList<AuctionEvent> auctionEventsEnded) {
		this.auctionEventsEnded = auctionEventsEnded;
	}

	/**
	 * @return the userEventsLogin
	 */
	public ConcurrentHashMap<String, ArrayList<UserEvent>> getUserEventsLogin() {
		return userEventsLogin;
	}

	/**
	 * @param userEventsLogin the userEventsLogin to set
	 */
	public void setUserEventsLogin(
			ConcurrentHashMap<String, ArrayList<UserEvent>> userEventsLogin) {
		this.userEventsLogin = userEventsLogin;
	}

	/**
	 * @return the userEventsLogout
	 */
	public ConcurrentHashMap<String, ArrayList<UserEvent>> getUserEventsLogout() {
		return userEventsLogout;
	}

	/**
	 * @param userEventsLogout the userEventsLogout to set
	 */
	public void setUserEventsLogout(
			ConcurrentHashMap<String, ArrayList<UserEvent>> userEventsLogout) {
		this.userEventsLogout = userEventsLogout;
	}

	/**
	 * @return the bidEvents
	 */
	public ConcurrentHashMap<Integer, ArrayList<BidEvent>> getBidEvents() {
		return bidEvents;
	}

	/**
	 * @param bidEvents the bidEvents to set
	 */
	public void setBidEvents(
			ConcurrentHashMap<Integer, ArrayList<BidEvent>> bidEvents) {
		this.bidEvents = bidEvents;
	}

	/**
	 * @return the statisticsEvents
	 */
	public ConcurrentHashMap<EventType, StatisticsEvent> getStatisticsEvents() {
		return statisticsEvents;
	}

	/**
	 * @param statisticsEvents the statisticsEvents to set
	 */
	public void setStatisticsEvents(
			ConcurrentHashMap<EventType, StatisticsEvent> statisticsEvents) {
		this.statisticsEvents = statisticsEvents;
	}

	/**
	 * @return the bidEventsOverbid
	 */
	public ConcurrentHashMap<Integer, ArrayList<BidEvent>> getBidEventsOverbid() {
		return bidEventsOverbid;
	}

	/**
	 * @param bidEventsOverbid the bidEventsOverbid to set
	 */
	public void setBidEventsOverbid(
			ConcurrentHashMap<Integer, ArrayList<BidEvent>> bidEventsOverbid) {
		this.bidEventsOverbid = bidEventsOverbid;
	}

	/**
	 * @return the bidEventsWon
	 */
	public ConcurrentHashMap<Integer, ArrayList<BidEvent>> getBidEventsWon() {
		return bidEventsWon;
	}

	/**
	 * @param bidEventsWon the bidEventsWon to set
	 */
	public void setBidEventsWon(
			ConcurrentHashMap<Integer, ArrayList<BidEvent>> bidEventsWon) {
		this.bidEventsWon = bidEventsWon;
	}

	/**
	 * @return the managementClients
	 */
	public ConcurrentHashMap<String, ConcurrentHashMap<UUID, ManagementClient>> getManagementClients() {
		return managementClients;
	}

	/**
	 * @param managementClients the managementClients to set
	 */
	public void setManagementClients(
			ConcurrentHashMap<String, ConcurrentHashMap<UUID, ManagementClient>> managementClients) {
		this.managementClients = managementClients;
	}

	/**
	 * @return the pattern
	 */
	public ArrayList<String> getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(ArrayList<String> pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the eventHandler
	 */
	public EventHandler getEventHandler() {
		return eventHandler;
	}

	/**
	 * @param eventHandler the eventHandler to set
	 */
	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
	
}
