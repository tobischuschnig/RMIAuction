package analyticserver;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.undo.StateEdit;

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
	private ConcurrentHashMap<String,String> managementClients; //incorect 
	//Important: first String is an ID for the Management Client
	// 	private ConcurrentHashMap<String,String> managementClients; //correct but not implemented now

	
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
		
		
		StatisticsEvent wert = new StatisticsEvent("1", EventType.USER_SESSIONTIME_MIN, System.currentTimeMillis(), 0);
		//TODO weis nicht wie ich die ID's machen soll
		statisticsEvents.put(EventType.USER_SESSIONTIME_MIN,wert); //fertig
		wert.setType(EventType.USER_SESSIONTIME_MAX);
		statisticsEvents.put(EventType.USER_SESSIONTIME_MAX,wert); //fertig
		wert.setType(EventType.USER_SESSIONTIME_AVG);
		statisticsEvents.put(EventType.USER_SESSIONTIME_AVG,wert); //fertig
		wert.setType(EventType.BID_PRICE_MAX);
		statisticsEvents.put(EventType.BID_PRICE_MAX,wert); // fertig
		wert.setType(EventType.BID_COUNT_PER_MINUTE);
		statisticsEvents.put(EventType.BID_COUNT_PER_MINUTE,wert); //fertig
		wert.setType(EventType.AUCTION_TIME_AVG);
		statisticsEvents.put(EventType.AUCTION_TIME_AVG,wert); // fertig
		wert.setType(EventType.ACUTION_SUCCESS_RATIO);
		statisticsEvents.put(EventType.ACUTION_SUCCESS_RATIO,wert); // fertig		
	}
	
	@Override
	public String suscribe(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processEvent(Event event) {
		notify(eventHandler.execute(event));
	} 

	@Override
	public void unsuscribe(String uid) {
		// TODO Auto-generated method stub
		
	}
	
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
	 * @return the managementClients
	 */
	public ConcurrentHashMap<String, String> getManagementClients() {
		return managementClients;
	}

	/**
	 * @param managementClients the managementClients to set
	 */
	public void setManagementClients(
			ConcurrentHashMap<String, String> managementClients) {
		this.managementClients = managementClients;
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
	
}
