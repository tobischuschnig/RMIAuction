package billingServer;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import model.PriceStep;
import model.PriceSteps;
import model.Bill;
import model.User;

/**
 * Diese Klasse bietet moeglichkeiten angelegte PriceSteps zu loeschen
 * oder neue hinzuzufuegen, sowie diese auszugeben. Weiters kann eine Rechnung
 * angelegt werden, wenn eine Auction zu ende ist. Diese Rechnung kann auch ueber
 * den usernamen abgefragt und zurueckgegeben werden.
 * @author Klune Alexander
 * @version 1.0
 * @email aklune@student.tgm.ac.at
 */
public class BillingServerSecure {

	private PriceSteps priceSteps;
	private ConcurrentHashMap<String,Bill> bills;
	
	public BillingServerSecure(){
		this.priceSteps=new PriceSteps();
	}

	
	public PriceSteps getPriceSteps(){
		return priceSteps;
	}
	
	public void createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) throws RemoteException{
		priceSteps = new PriceSteps();
		boolean overlaped = false;
		ConcurrentHashMap<Integer,PriceStep> psTemp = priceSteps.getPriceSteps();
		if(startPrice < 0 || endPrice < 0 || fixedPrice < 0 || variablePricePercent < 0)
			throw new RemoteException();
		
		Iterator<Integer> it = psTemp.keySet().iterator();
		while(it.hasNext()){
			int key = it.next();
			PriceStep temp = psTemp.get(key);
			if(temp.getStartPrice() < startPrice && startPrice < temp.getEndPrice())
				overlaped = true;
			
			if(temp.getStartPrice() < endPrice && endPrice < temp.getEndPrice())
				overlaped = true;
						
		}
		
		if(!overlaped){
			psTemp.put(psTemp.size(), new PriceStep(startPrice,endPrice,fixedPrice,variablePricePercent));
			priceSteps.setPriceSteps(psTemp);
		}else
			System.out.println("Es konnte kein neuer PriceStep angelgegt werden, da er sich mit einem vorhandnen ueberschneidet");
	}
	
	public void deletePriceStep(double startPrice, double endPrice){
		ConcurrentHashMap<Integer,PriceStep> psTemp = priceSteps.getPriceSteps();
		Iterator<Integer> it = psTemp.keySet().iterator();
		while(it.hasNext()){
			int key = it.next();
			PriceStep temp = psTemp.get(key);
			
			if(temp.getStartPrice() == startPrice && temp.getStartPrice() == endPrice ){
				psTemp.remove(key);
			}
		}
	}
	
	//User user, int auctionID, int feeFixed, double strikePrice,
    //double feeVariable, double feeTotal
	public void billAuction(String user, long auctionID, double price){
		bills.put(user, new Bill(user, auctionID, price));
	}
	
	public Bill getBill(String user){
		if(bills.containsKey(user))
			return bills.get(user);
		else return null;		
	}
}
