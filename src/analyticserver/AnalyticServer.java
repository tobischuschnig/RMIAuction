package analyticserver;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.undo.StateEdit;

import managmentclient.ManagementClient;
import model.*;

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
	
	private ConcurrentHashMap<EventType,ArrayList<ManagementClient>> managementClients;
	//String (previously checked with Pattern compile , ArrayList with users with this regex pattern

	
	EventHandler eventHandler;
	
	public AnalyticServer() {
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
	@Override
	public String suscribe(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processEvent(Event event) {
		notify(eventHandler.execute(event));
	} 

	
	//TODO bekomme nur meine uuid und schaue diese in der Map nach und loesche diese dann
	@Override
	public void unsuscribe(String uid) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO An Management Client Schicken
	public void notify(ArrayList<StatisticsEvent> events) {
		if(events != null) {
			for(int i = 0; i < events.size();i++) {
				System.out.println(events.get(i).getType()+"             "+events.get(i).getValue());
			}
		} else {
			System.out.println("null");
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
	public ConcurrentHashMap<EventType, ArrayList<ManagementClient>> getManagementClients() {
		return managementClients;
	}

	/**
	 * @param managementClients the managementClients to set
	 */
	public void setManagementClients(
			ConcurrentHashMap<EventType, ArrayList<ManagementClient>> managementClients) {
		this.managementClients = managementClients;
	}
	
	
	
}
