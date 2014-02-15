package model;

public class UserEvent extends Event {

	private String userName;
	
	public UserEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
		// TODO Auto-generated constructor stub
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

	
}
