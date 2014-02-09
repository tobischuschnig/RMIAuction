package managmentclient;

/**
 *
 * @author alexander auradnik
 */
public class StartManagmentClient {

    public static void main(String[] args) {

        // TODO arugment check

       // if (args.length != 2) {
            System.out.println("Wrong arguments\n<BillingServer Remote Adress> <AnalyticsServer Remote Adress>");
       // } else {
            ManagmentClient mc = new ManagmentClient("billing ip","analytics ip");
            mc.startService();
            mc.run();
      //  }
    }
}
