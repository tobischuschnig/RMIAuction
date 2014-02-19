package model.test;

import static org.junit.Assert.*;
import model.EventType;
import model.StatisticsEvent;

import org.junit.Before;
import org.junit.Test;


/**
 * Testclass for StatisticsEvent 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class StatisticsEventTest {
	private StatisticsEvent se, se1,se2,se3,se4,se5,se6,se7,se8;

	@Before
	public void setUp() throws Exception {
		se = new StatisticsEvent(null, null, 0);
		se1 = new StatisticsEvent(null, null, 0, 0);
		se2 = new StatisticsEvent("asd",EventType.BID_PRICE_MAX, 10L);
		se3 = new StatisticsEvent("asd",EventType.BID_COUNT_PER_MINUTE, 10L);
		se4 = new StatisticsEvent("asd",EventType.USER_SESSIONTIME_MIN, 10L);
		se5 = new StatisticsEvent("asd",EventType.USER_SESSIONTIME_MAX, 10L);
		se6 = new StatisticsEvent("asd",EventType.USER_SESSIONTIME_AVG, 10L);
		se7 = new StatisticsEvent("asd",EventType.AUCTION_TIME_AVG, 10L);
		se8 = new StatisticsEvent("asd",EventType.ACUTION_SUCCESS_RATIO, 10L);
	}

	
	/**
	 * Testing of getVariablePricePercent Method in PriceStep
	 */
	@Test
	public void testGetValue() {
		se.setValue(100.0);
		assertEquals(se.getValue(), 100.0,0);
	}
	
	/**
	 * Testing of Method toString with StatisticsEvent BID_PRICE_MAX
	 */
	@Test
	public void testToStringBPMAX() {
		System.out.println(se2.toString());
		assertEquals(se2.toString(),"BID_PRICE_MAX: 01.01.1970 01:00:00:00 - maximum bid price seen so far is 0.0");
	}
	
	/**
	 * Testing of Method toString with StatisticsEvent BID_COUNT_PER_MINUTE
	 */
	@Test
	public void testToStringBCPM() {
		System.out.println(se3.toString());
		assertEquals(se3.toString(),"BID_COUNT_PER_MINUTE: 01.01.1970 01:00:00:00 - current bids per minute is 0.0");
	}
	
	/**
	 * Testing of Method toString with StatisticsEvent USER_SESSIONTIME_MIN
	 */
	@Test
	public void testToStringUSTMIN() {
		System.out.println(se4.toString());
		assertEquals(se4.toString(),"USER_SESSIONTIME_MIN: 01.01.1970 01:00:00:00 - minimum session time is 0.0 seconds");
	}
	
	/**
	 * Testing of Method toString with StatisticsEvent USER_SESSIONTIME_MAX
	 */
	@Test
	public void testToStringUSTMAX() {
		System.out.println(se5.toString());
		assertEquals(se5.toString(),"USER_SESSIONTIME_MAX: 01.01.1970 01:00:00:00 - maximum session time is 0.0 seconds");
	}
	
	/**
	 * Testing of Method toString with StatisticsEvent USER_SESSIONTIME_AVG
	 */
	@Test
	public void testToStringUSTAVG() {
		System.out.println(se6.toString());
		assertEquals(se6.toString(),"USER_SESSIONTIME_AVG: 01.01.1970 01:00:00:00 - average session time is 0.0 seconds");
	}
	
	/**
	 * Testing of Method toString with StatisticsEvent AUCTION_TIME_AVG
	 */
	@Test
	public void testToStringATAVG() {
		System.out.println(se7.toString());
		assertEquals(se7.toString(),"AUCTION_TIME_AVG: 01.01.1970 01:00:00:00 - auction timme average is 0.0 seconds");
	}
	
	/**
	 * Testing of Method toString with StatisticsEvent AUCTION_SUCESS_RATIO
	 */
	@Test
	public void testToStringASRAT() {
		System.out.println(se8.toString());
		assertEquals(se8.toString(),"ACUTION_SUCCESS_RATIO: 01.01.1970 01:00:00:00 - auction succes ratio is0.0");
	}
}
