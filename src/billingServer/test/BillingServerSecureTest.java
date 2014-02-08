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
	public void testCreatePriceStepNormal(){
		bss.createPriceStep(0, 100, 3, 5);
		//TODO Assert
	}
	@Test
	public void testCreatePriceStepOverlapse(){
		bss.createPriceStep(0, 100, 3, 5);
		bss.createPriceStep(50, 150, 3, 5);
		//TODO Assert
	}
	@Test
	public void testCreatePriceStepStartNegative(){
		bss.createPriceStep(-1, 100, 3, 5);
		//TODO Assert
	}
	@Test
	public void testCreatePriceStepEndNegative(){
		bss.createPriceStep(0, -1, 3, 5);
		//TODO Assert
	}
	@Test
	public void testCreatePriceStepFixedNegative(){
		bss.createPriceStep(0, 100, -1, 5);
		//TODO Assert
	}
	@Test
	public void testCreatePriceStepVariableNegative(){
		bss.createPriceStep(0, 100, 3, -1);
		//TODO Assert
	}
	@Test
	public void testdeletePriceStep(){
		bss.deletePriceStep(0, 100);
		//TODO Assert
	}
	@Test
	public void testdeletePriceStepNotExisting(){
		bss.deletePriceStep(0, 100);
		//TODO Assert
	}
	@Test
	public void testbillAuction(){
		bss.billAuction("test", 123, 100);
		//TODO Assert
	}
	@Test
	public void testbillAuctionUserNotExisting(){
		bss.billAuction("none", 123, 100);
		//TODO Assert
	}
	@Test
	public void testbillAuctionAuctionNotExisting(){
		bss.billAuction("test", 123454, 100);
		//TODO Assert
	}
	@Test
	public void testbillAuctionPriceInvalid(){
		bss.billAuction("test", 123, -1);
		//TODO Assert
	}
	@Test
	public void testbillAuctionAuctionInvalid(){
		bss.billAuction("test", -1, 100);
		//TODO Assert
	}
	@Test
	public void testgetBill(){
		Bill b = new Bill(null, 0, 0, 0, 0, 0);
		bss.setBill(b);
		assertEquals(bss.getBill(null),b);
	}
}
