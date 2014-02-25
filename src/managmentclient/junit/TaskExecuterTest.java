package managmentclient.junit;

import static org.junit.Assert.assertEquals;
import managmentclient.ManagementClient;
import managmentclient.TaskExecuter;
import model.AuctionEvent;
import model.EventType;

import org.junit.Before;
import org.junit.Test;

public class TaskExecuterTest {
	TaskExecuter te;
	
	@Before
	public void setUp() {
		te = new TaskExecuter(new ManagementClient("AnalyticServer", "BillingServer"),  "BillingServer","AnalyticServer");
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
