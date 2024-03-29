package server;


import java.util.UUID;

import model.Auction;
import model.AuctionEvent;
import model.CreateMessage;
import model.EventType;
import model.Message;
import model.User;

/**
 * This class is responsible for a functionality off the server.
 * If the client creates an auction this class is called via the RequestHandler.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2014-01-05 
 */
public class ServerCreate implements ServerAction{

	/**
	 * In this Method the functionality of create auction is implemented
	 * @param message contains every parameters for the work step
	 * @param server which should be used
	 * @return result of the operation which is handed over to the client via TCP to the client.
	 */
	@Override
	public String doOperation(Message message, Server server) {
		CreateMessage create = (CreateMessage) message;
		User creater = null;
		
		if (server.getUser().get(message.getName()) == null) { //if the user doesn't exists the operation is canceled with an error message
			return "This User doesn't exists please log in first!";
		}
		creater = server.getUser().get(message.getName());
		//adds the auction to the list
		Auction hilf = new Auction(creater, create.getDesc(),  create.getDuration() , server.getAuction().size() );
		server.getAuction().put(hilf.getId(),hilf);

		server.notify(server.getUser(),"An auction '"+hilf.getDescription()+"' with the ID: "
				+hilf.getId()+" has been created and will end on "
				+hilf.getDeadline()+".");
		//////////////////////////////////////////////////////////////////////////////
		//Event verschicken
		AuctionEvent userEvent = new AuctionEvent(UUID.randomUUID().toString(), EventType.AUCTION_STARTED, System.currentTimeMillis(), hilf.getId());
		server.notifyAnalytic(userEvent);
		//////////////////////////////////////////////////////////////////////////////
		return "You have succesfully created a new auction!";	
	}

	
}
