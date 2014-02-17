package analyticserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Event;

public interface AnalyticServerInterface extends Remote {
	public String suscribe(String filter) throws RemoteException;;
	public void processEvent(Event event) throws RemoteException;;
	public void unsuscribe(String uid) throws RemoteException;;
}
