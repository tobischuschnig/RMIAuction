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
		b = new Bill("asd", 5, 100);
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
	
	/**
	 * Testing of getFeeFixed Method in Bill
	 */
	@Test
	public void testGetFeeFixed() {
		b.setFeeFixed(100.0);
		assertEquals(b.getFeeFixed(), 100.0, 0);
	}
	
	/**
	 * Testing of getFeeVariable Method in Bill
	 */
	@Test
	public void testGetFeeVariable() {
		b.setFeeVariable(100.0);
		assertEquals(b.getFeeVariable(), 100.0, 0);
	}
	
	/**
	 * Testing of getFeeTotal Method in Bill
	 */
	@Test
	public void testGetFeeTotal() {
		b.setFeeTotal(100.0);
		assertEquals(b.getFeeTotal(), 100.0, 0);
	}
	
	/**
	 * Testing of toString Method in Bill
	 */
	@Test
	public void testToString() {
		System.out.println(b.toString());
		assertEquals(b.toString(), "5	100.0	0.0	0.0	0.0");
	}
}
