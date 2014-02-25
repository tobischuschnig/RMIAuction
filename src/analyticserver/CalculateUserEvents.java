package analyticserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import model.*;

/**
 * Receives events from the system and computes simple statistics/analytics.
 * In this class all User Events are calculated
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-02-25
 */
public class CalculateUserEvents implements InterfaceCalculate {
	private AnalyticServer a; // a...AnalyticServer (Variable is often used so its better when its a shortcut
	/**
	 * Konstruktor
	 * @param a The calling AnalyticServer
	 */
	public CalculateUserEvents (AnalyticServer a) {
		this.a = a;
	}
	
	/**
	 * The Method calculate in this Method all calculation about statistics are done
	 * All User Events are calculated here.
	 * First their will be the Min and Max sessiontime calculated and after that the Average sessiontimme
	 * @param event the Event which is new to do new calculations
	 * @return an ArrayList of statistics events which have been created
	 */
	@Override
	public ArrayList<StatisticsEvent> calculate(Event event) {
		ArrayList<StatisticsEvent> ret = new ArrayList();
		
		/////////////////////////////////////////////////////////////////////////////////
		// Min and Max
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