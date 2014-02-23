package managmentclient.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Queue;

import managmentclient.CLI;
import managmentclient.ManagementClient;
import managmentclient.TaskExecuter;
import model.Event;

import org.junit.Before;
import org.junit.Test;

/**
 * TDD Testclass for ManagementClient
 * @author Alexander Rieppel
 * @email <arieppel@student.tgm.ac.at>
 */
public class ManagementClientTest {
	
	private ManagementClient mc;
	
	@Before
	public void setUp(){
		mc = new ManagementClient("BillingServer", "AnalyticServer");
	}
	
	/**
	 * Testing of auto Method, when autoprint should equals true
	 */
	@Test
	public void testAuto(){
		mc.auto();
		assertTrue(mc.isAutoprint());
	}
	
	/**
	 * Testing of hide Method, when login autoprint should equals false
	 */
	@Test
	public void testHide(){
		mc.hide();
		assertFalse(mc.isAutoprint());
	}
	/**
	 * Testing of processEvent Method, when autoprint equals true and no Events are written to the Queue
	 */
	@Test
	public void testProcessEventAutoPrint(){
		String e = "USER_LOGIN";
		mc.setAutoprint(true);
		mc.processEvent(e);
		assertEquals(mc.getUnprintedMessages().size(),0);
	}
	/**
	 * Testing of processEvent Method, when autoprint equals false and all Events are written to the Queue
	 */
	@Test
	public void testProcessEventHide(){
		String e = "USER_LOGIN";
		mc.setAutoprint(false);
		mc.processEvent(e);
		assertEquals(mc.getUnprintedMessages().size(),1);
	}
//	/**
//	 * Testing of startService Method, when everything should do fine and no exceptions are thrown
//	 */
//	@Test
//	public void testStartService() throws AlreadyBoundException,RemoteException{
//		assertTrue(mc.startService());
//	}
//	/**
//	 * Testing of startService Method, when a second attempt to start should fail, due to multiple binding
//	 * AlreadyBoundException should be thrown
//	 */
//	@Test(expected=AlreadyBoundException.class)
//	public void testStartServiceAlreadyBound(){
//		assertTrue(mc.startService());
//		assertFalse(mc.startService());
//	}
//	/**
//	 * Testing of startService Method, when the remote addresses are invalid
//	 * RemoteException should be thrown
//	 */
//	@Test(expected=RemoteException.class)
//	public void testStartServiceFalseBinding(){
//		ManagementClient mc1 = new ManagementClient("10.0.0.1","10.0.0.2");
//		assertFalse(mc1.startService());
//	}
	
	/**
	 * Testing of the run Method
	 */
	@Test
	public void testsetActive(){
		mc.setActive(true);
	//	mc.run();
	}
	
//	/**
//	 * Testing of the run Method with loggedIn true
//	 */
//	@Test
//	public void testRun1(){
//		mc.setActive(true);
//		mc.setLoggedIn(true);
//		mc.run();
//	}
	
	
	/**
	 * Testing of print Method
	 */
	@Test
	public void testPrint(){
		Queue<String> um = new LinkedList<String>();
		String e = "USER_LOGIN";
		String e1 = "USER_LOGOUT";
		um.add(e);
		um.add(e1);
		mc.setUnprintedMessages(um);
		mc.print();
		assertEquals(mc.getUnprintedMessages().size(),0);
	}
	
	/**
	 * Testing of username Getters and Setters Methods
	 */
	@Test
	public void testSetUsername(){
		mc.setUsername("asd");
		assertEquals("asd",mc.getUsername());
	}
	/**
	 * Testing of LoggedIn Getters and Setters Methods
	 */
	@Test
	public void testSetLoggedIn(){
		mc.setLoggedIn(true);
		assertEquals(true,mc.isLoggedIn());
	}
	/**
	 * Testing of Active Getters and Setters Methods
	 */
	@Test
	public void testSetActive(){
		mc.setActive(true);
		assertEquals(true,mc.isActive());
	}
	/**
	 * Testing of UnprintedMessages Getters and Setters Methods
	 */
	@Test
	public void testSetUnprintedMessages(){
		Queue<String> um = new LinkedList<String>();
		mc.setUnprintedMessages(um);
		assertEquals(um,mc.getUnprintedMessages());
	}
	/**
	 * Testing of CLI Getters and Setters Methods
	 */
	@Test
	public void testSetCli(){
		CLI cli = new CLI();
		mc.setCli(cli);
		assertEquals(cli,mc.getCli());
	}
	/**
	 * Testing of TaskExecuter Getters and Setters Methods
	 */
	@Test
	public void testSetTaskExecuter(){
		TaskExecuter te = null;
		mc.setT(te);
		assertEquals(te,mc.getT());
	}
}

