package alltest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Diese Klasse ruft alle Testklassen um Dreieck zu testen auf.
 * @author Tobias Schuschnig
 * @version 13-01-2013
 */

@RunWith(Suite.class)
@SuiteClasses({ server.test.AuctionHandlerTest.class, server.test.ServerTest.class})
public class AllTest {

}