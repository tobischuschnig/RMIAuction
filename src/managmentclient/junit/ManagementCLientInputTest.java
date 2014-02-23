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
		mc = new ManagementClient("BillingServer", "AnalyticServer");
	}
	@Test
	public void testNoInput() {
		boolean b;
		b=mc.input("");
		assertFalse(b);
	}
	@Test
	public void testWrongCommand() {
		boolean b;
		b=mc.input("!buy");
		assertFalse(b);
	}
	@Test
	public void testLogin() {
		boolean b;
		b=mc.input("!login admin admin");
		assertTrue(b);
	}
	@Test
	public void testDoubleLogin() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!login admin admin");
		assertFalse(b);
	}
	@Test
	public void testLoginNoPass() {
		boolean b;
		b=mc.input("!login admin");
		assertFalse(b);
	}
	@Test
	public void testLoginWrong() {
		boolean b;
		b=mc.input("!login adminZ adminY");
		assertFalse(b);
	}
	@Test
	public void testLogout() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!logout");
		assertTrue(b);
	}
	@Test
	public void testLogoutWithoutLogin() {
		boolean b;
		b=mc.input("!logout");
		assertFalse(b);
	}
	@Test
	public void testSteps() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!steps");
		assertTrue(b);
	}
	@Test
	public void testEnd() {
		boolean b;
		b=mc.input("!end");
		assertTrue(b);
	}

	@Test
	public void testAddSteps() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep 11.0 16.0 55.0 9.0");
		mc.input("!removestep 11.0 16.0");
		assertTrue(b);
	}
	@Test
	public void testRemoveSteps() {
		boolean b;
		System.out.println("!!!");
		mc.input("!login admin admin");
		mc.input("!addstep 11.0 16.0 55.0 9.0");
		b=mc.input("!removestep 11.0 16.0");
		assertTrue(b);
	}
	@Test
	public void testAddStepsNoLogin() {
		boolean b;
		b=mc.input("!addstep 11.0 16.0 55.0 9.0");
		assertFalse(b);
	}
	@Test
	public void testAddStepsWrongFormat() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep ee.3 16.0 55.0 9.0");
		assertFalse(b);
	}
	@Test
	public void testAddStepsWrongArguments() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep 16.0 55.0 9.0");
		assertFalse(b);
	}
	@Test
	public void testAddStepsFalseParameter() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!addstep 3.0 2.0 3.0 4.0");
		assertFalse(b);
	}
	@Test
	public void testbillWithoutLogin() {
		boolean b;
		b=mc.input("!bill Bill");
		assertFalse(b);
	}
	@Test
	public void testbillWithoutUser() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!bill");
		assertFalse(b);
	}
	@Test
	public void testSubscribe() {
		boolean b;
		b=mc.input("!subscribe (USER_.*)|(BID_.*)");
		assertTrue(b);
	}
	@Test
	public void testUnSubscribeCall() {
		boolean b;
		b=mc.input("!unsubscribe eeee");
		assertTrue(b);
	}
	@Test
	public void testUnSubscribeNoId() {
		boolean b;
		b=mc.input("!unsubscribe");
		assertFalse(b);
	}
	@Test
	public void testAuto() {
		boolean b;
		b=mc.input("!auto");
		assertTrue(b);
	}
	@Test
	public void testHide() {
		boolean b;
		b=mc.input("!hide");
		assertTrue(b);
	}
	@Test
	public void testPrint() {
		boolean b;
		b=mc.input("!print");
		assertTrue(b);
	}
	@Test
	public void testbill() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!bill Bill");
		assertTrue(b);
	}
	@Test
	public void testBill() {
		boolean b;
		mc.input("!login admin admin");
		b=mc.input("!bill Bill");
		assertTrue(b);
	}
	@Test
	public void testInputSpace() {
		boolean b;
		b=mc.input(" !login admin admin");
		assertTrue(b);
	}
	@Test
	public void testBillwothoutLogin() {
		boolean b;
		b=mc.input("!bill bill");
		assertFalse(b);
	}
	@Test
	public void testStepsWithoutLogin() {
		boolean b;
		b=mc.input("!steps");
		assertFalse(b);
	}
	@Test
	public void testSubscribeWithoutLogin() {
		boolean b;
		b=mc.input("!subscribe 1.0 2.0 3.0 4.0");
		assertFalse(b);
	}
	@Test
	public void testSubscribeWrongArgumens() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!subscribe 1.0 2.0 3.0");
		assertFalse(b);
	}
	@Test
	public void testRemoveStepWrongArguments() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!removestep 1.0");
		assertFalse(b);
	}
	@Test
	public void testRemoveStepNoLogin() {
		boolean b;
		b=mc.input("!removestep 1.0 2.0");
		assertFalse(b);
	}
	@Test
	public void testRemoveStepNumberFormat() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!removestep 1.eer 2.0");
		assertFalse(b);
	}
	@Test
	public void testRemoveStepCannotRemove() {
		boolean b;
		mc.input(" !login admin admin");
		b=mc.input("!removestep 88 99");
		assertFalse(b);
	}
	
}
