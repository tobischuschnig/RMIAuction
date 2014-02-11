package analyticserver.junit;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Fügt mit SuitClasses alle Testcaste klassen zusammen.
 * @author auradnik alexander Alexander Auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 08/02/2014
 */
// run modus suite
@RunWith(Suite.class)
// Klassen auswaehlen: 
@SuiteClasses({ AnalyticsServerInterfaceTest.class,AnalyticsServerTest.class})

public class AllTest {}