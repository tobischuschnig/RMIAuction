package analyticserver;

import java.util.concurrent.ConcurrentHashMap;

import model.*;

public class AnalyticServer implements AnalyticServerInterface{
	
	private ConcurrentHashMap<Integer,AuctionEvent> auctionEvents;
	private ConcurrentHashMap<Integer,UserEvent> userEvents;
	private ConcurrentHashMap<Integer,BidEvent> bidEvents;
	private ConcurrentHashMap<Integer,StatisticsEvent> statisticsEvent;
	private ConcurrentHashMap<String,String> managementClients; //incorect 
	//Important: first String is an ID for the Management Client
	// 	private ConcurrentHashMap<String,String> managementClients; //correct but not implemented now

	@Override
	public String suscribe(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processEvent(Event event) {
		// TODO Auto-generated method stub
		if(event instanceof AuctionEvent) {
			auctionEvents.put(event.getID(), event);
			this.claculate();
		}
		else if(event instanceof AuctionEvent) {
			
		}else if(event instanceof AuctionEvent) {
			
		}else if(event instanceof AuctionEvent) {
			
		}
	}

	@Override
	public void unsuscribe(String uid) {
		// TODO Auto-generated method stub
		
	}
	
	public void claculate() {
		
	}
	
}
