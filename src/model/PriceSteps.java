package model;

import java.util.concurrent.ConcurrentHashMap;

public class PriceSteps {
	private ConcurrentHashMap<Integer,PriceStep> priceSteps;

	public PriceSteps(ConcurrentHashMap<Integer, PriceStep> priceSteps) {
		this.priceSteps = priceSteps;
	}

	/**
	 * @return the priceSteps
	 */
	public ConcurrentHashMap<Integer, PriceStep> getPriceSteps() {
		return priceSteps;
	}

	/**
	 * @param priceSteps the priceSteps to set
	 */
	public void setPriceSteps(ConcurrentHashMap<Integer, PriceStep> priceSteps) {
		this.priceSteps = priceSteps;
	}
}
