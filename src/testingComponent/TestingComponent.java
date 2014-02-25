package testingComponent;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import managmentclient.ManagementClient;
import managmentclient.TaskExecuter;
import model.Properties;

/**
 * Class TestingComponent to read the loadtest.properties-File and create the Clients with the Data from the File
 * @author Patrick Poelzlbauer <ppoelzlbauer@student.tgm.ac.at> ,Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 10.02.2014 
 */
public class TestingComponent {

	private static ManagementClient managementClient;
	
	//private static final boolean StringIndexOutOfBoundsException = false;

	private static int clients;
	
	//private String clientID;

	private static int auctionsPerMin;

	private static int auctionDuration;

	private static int updateIntervalSec;

	private static int bidsPerMin;
	
	private static  ConcurrentHashMap <Integer, TestingClient> testingclients;
	

	
	//public void createMessage(){
			
	//}
	
	/**
	 * This method loads the loadtest.properties-File and save the relevant data 
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public static void loadFile() throws IOException{
//		String dataset = System.getProperty("loadtest.properties") + "/loadtest.properties"; 
		Properties p=new Properties("loadtest.properties");
		String[] values = new String[5];
		values[0] = p.getProperty("clients").toString();
		values[1] = p.getProperty("auctionsPerMin").toString();
		values[2]= p.getProperty("auctionDuration").toString();
		values[3] = p.getProperty("updateIntervalSec").toString();
		values[4] = p.getProperty("bidsPerMin").toString();
		Integer[] valuess = new Integer[5];
		for(int i = 0 ; i < 5 ; i++) {
			System.out.println(values[i]);
		}
		
		
		try {
		for(int i = 0 ; i < 5 ; i++) {
			if (values[i].contains("*")) {
				int hilf = values[i].indexOf('*');
				String wert1 = values[i].substring(0,hilf);
				String wert2 = values[i].substring(hilf+1,values[i].length());
				valuess[i] = Integer.parseInt(wert1) * Integer.parseInt(wert2);
			}
			else if (values[i].contains("+")) {
				int hilf = values[i].indexOf('+');
				String wert1 = values[i].substring(0,hilf);
				String wert2 = values[i].substring(hilf+1,values[i].length());
				valuess[i] = Integer.parseInt(wert1) + Integer.parseInt(wert2);
			}
			else if (values[i].contains("-")) {
				int hilf = values[i].indexOf('-');
				String wert1 = values[i].substring(0,hilf);
				String wert2 = values[i].substring(hilf+1,values[i].length());
				valuess[i] = Integer.parseInt(wert1) - Integer.parseInt(wert2);
			}
			else if (values[i].contains("/")) {
				int hilf = values[i].indexOf('/');
				String wert1 = values[i].substring(0,hilf);
				String wert2 = values[i].substring(hilf+1,values[i].length());
				valuess[i] = Integer.parseInt(wert1) / Integer.parseInt(wert2);
			}
			else {
				//System.out.println(values[i]);
				valuess[i] = Integer.parseInt(values[i]);
			}
		}
		clients = valuess[0];
		auctionsPerMin = valuess[1];
		auctionDuration = valuess[2];
		updateIntervalSec = valuess[3];
		bidsPerMin = valuess[4];
		} catch(Exception e) {
			System.err.println("Please check the loadtest.properties file.\n Every value is only allowed to contain one operater.");
			e.printStackTrace();
			return;
		}

		//Use the following line for the proove of the functionality
		System.out.println(clients + "\n" + auctionsPerMin + "\n" + auctionDuration + "\n" + updateIntervalSec + "\n" + bidsPerMin);

	}
	
	/**
	 * main-method --> using the loadFile and createClient methods
	 * @param args
	 */
	public static void main(String [] args){
		if(args.length!=3){
			System.out.println("Wrong arguments\nServer-IP TCP-Port AnalyticServerName");
			System.exit(0);		//If there are no 3 arguments, program exits immediately
		}

		
		
		
		managementClient = new ManagementClient(args[2], null ); //TODO Error changed, AnalyticsServer (!sic)
		TaskExecuter t = managementClient.getT();
		t.subscribe(".*");
		
		testingclients = new ConcurrentHashMap<Integer, TestingClient>(); //TODO Error the reason of the Nullpointer 
		
		int clientID;
		TestingClient c;
		try {
			loadFile(); //TODO Error with the arguments  ...92, 1, 20, 2, 0 should be 100, 1 , 2*60 , 20 , 2
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String host=args[0];
			int tcpPort=Integer.parseInt(args[1]);
//			int udpPort=Integer.parseInt(args[2]);	//Save arguments

			for(int i = 0; i < clients; i++){
				clientID = i;
				//System.out.println(clientID+"\n" +auctionsPerMin+"\n" + auctionDuration+"\n" + updateIntervalSec+"\n" + bidsPerMin);
				c = new TestingClient(clientID,host,tcpPort, auctionsPerMin, auctionDuration, updateIntervalSec, bidsPerMin);
				testingclients.put(clientID, c);	//TODO Error now i dont need the try / catch anymore
				if (i == 0 ) {
					try {
						Thread.sleep(1000);
					} catch(InterruptedException e) {

					}
				}

			}
		}catch(NumberFormatException e){
			System.out.println("Port(s) is/are not numeric");
			System.exit(0);
		}catch(Exception e){
			System.out.println("Can not connect to Server");
			System.exit(0);
		}
		
			
	}
}
