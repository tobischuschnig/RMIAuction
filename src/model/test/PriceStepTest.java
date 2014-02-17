package model.test;

import static org.junit.Assert.*;
import model.PriceStep;

import org.junit.Before;
import org.junit.Test;

public class PriceStepTest {
	private PriceStep ps;

	@Before
	public void setUp() throws Exception {
		ps = new PriceStep(0, 0, 0, 0);
	}

}
