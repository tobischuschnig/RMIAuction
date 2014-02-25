package testingComponent.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Client.Client;
import Client.TCPConnector;
import testingComponent.BidThread;
import testingComponent.CreateThread;
import testingComponent.ListThread;
import testingComponent.TestingClient;
import testingComponent.TestingCompCLI;


/**
 * Testclass for TestingComponent
 * @author Alexander Rieppel
 * @email <arieppel@student.tgm.ac.at>
 */
public class TestingComponentTest {
	private TestingClient tc,tc1;
	private BidThread bt;
	private CreateThread ct;
	private ListThread lt;
	private TestingCompCLI tcc;
	
	@Before
	public void setUp(){
		tc = new TestingClient(1, 1, 1, 1, 1);
		tc1 = new TestingClient(1, "asd", 1, 1, 1, 1, 1);
		bt = new BidThread(tc);
		ct = new CreateThread(tc);
		lt = new ListThread(tc);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testCreateAuction(){
		tc.createAuctionTest();
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetAuctionsID(){
		ArrayList<Integer> auctions = null;
		tc.setAuctionsIDs(auctions);
		assertEquals(tc.getAuctionsIDs(),auctions);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetAuctionsPerMin(){
		tc.setAuctionsPerMin(12);
		assertEquals(tc.getAuctionsPerMin(),12);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetAuctionDuration(){
		tc.setAuctionDuration(12);
		assertEquals(tc.getAuctionDuration(),12);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetUpdateIntervalSec(){
		tc.setUpdateIntervalSec(12);
		assertEquals(tc.getUpdateIntervalSec(),12);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetClientID(){
		tc.setClientID(12);
		assertEquals(tc.getClientID(),12);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetClient(){
		tc.setClients(12);
		assertEquals(tc.getClients(),12);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetTestingCompCLI(){
		tc.getTestingCompCLI();
//		assertEquals(tc.getAuctionDuration(),12);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetTCPConnector(){
		TCPConnector tcpc = null;
		tc.setTCPConnector(tcpc);
		assertEquals(tc.getTCPConnector(),tcpc);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetUsername(){
		tc.setUsername("asd");
		assertEquals(tc.getUsername(),"asd");
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetC(){
		Client c = new Client(null, 0);
		tc.setC(c);
		assertEquals(tc.getC(),c);
	}
	
	/**
	 * Testing of Method, when something should happen
	 */
	@Test
	public void testGetBidsPerMin(){
		tc.setBidsPerMin(12);
		assertEquals(tc.getBidsPerMin(),12);
	}
}

