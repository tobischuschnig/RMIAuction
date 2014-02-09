package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;

public class PriceSteps {
	private ConcurrentHashMap<Integer,PriceStep> priceSteps;

	public PriceSteps() {
		this.priceSteps = new ConcurrentHashMap<Integer,PriceStep>();
	}
	
	/**
	 * Dieser Konstruktor ist ein CHANGEREQUEST
	 * Er laedt aus price.steps die gespeicherten priceStep Objekte und uebergibt diese dem 
	 * ConcurrentHashMap
	 */
	public PriceSteps(){
		try{
			FileInputStream fin = new FileInputStream("price.steps");
			ObjectInputStream ois = new ObjectInputStream(fin);
			this.priceSteps = (ConcurrentHashMap<Integer, PriceStep>) ois.readObject();
			ois.close();
		}catch(Exception e){
			System.err.println("Fehler beim Laden von price.steps: 0");
		}
		
		
		
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
		
		try {
			FileOutputStream fout = new FileOutputStream("price.steps");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this.priceSteps);
			oos.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fehler beim Laden von price.steps: 2");
		} catch (IOException e) {
			System.err.println("Fehler beim Laden von price.steps: 3");
		}
	}
}
