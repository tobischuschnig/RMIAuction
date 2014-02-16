package testingComponent;

import Client.Client;

public class ListThread implements Runnable{
	
private Client testingClient;
	
	public ListThread(Client testingClient){
		this.testingClient = testingClient;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
