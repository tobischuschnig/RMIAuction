package analyticserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.regex.PatternSyntaxException;

import managmentclient.ManagementClientInterface;
import model.Event;
import exceptions.InvalidFilterException;
/**
 * The Interface of AnalyticServer provides the RMI Methods which are needed 
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-02-25
 */
public interface AnalyticServerInterface extends Remote {
	/**
	 * Sucribes with a regular expression for events
	 * @param filter The regular Expression
	 * @param managementClientInterface The Interface for the callback
	 * @return rturns the String of the suscribtion
	 * @throws RemoteException throws a Remote exception
	 * @throws PatternSyntaxException When the Pattern is invalid this is thrown
	 * @throws InvalidFilterException When the Pattern is invalid this is thrown 
	 */
	public String suscribe(String filter, ManagementClientInterface managementClientInterface) throws RemoteException, PatternSyntaxException, InvalidFilterException;;
	/**
	 *  Receives events from the system and computes simple statistics/analytics.
	 *  processes the events to Taskexecuter
	 * @param event the processed event
	 * @throws RemoteException throws a Remote exception
	 */
	public void processEvent(Event event) throws RemoteException;;
	/**
	 * Unsuscribe from events
	 * @param uid the id of the cliet who has suscribed
	 * @throws RemoteException throws a Remote exception
	 */
	public void unsuscribe(String uid) throws RemoteException;;
}
