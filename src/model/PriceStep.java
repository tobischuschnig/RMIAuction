package model;

import java.io.Serializable;

public class PriceStep implements Serializable{
	private double startPrice, endPrice, fixedPrice, variablePricePercent;

	public PriceStep(double startPrice, double endPrice, double fixedPrice,
			double variablePricePercent) {
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.fixedPrice = fixedPrice;
		this.variablePricePercent = variablePricePercent;
	}

	/**
	 * @return the startPrice
	 */
	public double getStartPrice() {
		return startPrice;
	}

	/**
	 * @param startPrice the startPrice to set
	 */
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	/**
	 * @return the endPrice
	 */
	public double getEndPrice() {
		return endPrice;
	}

	/**
	 * @param endPrice the endPrice to set
	 */
	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	/**
	 * @return the fixedPrice
	 */
	public double getFixedPrice() {
		return fixedPrice;
	}

	/**
	 * @param fixedPrice the fixedPrice to set
	 */
	public void setFixedPrice(double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	/**
	 * @return the variablePricePercent
	 */
	public double getVariablePricePercent() {
		return variablePricePercent;
	}

	/**
	 * @param variablePricePercent the variablePricePercent to set
	 */
	public void setVariablePricePercent(double variablePricePercent) {
		this.variablePricePercent = variablePricePercent;
	}
}
