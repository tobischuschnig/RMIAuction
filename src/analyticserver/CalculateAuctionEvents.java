package analyticserver;

import model.*;

public class CalculateAuctionEvents implements InterfaceCalculate {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	
	public CalculateAuctionEvents (AnalyticServer a) {
		this.a = a;
	}
	
	@Override
	public StatisticsEvent calculate(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

}
