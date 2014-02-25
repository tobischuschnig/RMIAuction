package alltest;

//import managmentclient.junit.ManagementClientTest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Overall Testsuite for all Testcases which are written so far
 * @author Alexander Rieppel <alexander.rieppel@student.tgm.ac.at>
 * @author Alexander Auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 13-01-2013
 */

@RunWith(Suite.class)
@SuiteClasses({ 
	
//	server.test.AuctionHandlerTest.class,
//	server.test.ServerTest.class,
	analyticserver.junit.AnalyticsServerTest.class,
	analyticserver.junit.EventHandlerTest.class,
	billingServer.junit.BillingServerTest.class,
	billingServer.junit.BillingServerSecureTest.class,
	model.test.AuctionTest.class,
	model.test.UserTest.class,
	model.test.AllModel.class,
	managmentclient.junit.ManagementCLientInputTest.class,
	managmentclient.junit.TaskExecuterTest.class,
	managmentclient.junit.ManagementClientTest.class})

public class AllTest {
	@BeforeClass 
	public static void setUpOnce() {
		   ServerStart.startServer();
		}
} 