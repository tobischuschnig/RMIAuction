package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages the auctions and everything who needs to needs to be stored
 * 
 * @author Daniel,Tobi
 * 
 */
public class DataManager implements Serializable{

	/**
	 * Saves all auctions in the file
	 * @param YourObject
	 * @param filePath
	 * @throws IOException
	 */
	public static void saveData(Object YourObject, String filePath) throws IOException 
	{ 
		ObjectOutputStream outputStream = null; 
		try 
		{ 
			outputStream = new ObjectOutputStream(new FileOutputStream(filePath)); 
			outputStream.writeObject(YourObject); 
		} 
		catch(FileNotFoundException ex) 
		{ 
			//ex.printStackTrace(); 
		} 
		catch(IOException ex) 
		{ 
			//ex.printStackTrace(); 
		} 
		finally 
		{ 
			try 
			{ 
				if(outputStream != null) 
				{ 
					outputStream.flush(); 
					outputStream.close(); 
				} 
			} 
			catch(IOException ex) 
			{ 
			} 
		} 
	} 
	
	/**
	 * load Pricesteps back from file
	 * @param filePath file to load
	 * @return Pricesteps which got loaded
	 * @throws IOException file not found
	 * @throws ClassNotFoundException class not found
	 */
	public static PriceSteps loadPriceSteps(String filePath) throws IOException, ClassNotFoundException 
	{ 
		try 
		{ 
			FileInputStream fileIn = new FileInputStream(filePath); 
			ObjectInputStream in = new ObjectInputStream(fileIn); 
			PriceSteps l=(PriceSteps) in.readObject();
			if(l==null){
				return new PriceSteps();
			}
			else{
				return l; 
			}
		} 
		catch(Exception ex) 
		{ 
			return new PriceSteps();
		} 
		
	} 
	/**
	 * load billes back from file
	 * @param filePath file to load
	 * @return Pricesteps which got loaded
	 * @throws IOException file not found
	 * @throws ClassNotFoundException class not found
	 */
	public static CopyOnWriteArrayList<Bill> loadBills(String filePath) throws IOException, ClassNotFoundException 
	{ 
		try 
		{ 
			FileInputStream fileIn = new FileInputStream(filePath); 
			ObjectInputStream in = new ObjectInputStream(fileIn); 
			CopyOnWriteArrayList<Bill> l=(CopyOnWriteArrayList<Bill>) in.readObject();
			if(l==null){
				return new CopyOnWriteArrayList<Bill>();
			}
			else{
				return l; 
			}
		} 
		catch(Exception ex) 
		{ 
			return new CopyOnWriteArrayList<Bill>();
		} 
		
	}

	public ConcurrentHashMap<Integer, Auction> loadAuctions(String filePath) {
		try 
		{ 
			FileInputStream fileIn = new FileInputStream(filePath); 
			ObjectInputStream in = new ObjectInputStream(fileIn); 
			ConcurrentHashMap<Integer, Auction> l=(ConcurrentHashMap<Integer, Auction>) in.readObject();
			if(l==null){
				return new ConcurrentHashMap<Integer, Auction>();
			}
			else{
				return l; 
			}
		} 
		catch(Exception ex) 
		{ 
			return new ConcurrentHashMap<Integer, Auction>();
		} 
	} 
}