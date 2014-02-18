package model.test;

import static org.junit.Assert.*;
import model.PriceStep;

import org.junit.Before;
import org.junit.Test;

/**
 * Testclass for PriceStep
 * 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 * 
 */
public class PriceStepTest {
	private PriceStep ps;

	@Before
	public void setUp() throws Exception {
		ps = new PriceStep(0, 0, 0, 0);
	}

	/**
	 * Testing of getStartPrice Method in PriceStep
	 */
	@Test
	public void testGetStartPrice() {
		ps.setStartPrice(100.0);
		assertEquals(ps.getStartPrice(), 100.0, 0);
	}

	/**
	 * Testing of getEndPrice Method in PriceStep
	 */
	@Test
	public void testGetEndPrice() {
		ps.setEndPrice(100.0);
		assertEquals(ps.getEndPrice(), 100.0, 0);
	}

	/**
	 * Testing of getFixedPrice Method in PriceStep
	 */
	@Test
	public void testGetFixedPrice() {
		ps.setFixedPrice(100.0);
		assertEquals(ps.getFixedPrice(), 100.0, 0);
	}

	/**
	 * Testing of getVariablePricePercent Method in PriceStep
	 */
	@Test
	public void testGetVariablePricePercent() {
		ps.setVariablePricePercent(100.0);
		assertEquals(ps.getVariablePricePercent(), 100.0, 0);
	}

}
