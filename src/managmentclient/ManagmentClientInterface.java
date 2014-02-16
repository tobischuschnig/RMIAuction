package managmentclient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Event;

/**
 *
 * @author alexander auradnik <alexander.auradik@student.tgm.ac.at>
 * @version 2014-02-14 
 */
public interface ManagmentClientInterface extends Remote {
    void processEvent(Event event) throws RemoteException;
}
