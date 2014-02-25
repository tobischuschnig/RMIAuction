package billingServer.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.rmi.RemoteException;
import java.security.InvalidParameterException;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Bill;
import model.PriceSteps;

import org.junit.Before;
import org.junit.Test;

import Exceptions.InvalidInputException;
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
	 * @throws InvalidInputException 
	 */
	@Test
	public void testCreatePriceStepNormal() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when no Exception should occur and 2
	 * PriceSteps are created properly
	 * @throws InvalidInputException 
	 */
	@Test
	public void testCreatePriceStepNormalWithTwoElements() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100, 3, 5);
		bss.createPriceStep(101, 200, 5, 6);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),2);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception should occur, but the
	 * second PriceSteps overlapse and so only the first is created properly
	 * @throws InvalidInputException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepOverlapse() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100, 3, 5);
		bss.createPriceStep(50, 80, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception should occur, but the
	 * second PriceSteps overlapse and so only the first is created properly
	 * @throws InvalidInputException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepOverlapse1() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100, 3, 5);
		bss.createPriceStep(0, 100, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception should occur, because
	 * startPrice > EndPrice
	 * @throws InvalidInputException 
	 */
	@Test(expected=InvalidInputException.class)
	public void testCreatePriceStepOverlapse2() throws RemoteException, InvalidInputException{
		bss.createPriceStep(200, 100, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative startPrice
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepStartNegative() throws RemoteException, InvalidInputException{
		bss.createPriceStep(-1, 100, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative EndPrice
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepEndNegative() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, -1, 3, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative fixedValue
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepFixedNegative() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100, -1, 5);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative VariableValue
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepVariableNegative() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100, 3, -1);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and the same deleted after that
	 * It checks if the priceStep was deleted
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test
	public void testdeletePriceStep() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(0, 100);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),0);
	}
	
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and the same deleted after that
	 * It checks if the priceStep wasnt deleted because of false startPrice
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test
	public void testdeletePriceStep1() throws RemoteException, InvalidInputException{
		bss.createPriceStep(0, 100,3,5);
		bss.deletePriceStep(1, 100);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),1);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and some other not existing
	 * PriceStep should be deleted
	 * It checks if the created PriceStep remains in the list
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test
	public void testdeletePriceStepNotExisting() throws RemoteException, InvalidInputException{
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
		CopyOnWriteArrayList<Bill> bills = new CopyOnWriteArrayList<Bill>();
		bss.setBills(bills);
		bss.billAuction("test", 123, 100);
		assertEquals( bss.getBills().size(),1);
	}
	/**
	 * Testing of billAuction Method, when the bill was created
	 * It checks if the created Bill is in the list
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 */
	@Test
	public void testbillAuction1() throws RemoteException, InvalidInputException{
		CopyOnWriteArrayList<Bill> bills = new CopyOnWriteArrayList<Bill>();
		bss.createPriceStep(0,100, 3, 5);
		bss.createPriceStep(101,200, 5, 6);
		bss.createPriceStep(201,400, 7, 8);
		bss.setBills(bills);
		//TODO Solve Problem with infinite priceStep
		bss.billAuction("test", 123, 50);
		bss.billAuction("test1", 123, 100);
		bss.billAuction("test2", 123, 100);
		bss.billAuction("test3", 123, 100);
		bss.billAuction("test4", 123, 100);
		bss.billAuction("test5", 123, 100);
		assertEquals( bss.getBills().size(),6);
		assertEquals(bss.getPriceSteps().getPriceSteps().size(),3);
	}
	
	/**
	 * Testing of getBill Method
	 * It checks if the bills of the corresponding user are in the list
	 */
	@Test
	public void testgetBill(){
		CopyOnWriteArrayList<Bill> bills = new CopyOnWriteArrayList<Bill>();
		
		bss.setBills(bills);
		bss.billAuction("test", 123, 100);
		assertNotNull(bss.getBill("test"));
	}
}
