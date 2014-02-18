package model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Bill;


/**
 * Testclass for Bill 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
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
		b.setUser("asd");
		assertEquals(b.getUser(), "asd");
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
		b.setStrikePrice(1.0);
		assertEquals(b.getStrikePrice(), 1.0, 0);
	}
}
