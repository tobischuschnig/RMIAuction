package model;

import java.io.Serializable;
/**
 * Interface for messages to send over network
 * @author Daniel Reichmann <dreichmann@student.tgm.ac.at>
 *
 */
public interface Message extends Serializable{
	public String getName();
}
