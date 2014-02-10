package analyticserver.junit;

import static org.junit.Assert.*;

import model.Event;

import org.junit.Before;
import org.junit.Test;


import analyticserver.AnalyticServerInterface;

public class AnalyticsServerInterfaceTest {

	public  AnalyticServerInterface asi;
	
	//public AnalyticsServerInterfaceTest(AnalyticServerInterface asi){
	//	this.asi=asi;
	//}
	
	@Before
	public void setUp() {
		//AnalyticServerInterface asi;
		//asi = new
		//this.asi=asi;	
	}

	@Test 
	public void testEquality() {
		try{
		asi.equals(asi);	
		fail("nullpointer expected");
		}catch(NullPointerException npe){
			
		}
	}
	@Test 
	public void testNullInit() {
		assertNull(asi);
	}
	
	@Test
	public void testEqualityObject() {
		try{
			asi.getClass();
			fail("nullpointer expected");
			}catch(NullPointerException npe){
				
			}
	}
	
	@Test
	public void testProcessEvent() {
		try{
		asi.processEvent(new Event());		
		fail("nullpointer expected");
	}catch(NullPointerException npe){
		
	}
	}
	@Test
	public void testInsuscribe() {
		try{
		asi.unsuscribe("id");
		fail("nullpointer expected");
	}catch(NullPointerException npe){
		
	}
	}

}
