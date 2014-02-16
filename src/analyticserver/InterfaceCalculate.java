package analyticserver;

import model.Event;
import model.StatisticsEvent;

public interface  InterfaceCalculate {
	public StatisticsEvent calculate(Event event);
}
