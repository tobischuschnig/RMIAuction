package billingServer;

import java.util.concurrent.ConcurrentHashMap;

import model.PriceSteps;
import model.Bill;

public class BillingServerSecure {

	private PriceSteps priceSteps;
	private ConcurrentHashMap<String,Bill> bills;
	
	public PriceSteps getPriceSteps(){
		return null;
	}
	
	public void createPriceStep(double startPrice, double endprice, double fixedPrice, double variablePricePercent){
	}
	
	public void deletePriceStep(double startPrice, double endPrice){
		
	}
	
	public void billAuction(String user, long auctionID, double Price){
		
	}
	
	public Bill getBill(String user){
		return null;
		
	}
}
