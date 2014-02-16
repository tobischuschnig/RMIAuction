package analyticserver;

import java.util.ArrayList;

import model.*;

public class CalculateAuctionEvents implements InterfaceCalculate {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	
	public CalculateAuctionEvents (AnalyticServer a) {
		this.a = a;
	}
	
	@Override
	public ArrayList<StatisticsEvent> calculate(Event event) {
		//Ansatz mit schleife immer neu durchrechnen
//		long gesamtzeit;
//		for (int i = 0; i < auctionEventsEnded.size(); i++) {
//			auctionEventsStarted.get(auctionEventsEnded.get(i).getAuctionID());
//			
//		}
		
		ArrayList<StatisticsEvent> ret = new ArrayList();
		
		////////////////////////////////////////////////////////////////////////
		//Auction Time Average
		
		double newtime = a.getAuctionEventsStarted().get( a.getAuctionEventsEnded().get(a.getAuctionEventsEnded().size()-1).getAuctionID() ).getTimestamp()
				- a.getAuctionEventsEnded().get(a.getAuctionEventsEnded().size()-1).getTimestamp();
		//Berechnen der neuen Zeit
		
		double value =  ( a.getStatisticsEvents().get(EventType.AUCTION_TIME_AVG).getValue() * (a.getAuctionEventsEnded().size()-1) 
				+ newtime) 
				/ a.getAuctionEventsEnded().size(); 
		//Berechnen der durchschnitts Zeit:
		// (AlterDurchschnitt * auctionEventsEnded.size()-1 + newtime) : auctionEventsEnded.size()
		// (AtlerDurchschnitt * alterDivident + NeueZeit) : NeuerDivident
		
		StatisticsEvent newEvent = new StatisticsEvent("1",EventType.AUCTION_TIME_AVG, 
				System.currentTimeMillis(),value);
		//TODO Wie mach ich das mit der ID;
		//Neues Event erstellen
		
		a.getStatisticsEvents().put(EventType.AUCTION_TIME_AVG, newEvent);
		ret.add(newEvent);
		//Ersetzten (da gleicher Key)
		
		
		///////////////////////////////////////////////////////////////////////////////
		//Auction Succes Ratio
		double alt =  a.getStatisticsEvents().get(EventType.ACUTION_SUCCESS_RATIO).getValue() * (a.getAuctionEventsEnded().size()-1);
		double newwert;
		if (a.getBidEvents().get(a.getAuctionEventsEnded().get(a.getAuctionEventsEnded().size()-1).getAuctionID()) == null) {
			newwert = (alt + 0) /  (a.getAuctionEventsEnded().size());
		}
		else {
			newwert = (alt + 100) /  (a.getAuctionEventsEnded().size());
		}
		StatisticsEvent newEventRadio = new StatisticsEvent("1", EventType.ACUTION_SUCCESS_RATIO,
				System.currentTimeMillis(),newwert);
		
		a.getStatisticsEvents().put(EventType.ACUTION_SUCCESS_RATIO, newEventRadio);
		ret.add(newEventRadio);
		return ret;
	}

}
