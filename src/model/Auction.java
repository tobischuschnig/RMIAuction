package model;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Saves important data for an auction
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 *
 */
public class Auction {
	private int id;
	private double highestBid;
	private User lastUser,owner;
	private String description;
	private Date deadline;
	private boolean finished;
	
	private Date startTime;
	
	public Auction(User owner,String description,Long duration){
		startTime = new Date();
		highestBid=0.0;
		this.owner=owner;
		this.description=description;
		deadline=new Date();
		deadline.setTime(deadline.getTime()+duration*1000);
		finished = false;
	}
	
	public Auction(User owner,String description,Long duration, int id){
		startTime = new Date();
		highestBid=0.0;
		this.owner=owner;
		this.description=description;
		deadline=new Date();
		deadline.setTime(deadline.getTime()+duration*1000);
		this.id = id;
		finished = false;
	}
	
	/**
	 * @return active
	 */
	public boolean isActive(){
		return false;
	}
	/**
	 * @param user
	 * @param amount
	 */
	public boolean bid(User user,double amount){
		return false;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the highestBid
	 */
	public double getHighestBid() {
		return highestBid;
	}
	/**
	 * @param highestBid the highestBid to set
	 */
	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}
	/**
	 * @return the lastUser
	 */
	public User getLastUser() {
		return lastUser;
	}
	/**
	 * @param lastUser the lastUser to set
	 */
	public void setLastUser(User lastUser) {
		this.lastUser = lastUser;
	}
	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}
	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	/**
	 * @return startTime of the specific Auction 
	 */
	public Date getStartTime(){
		return this.startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * @return the startTimeFormat
	 */
	public String getStartTimeFormat(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.startTime);
	}
}
