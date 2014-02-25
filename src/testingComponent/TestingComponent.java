package testingComponent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.Subject;

import client.Client;


import managmentclient.ManagementClient;
import managmentclient.TaskExecuter;

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
		BufferedReader buff = null;
		String cline;
		//String dataset = System.getProperty("user.dir") + "/loadtest.properties"; 
		String dataset = ("/Users/tobi/Workspace/RMIAuction/src/testingComponent/loadtest.properties"); 

		//TODO don't Hardcode this!!!!!
		//String dataset = System.getProperty("/home/poezze/Dokumente/WorkspaceJava/RMI/loadtest.properties"); 
		buff = new BufferedReader(new FileReader(dataset));
		
		int i = 0;
		int [] at = new int[5];
		String[] aline;
		String[] n;
		
		while ((cline = buff.readLine())!=null){
			try{
				String ch = cline.substring(0,1);
				if(!ch.equals("#")){
					aline = cline.split("(=)|(\\:)");
					
					if(aline[1].contains("*")){
						n = aline[1].split("\\*");
						int ch1=Integer.parseInt(n[0].replaceAll("\\s+",""));
						int ch2=Integer.parseInt(n[1].replaceAll("\\s+",""));
						at[i]= ch1 * ch2;
					}else if(aline[1].contains("+")){
						n = aline[1].split("\\+");
						int ch1=Integer.parseInt(n[0].replaceAll("\\s+",""));
						int ch2=Integer.parseInt(n[1].replaceAll("\\s+",""));
						at[i]= ch1 + ch2;
					}else if(aline[1].contains("-")){
						n = aline[1].split("\\-");
						int ch1=Integer.parseInt(n[0].replaceAll("\\s+",""));
						int ch2=Integer.parseInt(n[1].replaceAll("\\s+",""));
						at[i]= ch1 - ch2;
					}else if(aline[1].contains("/")){
						n = aline[1].split("\\/");
						int ch1=Integer.parseInt(n[0].replaceAll("\\s+",""));
						int ch2=Integer.parseInt(n[1].replaceAll("\\s+",""));
						at[i]= ch1 / ch2;
					}else{
						at[i]=Integer.parseInt(aline[1].replaceAll("\\s+",""));
						i++;
					}
				}
			}catch (StringIndexOutOfBoundsException e){
			}
		}
		
		try{
			clients = at[0];
			auctionsPerMin = at[1];
			auctionDuration = at[2];
			updateIntervalSec = at[3];
			bidsPerMin = at[4];
			//Use the following line for the proove of the functionality
			//System.out.println(clients + "\n" + auctionsPerMin + "\n" + auctionDuration + "\n" + updateIntervalSec + "\n" + bidsPerMin);
		}catch (ArrayIndexOutOfBoundsException e){
		}
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
