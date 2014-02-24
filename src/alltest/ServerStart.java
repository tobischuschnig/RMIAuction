package alltest;

public class ServerStart {
	public static void startServer(){
		String[] args = new String[] {""};
		//analyticserver.AnalyticServerMain.
		analyticserver.AnalyticServerMain.main(args);
		billingServer.BillingServer.main(args);
	}
}
