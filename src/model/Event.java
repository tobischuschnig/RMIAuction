package model;

import java.io.Serializable;

/**
 * Provides the basic Event which the specific Events can extend from
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public abstract class Event implements Serializable{

	private String ID;

	private EventType type;

	private long timestamp;

	
	public Event(String iD, EventType type, long timestamp) {
		this.ID = iD;
		this.type = type;
		this.timestamp = timestamp;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	
}
