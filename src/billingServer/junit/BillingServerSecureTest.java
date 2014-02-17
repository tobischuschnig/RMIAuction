package billingServer.junit;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentHashMap;

import model.Bill;
import model.PriceSteps;

import org.junit.*;

import billingServer.BillingServerSecure;

/**
 * Testclass for BillingServerSecure
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @version 17/02/2014
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
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	
	//TODO not working yet
//	/**
//	 * Testing of CreatePriceStep Method when no Exception should occur and 2
//	 * PriceSteps are created properly
//	 */
//	@Test
//	public void testCreatePriceStepNormalWithTwoElements() throws RemoteException{
//		bss.createPriceStep(0, 100, 3, 5);
//		bss.createPriceStep(101, 200, 5, 6);
//		assertEquals(bss.getPriceSteps().getPriceSteps().size(),2);
//	}
	/**
	 * Testing of CreatePriceStep Method when no Exception should occur, but the
	 * two PriceSteps overlapse and so only the first is created properly
	 */
	@Test//TODO not counting as coverage
	public void testCreatePriceStepOverlapse() throws RemoteException{
		bss.createPriceStep(0, 100, 3, 5);
		bss.createPriceStep(50, 80, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative startPrice
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepStartNegative() throws RemoteException{
		bss.createPriceStep(-1, 100, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative EndPrice
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepEndNegative() throws RemoteException{
		bss.createPriceStep(0, -1, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative fixedValue
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepFixedNegative() throws RemoteException{
		bss.createPriceStep(0, 100, -1, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative VariableValue
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepVariableNegative() throws RemoteException{
		bss.createPriceStep(0, 100, 3, -1);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and the same deleted after that
	 * It checks if the priceStep was deleted
	 * @throws RemoteException 
	 */
	@Test
	public void testdeletePriceStep() throws RemoteException{
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(0, 100);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and some other not existing
	 * PriceStep should be deleted
	 * It checks if the created PriceStep remains in the list
	 * @throws RemoteException 
	 */
	@Test
	public void testdeletePriceStepNotExisting() throws RemoteException{
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(0, 200);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	/**
	 * Testing of billAuction Method, when the bill was created
	 * It checks if the created Bill is in the list
	 */
	@Test
	public void testbillAuction(){
		ConcurrentHashMap<String,Bill> bills = new ConcurrentHashMap<String,Bill>();
		bss.setBills(bills);
		bss.billAuction("test", 123, 100);
		//assertNotNull(bss.getBill("test"));
		assertEquals( bss.getBills().size(),1);
	}
	
	//TODO not checked in BillingServerSecure yet
//	/**
//	 * Testing of billAuction Method, when the bill was created for not existing user
//	 * It checks if the created Bill is in the list, which should not
//	 */
//	@Test
//	public void testbillAuctionUserNotExisting(){
//		ConcurrentHashMap<String,Bill> bills = new ConcurrentHashMap<String,Bill>();
//		bss.setBills(bills);
//		bss.billAuction("none", 123, 100);
//		//assertNull(bss.getBill("none"));
//		assertEquals(bss.getBills().size(),0);
//	}
//	/**
//	 * Testing of billAuction Method, when the bill was created for not existing auction
//	 * It checks if the created Bill is in the list, which should not
//	 */
//	@Test
//	public void testbillAuctionAuctionNotExisting(){
//		ConcurrentHashMap<String,Bill> bills = new ConcurrentHashMap<String,Bill>();
//		bss.setBills(bills);
//		bss.billAuction("test", 123454, 100);
//		//assertNull(bss.getBill("test"));
//		assertEquals(bss.getBills().size(),0);
//	}
	/**
	 * Testing of getBill Method
	 * It checks if the bills of the corresponding user are in the list
	 */
	@Test
	public void testgetBill(){
		ConcurrentHashMap<String,Bill> bills = new ConcurrentHashMap<String,Bill>();
		
		bss.setBills(bills);
		bss.billAuction("test", 123, 100);
		assertNotNull(bss.getBill("test"));
	}
	/**
	 * Testing of getBill Method
	 * It checks if the bills of the corresponding user are in the list, which should not
	 */
	@Test
	public void testgetBillNotExisting(){
		ConcurrentHashMap<String,Bill> bills = new ConcurrentHashMap<String,Bill>();
		
		bss.setBills(bills);
		bss.billAuction("test", 123, 100);
		assertNull(bss.getBill("test1"));
	}
}
