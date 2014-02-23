package managmentclient;

/**
 * Mainclass to Start ManagmentClient
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class StartManagementClient {

    private static ManagementClient mc;

    public static void main(String[] args) {
        // Input bei andren Rechner in Netzwerk //10.0.105.65/RmiServer
        // + Firewall anpassen
    	//TODO vernueftiges argumente pruefen das ist gar nichts.
        
        // Remove in Final Version
        String[] args2 = {"BillingServer", "AnalyticServer"};
        args = args2;
        // ***
        if (args.length != 2) {
            // example "//localhost/RmiServerBilling"  "localhost/RmiServerAnalytics"
            System.out.println("Wrong arguments - Syntax: <BillingServer Remote Adress> <AnalyticsServer Remote Adress>");
        }
        mc = new ManagementClient(args[0], args[1]);
        // thread for user input
        mc.run();
    }
}
