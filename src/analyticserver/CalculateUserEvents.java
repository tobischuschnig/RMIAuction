package analyticserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import model.*;

public class CalculateUserEvents implements InterfaceCalculate {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	
	public CalculateUserEvents (AnalyticServer a) {
		this.a = a;
	}
	
	@Override
	public ArrayList<StatisticsEvent> calculate(Event event) {
		ArrayList<StatisticsEvent> ret = new ArrayList();
		
		/////////////////////////////////////////////////////////////////////////////////
		// Min und Max
		long time = event.getTimestamp() - 
				a.getUserEventsLogin()
				.get(((UserEvent) event).getUserName()) 
				.get(a.getUserEventsLogin().get(((UserEvent) event).getUserName())
						.size()-1).getTimestamp();
		if(time >= a.getStatisticsEvents().get(EventType.USER_SESSIONTIME_MAX).getValue()) {
			StatisticsEvent wert = new StatisticsEvent(UUID.randomUUID().toString(), EventType.USER_SESSIONTIME_MAX, System.currentTimeMillis(),
					time);
			
			a.getStatisticsEvents().put(EventType.USER_SESSIONTIME_MAX, wert);
			ret = new ArrayList();
			ret.add(wert);
		}
		//System.out.println(a.getStatisticsEvents().get(EventType.USER_SESSIONTIME_MIN).getValue()+"        "+time);
		if(time <= a.getStatisticsEvents().get(EventType.USER_SESSIONTIME_MIN).getValue()) {
			StatisticsEvent wert = new StatisticsEvent(UUID.randomUUID().toString(), EventType.USER_SESSIONTIME_MIN, System.currentTimeMillis(),
					time);
			
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
				* (logouts-1) + time) / logouts;
		//System.out.println(a.getStatisticsEvents().get(EventType.USER_SESSIONTIME_AVG).getValue()+"\n"+ time);
	
		StatisticsEvent wert2 = new StatisticsEvent(UUID.randomUUID().toString(), EventType.USER_SESSIONTIME_AVG, System.currentTimeMillis(),
				value);
		a.getStatisticsEvents().put(EventType.USER_SESSIONTIME_AVG,wert2);
		ret.add(wert2);
		return ret;
	}

}