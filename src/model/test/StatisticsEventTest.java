package model.test;

import static org.junit.Assert.*;
import model.StatisticsEvent;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for StatisticsEvent 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class StatisticsEventTest {
	private StatisticsEvent se, se1;

	@Before
	public void setUp() throws Exception {
		se = new StatisticsEvent(null, null, 0);
		se1 = new StatisticsEvent(null, null, 0, 0);
	}

	
	/**
	 * Testing of getVariablePricePercent Method in PriceStep
	 */
	@Test
	public void testGetValue() {
		se.setValue(100.0);
		assertEquals(se.getValue(), 100.0,0);
	}
}
