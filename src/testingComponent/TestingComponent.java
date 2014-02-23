package testingComponent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.Subject;

import managmentclient.ManagementClient;
import managmentclient.TaskExecuter;

/**
 * Class TestingComponent to read the loadtest.properties-File and create the Clients with the Data from the File
 * @author Patrick Poelzlbauer
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
	

	
	public void createMessage(){
			
	}
	
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

		//TODO vill nicht hardcoden
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
			//Fuer einen Funktionsbeweis einfach auskommentieren
			//System.out.println(clients + "\n" + auctionsPerMin + "\n" + auctionDuration + "\n" + updateIntervalSec + "\n" + bidsPerMin);
		}catch (ArrayIndexOutOfBoundsException e){
		}
	}
	
	/**
	 * main-method --> using the loadFile and createClient methods
	 * @param args
	 */
	public static void main(String [] args){
		
		managementClient = new ManagementClient("BillingServer", "AnalyticServer"); //TODO Fehler ausgebessert: AnalyticsServer (!sic)
		TaskExecuter t = managementClient.getT();
		t.subscribe(".*");
		
		testingclients = new ConcurrentHashMap<>(); //TODO Fehler hat gefaellt deshalb NullPointer
		
		int clientID;
		TestingClient c;
		try {
			loadFile(); //TODO laut ueberprueften Werten falsch ...92, 1, 20, 2, 0 should be 100, 1 , 2*60 , 20 , 2
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < clients; i++){
			clientID = i;
			System.out.println(clientID+"\n" +auctionsPerMin+"\n" + auctionDuration+"\n" + updateIntervalSec+"\n" + bidsPerMin);
			c = new TestingClient(clientID, auctionsPerMin, auctionDuration, updateIntervalSec, bidsPerMin);
			try{
				testingclients.put(clientID, c);	
			}catch(NullPointerException e){
				e.printStackTrace(); //TODO warum ist da jedesmal ein nullpointer??
			}
		}
		
			
	}
}
