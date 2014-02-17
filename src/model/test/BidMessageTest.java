package model.test;

import static org.junit.Assert.assertEquals;
import model.BidMessage;

import org.junit.Before;
import org.junit.Test;

public class BidMessageTest {
	private BidMessage bm, bm1;

	@Before
	public void setUp() {
		bm = new BidMessage();
		bm1 = new BidMessage(null, 0, 0);
	}

	/**
	 * Testing of getName Method in BidMessage
	 */
	@Test
	public void testGetNameBidMessage() {
		bm.setName("asd");
		;
		assertEquals(bm.getName(), "asd");
	}

	/**
	 * Testing of getId Method in BidMessage
	 */
	@Test
	public void testGetIDBidMessage() {
		bm.setId(1);
		assertEquals(1, bm.getId());
	}

	/**
	 * Testing of getAmount Method in BidMessage
	 */
	@Test
	public void testGetAmountBidMessage() {
		bm.setAmount(1);
		assertEquals(1, bm.getAmount(), 0);
	}
}
