package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import billingServer.BillingServerInterface;
import billingServer.BillingServerSecureInterface;

import analyticserver.AnalyticServerInterface;

import connect.Notifier;
import connect.NotifierFactory;

import managmentclient.ManagementClientInterface;
import model.*;

/**
 * The main server with all functionalities and the user data.
 * Has a request method which responds on every request from the client.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2013-01-05
 */
public class Server {

	private int tcpPort;
	private ConcurrentHashMap<String, User> user;
	private ConcurrentHashMap<Integer, Auction> auction;
	private AuctionHandler ahandler;
	private RequestHandler rhandler; 
	//private Notifier udp;
	private boolean active;
	
	
    private AnalyticServerInterface obja;
    private BillingServerInterface billint;
    private BillingServerSecureInterface secure;
	
	/**
	 * The standard konstructor where are all attributes are set up and the attributes are
	 * initialised.
	 */
	public Server() {
		user=new ConcurrentHashMap<String,User>();
		active = true;
		auction=new ConcurrentHashMap<Integer,Auction>();
		ahandler = new AuctionHandler(this);
		rhandler = new RequestHandler();
		//udp = NotifierFactory.getUDPNotifer();
		Properties p=new Properties("registry.properties");
		String host=p.getProperty("registry.host");
		int port=Integer.parseInt(p.getProperty("registry.port"));		
		try {
            obja = (AnalyticServerInterface) Naming.lookup("rmi://"+host+":"+port+"/AnalyticServer");
        } catch (Exception ex) {
            System.err.println("Client exit: Cannot connect to AnalyticsServer: AnalyticServer");
            System.exit(0);
        }
		billint = null;
		try {     
			billint = (BillingServerInterface)Naming.lookup("rmi://"+host+":"+port+"/BillingServer");
		} 
		catch (Exception e) {
			//e.printStackTrace();
		}
		secure=null;
		try {
			secure=((BillingServerSecureInterface)billint.login("admin", "admin"));
		}catch (Exception e) {
			System.out.println("Cant login to billingserver");
		}
		
		Thread athread = new Thread();
		athread.setPriority(Thread.MIN_PRIORITY);
		new Thread(ahandler).start();
	}

	/**
	 * This method receives all requests of the client
	 * @param message contains every parameters for the work step
	 * @return result of the operation which is handed over to the client via TCP.
	 */
	public String request(Message message) {
		return rhandler.execute(message, this);
	}
	
	/**
	 * In this method the notify method of the class UDPNotifiers is called. There the 
	 * message is forwarded via UDP to the correct clients.
	 * @param concurrentHashMap contains the users which should receive the message
	 * @param message the message which is sended to the client.
	 */
	public void notify(ConcurrentHashMap<String, User> al, String message) {
		Set<String> wert = al.keySet();
		Iterator<String> it = wert.iterator();
		ArrayList<User> users = new ArrayList<User>();
		while(it.hasNext()) { 
			users.add(al.get(it.next()));
		}
//		udp.notify(users,message);
		System.out.println(message); //TODO only for testing after that delete
	}
	
	public void notifyAnalytic(Event event)  {
		try {
			obja.processEvent(event);
			System.out.println("Hallo from notifyanalytic");
		} catch (RemoteException e) {
			try {
				obja.processEvent(event);
			} catch (RemoteException e1) {
				System.err.println("Lost the Connection to the Analytic Server please restart.");
				e1.printStackTrace();
				System.exit(0);
			}
		}
	}

	
	/**
	 * @return the tcpPort
	 */
	public int getTcpPort() {
		return tcpPort;
	}


	/**
	 * @param tcpPort the tcpPort to set
	 */
	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}

	/**
	 * @return the user
	 */
	public ConcurrentHashMap<String, User> getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(ConcurrentHashMap<String, User> user) {
		this.user = user;
	}

	/**
	 * @return the auction
	 */
	public ConcurrentHashMap<Integer, Auction> getAuction() {
		return auction;
	}

	/**
	 * @param auction the auction to set
	 */
	public void setAuction(ConcurrentHashMap<Integer, Auction> auction) {
		this.auction = auction;
	}

	/**
	 * @return the ahandler
	 */
	public AuctionHandler getAhandler() {
		return ahandler;
	}

	/**
	 * @param ahandler the ahandler to set
	 */
	public void setAhandler(AuctionHandler ahandler) {
		this.ahandler = ahandler;
	}

	/**
	 * @return the rhandler
	 */
	public RequestHandler getRhandler() {
		return rhandler;
	}

	/**
	 * @param rhandler the rhandler to set
	 */
	public void setRhandler(RequestHandler rhandler) {
		this.rhandler = rhandler;
	}
//
//	/**
//	 * @return the udp
//	 */
//	public Notifier getUdp() {
//		return udp;
//	}
//
//	/**
//	 * @param udp the udp to set
//	 */
//	public void setUdp(Notifier udp) {
//		this.udp = udp;
//	}

	public boolean isActive() {

		return active;
	}
	public void setActive(boolean active){
		this.active=active;
	}

	public BillingServerSecureInterface getSecure() {
		return secure;
	}

}
