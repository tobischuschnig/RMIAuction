/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managmentclient;

import java.rmi.Naming;
import java.rmi.RemoteException;


/**
 *
 * @author j
 */
public class TestManagementClient {

    public static void main(String[] args) throws RemoteException {
        ManagementClientInterface acc = null;
        try {
            acc = (ManagementClientInterface) Naming.lookup("ManagmentClient");
            acc.processEvent("eventnachricht");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
