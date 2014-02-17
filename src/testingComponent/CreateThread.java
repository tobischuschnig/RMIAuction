package testingComponent;

import java.util.ArrayList;

import Client.Client;
import Client.TaskExecuter;
/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class CreateThread implements Runnable {
	
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
	public CreateThread(TestingClient testingClient){
		this.testingClient = testingClient;
		te = new TaskExecuter(new Client("localhost",80,1241));
	}
	
/**
 * 
 */
	@Override
	public void run() {
		
		te.create(testingClient.getAuctionDuration(), "TestAuction_No"+incrementer);
		incrementer++;
		try {
			Thread.sleep((testingClient.getAuctionsPerMin()/60)*1000);
		} catch (InterruptedException e) {
			System.err.println("Fehler beim schlafen legen des CreateThread");
		}
		
	}

}
