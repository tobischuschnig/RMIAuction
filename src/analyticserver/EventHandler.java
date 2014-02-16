package analyticserver;

import java.util.ArrayList;

import model.*;

public class EventHandler {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	private CalculateAuctionEvents calculateAuctionEvents; 
	private CalculateUserEvents calculateUserEvents;
	private CalculateBidEvents calculateBidEvents;
	
	
	public EventHandler(AnalyticServer a) {
		this.a = a;
		calculateAuctionEvents = new CalculateAuctionEvents(a);
		calculateBidEvents = new CalculateBidEvents(a);
		calculateUserEvents = new CalculateUserEvents(a);
	}
	
	public StatisticsEvent execute(Event event) {
		if(event instanceof AuctionEvent) { //fertig
			if (event.getType().equals(EventType.AUCTION_STARTED)) {
				a.getAuctionEventsStarted().put(((AuctionEvent) event).getAuctionID(), (AuctionEvent) event);
			} else {
				a.getAuctionEventsEnded().add((AuctionEvent) event);
				return calculateAuctionEvents.calculate(event);
			}
			
		}
		else if(event instanceof UserEvent) {
			if (event.getType().equals(EventType.USER_LOGIN)) {
				if (a.getUserEventsLogin().get(((UserEvent) event).getUserName()) == null) {
					ArrayList<UserEvent> wert = new ArrayList();
					wert.add((UserEvent) event);
					a.getUserEventsLogin().put(((UserEvent) event).getUserName(),wert);
				} else {
					a.getUserEventsLogin().get(((UserEvent) event).getUserName()).add((UserEvent) event);
				}
			} else {
				if (a.getUserEventsLogout().get(((UserEvent) event).getUserName()) == null) {
					ArrayList<UserEvent> wert = new ArrayList();
					wert.add((UserEvent) event);
					a.getUserEventsLogout().put(((UserEvent) event).getUserName(),wert);
					return calculateUserEvents.calculate(event);
				} else {
					a.getUserEventsLogout().get(((UserEvent) event).getUserName()).add((UserEvent) event);
					return calculateUserEvents.calculate(event);
				}
			}
		
		}else if(event instanceof BidEvent) { //fertig
			if(event.getType().equals(EventType.BID_PLACED)) {
				if (a.getBidEvents().get(((BidEvent) event).getAuctionID()) == null) {
					ArrayList<BidEvent> wert = new ArrayList();
					wert.add((BidEvent) event);
					a.getBidEvents().put(((BidEvent) event).getAuctionID(), wert);
					return calculateBidEvents.calculate(event);
				}
				else {
					a.getBidEvents().get(((BidEvent) event).getAuctionID()).add((BidEvent) event);
					return calculateBidEvents.calculate(event);
				}
				
			} else if(event.getType().equals(EventType.BID_OVERBID)) {
				if (a.getBidEventsOverbid().get(((BidEvent) event).getAuctionID()) == null) {
					ArrayList<BidEvent> wert = new ArrayList();
					wert.add((BidEvent) event);
					a.getBidEventsOverbid().put(((BidEvent) event).getAuctionID(), wert);
					return calculateBidEvents.calculate(event);
				}
				else {
					a.getBidEventsOverbid().get(((BidEvent) event).getAuctionID()).add((BidEvent) event);
					return calculateBidEvents.calculate(event);
				}
			
			} else { //BidWON
				if (a.getBidEventsWon().get(((BidEvent) event).getAuctionID()) == null) {
					ArrayList<BidEvent> wert = new ArrayList();
					wert.add((BidEvent) event);
					a.getBidEventsWon().put(((BidEvent) event).getAuctionID(), wert);
					return calculateBidEvents.calculate(event);
				}
				else {
					a.getBidEventsWon().get(((BidEvent) event).getAuctionID()).add((BidEvent) event);
					return calculateBidEvents.calculate(event);
				}
			}
		}
		return null;
	}
}
