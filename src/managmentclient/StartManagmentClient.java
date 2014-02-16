package managmentclient;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mainclass to Start ManagmentClient
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class StartManagmentClient {

    private static Registry r;
    private static ManagmentClient mc;

    public static void main(String[] args) {
        String bname;
        String aname;
        String[] args2 = {"BillingServer", "AnalyticsServer"};
        args = args2;
        if (args.length != 2) {
            System.out.println("Wrong arguments\n Please type in this:<BillingServer Remote Adress> <AnalyticsServer Remote Adress>");
            // example "//localhost/RmiServerBilling"  "localhost/RmiServerAnalytics"
        } else {
            try {
                bname = args[0];
                aname = args[1];
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong inputformat\n Please type in this:<BillingServer Remote Adress> <AnalyticsServer Remote Adress>");
            }
            mc = new ManagmentClient(args[0], args[1]);
            //ManagmentClient mc = new ManagmentClient(bname, aname);
            // remote object
             mc.startService();
            // thread for user input
            mc.run();


        }


    }

    static void startRemote() {
        try {
            r = LocateRegistry.createRegistry(1089);
            r.rebind("ManagmentClient", (ManagmentClientInterface) UnicastRemoteObject.exportObject(mc, 0));
            System.out.println("ManagmentClient bound");
        } catch (RemoteException ex) {
            Logger.getLogger(StartManagmentClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
