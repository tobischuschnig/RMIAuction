package analyticserver.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.AuctionEvent;
import model.Event;
import model.EventType;
import model.StatisticsEvent;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;

/**
 * Junit Tests for analytics server which iplements the remote interface
 * to communicate with rmi methods.
 * @author Alexander Auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 08/02/2014
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
	/**
	 * Testing subscribe with correct Input
	 */
	@Test
	public void testSuscribe(){
		// input is a correct input
		String input = "(USER_.*)|(BID_.*)";
		String n = as.suscribe(input);
		assertNotNull(n);
	}
	/**
	 * Testing subscribe with incorrect Input
	 */
	@Test
	public void testSuscribeWrong(){
		// input is a incorrect input
		String input = "§$%&/()=";
		String n = as.suscribe(input);
		assertNull(n);
	}
	/**
	 * Testing unsubscribe with incorrect Input
	 */	
	@Test
	public void testUnsuscribeWrong(){
		// input is a incorrect input
		String input = "§$%&/()=";
		as.unsuscribe(input);
		//assertEquals(as.getList().size(),1);
	}
	/**
	 * Testing unsubscribe with correct Input
	 */	
	@Test
	public void testUnsuscribeW(){
		// input is a correct input
		String input = "1";
		as.unsuscribe(input);
		//assertEquals(as.getList().size(),0);
	}
	/**
	 * Testing processEvent and notify with null Event
	 */
	@Test
	public void testprocessEventNull(){
		Event e =new Event(null, null, 0);
		as.processEvent(e);
		//assertNull(e);
	}
	/**
	 * Testing notify with not-null Event
	 */
	@Test
	public void testNotifyNotNull(){
		ArrayList<StatisticsEvent> al = new ArrayList<StatisticsEvent>();
		al.add(new StatisticsEvent("1", EventType.BID_PRICE_MAX, System.currentTimeMillis(), 0));
		//Event e = new Event("asd",EventType.BID_PRICE_MAX, 12);
		as.notify(al);
		assertNotNull(al);
	}
}
