package billingServer.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Testsuite for BillingServer only
 * will be removed later
 * @author Alexander Rieppel <alexander.rieppe@tgm.ac.at>
 * @version 08/02/2014
 */
@RunWith(Suite.class)
@SuiteClasses({ BillingServerTest.class,
	BillingServerSecureTest.class})

public class AllBillingServer {}
