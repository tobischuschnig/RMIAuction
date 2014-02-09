package managmentclient.junit;

import static org.junit.Assert.*;
import managmentclient.ManagmentClient;
import model.Event;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.*;

/**
 * TDD Testclass for ManagementClient
 * @author Alexander Rieppel
 * @email <arieppel@student.tgm.ac.at>
 */
public class ManagementClientTest {
	
	private ManagmentClient mc;
	
	@Before
	public void setUp(){
		mc = new ManagmentClient("0.0.0.0", "0.0.0.0");
	}
	
	/**
	 * Testing of auto Method, when autoprint should equals true
	 */
	@Test
	public void testAuto(){
		mc.auto();
		assertTrue(mc.getAutoprint());
	}
	
	/**
	 * Testing of hide Method, when login autoprint should equals false
	 */
	@Test
	public void testHide(){
		mc.hide();
		assertFalse(mc.getAutoprint());
	}
	/**
	 * Testing of processEvent Method, when autoprint equals true and no Events are written to the Queue
	 */
	@Test
	public void testProcessEventAutoPrint(){
		Event e = new Event();
		mc.setAutoprint(true);
		mc.processEvent(e);
		assertEquals(mc.getUnprintedMessages().size(),0);
	}
	/**
	 * Testing of processEvent Method, when autoprint equals false and all Events are written to the Queue
	 */
	@Test
	public void testProcessEventHide(){
		Event e = new Event();
		mc.setAutoprint(false);
		mc.processEvent(e);
		assertEquals(mc.getUnprintedMessages().size(),1);
	}
	/**
	 * Testing of startService Method, when everything should do fine and no exceptions are thrown
	 */
	@Test
	public void testStartService() throws AlreadyBoundException,RemoteException{
		assertTrue(mc.startService());
	}
	/**
	 * Testing of startService Method, when a second attempt to start should fail, due to multiple binding
	 * AlreadyBoundException should be thrown
	 */
	@Test(expected=AlreadyBoundException.class)
	public void testStartServiceAlreadyBound(){
		assertTrue(mc.startService());
		assertFalse(mc.startService());
	}
	/**
	 * Testing of startService Method, when the remote addresses are invalid
	 * RemoteException should be thrown
	 */
	@Test(expected=RemoteException.class)
	public void testStartServiceFalseBinding(){
		mc.setBillingAddress("10.0.0.1");
		mc.setRemoteAddress("10.0.0.2");
		assertFalse(mc.startService());
	}
}

