package analyticserver.junit;

import static org.junit.Assert.*;
import model.BidEvent;
import model.EventType;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;
import analyticserver.CalculateBidEvents;

/**
 * Testclass for Calculate BidEvents
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 *
 */
public class CalculateBidEventsTest {
	private AnalyticServer as;
	private CalculateBidEvents cbe;
	@Before
	public void setUp() {
		as = new AnalyticServer();
		cbe = new CalculateBidEvents(as);
	}
	
	/**
	 * Testing BidAverageTime
	 */
	@Test
	public void testCalculateBidEventAverageTime(){
		assertNotNull(cbe.calculateBidEventAverageTime());
	}
	
	/**
	 * Testing Calculate
	 */
	@Test
	public void testCalculate(){
		BidEvent be = new BidEvent("1", EventType.BID_PLACED,1000001 , "b1", 1,  10);
		assertNotNull(cbe.calculate(be));
	}
}
