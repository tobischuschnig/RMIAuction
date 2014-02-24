/**
 * 
 */
package server.test;

import static org.junit.Assert.*;

import model.CreateMessage;
import model.LoginMessage;

import org.junit.Before;
import org.junit.Test;

import server.AuctionHandler;
import server.Server;

/**
 * @author tobi
 *
 */
public class AuctionHandlerTest {
	private Server server;
	private AuctionHandler ahandler;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		server = new Server();
		ahandler = new AuctionHandler(server);
	}

	/**
	 * Test method for {@link server.AuctionHandler#run()}.
	 */
	@Test
	public void testRun() {
		LoginMessage login = new LoginMessage();
		login.setName("name");
		login.setAdresse("127.0.0.1");
		login.setTcpPort(123);
//		login.setUdpPort(123);
		
		String wert = server.request(login); //Before testing auctionhandler i need a user
		assertEquals(wert,"Successfully suscribed and loged in as: name");
		
		Long duration = 100L;
		CreateMessage create = new CreateMessage();
		create.setDesc("laptop");
		create.setName("name");
		create.setDuration(duration);
		String wert1 = server.request(create);
		assertEquals(wert1,"You have succesfully created a new auction!"); //Before testing auctionhandler i need  an auction
		
		//ahandler.run();
	}

}
