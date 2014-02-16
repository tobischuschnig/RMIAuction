package testingComponent;

import Client.Client;
/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class CreateThread implements Runnable {
	
private Client testingClient;
	
/**
 * 
 * @param testingClient
 */
	public CreateThread(Client testingClient){
		this.testingClient = testingClient;
	}
	
/**
 * 
 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
