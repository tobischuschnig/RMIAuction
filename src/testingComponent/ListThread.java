package testingComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.ListMessage;
import Client.Client;
import Client.TCPConnector;
/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class ListThread implements Runnable{
	
	private TestingClient testingClient;
	private long end;
	
	public ListThread(TestingClient testingClient){
		this.testingClient = testingClient;
		end = System.currentTimeMillis()*1000*60*2;//TODO Endet mit Math.random zwischen 7 und 10
	}
	
/**
 * 
 */
	@Override
	public void run() {
		while(System.currentTimeMillis() <= end) {//TODO Fehler nicht einmal eine Schleife im Thread
			//System.out.println("Hallo von list");
			//System.out.println(testingClient.getTaskExecuter().list());
			testingClient.getTaskExecuter().list();
			String wert = "";
			while(!wert.contains("ID")) {
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
				Thread.sleep(((60/testingClient.getAuctionsPerMin())*1000));//TODO Fehler falsche Formel 60/x richtig!!
			} catch (InterruptedException e) {
				System.err.println("Fehler beim schlafen legen des ListThreads");
			}
		}
	}

}
