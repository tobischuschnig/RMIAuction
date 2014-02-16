/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managmentclient;

import java.rmi.Naming;
import java.rmi.RemoteException;
import model.Event;
import model.EventType;

/**
 *
 * @author j
 */
public class TestManagmentClient {

    public static void main(String[] args) throws RemoteException {
        ManagmentClientInterface acc = null;
        try {
            acc = (ManagmentClientInterface) Naming.lookup("ManagmentClient");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Event event =new Event("1", EventType.USER_SESSIONTIME_MIN, 12323);
        acc.processEvent(event);
    }
}
