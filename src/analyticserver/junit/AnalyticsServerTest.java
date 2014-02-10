package analyticserver.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import analyticserver.AnalyticServer;

public class AnalyticsServerTest {

	public AnalyticServer as;
	
	@Before
	public void setUp() {
		as=new  AnalyticServer();
	}
	
	@Test
	public void testInitAnalyticServer() {
		as=new  AnalyticServer();
	}
	@Test
	public void testEquality() {
		assertTrue(as.equals(as));
	}
	
	@Test
	public void testSuscribe(){
		//as=new  AnalyticServer();
		String n = as.suscribe("something wrong");
		n.equals("");
	}
	
	@Test
	public void testUnsuscribeSuccessfullCall(){
		//as=new  AnalyticServer();
		try{
		as.unsuscribe("wrong id");
		}catch(Exception e){
			fail("Exception thrown");
		}
	}
	
	@Test
	public void testCalculate(){
		//as=new  AnalyticServer();
		try{
		as.claculate();
		}catch(Exception e){
			fail("Exception thrown");
		}
	}
	
}
