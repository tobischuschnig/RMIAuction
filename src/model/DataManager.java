package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
}