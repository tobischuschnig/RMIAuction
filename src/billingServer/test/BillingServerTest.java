package billingServer.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import billingServer.BillingServer;

public class BillingServerTest {
private BillingServer bs;
	
	@Before
	public void setUp(){
		bs = new BillingServer();
	}
	
	@Test
	public void testLogin(){
		bs.login("test", "1234");
		//TODO Assert
	}
	
}
