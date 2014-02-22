package TestingComponent;

import java.util.ArrayList;


import model.BidMessage;

import Client.TCPConnector;

/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class BidThread implements Runnable{

	private TestingClient testingClient;
	
	/**
	 * 
	 * @param testingClient
	 */
	public BidThread(TestingClient testingClient){
		this.testingClient = testingClient;
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		//fuer verschicken der befehle an server
		TCPConnector tcp = testingClient.getTCPConnector();
		//timestamp
//		Date date = new Date();
		ArrayList<Integer> arl = testingClient.getAuctionsIDs();
//		Date getstartTime;		
		int id = (int)Math.random() * arl.size() -1;
		System.out.println("Random ID: " + id);
		double amount = 0.0 ;
		//"TestAuction"+id,"BeschreibungBLA",testingClient.getAuctionDuration()
		BidMessage cm = new BidMessage("admin",id,amount);
		tcp.sendMessage(cm);
		try {
			Thread.sleep((testingClient.getBidsPerMin()/60)*1000);
		} catch (InterruptedException e) {
			System.err.println("Fehler beim schlafen legen des BidThread");
		}
	}

}
