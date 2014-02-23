package analyticserver.junit;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;
import analyticserver.CalculateBidEvents;

public class CalculateBidEventsTest {
	private AnalyticServer as;
	private CalculateBidEvents cbe;
	@Before
	public void setUp() {
		as = new AnalyticServer();
		cbe = new CalculateBidEvents(as);
	}
	
	@Test
	public void testCalculateBidEventAverageTime(){
		
	}
}
