package testingComponent;

import java.util.ArrayList;

import Client.CLI;
import Client.Client;
import Client.TCPConnector;
import Client.UI;

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

	private long auctionDuration;

	private int updateIntervalSec;

	private int bidsPerMin;
	
	private int clientID;
	
	private ArrayList <String[]> auctions;
	
	private TCPConnector tcp;
	
	private testingCompCLI tcc;
	
	/**
	 * 
	 * @param clientID
	 * @param auctionsPerMin
	 * @param auctionDuration
	 * @param updateIntervalSec
	 * @param bidsPerMin
	 */
	public TestingClient(int clientID, int auctionsPerMin, long auctionDuration, int updateIntervalSec, int bidsPerMin) {
		this.clientID = clientID;
		this.auctionsPerMin = auctionsPerMin;
		this.auctionDuration = auctionDuration;
		this.updateIntervalSec = updateIntervalSec;
		this.bidsPerMin = bidsPerMin;
		tcc = new testingCompCLI();
		this.tcp = new TCPConnector(1234,tcc,new Client("localhost",1234,4321));
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
	public void setAuctionsIDs(ArrayList <String[]> auctions){
		this.auctions = auctions;
	}

	public int getClients() {
		return clients;
	}

	public void setClients(int clients) {
		this.clients = clients;
	}

	public int getAuctionsPerMin() {
		return auctionsPerMin;
	}

	public void setAuctionsPerMin(int auctionsPerMin) {
		this.auctionsPerMin = auctionsPerMin;
	}

	public long getAuctionDuration() {
		return auctionDuration;
	}

	public void setAuctionDuration(int auctionDuration) {
		this.auctionDuration = auctionDuration;
	}

	public int getUpdateIntervalSec() {
		return updateIntervalSec;
	}

	public void setUpdateIntervalSec(int updateIntervalSec) {
		this.updateIntervalSec = updateIntervalSec;
	}

	public int getBidsPerMin() {
		return bidsPerMin;
	}

	public void setBidsPerMin(int bidsPerMin) {
		this.bidsPerMin = bidsPerMin;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public ArrayList<String[]> getAuctionsIDs() {
		return auctions;
	}
	
	public TCPConnector getTCPConnector(){
		return tcp;
	}
	
	public testingCompCLI getTestingCompCLI(){
		return this.tcc;
	}
}
