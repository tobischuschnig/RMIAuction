package model;

public class StatisticsEvent extends Event {

	public StatisticsEvent(String iD, EventType type, long timestamp) {
		super(iD, type, timestamp);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public StatisticsEvent(String iD, EventType type, long timestamp,
			double value) {
		super(iD, type, timestamp);
		this.value = value;
	}



	private double value;
	
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
	
	
}
