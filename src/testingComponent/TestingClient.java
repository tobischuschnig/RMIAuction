package testingComponent;

import java.util.ArrayList;

import model.Auction;
import model.User;
/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class TestingClient{
	
	private int clients;

	private int auctionsPerMin;

	private int auctionDuration;

	private int updateIntervalSec;

	private int bidsPerMin;
	
	private int clientID;
	
	private ArrayList <Integer> auctionsIDs;
	
	/**
	 * 
	 * @param clientID
	 * @param auctionsPerMin
	 * @param auctionDuration
	 * @param updateIntervalSec
	 * @param bidsPerMin
	 */
	public TestingClient(int clientID, int auctionsPerMin, int auctionDuration, int updateIntervalSec, int bidsPerMin) {
		this.clientID = clientID;
		this.auctionsPerMin = auctionsPerMin;
		this.auctionDuration = auctionDuration;
		this.updateIntervalSec = updateIntervalSec;
		this.bidsPerMin = bidsPerMin;
	}
	
	/**
	 * 
	 */
	public void createAuctionTest(){
		Auction auc = new Auction(new User(),"Test Auction Description",1000l);
		
	}
	
	/**
	 * 
	 * @param ids
	 */
	public void setAuctionsIDs(ArrayList <Integer> ids){
		this.auctionsIDs = ids;
	}
}
