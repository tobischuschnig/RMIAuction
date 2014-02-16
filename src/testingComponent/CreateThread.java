package testingComponent;

import Client.Client;

public class CreateThread implements Runnable {
	
private Client testingClient;
	
	public CreateThread(Client testingClient){
		this.testingClient = testingClient;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
