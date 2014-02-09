/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managmentclient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author j
 */
public interface RMI extends Remote {
    void processEvent(Event event) throws RemoteException;
}
