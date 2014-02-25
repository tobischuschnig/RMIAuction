package testingComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.BidMessage;

import Client.TCPConnector;

/**
 * This class represents a thread, which bids to a random auction x - times
 * per minute. The thread stops working after a specific time.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 1.0
 */
public class BidThread implements Runnable {

	private TestingClient testingClient;
	private double amount;

	private long end;
	
	/**
	 * This Konstructor sets the testingclient and calculates the time
	 * when the threads has to be stopped.
	 * @param testingClient	TestingClient
	 */
	public BidThread(TestingClient testingClient) {
		this.testingClient = testingClient;
		end = System.currentTimeMillis()*1000*60*2;//TODO end with Math.random between 7 and 10
		amount = 1000;
	}
	
	/**
	 * This run method operates as lang as end has reached. 
	 * it bids to a random Auction ID x times per minute.
	 */
	@Override
	public void run() {
		while(System.currentTimeMillis() <= end) {//TODO Error not even a Loop in the Thread
			int max = testingClient.getAuctionsIDs().size();
			System.out.println(max+"asdfasdf");
			if(max != 0) {
				int bidid = ((int) (Math.random()*max));
				System.out.println(bidid+"sdfasdfd");
				bidid = testingClient.getAuctionsIDs().get(bidid);
				amount+=  (Math.random()*max);
				testingClient.getTaskExecuter().bid(bidid, amount);
			}
			try {
				Thread.sleep(((60/testingClient.getAuctionsPerMin())*1000));//TODO Error wrong formel  60/x correct!!
			} catch (InterruptedException e) {
				System.err.println("Fehler beim schlafen legen des ListThreads");
			}
		}
	}
		

}
