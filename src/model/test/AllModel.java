package model.test;

import model.AuctionEvent;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import analyticserver.EventHandler;


/**
 * Testsuite for Model only
 * will be removed later
 * @author Alexander Rieppel <alexander.rieppe@tgm.ac.at>
 * @version 08/02/2014
 */
@RunWith(Suite.class)
@SuiteClasses({ AuctionEventTest.class, AuctionTest.class,BidEventTest.class,BidMessageTest.class,BillTest.class,CreateMessageTest.class,EventTest.class,ListMessageTest.class,
	LoginMessageTest.class,LogoutMessageTest.class,PriceStepsTest.class,PriceStepTest.class,StatisticsEventTest.class,UserEventTest.class,UserTest.class})

public class AllModel {}
