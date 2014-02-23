package testingComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.BidMessage;

import Client.TCPConnector;

/**
 * 
 * @author Klune Alexander
 * @version 1.0
 * @email aklune@student.tgm.ac.at
 */
public class BidThread implements Runnable {

	private TestingClient testingClient;

	private long end;
	/**
	 * 
	 * @param testingClient
	 */
	public BidThread(TestingClient testingClient) {
		this.testingClient = testingClient;
		end = System.currentTimeMillis()*1000*60*2;//TODO Endet mit Math.random zwischen 7 und 10
	}
	
/**
 * 
 */
	@Override
	public void run() {
		while(System.currentTimeMillis() <= end) {//TODO Fehler nicht einmal eine Schleife im Thread
			int max = testingClient.getAuctionsIDs().size();
			int bidid = ((int) (Math.random()*max));
			double amount = System.currentTimeMillis();
			testingClient.getTaskExecuter().bid(bidid, amount);
			try {
				Thread.sleep(((60/testingClient.getAuctionsPerMin())*1000));//TODO Fehler falsche Formel 60/x richtig!!
			} catch (InterruptedException e) {
				System.err.println("Fehler beim schlafen legen des ListThreads");
			}
		}
	}
		

}
