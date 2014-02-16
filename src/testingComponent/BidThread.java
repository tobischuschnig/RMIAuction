package testingComponent;

import Client.Client;

public class BidThread implements Runnable{

	private Client testingClient;
	
	public BidThread(Client testingClient){
		this.testingClient = testingClient;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
