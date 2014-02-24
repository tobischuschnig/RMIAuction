package server;


import model.LogoutMessage;
import model.Message;
import model.User;



/**
 * This class is responsible for a functionality off the server.
 * Is the logout server for the users this class is called via the RequestHandler.
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 * @version 2014-01-05
 */
public class ServerLogout implements ServerAction{

	/**
	 * In this Method the functionality of logout is implemented
	 * @param message contains every parameters for the work step
	 * @param server which should be used
	 * @return result of the operation which is handed over to the client via TCP to the client.
	 */
	@Override
	public String doOperation(Message message, Server server) {
		LogoutMessage logout = (LogoutMessage) message;
		User loger = null;
		
		if(server.getUser().get(message.getName()) == null) { //Errormessage if the user doesn't exists
			return "This User doesn't exists!";
		}
		loger = server.getUser().get(message.getName());
		if (loger != null && server.getUser().get(message.getName()).isActive() == true) { //if it exists logout
			server.getUser().get(message.getName()).setActive(false);			
			//TODO cancel the connections
			return "Succesfully loged out as: "+loger.getName();
		}
		return "Error you must log in first!";
	}

}
