package model.test;

import static org.junit.Assert.*;
import model.StatisticsEvent;

import org.junit.Before;
import org.junit.Test;

public class StatisticsEventTest {
	private StatisticsEvent se, se1;

	@Before
	public void setUp() throws Exception {
		se = new StatisticsEvent(null, null, 0);
		se1 = new StatisticsEvent(null, null, 0, 0);
	}

}
