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

public interface RMI_Analytics extends Remote {
    String subscribe(String filter) throws RemoteException;
    void processEvent(Event event) throws RemoteException;
    void unsubscribe (String uid)throws RemoteException;
    void calculate()throws RemoteException;
}