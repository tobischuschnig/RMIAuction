package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class PriceSteps implements Serializable{
	private ConcurrentHashMap<Integer,PriceStep> priceSteps;

	
	/**
	 * Dieser Konstruktor ist ein CHANGEREQUEST
	 * Er laedt aus price.steps die gespeicherten priceStep Objekte und uebergibt diese dem 
	 * ConcurrentHashMap
	 */
	public PriceSteps(){
		try{
			//FileInputStream fin = new FileInputStream("price.steps");
			//ObjectInputStream ois = new ObjectInputStream(fin);
			//this.priceSteps = (ConcurrentHashMap<Integer, PriceStep>) ois.readObject();
			//ois.close();
			this.priceSteps=new ConcurrentHashMap<Integer, PriceStep>();
			//this.priceSteps.put(1, new PriceStep(1.0, 5.0, 2.0, 5.0));
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Fehler beim Laden von price.steps: 0");
		}		
	}

	@Override
	public String toString(){
		String returnment="Min_Price\tMax_Price\tFee_Fixed\tFee_Variable";
//		PriceStep c;
		System.out.println(priceSteps.size());
		try{
			Iterator<Integer> it = priceSteps.keySet().iterator();
			while(it.hasNext()){
				int key = it.next();
				PriceStep temp = priceSteps.get(key);
				returnment+="\n"+temp.getStartPrice()+"\t"+temp.getEndPrice()+"\t"+temp.getFixedPrice()+"\t"+temp.getVariablePricePercent();
			}
//		for (int x=0;x<priceSteps.size();x++){
//			c=priceSteps.get(x);
//			returnment+="\n"+c.getStartPrice()+"\t"+c.getEndPrice()+"\t"+c.getFixedPrice()+"\t"+c.getVariablePricePercent();
//		}
		}catch (NullPointerException e){
			e.printStackTrace();
			return returnment;
		}
		return returnment;
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
