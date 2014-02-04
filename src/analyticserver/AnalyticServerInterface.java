package analyticserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AnalyticServerInterface extends Remote {
	public String suscribe(String filter);
	public void processEvent(Event event);
	public void unsuscribe(String uid);
}
