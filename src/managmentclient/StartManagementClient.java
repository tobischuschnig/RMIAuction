package managmentclient;

/**
 * Mainclass to Start ManagmentClient
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class StartManagementClient {

    private static ManagementClient mc;

    public static void main(String[] args) {      
        if (args.length != 2) {
            // example "//localhost/RmiServerBilling"  "localhost/RmiServerAnalytics"
            System.out.println("Wrong arguments - Syntax: <AnalyticsServer Remote Adress>");
        }
        mc = new ManagementClient(args[0], args[1]);
        // thread for user input
        mc.run();
    }
}
