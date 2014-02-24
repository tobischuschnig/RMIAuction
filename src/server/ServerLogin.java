package server;

import java.util.ArrayList;
import java.util.UUID;

import model.EventType;
import model.LoginMessage;
import model.Message;
import model.User;
import model.UserEvent;

/**
 * This class is responsible for a functionality off the server.
 * Is the login server for the users this class is called via the RequestHandler.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2014-01-05
 */
public class ServerLogin implements ServerAction {
	
	/**
	 * In this Method the functionality of login is implemented
	 * @param message contains every parameters for the work step
	 * @param server which should be used
	 * @return result of the operation which is handed over to the client via TCP to the client.
	 */
	@Override
	public String doOperation(Message message, Server server) {
		LoginMessage bid = (LoginMessage) message;
		String ret="";
		User loger = null;
		
		if(server.getUser().get(message.getName()) == null) { //if the user doesn't exists it is created
			loger = new User();
			loger.setName(bid.getName());
			loger.setAdresse(bid.getAdresse()); 
			loger.setTcpPort(bid.getTcpPort()); 
//			loger.setUdpPort(bid.getUdpPort()); 
			loger.setActive(true);
			loger.setMessages(new ArrayList<String>());
			server.getUser().put(loger.getName(),loger);
			//////////////////////////////////////////////////////////////////////////////
			//Event verschicken
			UserEvent userEvent = new UserEvent(UUID.randomUUID().toString(), EventType.USER_LOGIN, System.currentTimeMillis(), message.getName());
			server.notifyAnalytic(userEvent);
			//////////////////////////////////////////////////////////////////////////////
			return "Successfully suscribed and loged in as: "+loger.getName();
		}
		loger= server.getUser().get(message.getName());
		
		if (server.getUser().get(message.getName()) != null && loger.isActive()==false){ //if the user exists active is set true
			loger.setAdresse(bid.getAdresse()); 
			loger.setTcpPort(bid.getTcpPort()); 
//			loger.setUdpPort(bid.getUdpPort()); 
			loger.setActive(true);
			if(loger.getMessages().size() != 0){
				for(String s : loger.getMessages())
					ret += s + "\n";
			}
			else
				ret ="No Messages";
			//////////////////////////////////////////////////////////////////////////////
			//Event verschicken
			UserEvent userEvent = new UserEvent(UUID.randomUUID().toString(), EventType.USER_LOGIN, System.currentTimeMillis(), message.getName());
			server.notifyAnalytic(userEvent);
			//////////////////////////////////////////////////////////////////////////////
			return "Successfully loged in as: "+loger.getName()+"\nUnread messages: "+ret;
		}
		return "This User is allready loged in please log out first!";
		
	}
}
