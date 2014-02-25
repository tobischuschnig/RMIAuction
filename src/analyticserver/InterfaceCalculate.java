package analyticserver;

import java.util.ArrayList;

import model.Event;
import model.StatisticsEvent;


/**
 * An Interface for the Calculation classes. 
 * It provides all Methods which are necessary for this step.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-02-25
 */
public interface  InterfaceCalculate {
	/**
	 * The Method calculate in this Method all calculation about statistics are done
	 * @param event the Event which is new to do new calculations
	 * @return an ArrayList of statistics events which have been created
	 */
	public ArrayList<StatisticsEvent> calculate(Event event);
}
