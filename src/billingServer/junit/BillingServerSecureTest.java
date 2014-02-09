package billingServer.junit;

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
	
	/**
	 * Testing of getPriceSteps Method
	 */
	@Test
	public void testGetPriceSteps(){
		PriceSteps ps = new PriceSteps();
		bss.setPriceSteps(ps);
		assertEquals(bss.getPriceSteps(),ps);
	}
	/**
	 * Testing of CreatePriceStep Method when no Exception should occur and
	 * it is created properly
	 */
	@Test
	public void testCreatePriceStepNormal() throws RemoteException{
		bss.createPriceStep(0, 100, 3, 5);
		assertEquals(bss.getPriceSteps().size(),1);
	}
	/**
	 * Testing of CreatePriceStep Method when no Exception should occur, but the
	 * two PriceSteps overlapse and so only the first is created properly
	 */
	@Test
	public void testCreatePriceStepOverlapse() throws RemoteException{
		bss.createPriceStep(0, 100, 3, 5);
		bss.createPriceStep(50, 150, 3, 5);
		assertEquals(bss.getPriceSteps().size(),1);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative startPrice
	 * It checks if a priceStep was created, which should not
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepStartNegative(){
		bss.createPriceStep(-1, 100, 3, 5);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative EndPrice
	 * It checks if a priceStep was created, which should not
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepEndNegative(){
		bss.createPriceStep(0, -1, 3, 5);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative fixedValue
	 * It checks if a priceStep was created, which should not
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepFixedNegative(){
		bss.createPriceStep(0, 100, -1, 5);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative VariableValue
	 * It checks if a priceStep was created, which should not
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepVariableNegative(){
		bss.createPriceStep(0, 100, 3, -1);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and the same deleted after that
	 * It checks if the priceStep was deleted
	 */
	@Test
	public void testdeletePriceStep(){
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(0, 100);
		assertEquals(bss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and some other not existing
	 * PriceStep should be deleted
	 * It checks if the created PriceStep remains in the list
	 */
	@Test
	public void testdeletePriceStepNotExisting(){
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(0, 200);
		assertEquals(bss.getPriceSteps().size(),1);
	}
	/**
	 * Testing of billAuction Method, when the bill was created
	 * It checks if the created Bill is in the list
	 */
	@Test
	public void testbillAuction(){
		bss.billAuction("test", 123, 100);
		assertEquals(bss.getBills().size(),1);
	}
	/**
	 * Testing of billAuction Method, when the bill was created for not existing user
	 * It checks if the created Bill is in the list, which should not
	 */
	@Test
	public void testbillAuctionUserNotExisting(){
		bss.billAuction("none", 123, 100);
		assertEquals(bss.getBills().size(),0);
	}
	/**
	 * Testing of billAuction Method, when the bill was created for not existing auction
	 * It checks if the created Bill is in the list, which should not
	 */
	@Test
	public void testbillAuctionAuctionNotExisting(){
		bss.billAuction("test", 123454, 100);
		assertEquals(bss.getBills().size(),0);
	}
	/**
	 * Testing of getBill Method
	 * It checks if the bills of the corresponding user are in the list
	 */
	@Test
	public void testgetBill(){
		Bill b = new Bill("test", 0, 0);
		bss.setBill(b);
		assertEquals(bss.getBill("test"),b);
	}
}
