package TestingComponent;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Class TestingComponent to read the loadtest.properties-File and create the Clients with the Data from the File
 * @author Patrick Poelzlbauer
 * @version 10.02.2014 
 */
public class TestingComponent {

	//private ManagementClient managementclient;
	private int clients;

	private int auctionsPerMin;

	private int auctionDuration;

	private int updateIntervalSec;

	private int bidsPerMin;
	
	//private int clientID;
	
	//private ArrayList <Integer> auctionsID;

	
	public void createMessage(){
			
	}
	
	/**
	 * This method loads the loadtest.properties-File and save the relevant data 
	 */
	public void loadFile(){
		BufferedReader buff = null;	
		
	}
	
	/**
	 * main-method --> using the loadFile and createClient methods
	 * @param args
	 */
	public void main(String [] args){
			
	}
	
	/**
	 * This method creates the clients with the data from the loadtest.properties-File
	 */
	public void createClient(){
			
	}
}
