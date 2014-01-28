package test;

import org.junit.Assert.*;
import org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.*;


public class TaskExecutorTest {
	
	private TaskExecutor te;
	private TcpConnector tcpc;
	
	@Before
	public void setUp(){
		tcpc = new TcpConnector();
		te = new TaskExecutor(tcpc);
		
	}
	
	@Test
	public void TestLogout(){
		te.logout();
	}
	@Test
	public void TestLogin(){
		te.login("user","password");
	}
	@Test
	public void TestSteps(){
		te.steps();
	}
	@Test
	public void TestAddSteps(){
		assertTrue(te.addStep(100.5,2000,1000,50));
	}
	@Test
	public void TestRemoveSteps(){
		assertTrue(te.removeStep(100.5,2000,1000,50));
	}
	@Test
	public void TestBill(){
		te.bill("user");
	}
	@Test
	public void TestSubscribe(){
		te.subscribe("filter");
	}
	@Test
	public void TestUnsubscribe(){
		te.unsubscribe(5);
	}
}

