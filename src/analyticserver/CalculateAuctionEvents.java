package analyticserver;

import java.util.ArrayList;
import java.util.UUID;

import model.*;

/**
 * Receives events from the system and computes simple statistics/analytics.
 * In this class all Auction Events are calculated
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-02-25
 */
public class CalculateAuctionEvents implements InterfaceCalculate {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	
	/**
	 * Konstruktor
	 * @param a The calling AnalyticServer
	 */
	public CalculateAuctionEvents (AnalyticServer a) {
		this.a = a;
	}
	
	/**
	 * The Method calculate in this Method all calculation about statistics are done
	 * In this Method all Auction Events are calculated
	 * In the first stept the Auction time Average is created and after that the Auction success ratio is calculated
	 * @param event the Event which is new to do new calculations
	 * @return an ArrayList of statistics events which have been created
	 */
	@Override
	public ArrayList<StatisticsEvent> calculate(Event event) {
		//Version with a loop but it is not performant
//		long gesamtzeit;
//		for (int i = 0; i < auctionEventsEnded.size(); i++) {
//			auctionEventsStarted.get(auctionEventsEnded.get(i).getAuctionID());
//			
//		}
		
		ArrayList<StatisticsEvent> ret = new ArrayList();
		
		////////////////////////////////////////////////////////////////////////
		//Auction Time Average
		
		double newtime = a.getAuctionEventsEnded().get(a.getAuctionEventsEnded().size()-1).getTimestamp() -
				a.getAuctionEventsStarted().get( a.getAuctionEventsEnded().get(a.getAuctionEventsEnded().size()-1).getAuctionID() ).getTimestamp();
		//calculates the new time
		
		double value =  ( a.getStatisticsEvents().get(EventType.AUCTION_TIME_AVG).getValue() * (a.getAuctionEventsEnded().size()-1) 
				+ newtime) 
				/ a.getAuctionEventsEnded().size(); 
		//calculating the average time
		// (AlterDurchschnitt * auctionEventsEnded.size()-1 + newtime) : auctionEventsEnded.size()
		// (AtlerDurchschnitt * alterDivident + NeueZeit) : NeuerDivident
		
		StatisticsEvent newEvent = new StatisticsEvent(UUID.randomUUID().toString(),EventType.AUCTION_TIME_AVG, 
				System.currentTimeMillis(),value);
		//Creating a new Event
		
		a.getStatisticsEvents().put(EventType.AUCTION_TIME_AVG, newEvent);
		ret.add(newEvent);
		//Replace bcause of the same key
		
		
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
		StatisticsEvent newEventRadio = new StatisticsEvent(UUID.randomUUID().toString(), EventType.ACUTION_SUCCESS_RATIO,
				System.currentTimeMillis(),newwert);
		
		a.getStatisticsEvents().put(EventType.ACUTION_SUCCESS_RATIO, newEventRadio);
		ret.add(newEventRadio);
		return ret;
	}

}
