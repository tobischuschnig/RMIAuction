package model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provides Methods to create and printing out StatisticsEvents
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public class StatisticsEvent extends Event {
	private double value;

	public StatisticsEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
	}
	
	
	
	public StatisticsEvent(String iD, EventType type, long timestamp,
			double value) {
		super(iD, type, timestamp);
		this.value = value;
	}

	
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * Returns the Specific Event in a well formatted way
	 * @return the formatted String
	 */
	@Override
	public String toString() {
		String hilf = "";
		Date date = new Date(this.getTimestamp());
		Format format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:ms");
		if(this.getType().equals(EventType.BID_PRICE_MAX)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - maximum bid price seen so far is "+value;
		} else if(this.getType().equals(EventType.BID_COUNT_PER_MINUTE)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - current bids per minute is "+value;
		} else if(this.getType().equals(EventType.USER_SESSIONTIME_MIN)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - minimum session time is "+value*1000+" seconds";
		} else if(this.getType().equals(EventType.USER_SESSIONTIME_MAX)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - maximum session time is "+value*1000+" seconds";
		} else if(this.getType().equals(EventType.USER_SESSIONTIME_AVG)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - average session time is "+value*1000+" seconds";
		} else if(this.getType().equals(EventType.AUCTION_TIME_AVG)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - auction timme average is "+value*1000+" seconds";
		} else if(this.getType().equals(EventType.ACUTION_SUCCESS_RATIO)) {
			hilf+= this.getType()+": "+format.format(date).toString()+" - auction succes ratio is"+value;
		}
		return hilf;
	}
}
