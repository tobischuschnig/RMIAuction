package testingComponent;


import model.CreateMessage;

import Client.Client;
import Client.TCPConnector;
import Client.TaskExecuter;
/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class CreateThread implements Runnable {
	
	private TestingClient testingClient;

	private int incrementer=0;

 
	public CreateThread(TestingClient testingClient){
		this.testingClient = testingClient;
	}
	
/**
 * 
 */
	@Override
	public void run() {
		
		CreateMessage cm = new CreateMessage("admin", "Beschreibung"+String.valueOf(incrementer), testingClient.getAuctionDuration());
		//TCPConnector tcp = testingClient.getTCPConnector();
		
//		te.create(testingClient.getAuctionDuration(), "TestAuction_No"+incrementer);
		//tcp.sendMessage(cm);
		testingClient.getTaskExecuter().create(testingClient.getAuctionDuration(), "Beschreibung"+String.valueOf(incrementer));
		
		incrementer++;
		try {
			Thread.sleep((testingClient.getAuctionsPerMin()/60)*1000);
		} catch (InterruptedException e) {
			System.err.println("Error by pausing the Thread."); 
		}
		
	}

}
