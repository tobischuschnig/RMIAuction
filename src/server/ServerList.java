package server;

import java.util.Iterator;
import java.util.Set;

import model.ListMessage;
import model.Message;

/**
 * This class is responsible for a functionality off the server.
 * Lists all auctions this class is called via the RequestHandler.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2014-01-05
 */
public class ServerList implements ServerAction {
	
	/**
	 * In this Method the functionality of list auctions is implemented
	 * @param message contains every parameters for the work step
	 * @param server which should be used
	 * @return result of the operation which is handed over to the client via TCP to the client.
	 */
	@Override
	public String doOperation(Message message, Server server) {
		ListMessage bid = (ListMessage) message;
		String out = "";
		Set<Integer> wert = server.getAuction().keySet();
		Iterator<Integer> it = wert.iterator();
		while(it.hasNext()) { 
			int i = it.next();
			if(server.getAuction().get(i).isFinished() == false) {
				String hilf;
				if (server.getAuction().get(i).getLastUser() == null) { //if there was no bidder
					hilf = "none";
				}
				else {
					hilf=server.getAuction().get(i).getLastUser().getName();
				}
				out+=   "ID: "+ server.getAuction().get(i).getId()+ 
						"    Description: " +server.getAuction().get(i).getDescription()+ 
						"    Highestbid: " + server.getAuction().get(i).getHighestBid() + 
						"    from: "+hilf+"\n";
			}
		}
		
		if(out.equals(""))
			out = "No auctions yet";
		return out;
		
	}

}
