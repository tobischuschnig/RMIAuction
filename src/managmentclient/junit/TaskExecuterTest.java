package managmentclient.junit;

import managmentclient.ManagementClient;
import managmentclient.TaskExecuter;

import org.junit.Before;
import org.junit.Test;

public class TaskExecuterTest {
	TaskExecuter te;
	
	@Before
	public void setUp() {
		te = new TaskExecuter(new ManagementClient("AnalyticServer", "BillingServer"),  "AnalyticServer","BillingServer");
	}

	/**
	 * Testing of getAuctionID Method in AuctionEvent
	 */
	@Test
	public void testGetAuctionID() {
//		ae1.setAuctionID(1);
//		assertEquals(ae1.getAuctionID(), 1);
	}
}
