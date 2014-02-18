package model.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.concurrent.ConcurrentHashMap;

import model.PriceSteps;
import model.PriceStep;

import org.junit.Before;
import org.junit.Test;


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
		assertEquals(pss.toString(),"Min_Price\tMax_Price\tFee_Fixed\tFee_Variable");
	}
	
	/**
	 * Testing of toString Method in PriceSteps with filled List
	 */
	@Test//TODO Funktioniert noch nicht gscheit
	public void testToStringFilled() {
		ConcurrentHashMap<Integer, PriceStep> ps = new ConcurrentHashMap<Integer, PriceStep>();
		PriceStep price = new PriceStep(2, 5, 8, 10);
		ps.put(100,price);
		pss.setPriceSteps(ps);
		assertEquals(pss.getPriceSteps().size(),1);
		assertEquals(pss.toString(),"Min_Price\tMax_Price\tFee_Fixed\tFee_Variable");
	}
	
	/**
	 * Testing of getPriceSteps Method in PriceSteps
	 */
	@Test
	public void testgetPriceSteps() {
		ConcurrentHashMap<Integer, PriceStep> ps = new ConcurrentHashMap<Integer, PriceStep>();
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
