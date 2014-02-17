package analyticserver.junit;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Testsuite for AnalyticsServer only
 * will be removed later
 * @author Alexander Auradnik <alexander.auradnik@student.tgm.ac.at>
 * @author Alexander Rieppel <alexander.rieppel@student.tgm.ac.at>
 * @version 17/02/2014
 */
// run modus suite
@RunWith(Suite.class)
// Klassen auswaehlen: 
@SuiteClasses({ AnalyticsServerTest.class,
	EventHandlerTest.class})

public class AllAnalyticsServer {}