package managmentclient.junit;

import static org.junit.Assert.*;

import managmentclient.ManagementClient;

import org.junit.Before;
import org.junit.Test;

/**
 * TDD Testclass for ManagementClient
 * @author Alexander Rieppel
 * @email <arieppel@student.tgm.ac.at>
 */
public class ManagementCLientInputTest {

private ManagementClient mc;
	
	@Before
	public void setUp(){
		mc = new ManagementClient("AnalyticServer", "BillingServer");
	}
	/**
	 * Testing of no input to Console should equals false
	 */
	@Test
	public void testNoInput() {
		boolean b;
		b=mc.input("");
		assertFalse(b);
	}
	/**
	 * Testing of wrong command to Console should equals false
	 */
	@Test
	public void testWrongCommand() {
		boolean b;
		b=mc.input("!buy");
		assertFalse(b);
	}
	/**
	 * Testing of !login with correct credentials should equals true
	 */
	@Test
	public void testLogin() {
		boolean b;
		b=mc.input("!login admin admin");
		assertTrue(b);
	}
	/**
	 * Testing of !login with correct credentials but two times, so the second one should equals false
	 */
	@Test
	public void testDoubleLogin() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!login admin admin");
		assertFalse(b);
	}
	/**
	 * Testing of !login with false command should equals false
	 */
	@Test
	public void testLoginNoPass() {
		boolean b;
		b=mc.input("!login admin");
		assertFalse(b);
	}
	/**
	 * Testing of !login with wrong credentials should equals false
	 */
	@Test
	public void testLoginWrong() {
		boolean b;
		b=mc.input("!login adminZ adminY");
		assertFalse(b);
	}
	/**
	 * Testing of !logout with correct credentials should equals true
	 */
	@Test
	public void testLogout() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!logout");
		assertTrue(b);
	}
	/**
	 * Testing of !logout without login should equals false
	 */
	@Test
	public void testLogoutWithoutLogin() {
		boolean b;
		b=mc.input("!logout");
		assertFalse(b);
	}
	/**
	 * Testing of !steps without login should equals false
	 */
	@Test
	public void testSteps() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!steps");
		assertTrue(b);
	}
//	/**
//	 * Testing of !end should equals true
//	 */
//	@Test
//	public void testEnd() {
//		boolean b;
//		b=mc.input("!end");
//		assertTrue(b);
//	}
	/**
	 * Testing of !addstep should equals true
	 */
	@Test
	public void testAddSteps() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep 11.0 16.0 55.0 9.0");
		mc.input("!removestep 11.0 16.0");
		assertTrue(b);
	}
	/**
	 * Testing of !removestep should equals true
	 */
	@Test
	public void testRemoveSteps() {
		boolean b;
		System.out.println("!!!");
		mc.input("!login admin admin");
		mc.input("!addstep 11.0 16.0 55.0 9.0");
		b=mc.input("!removestep 11.0 16.0");
		assertTrue(b);
	}
	/**
	 * Testing of !addstep without login should equals false
	 */
	@Test
	public void testAddStepsNoLogin() {
		boolean b;
		b=mc.input("!addstep 11.0 16.0 55.0 9.0");
		assertFalse(b);
	}
	/**
	 * Testing of !addstep with wrong format should equals false
	 */
	@Test
	public void testAddStepsWrongFormat() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep ee.3 16.0 55.0 9.0");
		assertFalse(b);
	}
	/**
	 * Testing of !addstep with wrong arguments should equals false
	 */
	@Test
	public void testAddStepsWrongArguments() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep 16.0 55.0 9.0");
		assertFalse(b);
	}
	/**
	 * Testing of !addstep with wrong parameter should equals false
	 */
	@Test
	public void testAddStepsFalseParameter() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep 3.0 2.0 3.0 4.0");
		assertFalse(b);
	}
	/**
	 * Testing of !bill without login should equals false
	 */
	@Test
	public void testbillWithoutLogin() {
		boolean b;
		b=mc.input("!bill Bill");
		assertFalse(b);
	}
	/**
	 * Testing of !bill without user should equals false
	 */
	@Test
	public void testbillWithoutUser() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!bill");
		assertFalse(b);
	}
	/**
	 * Testing of !subscribe should equals true
	 */
	@Test
	public void testSubscribe() {
		boolean b;
		b=mc.input("!subscribe (USER_.*)|(BID_.*)");
		assertTrue(b);
	}
	/**
	 * Testing of !unsubscribe should equals true
	 */
	@Test
	public void testUnSubscribeCall() {
		boolean b;
		b=mc.input("!unsubscribe eeee");
		assertTrue(b);
	}
	/**
	 * Testing of !unsubscribe without id should equals false
	 */
	@Test
	public void testUnSubscribeNoId() {
		boolean b;
		b=mc.input("!unsubscribe");
		assertFalse(b);
	}
	/**
	 * Testing of !auto should equals true
	 */
	@Test
	public void testAuto() {
		boolean b;
		b=mc.input("!auto");
		assertTrue(b);
	}
	/**
	 * Testing of !hide should equals true
	 */
	@Test
	public void testHide() {
		boolean b;
		b=mc.input("!hide");
		assertTrue(b);
	}
	/**
	 * Testing of !print should equals true
	 */
	@Test
	public void testPrint() {
		boolean b;
		b=mc.input("!print");
		assertTrue(b);
	}
	/**
	 * Testing of !bill should equals true
	 */
	@Test
	public void testbill() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!bill Bill");
		assertTrue(b);
	}
	/**
	 * Testing of !bill should equals true
	 */
	@Test
	public void testBill() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!bill Bill");
		assertTrue(b);
	}
	/**
	 * Testing of !login with a space before should equals true
	 */
	@Test
	public void testInputSpace() {
		boolean b;
		b=mc.input(" !login admin admin");
		assertTrue(b);
	}
	/**
	 * Testing of !bill without login should equals false
	 */
	@Test
	public void testBillwothoutLogin() {
		boolean b;
		b=mc.input("!bill bill");
		assertFalse(b);
	}
	/**
	 * Testing of !steps without login should equals false
	 */
	@Test
	public void testStepsWithoutLogin() {
		boolean b;
		b=mc.input("!steps");
		assertFalse(b);
	}
	/**
	 * Testing of !subscribe without login should equals false
	 */
	@Test
	public void testSubscribeWithoutLogin() {
		boolean b;
		b=mc.input("!subscribe 1.0 2.0 3.0 4.0");
		assertFalse(b);
	}
	/**
	 * Testing of !subscribe with wrong arguments should equals false
	 */
	@Test
	public void testSubscribeWrongArgumens() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!subscribe 1.0 2.0 3.0");
		assertFalse(b);
	}
	/**
	 * Testing of !removestep with wrong arguments should equals false
	 */
	@Test
	public void testRemoveStepWrongArguments() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!removestep 1.0");
		assertFalse(b);
	}
	/**
	 * Testing of !removestep without login should equals false
	 */
	@Test
	public void testRemoveStepNoLogin() {
		boolean b;
		b=mc.input("!removestep 1.0 2.0");
		assertFalse(b);
	}
	/**
	 * Testing of !removestep with wrong format should equals false
	 */
	@Test
	public void testRemoveStepNumberFormat() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!removestep 1.eer 2.0");
		assertFalse(b);
	}
	/**
	 * Testing of !removestep with with not existing step should equals false
	 */
	@Test
	public void testRemoveStepCannotRemove() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!removestep 88 99");
		assertFalse(b);
	}
	
}
