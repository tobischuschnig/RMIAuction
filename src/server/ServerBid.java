package server;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import model.Auction;
import model.BidMessage;
import model.Message;
import model.User;

/**
 * This class is responsible for the a functionality of the server.
 * If the client bids on an auction this class is called via the RequestHandler.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2014-01-05
 */
public class ServerBid implements ServerAction {

	/**
	 * In this method the functionality of bid is implemented
	 * @param message contains every parameters for the work step
	 * @param server which should be used
	 * @return result of the operation which is handed over to the client via TCP to the client.
	 */
	@Override
	public String doOperation(Message message, Server server) {
		BidMessage bid = (BidMessage) message;
		User bidder = null;
		
		
		if (server.getUser().get(message.getName()) == null) { //If the user doesn't exists the operation is canceled
			return "This User doesn't exists!";
		} 
		bidder = server.getUser().get(message.getName());




		if(server.getAuction().get(((BidMessage) message).getId()) != null) {
			if(server.getAuction().get(((BidMessage) message).getId()).isFinished() == false) {
				if(server.getAuction().get(((BidMessage) message).getId()).getOwner().getName().equals(bid.getName())){
					return "You cannot bid on your own auction!";
				}
				else if(server.getAuction().get(((BidMessage) message).getId()).getHighestBid() < bid.getAmount() ) { //checks if the bid is higher and if the auction is over
					User lastUser;
					Auction hilf = server.getAuction().get(((BidMessage) message).getId());
					hilf.setHighestBid(bid.getAmount());
					lastUser = server.getAuction().get(((BidMessage) message).getId()).getLastUser();
					hilf.setLastUser(bidder);
					server.getAuction().replace(((BidMessage) message).getId(), hilf);

					if (lastUser != null) { //If there is a previous bidder he gets notified
						ConcurrentHashMap<String,User> al = new ConcurrentHashMap();
						al.put(lastUser.getName(),lastUser);
						server.notify(al,"You have been overbid on '" +
								server.getAuction().get(((BidMessage) message).getId()).getDescription()+"' with the ID: "+
								server.getAuction().get(((BidMessage) message).getId()).getId()+".");
					}

					//Informs if the bid was succesfully
					//return "You successfully bid with "+bid.getAmount()+" on '"
					//	+server.getAuction().get(i).getDescription()+"'.";

					return "You successfully bid with "+server.getAuction().get(((BidMessage) message).getId()).getHighestBid()+" on '"
					+server.getAuction().get(((BidMessage) message).getId()).getDescription()+"'.";
				}
				else { //If the bid his lower then the current bid than the operation is canceled and a error message is produced
					return "Your bid must be higher then the current bid! The current bid is: "+
					server.getAuction().get(((BidMessage) message).getId()).getHighestBid();
				}
			}
			else {
				return "The auction '"+server.getAuction().get(((BidMessage) message).getId()).getDescription()+"' is allready over you can not bid on this auction anymore!";
			}
		}

		return "There is no Auction with this ID!"; //Errormessage if the auction doesn't exists
	}
}
