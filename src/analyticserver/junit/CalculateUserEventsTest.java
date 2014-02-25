package analyticserver.junit;

import static org.junit.Assert.assertEquals;
import model.EventType;
import model.UserEvent;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;
import analyticserver.CalculateUserEvents;

public class CalculateUserEventsTest {
	private AnalyticServer as;
	private CalculateUserEvents cue;
	@Before
	public void setUp() {
		as = new AnalyticServer();
		cue = new CalculateUserEvents(as);
	}
	
	@Test
	public void calculateTest(){
		UserEvent u1 = new UserEvent("1", EventType.USER_LOGIN, 1000000, "u1");
		assertEquals(cue.calculate(u1).size(),1);
	}
}
