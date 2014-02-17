package testingComponent;

import java.util.ArrayList;
import java.util.Date;

import Client.Client;
import Client.TaskExecuter;
/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class BidThread implements Runnable{

	private TestingClient testingClient;
	/**
	 * change Request
	 * Diese beiden attribute sind nicht in KLassendiagramm
	 */
	private TaskExecuter te;
	private int incrementer=0;
	
	/**
	 * 
	 * @param testingClient
	 */
	public BidThread(TestingClient testingClient){
		this.testingClient = testingClient;
		te = new TaskExecuter(new Client("localhost",80,1241));
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		Date date = new Date();
		ArrayList<Integer> arl = testingClient.getAuctionsIDs();
		Date getstartTime;		
		int id = (int)Math.random() * arl.size() -1;
		System.out.println("Random ID: " + id);
		double amount = 0.0 ;
		te.bid(id, amount);
		incrementer++;
		try {
			Thread.sleep((testingClient.getAuctionsPerMin()/60)*1000);
		} catch (InterruptedException e) {
			System.err.println("Fehler beim schlafen legen des CreateThread");
		}
	}

}
