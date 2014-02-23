package testingComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.BidMessage;

import Client.TCPConnector;

/**
 * 
 * @author Klune Alexander
 * @version 1.0
 * @email aklune@student.tgm.ac.at
 */
public class BidThread implements Runnable {

	private TestingClient testingClient;

	private long end;
	/**
	 * 
	 * @param testingClient
	 */
	public BidThread(TestingClient testingClient) {
		this.testingClient = testingClient;
		end = System.currentTimeMillis()*1000*60*2;//TODO Endet mit Math.random zwischen 7 und 10
	}
	
/**
 * 
 */
	@Override
	public void run() {
		while(System.currentTimeMillis() <= end) {//TODO Fehler nicht einmal eine Schleife im Thread
			// fuer verschicken der befehle an server
			TCPConnector tcp = testingClient.getTCPConnector();
			// timestamp
			// Date date = new Date();
			ArrayList<String[]> arl = testingClient.getAuctionsIDs();
			// Date getstartTime;
			int id = (int) Math.random() * arl.size() - 1;
			System.out.println("Random ID: " + id);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime = null;
			try {
				startTime = sdf.parse(arl.get(id)[4].substring(11,arl.get(id)[4].length()));
			} catch (ParseException e) {
				System.err
				.println("Fehler beim umformatieren des Datums in BidThread!");
			}

			//ermittlen des bid amounts

			Long diff =  new Date().getTime() - startTime.getTime();
			double amount = diff / 1000;

			// "TestAuction"+id,"BeschreibungBLA",testingClient.getAuctionDuration()
			BidMessage cm = new BidMessage("admin", id, amount);
			tcp.sendMessage(cm);
			try {
				Thread.sleep((testingClient.getBidsPerMin() / 60) * 1000);
			} catch (InterruptedException e) {
				System.err.println("Fehler beim schlafen legen des BidThread");
			}
		}
	}
		

}
