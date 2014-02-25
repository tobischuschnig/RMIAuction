package testingComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.ListMessage;
import Client.Client;
import Client.TCPConnector;
/**
 * This Class represents a thread which updates the list of current auction
 * every x secronds
 * 
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 1.0
 * @email aklune@student.tgm.ac.at
 */
public class ListThread implements Runnable{
	
	private TestingClient testingClient;
	private long end;
	
	/**
	 * This Constructor sets the testingClient for the thread and calculates the
	 * end time to stop the thread.
	 */
	public ListThread(TestingClient testingClient){
		this.testingClient = testingClient;
		end = System.currentTimeMillis()*1000*60*2;//TODO end with Math.random between 7 and 10
	}
	
	/**
	 * This run method works as long as the end time has reached. Is invokes the
	 * list command and splits the returning String into atomic Auctions and saves them 
	 * into an arrayList.
	 */
	@Override
	public void run() {
		while(System.currentTimeMillis() <= end) {//TODO Error not even a Loop in the Thread
			//System.out.println("Hallo from list");
			//System.out.println(testingClient.getTaskExecuter().list());
			testingClient.getTaskExecuter().list();
			String wert = "";
			while(!wert.startsWith("ID:")) {
				try {
					wert = testingClient.getC().getTestingoutput();
				}catch (NullPointerException e) {}
			}
			String[] wert1 = wert.split(" ");
			ArrayList<Integer> ids = new ArrayList();
			for (int i = 0; i < wert1.length;i++) {
				//System.out.println(i+"    "+wert1[i]);
				if(wert1[i].contains("ID")) {
					try {
						ids.add(Integer.parseInt(wert1[i+1]));
					} catch(NumberFormatException e) {
					}
					
				}
			}
			
			testingClient.setAuctionsIDs(ids);
			try {
				Thread.sleep(((60/testingClient.getAuctionsPerMin())*1000));//TODO Error wrong formel  60/x correct!!
			} catch (InterruptedException e) {
				System.err.println("Fehler beim schlafen legen des ListThreads");
			}
		}
//		System.out.println("---------------------------------------------------------------------------------------------------------------------");
	}

}
