package managmentclient;

/**
 *
 * @author alexander auradnik
 * @author Alexander Rieppel
 */
public class StartManagmentClient {

    public static void main(String[] args) {

		boolean wronginput = false;
		if(!(args.length == 2)){
			wronginput=true;
		}else{
			ManagmentClient mc = new ManagmentClient("billing ip","analytics ip");
            mc.startService();
            mc.run();
		}
		if(wronginput){
			System.out.println("Wrong arguments\n Please enter like this:<BillingServer Remote Adress> <AnalyticsServer Remote Adress>");
		}
    }
}
