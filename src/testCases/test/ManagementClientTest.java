package test;

import org.junit.Assert.*;
import org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.*;


public class ManagementClientTest {
	
	private ManagementClient mc;
	private ArrayList<String> unpm;
	
	@Before
	public void setUp(){
		mc = new ManagementClient();
		mc.setUsername("ManUser");
		mc.setLoggedIn(false);
		mc.setActive(false);
		mc.setUnprintedMessages(unpm);
		mc.setAutoPrint(false);
	}
	
	@Test
	public void TestAuto(){
		mc.auto();
	}
	@Test
	public void TestHide(){
		mc.hide();
	}
	@Test
	public void TestPrint(){
		mc.print();
	}
	@Test
	public void TestProcessEvent(){
		mc.processEvent("Event");
	}
}

