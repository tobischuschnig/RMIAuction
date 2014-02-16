package analyticserver;

import java.util.Iterator;
import java.util.Set;

import model.*;


public class CalculateBidEvents implements InterfaceCalculate, Runnable {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	private long startTime;
	
	public CalculateBidEvents (AnalyticServer a) {
		this.a = a;
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public StatisticsEvent calculate(Event event) {
		if(((BidEvent) event).getPrice()  >= a.getStatisticsEvents().get(EventType.BID_PRICE_MAX).getValue()) {
			StatisticsEvent wert = new StatisticsEvent("1", EventType.BID_PRICE_MAX, System.currentTimeMillis(),
					((BidEvent) event).getPrice());
			//TODO weis nicht wie ich die ID's machen soll
			
			a.getStatisticsEvents().put(EventType.BID_PRICE_MAX, wert);
			return wert;
		}
		return null;
	}
	
	private StatisticsEvent calculateBidEventAverageTime() {
		// einfach nur schauen groesse jeder arraylist zu einer Variable hinzufuegen
		// Differenz! in Minuten am besten
		// Bids / Minuten = Bid Count per Minute
		//Ausnahme wenn minute null 
		//Bid Time Average berechnen
		Set<Integer> wert = a.getBidEvents().keySet();
		Iterator<Integer> it = wert.iterator();
		int bids = 0;
		while(it.hasNext()) {
			bids += a.getBidEvents().get(it.next()).size();
		}
		double value = bids / (Math.round(System.currentTimeMillis()-startTime/60000));
		StatisticsEvent wert1 = new StatisticsEvent("1", EventType.BID_COUNT_PER_MINUTE, System.currentTimeMillis(),
				value);
		a.getStatisticsEvents().put(EventType.BID_COUNT_PER_MINUTE, wert1);
		//TODO weis nicht wie ich die ID's machen soll
		return wert1;		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(60000);
			this.calculateBidEventAverageTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}