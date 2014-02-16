package analyticserver;

import java.util.ArrayList;

import model.Event;
import model.StatisticsEvent;

public interface  InterfaceCalculate {
	public ArrayList<StatisticsEvent> calculate(Event event);
}
