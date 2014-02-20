package managmentclient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alexander auradnik <alexander.auradik@student.tgm.ac.at>
 * @version 2014-02-14 
 */
public interface ManagementClientInterface extends Remote {
    void processEvent(String event) throws RemoteException;
}
