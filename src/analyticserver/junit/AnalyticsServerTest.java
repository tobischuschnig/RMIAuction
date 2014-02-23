package analyticserver.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.PatternSyntaxException;

import managmentclient.ManagementClientInterface;
import model.AuctionEvent;
import model.BidEvent;
import model.EventType;
import model.StatisticsEvent;
import model.UserEvent;

import org.junit.Before;
import org.junit.Test;

import Exceptions.InvalidFilterException;
import analyticserver.AnalyticServer;
import analyticserver.EventHandler;

/**
 * Junit Tests for analytics server which iplements the remote interface
 * to communicate with rmi methods.
 * @author Alexander Auradnik <alexander.auradnik@student.tgm.ac.at>
 * @author Alexander Rieppel <alexander.rieppel@student.tgm.ac.at>
 * @version 17/02/2014
 *
 */
public class AnalyticsServerTest {

	public AnalyticServer as;
	public AnalyticServer as2;
	
	/**
	 * Settup which is started for every single test.
	 */
	@Before
	public void setUp() {
		as=new  AnalyticServer();
		as2=new  AnalyticServer();
	}
	
	/**
	 * Tesing sucessfull initializing of the Object
	 */
	@Test
	public void testInitAnalyticServer() {
		as=new  AnalyticServer();
		assertNotNull(as);
	}
	/**
	 * Testing equlity of same object initializing
	 */
	@Test
	public void testEquality() {
		as=new  AnalyticServer();
		as2=as;
		assertTrue(as.equals(as));
	}
//	/**
//	 * Testing subscribe with correct Input
//	 * @throws InvalidFilterException 
//	 * @throws PatternSyntaxException 
//	 */
//	@Test
//	public void testSuscribe() throws PatternSyntaxException, InvalidFilterException{
//		// input is a correct input
//		ManagementClientInterface man = null;
//		String input = "(USER_.*)|(BID_.*)";
//		String n = as.suscribe(input, man);
//		assertNotNull(n);
//	}
//	/**
//	 * Testing subscribe with incorrect Input
//	 * @throws InvalidFilterException 
//	 * @throws PatternSyntaxException 
//	 */
//	@Test
//	public void testSuscribeWrong() throws PatternSyntaxException, InvalidFilterException{
//		// input is a incorrect input
//		String input = "/()=";
//		String n = as.suscribe(input, null);
//		assertNull(n);
//	}
//	/**
//	 * Testing unsubscribe with incorrect Input
//	 * @throws InvalidFilterException 
//	 * @throws PatternSyntaxException 
//	 */	
//	@Test
//	public void testUnsuscribeWrong() throws PatternSyntaxException, InvalidFilterException{
//		// input is a incorrect input
//		ManagementClientInterface man = null;
//		String input = "(USER_.*)|(BID_.*)";
//		String n = as.suscribe(input, man);
//		input = "/()=";
//		as.unsuscribe(input);
//		assertEquals(as.getManagementClients().size(),1);
//	}
//	/**
//	 * Testing unsubscribe with correct Input
//	 * @throws InvalidFilterException 
//	 * @throws PatternSyntaxException 
//	 */	
//	@Test
//	public void testUnsuscribe() throws PatternSyntaxException, InvalidFilterException{
//		// input is a correct input
//		ManagementClientInterface man = null;
//		String input = "(USER_.*)|(BID_.*)";
//		String n = as.suscribe(input, man);
//		input = "1";
//		as.unsuscribe(input);
//		assertEquals(as.getManagementClients().size(),0);
//	}
	/**
	 * Testing Method process Event
	 */
	@Test
	public void testprocessEvent(){
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		as.processEvent(a1);
	}
	
	/**
	 * Testing Method notify 
	 */
	@Test
	public void testNotify(){
		ArrayList<StatisticsEvent> al = new ArrayList<StatisticsEvent>();
		al.add(new StatisticsEvent("1", EventType.BID_PRICE_MAX, System.currentTimeMillis(), 0));
		AuctionEvent a1 = new AuctionEvent("1", EventType.AUCTION_STARTED, 1000000,1);
		as.notify(al,a1);
		assertNotNull(al);
	}
	
	/**
	 * Testing Method getAuctionEventsStarted
	 */
	@Test
	public void testGetAuctionEventsStarted(){
		ConcurrentHashMap<Integer,AuctionEvent> a = null;
		as.setAuctionEventsStarted(a);
		assertEquals(as.getAuctionEventsStarted(),a);
	}
	
	/**
	 * Testing Method getAuctionEventsEnded
	 */
	@Test
	public void testGetAuctionEventsEnded(){
		ArrayList<AuctionEvent> a = null;
		as.setAuctionEventsEnded(a);
		assertEquals(as.getAuctionEventsEnded(),a);
	}
	
	/**
	 * Testing Method getUserEventsLogin
	 */
	@Test
	public void testGetUserEventsLogin(){
		ConcurrentHashMap<String,ArrayList<UserEvent>> a = null;
		as.setUserEventsLogin(a);
		assertEquals(as.getUserEventsLogin(),a);
	}
	
	/**
	 * Testing Method getUserEventsLogout
	 */
	@Test
	public void testGetUserEventsLogout(){
		ConcurrentHashMap<String,ArrayList<UserEvent>> a = null;
		as.setUserEventsLogout(a);
		assertEquals(as.getUserEventsLogout(),a);
	}
	
	/**
	 * Testing Method getUserEventsLogout
	 */
	@Test
	public void testGetBidEvents(){
		ConcurrentHashMap<Integer,ArrayList<BidEvent>> a = null;
		as.setBidEvents(a);
		assertEquals(as.getBidEvents(),a);
	}
	
	/**
	 * Testing Method GetStatisticsEvents
	 */
	@Test
	public void testGetStatisticsEvents(){
		ConcurrentHashMap<EventType,StatisticsEvent> a = null;
		as.setStatisticsEvents(a);
		assertEquals(as.getStatisticsEvents(),a);
	}
	
	/**
	 * Testing Method GetBidEventsOverbid
	 */
	@Test
	public void testGetBidEventsOverbid(){
		ConcurrentHashMap<Integer,ArrayList<BidEvent>> a = null;
		as.setBidEventsOverbid(a);
		assertEquals(as.getBidEventsOverbid(),a);
	}
	
	/**
	 * Testing Method GetBidEventsWon
	 */
	@Test
	public void testGetBidEventsWon(){
		ConcurrentHashMap<Integer,ArrayList<BidEvent>> a = null;
		as.setBidEventsWon(a);
		assertEquals(as.getBidEventsWon(),a);
	}
	
	/**
	 * Testing Method GetManagementClients
	 */
	@Test
	public void testGetManagementClients(){
		ConcurrentHashMap<String, ConcurrentHashMap<UUID, ManagementClientInterface>> a = null;
		as.setManagementClients(a);
		assertEquals(as.getManagementClients(),a);
	}
	
	/**
	 * Testing Method GetPattern
	 */
	@Test
	public void testGetPattern(){
		ArrayList<String> a = null;
		as.setPattern(a);
		assertEquals(as.getPattern(),a);
	}
	
	/**
	 * Testing Method GetEventHandler
	 */
	@Test
	public void testGetEventHandler(){
		EventHandler a = null;
		as.setEventHandler(a);
		assertEquals(as.getEventHandler(),a);
	}
}
