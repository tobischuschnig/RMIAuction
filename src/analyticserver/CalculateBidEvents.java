package analyticserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import model.*;

/**
 * Receives events from the system and computes simple statistics/analytics.
 * In this class all Bid Events are calculated
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-02-25
 */
public class CalculateBidEvents implements InterfaceCalculate, Runnable {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	private long startTime;
	//private int cout;
	
	/**
	 * Konstruktor
	 * @param a The calling AnalyticServer
	 */
	public CalculateBidEvents (AnalyticServer a) {
		this.a = a;
		startTime = System.currentTimeMillis();
		//cout = 0;
	}
	
	/**
	 * The Method calculate in this Method all calculation about statistics are done
	 * In this class all Bid Events are calculated except the Bids per Minute these Statistic Events are calculated in calculateBidEventAverageTime
	 * @param event the Event which is new to do new calculations
	 * @return an ArrayList of statistics events which have been created
	 */
	@Override
	public ArrayList<StatisticsEvent> calculate(Event event) {
		if(((BidEvent) event).getPrice()  >= a.getStatisticsEvents().get(EventType.BID_PRICE_MAX).getValue()) {
			StatisticsEvent wert = new StatisticsEvent(UUID.randomUUID().toString(), EventType.BID_PRICE_MAX, System.currentTimeMillis(),
					((BidEvent) event).getPrice());
			
			a.getStatisticsEvents().put(EventType.BID_PRICE_MAX, wert);
			ArrayList<StatisticsEvent> ret = new ArrayList();
			ret.add(wert);
			return ret;
		}
		return null;
	}
	
	/**
	 * A second Method for the calculation Part of Bids per minute
	 * @return An ArrayList of statistic Events
	 */
	public ArrayList<StatisticsEvent> calculateBidEventAverageTime() {
		
		Set<Integer> wert = a.getBidEvents().keySet();
		Iterator<Integer> it = wert.iterator();
		int bids = 0;
		
		while(it.hasNext()) {
			bids += a.getBidEvents().get(it.next()).size();
		}
		//System.out.println(((Math.round(System.currentTimeMillis()-startTime)/60000)));
		double value = (double) (bids / ((double)(Math.round(System.currentTimeMillis()-startTime)/60000)));
		//System.out.println(value);
		
	
		StatisticsEvent wert1 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.BID_COUNT_PER_MINUTE, System.currentTimeMillis(),
				value);

		a.getStatisticsEvents().put(EventType.BID_COUNT_PER_MINUTE, wert1);
		ArrayList<StatisticsEvent> ret = new ArrayList();
		ret.add(wert1);
		return ret;		
	}
	
	/**
	 * The run Method to calculate the Bids per Minute every minute
	 */
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(60000);
				a.notify(this.calculateBidEventAverageTime(),null); 
				//if(cout ==0)AnalyticServerTest.wert(a);
				//cout++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}