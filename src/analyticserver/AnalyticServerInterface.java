package analyticserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.regex.PatternSyntaxException;

import Exceptions.InvalidFilterException;

import managmentclient.ManagementClient;
import managmentclient.ManagementClientInterface;
import model.Event;

public interface AnalyticServerInterface extends Remote {
	public String suscribe(String filter, ManagementClientInterface managementClientInterface) throws RemoteException, PatternSyntaxException, InvalidFilterException;;
	public void processEvent(Event event) throws RemoteException;;
	public void unsuscribe(String uid) throws RemoteException;;
}
