package billingServer.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import model.Bill;
import model.PriceSteps;

import org.junit.*;

import billingServer.BillingServerSecure;

/**
 * TDD Testclass for BillingServerSecure
 * @author Alexander Rieppel
 * @email <arieppel@student.tgm.ac.at>
 */
public class BillingServerSecureTest {
	private BillingServerSecure bss;
	
	@Before
	public void setUp(){
		bss = new BillingServerSecure();
	}
	
	@Test
	public void testGetPriceSteps(){
		PriceSteps ps = new PriceSteps();
		bss.setPriceSteps(ps);
		assertEquals(bss.getPriceSteps(),ps);
	}
	@Test
	public void testCreatePriceStepNormal() throws RemoteException{
		bss.createPriceStep(0, 100, 3, 5);
		assertEquals(bss.getPriceSteps().size(),1);
	}
	@Test
	public void testCreatePriceStepOverlapse() throws RemoteException{
		bss.createPriceStep(0, 100, 3, 5);
		bss.createPriceStep(50, 150, 3, 5);
		assertEquals(bss.getPriceSteps().size(),1);
	}
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepStartNegative(){
		bss.createPriceStep(-1, 100, 3, 5);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepEndNegative(){
		bss.createPriceStep(0, -1, 3, 5);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepFixedNegative(){
		bss.createPriceStep(0, 100, -1, 5);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepVariableNegative(){
		bss.createPriceStep(0, 100, 3, -1);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	@Test
	public void testdeletePriceStep(){
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(0, 100);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	@Test
	public void testdeletePriceStepNotExisting(){
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(0, 200);
		assertEquals(bss.getPriceSteps().size(),1);
	}
	
	@Test
	public void testbillAuction(){
		bss.billAuction("test", 123, 100);
		assertEquals(bss.getBills().size(),1);
	}
	@Test
	public void testbillAuctionUserNotExisting(){
		bss.billAuction("none", 123, 100);
		assertEquals(bss.getBills().size(),0);
	}
	@Test
	public void testbillAuctionAuctionNotExisting(){
		bss.billAuction("test", 123454, 100);
		assertEquals(bss.getBills().size(),0);
	}
	@Test
	public void testgetBill(){
		Bill b = new Bill("test", 0, 0);
		bss.setBill(b);
		assertEquals(bss.getBill(null),b);
	}
}
