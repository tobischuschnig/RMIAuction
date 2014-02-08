package billingServer.test;

import static org.junit.Assert.*;
import model.Bill;
import model.PriceSteps;

import org.junit.*;

import billingServer.BillingServerSecure;

public class BillingServerSecureTest {
	private BillingServerSecure bss;
	
	@Before
	public void setUp(){
		bss = new BillingServerSecure();
	}
	
	@Test
	public void testGetPriceSteps(){
		PriceSteps ps = new PriceSteps(null);
		bss.setPriceSteps(ps);
		assertEquals(bss.getPriceSteps(),ps);
		
	}
	@Test
	public void testCreatePriceStep(){
		bss.createPriceStep(0, 100, 3, 5);
		//TODO Assert
		
	}
	@Test
	public void testdeletePriceStep(){
		bss.deletePriceStep(0, 100);
		//TODO Assert
	}
	@Test
	public void testbillAuction(){
		bss.billAuction("test", 123, 100);
		//TODO Assert
	}
	@Test
	public void testgetBill(){
		Bill b = new Bill(null, 0, 0, 0, 0, 0);
		bss.setBill(b);
		assertEquals(bss.getBill(null),b);
	}
}
