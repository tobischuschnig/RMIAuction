package model.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.security.InvalidParameterException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import model.PriceSteps;
import model.PriceStep;

import org.junit.Before;
import org.junit.Test;

import Exceptions.InvalidInputException;


/**
 * Testclass for PriceSteps 
 * @author Alexander Rieppel <alexander.rieppel@tgm.ac.at>
 *
 */
public class PriceStepsTest {
	private PriceSteps pss;

	@Before
	public void setUp() throws Exception {
		pss = new PriceSteps();
	}

	/**
	 * Testing of toString Method in PriceSteps with empty List
	 */
	@Test
	public void testToString() {
		String b = pss.toString();
		System.out.println(b);
		assertEquals(pss.toString(),b);
	}
	
	/**
	 * Testing of toString Method in PriceSteps with filled List
	 */
	@Test
	public void testToStringFilled() {
		CopyOnWriteArrayList<PriceStep> ps = new CopyOnWriteArrayList<PriceStep>();
		ps.add(new PriceStep(1, 5, 8, 10));
		ps.add(new PriceStep(21, 30, 10, 11));
		ps.add(new PriceStep(15, 18, 10, 11));
		ps.add(new PriceStep(30,0,10,11));
		ps.add(new PriceStep(8, 13, 10, 11));
		ps.add(new PriceStep(19, 20, 10, 11));
		pss.setPriceSteps(ps);
		pss.size();
		assertEquals(pss.getPriceSteps().size(),6);
		String b = pss.toString();
		System.out.println(b);
		assertEquals(pss.toString(),b);
	}
	
	/**
	 * Testing of getPriceSteps Method in PriceSteps
	 */
	@Test
	public void testgetPriceSteps() {
		CopyOnWriteArrayList<PriceStep> ps = new CopyOnWriteArrayList<PriceStep>();
		pss.setPriceSteps(ps);
		assertEquals(pss.getPriceSteps(), ps);
	}
	
	/**
	 * Testing of getFilePriceSteps Method in PriceSteps
	 */
	@Test
	public void testgetFilePriceSteps() {
		pss.setFilePriceSteps("asd");
		assertEquals(pss.getFilePriceSteps(), "asd");
	}
	
	/**
	 * Testing of CreatePriceStep Method when no Exception should occur and
	 * it is created properly
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test
	public void testCreatePriceStepNormal() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100, 3, 5);
		assertEquals(pss.getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when no Exception should occur and 3
	 * PriceSteps are created properly with one Infinite Endprice
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test
	public void testCreatePriceStepNormalWithThreeAndInfiniteElements() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100, 3, 5);
		pss.addPricestep(101, 200, 5, 6);
		pss.addPricestep(200, 0, 5, 6);
		assertEquals(pss.getPriceSteps().size(),3);
	}
	
	/**
	 * Testing of CreatePriceStep Method when no Exception should occur and 3
	 * PriceSteps are created properly with one Infinite Endprice
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test
	public void testCreatePriceStepNormalWithThreeAndInfiniteElements1() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100, 3, 5);
		pss.addPricestep(200, 0, 5, 6);
		pss.addPricestep(101, 200, 5, 6);
		assertEquals(pss.getPriceSteps().size(),3);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception should occur, but the
	 * second PriceSteps overlapse and so only the first is created properly
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepOverlapse() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100, 3, 5);
		pss.addPricestep(50, 80, 3, 5);
		assertEquals(pss.getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception should occur, but the
	 * second PriceSteps overlapse and so only the first is created properly
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test(expected=RemoteException.class)
	public void testCreatePriceStepOverlapse1() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100, 3, 5);
		pss.addPricestep(0, 100, 3, 5);
		assertEquals(pss.getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception should occur, because
	 * startPrice > EndPrice
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test(expected=InvalidInputException.class)
	public void testCreatePriceStepOverlapse2() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(200, 100, 3, 5);
		assertEquals(pss.getPriceSteps().size(),1);
	}
	
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative startPrice
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepStartNegative() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(-1, 100, 3, 5);
		assertEquals(pss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative EndPrice
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepEndNegative() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, -1, 3, 5);
		assertEquals(pss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative fixedValue
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepFixedNegative() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100, -1, 5);
		assertEquals(pss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of CreatePriceStep Method when an Exception is expected, 
	 * because of negative VariableValue
	 * It checks if a priceStep was created, which should not
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testCreatePriceStepVariableNegative() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100, 3, -1);
		assertEquals(pss.getPriceSteps().size(),0);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and the same deleted after that
	 * It checks if the priceStep was deleted
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test
	public void testdeletePriceStep() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100,3,5);
		pss.removePricestep(0, 100);
		assertEquals(pss.getPriceSteps().size(),0);
	}
	
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and the same deleted after that
	 * It checks if the priceStep wasnt deleted because of false startPrice
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test
	public void testdeletePriceStep1() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100,3,5);
		pss.removePricestep(1, 100);
		assertEquals(pss.getPriceSteps().size(),1);
	}
	/**
	 * Testing of DeletePriceStep Method, when a priceStep is created and some other not existing
	 * PriceStep should be deleted
	 * It checks if the created PriceStep remains in the list
	 * @throws RemoteException 
	 * @throws InvalidInputException 
	 * @throws InvalidParameterException 
	 */
	@Test
	public void testdeletePriceStepNotExisting() throws RemoteException, InvalidParameterException, InvalidInputException{
		pss.addPricestep(0, 100,3,5);
		pss.removePricestep(0, 200);
		assertEquals(pss.getPriceSteps().size(),1);
	}
	
	
//	/**
//	 * Testing of getFilePriceSteps Method in PriceSteps
//	 */
//	@Test(expected=FileNotFoundException.class)//TODO tritt nicht auf, da File einfach neu erzeugt wird. Wozu dann Exception eigentlich?
//	public void testFilePathWrong() {
//		ConcurrentHashMap<Integer, PriceStep> ps = new ConcurrentHashMap<Integer, PriceStep>();
//		PriceStep price = new PriceStep(2, 5, 8, 10);
//		ps.put(100,price);
//		pss.setFilePriceSteps("asd");
//		pss.setPriceSteps(ps);
//	}
}
