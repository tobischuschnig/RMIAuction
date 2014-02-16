package managmentclient;

/**
 * Mainclass to Start ManagmentClient
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class StartManagmentClient {

    public static void main(String[] args) {
        String bname;
        String aname;
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
            ManagmentClient mc = new ManagmentClient("billing ip", "analytics ip");
            //ManagmentClient mc = new ManagmentClient(bname, aname);
            // remote object
            mc.startService();
            // thread for user input
            mc.run();
        }

    }
}
