package model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class wich contains the Constructor and all getters and setters for the specific EventType for Bids
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public class UserEvent extends Event {

	private String userName;
	
	public UserEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
	}
	
	public UserEvent(String iD, EventType type, long timestamp, String userName) {
		super(iD, type, timestamp);
		this.userName = userName;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Returns the specific type of a UserEvent in a well formatted way 
	 */
	@Override
	public String toString() {
		String hilf = "";
		Date date = new Date(this.getTimestamp());
		Format format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:ms");
		if(this.getType().equals(EventType.USER_LOGIN)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - user "+userName+" logged in";
		} else if(this.getType().equals(EventType.USER_LOGOUT)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - user "+userName+" logged out";
		} else if(this.getType().equals(EventType.USER_DISCONNECTED)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - user "+userName+" disconnected";
		}
		return hilf;
	}
}
