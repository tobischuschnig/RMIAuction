package alltest;

import org.junit.runner.RunWith;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import server.test.AuctionHandlerTest;
import server.test.ServerTest;

/**
 * Diese Klasse ruft alle Testklassen um Dreieck zu testen auf.
 * @author Tobias Schuschnig
 * @version 13-01-2013
 */

@RunWith(Suite.class)
@SuiteClasses({ AuctionHandlerTest.class, ServerTest.class})
public class AllTests {

}