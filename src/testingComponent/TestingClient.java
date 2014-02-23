package testingComponent;

import java.util.ArrayList;
import java.util.UUID;

import Client.CLI;
import Client.Client;
import Client.TCPConnector;
import Client.TaskExecuter;
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

	private TCPConnector tcp; //TODO brauch ich eig net?
	private TaskExecuter taskExecuter;

	private TestingCompCLI tcc;
	
	private String username;

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
		tcc = new TestingCompCLI();
		//this.tcp = new TCPConnector(1234,tcc,new Client("localhost",1234,4321));
		//TODO neuer Client nicht auf run()
		//TODO dem seinen TaskExecuter hohlen und alles mit dem machen 


	}

	/**
	 * 
	 * @param clientID
	 * @param auctionsPerMin
	 * @param auctionDuration
	 * @param updateIntervalSec
	 * @param bidsPerMin
	 */
	public TestingClient(int clientID,String host, int tcpPort,int udpPort, int auctionsPerMin, long auctionDuration, int updateIntervalSec, int bidsPerMin) {
		this.clientID = clientID;
		this.auctionsPerMin = auctionsPerMin;
		this.auctionDuration = auctionDuration;
		this.updateIntervalSec = updateIntervalSec;
		this.bidsPerMin = bidsPerMin;
		//tcc = new TestingCompCLI();
		//this.tcp = new TCPConnector(1234,tcc,new Client("localhost",1234,4321));
		//TODO dem seinen TaskExecuter hohlen und alles mit dem machen 
		try {
			Client c=new Client(host,tcpPort,udpPort);
			taskExecuter = c.getT();
			username = UUID.randomUUID().toString();
			
			System.out.println("hallo"+tcpPort+udpPort+host);
			
			
			taskExecuter.login(username, tcpPort, udpPort); //TODO brauche den rueckgabewert
			
			//System.out.println("hallo "+tcpPort+udpPort+host);
			
			//BidThread bidThread = new BidThread(this);
			//bidThread.run();
			//c.run();		// i dont need this
		}catch(Exception e){
			System.out.println("Can not connect to Server");
		}
		//TODO jetzt einlogen machen vill mit einer UUID
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
	
	public TestingCompCLI getTestingCompCLI(){
		return this.tcc;
	}

	/**
	 * @return the tcp
	 */
	public TCPConnector getTCPConnector() {
		return tcp;
	}

	/**
	 * @param tcp the tcp to set
	 */
	public void setTCPConnector(TCPConnector tcp) {
		this.tcp = tcp;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the taskExecuter
	 */
	public TaskExecuter getTaskExecuter() {
		return taskExecuter;
	}

	/**
	 * @param taskExecuter the taskExecuter to set
	 */
	public void setTaskExecuter(TaskExecuter taskExecuter) {
		this.taskExecuter = taskExecuter;
	}
	
	
}
