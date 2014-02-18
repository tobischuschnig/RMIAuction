package analyticserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import model.*;


public class CalculateBidEvents implements InterfaceCalculate, Runnable {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	private long startTime;
	
	public CalculateBidEvents (AnalyticServer a) {
		this.a = a;
		startTime = System.currentTimeMillis();
	}
	
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
	
	private ArrayList<StatisticsEvent> calculateBidEventAverageTime() {
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

	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(60000);
				a.notify(this.calculateBidEventAverageTime(),null); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}