package testingComponent;


import model.CreateMessage;

import Client.Client;
import Client.TCPConnector;
import Client.TaskExecuter;
/**
 * This Class represents a thread which creates x times per
 * minute a new Auction with incrementing their IDs.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 *@version 1.0
 */
public class CreateThread implements Runnable {
	
	private TestingClient testingClient;

	private int incrementer=0;
	private long end;
 
	/**
	 * This Constructor sets the testinClient for the thread and also
	 * calculates the end time for stopping the thread
	 * @param testingClient TestingClient
	 */
	public CreateThread(TestingClient testingClient){
		this.testingClient = testingClient;
		end = System.currentTimeMillis()+1000*60*8;//TODO end with Math.random between 7 and 10
	}
	
	/**
	 * This run method runs as long as the end time has reached. It creates x times per minute an Auction
	 * with incrementing the AuctionID.
	 */
	@Override
	public void run() {
		while(System.currentTimeMillis() <= end) {//TODO Error not even a Loop in the Thread
			//CreateMessage cm = new CreateMessage("admin", "Beschreibung"+String.valueOf(incrementer), testingClient.getAuctionDuration());
			//TCPConnector tcp = testingClient.getTCPConnector();

			//		te.create(testingClient.getAuctionDuration(), "TestAuction_No"+incrementer);
			//tcp.sendMessage(cm);
			testingClient.getTaskExecuter().create(testingClient.getAuctionDuration(), "Beschreibung"+String.valueOf(incrementer));

			incrementer++;
			try {
				//System.out.println(testingClient.getAuctionsPerMin()+""+((testingClient.getAuctionsPerMin()/60)*1000));
				Thread.sleep(((60/testingClient.getAuctionsPerMin())*1000));//TODO Error wrong formel  60/x correct!!
			} catch (InterruptedException e) {
				System.err.println("Error by pausing the Thread."); 
			}
		}
	}

}
