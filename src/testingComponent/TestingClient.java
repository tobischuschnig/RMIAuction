package testingComponent;

import java.util.ArrayList;

public class TestingClient{
	
	private int clients;

	private int auctionsPerMin;

	private int auctionDuration;

	private int updateIntervalSec;

	private int bidsPerMin;
	
	private int clientID;
	
	private ArrayList <Integer> auctionsIDs;
	
	public TestingClient(int clientID, int auctionsPerMin, int auctionDuration, int updateIntervalSec, int bidsPerMin) {
		this.clientID = clientID;
		this.auctionsPerMin = auctionsPerMin;
		this.auctionDuration = auctionDuration;
		this.updateIntervalSec = updateIntervalSec;
		this.bidsPerMin = bidsPerMin;
	}
	
	
	public void createAuctionTest(){
		
	}
	
	public void setAuctionsIDs(ArrayList <Integer> ids){
		
	}
}
