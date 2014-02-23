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
	private long end;
 
	public CreateThread(TestingClient testingClient){
		this.testingClient = testingClient;
		end = System.currentTimeMillis()*1000*60*2;//TODO Endet mit Math.random zwischen 7 und 10
	}
	
/**
 * 
 */
	@Override
	public void run() {
		while(System.currentTimeMillis() <= end) {//TODO Fehler nicht einmal eine Schleife im Thread
			//CreateMessage cm = new CreateMessage("admin", "Beschreibung"+String.valueOf(incrementer), testingClient.getAuctionDuration());
			//TCPConnector tcp = testingClient.getTCPConnector();

			//		te.create(testingClient.getAuctionDuration(), "TestAuction_No"+incrementer);
			//tcp.sendMessage(cm);
			testingClient.getTaskExecuter().create(testingClient.getAuctionDuration(), "Beschreibung"+String.valueOf(incrementer));

			incrementer++;
			try {
				System.out.println(testingClient.getAuctionsPerMin()+""+((testingClient.getAuctionsPerMin()/60)*1000));
				Thread.sleep(((60/testingClient.getAuctionsPerMin())*1000));//TODO Fehler falsche Formel 60/x richtig!!
			} catch (InterruptedException e) {
				System.err.println("Error by pausing the Thread."); 
			}
		}
	}

}
