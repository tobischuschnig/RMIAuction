package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import Exceptions.InvalidInputException;
import Exceptions.OverlappedPricestepException;

/**
 * Provides the Methods to create delete and printing out a PriceStep which is created at the BillingServer
 * @author Alexander Rieppel <arieppel@student.tgm.ac.at>
 * @author Tobias Schuschnig <tschuschnig@student.tgm.ac.at>
 */
public class PriceSteps implements Serializable {
	private CopyOnWriteArrayList<PriceStep> priceSteps;
	// private ConcurrentHashMap<Integer, PriceStep> priceSteps;
	private String filePriceSteps;

	public PriceSteps() {
		try {
			// FileInputStream fin = new FileInputStream("price.steps");
			// ObjectInputStream ois = new ObjectInputStream(fin);
			// this.priceSteps = (ConcurrentHashMap<Integer, PriceStep>)
			// ois.readObject();
			// ois.close();
			this.priceSteps = new CopyOnWriteArrayList<PriceStep>();
			this.filePriceSteps = "price.steps";
			// this.priceSteps.put(1, new PriceStep(1.0, 5.0, 2.0, 5.0));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Fehler beim Laden von price.steps: 0");
		}
	}

	/**
	 * Method to delete a PriceStep
	 * @param start	the StartPrice
	 * @param end the EndPrice
	 * @return	successful or not
	 */
	public boolean removePricestep(double start, double end) {
		for (int x = 0; x < priceSteps.size(); x++) {
			if (priceSteps.get(x).getStartPrice() == start
					&& priceSteps.get(x).getEndPrice() == end) {
				priceSteps.remove(x);
				return true;
			}
		}
		return false;
	}

	/**
	 * returns the size of the current PriceStep List
	 * @return  the size of the list
	 */
	public int size() {
		return priceSteps.size();
	}

	/**
	 * Returns all PriceSteps in the list ordered by the Prices in a well formatted way
	 */
	@Override
	public String toString() {
		
		//BUBBLE SORT
		PriceStep tmp = null;
		  for(int i = 0;i<priceSteps.size();i++){
		    for(int j = (priceSteps.size()-1);j>=(i+1);j--){
		      if(priceSteps.get(j).getStartPrice() < priceSteps.get(j-1).getStartPrice()){
		        tmp = priceSteps.get(j);
		        priceSteps.set(j, priceSteps.get(j-1));
		        priceSteps.set(j-1, tmp);
		      }
		    }
		  }
		//------------------------------

		PriceStep temp;
		String returnment = String.format("%10s%15s%15s%15s", "Min_Price",
				"Max_Price", "Fee_Fixed", "Fee_Variable");
		String endp="";
		for (int x = 0; x < priceSteps.size(); x++) {
			temp = priceSteps.get(x);
			if(temp.getEndPrice()==Double.MAX_VALUE){
				endp="infinity";
			}else{
				endp=temp.getEndPrice()+"";
			}
			
			returnment += String.format("\n%10s%15s%15s%15s",
					temp.getStartPrice(), endp,
					temp.getFixedPrice(), temp.getVariablePricePercent());
		}
		return returnment;
	}

	/**
	 * @return the priceSteps
	 */
	public CopyOnWriteArrayList<PriceStep> getPriceSteps() {
		return priceSteps;
	}

	/**
	 * @param priceSteps
	 *            the priceSteps to set
	 */
	public void setPriceSteps(CopyOnWriteArrayList<PriceStep> priceSteps) {
		this.priceSteps = priceSteps;

		try {
			FileOutputStream fout = new FileOutputStream(this.filePriceSteps);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this.priceSteps);
			oos.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fehler beim Laden von price.steps: 2");
		} catch (IOException e) {
			System.err.println("Fehler beim Laden von price.steps: 3");
		}
	}

	/**
	 * @return the filePriceSteps
	 */
	public String getFilePriceSteps() {
		return filePriceSteps;
	}

	/**
	 * @param filePriceSteps
	 *            the filePriceSteps to set
	 */
	public void setFilePriceSteps(String filePriceSteps) {
		this.filePriceSteps = filePriceSteps;
	}

	/**
	 * Adds a priceStep to the list, and checks if the PriceStep overlaps with a existing PriceStep and if the PriceStep is valid in general
	 * @param startPrice	the StartPrice
	 * @param endPrice		the EndPrice
	 * @param fixedPrice	the FixedPrice
	 * @param variablePricePercent	the VariablePricePercent
	 * @return	successful or not
	 * @throws RemoteException
	 * @throws InvalidInputException
	 * @throws InvalidParameterException
	 * @throws OverlappedPricestepException
	 */
	public boolean addPricestep(double startPrice, double endPrice,
			double fixedPrice, double variablePricePercent) throws RemoteException,InvalidInputException,InvalidParameterException,OverlappedPricestepException {
		boolean overlaped = false;
		if (startPrice <= -1 || endPrice <= -1 || fixedPrice <= -1
				|| variablePricePercent <= -1)
			throw new InvalidParameterException();
//		System.out.println(startPrice + " < "+endPrice);
		if(endPrice==0){
			endPrice = Double.MAX_VALUE;
		}else if(startPrice > endPrice){
			throw new InvalidInputException("The startprice should be smaller than endprice");
		}
		

		for (int x = 0; x < priceSteps.size(); x++) {
			PriceStep temp = priceSteps.get(x);
			if (temp.getStartPrice() < startPrice
					&& startPrice < temp.getEndPrice())
				overlaped = true;

			if (temp.getStartPrice() < endPrice
					&& endPrice < temp.getEndPrice())
				overlaped = true;
			if (temp.getStartPrice() == startPrice
					&& temp.getEndPrice() == endPrice)
				overlaped = true;

		}

		if (!overlaped) {
			priceSteps.add(new PriceStep(startPrice, endPrice, fixedPrice, variablePricePercent));	 
			return true;
		} else {
			System.out
					.println("Es konnte kein neuer PriceStep angelgegt werden, da er sich mit einem vorhandnen ueberschneidet oder dieser bereits existiert oder startTime > endTime.");
			throw new OverlappedPricestepException();
		}
	}
}
