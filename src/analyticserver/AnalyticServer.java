package analyticserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import managmentclient.ManagementClientInterface;
import model.AuctionEvent;
import model.BidEvent;
import model.Event;
import model.EventType;
import model.StatisticsEvent;
import model.UserEvent;
import exceptions.InvalidFilterException;

/**
 * Receives events from the system and computes simple statistics/analytics.
 * The calculation are made in Subclasses
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-02-25
 */ 
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
	
	private ConcurrentHashMap<String,ConcurrentHashMap<String,ManagementClientInterface>> managementClients;
	//String (previously checked with Pattern compile , ArrayList with users with this regex pattern
	
	private ArrayList<String> pattern;
	
	private EventHandler eventHandler;
	
	private long guid;
	
	
	/**
	 * Konstruktor initialisises all necessary Objects
	 */
	public AnalyticServer() {
		guid = 0;
		
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
		
		StatisticsEvent wert1 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.USER_SESSIONTIME_MIN, System.currentTimeMillis(), Double.MAX_VALUE);
		StatisticsEvent wert2 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.USER_SESSIONTIME_MAX, System.currentTimeMillis(), 0);
		StatisticsEvent wert3 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.USER_SESSIONTIME_AVG, System.currentTimeMillis(), 0);
		StatisticsEvent wert4 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.BID_PRICE_MAX, System.currentTimeMillis(), 0);
		StatisticsEvent wert5 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.BID_COUNT_PER_MINUTE, System.currentTimeMillis(), 0);
		StatisticsEvent wert6 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.AUCTION_TIME_AVG, System.currentTimeMillis(), 0);
		StatisticsEvent wert7 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.ACUTION_SUCCESS_RATIO, System.currentTimeMillis(), 0);

		statisticsEvents.put(EventType.USER_SESSIONTIME_MIN,wert1); //finished		 //works
		statisticsEvents.put(EventType.USER_SESSIONTIME_MAX,wert2); //finished	     //works
		statisticsEvents.put(EventType.USER_SESSIONTIME_AVG,wert3); //finished	     //works
		statisticsEvents.put(EventType.BID_PRICE_MAX,wert4); // finished             //works
		statisticsEvents.put(EventType.BID_COUNT_PER_MINUTE,wert5); //finished       //works
		statisticsEvents.put(EventType.AUCTION_TIME_AVG,wert6); // finished          //works
		statisticsEvents.put(EventType.ACUTION_SUCCESS_RATIO,wert7); // finished	 //works

	}
	
	/**
	 * Sucribes with a regular expression for events
	 * @param filter The regular Expression
	 * @param managementClientInterface The Interface for the callback
	 * @return rturns the String of the suscribtion
	 * @throws RemoteException throws a Remote exception
	 * @throws PatternSyntaxException When the Pattern is invalid this is thrown
	 * @throws InvalidFilterException When the Pattern is invalid this is thrown 
	 */
	@Override
	public String suscribe(String filter, ManagementClientInterface managementClient) throws PatternSyntaxException, InvalidFilterException {
		if(managementClient == null) System.out.println("Fuck you!");
		
		filter = filter.toUpperCase();
//		System.out.println(filter);
		String uid = ""+this.guid;
		int wert =0;
		for (int i = 0; i < pattern.size();i++) {
			if(Pattern.matches(filter,pattern.get(i))) wert++;
//			System.out.println(Pattern.matches(filter,pattern.get(i)));
		}
		if (wert==0) {
			throw new InvalidFilterException();
		} else {
			if (managementClients.get(filter) == null) {
				ConcurrentHashMap<String, ManagementClientInterface> hilf = new ConcurrentHashMap();
				hilf.put(uid, managementClient);
				managementClients.put(filter, hilf);
			} else {
				managementClients.get(filter).put(uid, managementClient);
			}
		}
		this.guid+=1;
		return uid;
	}
	/**
	 *  Receives events from the system and computes simple statistics/analytics.
	 *  processes the events to Taskexecuter
	 * @param event the processed event
	 * @throws RemoteException throws a Remote exception
	 */
	@Override
	public void processEvent(Event event) {
		notify(null,event);
		notify(eventHandler.execute(event),null);
	} 

	/**
	 *  Receives events from the system and computes simple statistics/analytics.
	 *  processes the events to Taskexecuter
	 * @param event the processed event
	 * @throws RemoteException throws a Remote exception
	 */
	@Override
	public void unsuscribe(String uid) {
		Set<String> wert = managementClients.keySet();
		Iterator<String> it = wert.iterator();
		while(it.hasNext()) { //TODo Exception handling when the client quits without unsuscribe
			try {
				String hilf = it.next();
				managementClients.get(hilf).remove(uid);
//				System.out.println("removed:" +uid);
//				System.out.println(managementClients.get(hilf).size());
			} catch(NullPointerException ex) {//System.out.println("Fehler bei unsubscribe!");}
				
			}
		}
		
	}
	
	/**
	 * Unsuscribe from events
	 * @param uid the id of the cliet who has suscribed
	 * @throws RemoteException throws a Remote exception
	 */
	public void notify(ArrayList<StatisticsEvent> statisticEvent,Event event) {
		if(statisticEvent != null) { 
			for(int i = 0; i < statisticEvent.size();i++) {
				//System.out.println(statisticEvent.get(i).getType()+"             "+statisticEvent.get(i).getValue());
//				System.out.println(statisticEvent.get(i).toString());
			}
		} else {
//			System.out.println("null");
		}
		if(event != null)
//		System.out.println(event.toString()); 
		
		
		if(event != null) { //notify of normal massages 
			Set<String> wert = managementClients.keySet();
			Iterator<String> it = wert.iterator();
			while(it.hasNext()) {
				String hilf = it.next();
				if(Pattern.matches(hilf,event.getType().toString())) {
					Set<String> wert1 = managementClients.get(hilf).keySet();
					Iterator<String> it1 = wert1.iterator();
					while(it1.hasNext()) {
						String hilf1 = it1.next();
						try {
							managementClients.get(hilf).get(hilf1).processEvent(event.toString());
						} catch (RemoteException e) {
							System.err.println("Couldn't callback Client!");
//							e.printStackTrace(); 
							this.unsuscribe(hilf1.toString()); 
						}
					}
				}
			}
		}
		if(statisticEvent != null) { //notify of statistic events
			Set<String> wert = managementClients.keySet();
			Iterator<String> it = wert.iterator();
			while(it.hasNext()) {
				String hilf = it.next();
				for (int i = 0; i < statisticEvent.size();i++) {
					if(Pattern.matches(hilf, statisticEvent.get(i).getType().toString())) { 
						Set<String> wert1 = managementClients.get(hilf).keySet();
						Iterator<String> it1 = wert1.iterator();
						while(it1.hasNext()) {
							String hilf1 = it1.next();
							try {
								for(int iii = 0; iii < statisticEvent.size();iii++) {
									managementClients.get(hilf).get(hilf1).processEvent(statisticEvent.get(iii).toString());
								}
							} catch (RemoteException e) {
								System.err.println("Couldn't callback Client!");
//								e.printStackTrace();
								this.unsuscribe(hilf1.toString()); //TODo Exception Handling isnt working the Client is not unsuscribed
							}
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
	public ConcurrentHashMap<String, ConcurrentHashMap<String, ManagementClientInterface>> getManagementClients() {
		return managementClients;
	}

	/**
	 * @param managementClients the managementClients to set
	 */
	public void setManagementClients(
			ConcurrentHashMap<String, ConcurrentHashMap<String, ManagementClientInterface>> managementClients) {
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
