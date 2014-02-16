package analyticserver;

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
		//Bid Time Average berechnen
		return null;		
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