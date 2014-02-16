package analyticserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import model.*;

public class CalculateUserEvents implements InterfaceCalculate {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	
	public CalculateUserEvents (AnalyticServer a) {
		this.a = a;
	}
	
	@Override
	public ArrayList<StatisticsEvent> calculate(Event event) {
		// TODO Auto-generated method stub
		ArrayList<StatisticsEvent> ret = null;
		
		/////////////////////////////////////////////////////////////////////////////////
		// Min und Max
		long time = event.getTimestamp() - 
				a.getUserEventsLogin().get(((BidEvent) event).getUserName()) .get(a.getUserEventsLogin().get(((BidEvent) event).getUserName()).size()-1).getTimestamp();
		if(time >= a.getStatisticsEvents().get(EventType.USER_SESSIONTIME_MAX).getValue()) {
			StatisticsEvent wert = new StatisticsEvent("1", EventType.USER_SESSIONTIME_MAX, System.currentTimeMillis(),
					time);
			//TODO weis nicht wie ich die ID's machen soll
			
			a.getStatisticsEvents().put(EventType.USER_SESSIONTIME_MAX, wert);
			ret = new ArrayList();
			ret.add(wert);
		}else if(time <= a.getStatisticsEvents().get(EventType.USER_SESSIONTIME_MIN).getValue()) {
			StatisticsEvent wert = new StatisticsEvent("1", EventType.USER_SESSIONTIME_MIN, System.currentTimeMillis(),
					time);
			//TODO weis nicht wie ich die ID's machen soll
			
			a.getStatisticsEvents().put(EventType.USER_SESSIONTIME_MIN, wert);
			ret = new ArrayList();
			ret.add(wert);
		}
		
		///////////////////////////////////////////////////////////////////////////////////////
		//Average
		Set<String> wert = a.getUserEventsLogout().keySet();
		Iterator<String> it = wert.iterator();
		int logouts = 0;
		while(it.hasNext()) {
			logouts += a.getUserEventsLogout().get(it.next()).size();
		}
		
		double value = (a.getStatisticsEvents().get(EventType.USER_SESSIONTIME_AVG).getValue()
				* logouts-1 + time) / logouts;
	
		StatisticsEvent wert2 = new StatisticsEvent("1", EventType.USER_SESSIONTIME_AVG, System.currentTimeMillis(),
				value);
		//TODO weis nicht wie ich die ID's machen soll
		ret.add(wert2);
		return ret;
	}

}