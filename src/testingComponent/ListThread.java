package testingComponent;

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

/**
 * 
 */
private TestingClient testingClient;
	
	public ListThread(TestingClient testingClient){
		this.testingClient = testingClient;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		ListMessage lm = new ListMessage("admin");
		TCPConnector tcp = testingClient.getTCPConnector();
		tcp.sendMessage(lm);
		
		try {
			Thread.sleep(testingClient.getUpdateIntervalSec()*1000);
		} catch (InterruptedException e) {
			System.err.println("Fehler beim schlafen legen des ListThreads");
		}
		
	}

}
