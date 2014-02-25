package testingComponent;

import java.util.ArrayList;
import java.util.UUID;

import client.*;


import model.Auction;
import model.User;
/**
 * This Class got the necessary values fot testing the programm. it starts all three threads:
 * bid, create, list. 
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 *@version 1.0
 */
public class TestingClient{

	private int clients;

	private int auctionsPerMin;

	private long auctionDuration;

	private int updateIntervalSec;

	private int bidsPerMin;

	private int clientID;

	private ArrayList<Integer> auctions;

	private TCPConnector tcp; //TODO brauch ich eig net?
	private TaskExecuter taskExecuter;

	
	private String username;
	
	private Client c;

	/**
	 * This Konstructor sets the minimum of parameters to start the testthreads.
	 * @param clientID	ID of the Client
	 * @param auctionsPerMin	# auction to be created in one minute
	 * @param auctionDuration	time the created auction are active
	 * @param updateIntervalSec		every x secunds the Auction list will be updated
	 * @param bidsPerMin	# of the random bids per min
	 */
	public TestingClient(int clientID, int auctionsPerMin, long auctionDuration, int updateIntervalSec, int bidsPerMin) {
		this.clientID = clientID;
		this.auctionsPerMin = auctionsPerMin;
		this.auctionDuration = auctionDuration;
		this.updateIntervalSec = updateIntervalSec;
		this.bidsPerMin = bidsPerMin;
		//this.tcp = new TCPConnector(1234,tcc,new Client("localhost",1234,4321));
		//TODO the Client not run()
		//TODO get the TaskExecuter and use only him 


	}

	/**
	 * This Contructor sets the maximum of parameters to start the testthreads
	 * @param clientID ID of the Client
	 * @param host	IP of the host
	 * @param tcpPort	tcp Port
	 * @param udpPort	udp Port
	 * @param auctionsPerMin	# auction to be created in one minute
	 * @param auctionDuration	time the created auction are active
	 * @param updateIntervalSec		every x secunds the Auction list will be updated
	 * @param bidsPerMin	# of the random bids per min
	 */
	public TestingClient(int clientID,String host, int tcpPort, int auctionsPerMin, long auctionDuration, int updateIntervalSec, int bidsPerMin) {
		this.clientID = clientID;
		this.auctionsPerMin = auctionsPerMin;
		this.auctionDuration = auctionDuration;
		this.updateIntervalSec = updateIntervalSec;
		this.bidsPerMin = bidsPerMin;
		auctions = new ArrayList();
		//tcc = new TestingCompCLI();
		//this.tcp = new TCPConnector(1234,tcc,new Client("localhost",1234,4321));
		//TODO  get the TaskExecuter and use only him 
		try {
			c=new Client(host,tcpPort);
			taskExecuter = c.getTaskExecuter();
			username = UUID.randomUUID().toString();
			
			//System.out.println("hallo"+tcpPort+udpPort+host);
			
			
			taskExecuter.login(username, tcpPort); 
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {}
			//System.out.println("hallo "+tcpPort+udpPort+host);
			
			CreateThread creater = new CreateThread(this);
			Thread createThread = new Thread(creater);
			createThread.start();
			ListThread lister = new ListThread(this);
			Thread listThread = new Thread(lister);
			listThread.start();
			Thread bidThread = new Thread(new BidThread(this));
			bidThread.start();
		}catch(Exception e){
			System.out.println("Can not connect to Server");
		}
	}
	
	/**
	 * This method creates a new auction.
	 */
	public void createAuctionTest(){
		Auction auc = new Auction(new User(),"Test Auction Description",1000l);

	}

	public void setAuctionsIDs(ArrayList <Integer> auctions){
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

	public ArrayList<Integer> getAuctionsIDs() {
		return auctions;
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

	/**
	 * @return the c
	 */
	public Client getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(Client c) {
		this.c = c;
	}
	
	
}
