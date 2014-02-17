package model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Bill;

public class BillTest {
	private Bill b;

	@Before
	public void setUp() {
		b = new Bill(null, 0, 0);
	}

	/**
	 * Testing of getUsername Method in Bill
	 */
	@Test
	public void testGetUsername() {
		b.setUsername("asd");
		assertEquals(b.getUsername(), "asd");
	}

	/**
	 * Testing of getAuction Method in Bill
	 */
	@Test
	public void testGetAuctionID() {
		b.setAuctionID(1);
		assertEquals(b.getAuctionID(), 1);
	}

	/**
	 * Testing of getPrice Method in Bill
	 */
	@Test
	public void testGetPrice() {
		b.setPrice(1.0);
		assertEquals(b.getPrice(), 1.0, 0);
	}
}
