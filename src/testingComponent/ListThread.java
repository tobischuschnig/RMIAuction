package testingComponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.ListMessage;
import Client.Client;
import Client.TCPConnector;
/**
 * 
 * @author Klune Alexander
 *@version 1.0
 *@email aklune@student.tgm.ac.at
 */
public class ListThread implements Runnable{

/**
 * 
 */
private TestingClient testingClient;
	
	public ListThread(TestingClient testingClient){
		this.testingClient = testingClient;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		ListMessage lm = new ListMessage("admin");
		TCPConnector tcp = testingClient.getTCPConnector();
		tcp.sendMessage(lm);
		String list = testingClient.getTestingCompCLI().getOutput();
		String[] auctions = list.split("\n");
		//ID: "+ server.getAuction().get(i).getId()+ 
//		"\tDescription: " +server.getAuction().get(i).getDescription()+ 
//		"\tHighestbid: " + server.getAuction().get(i).getHighestBid() + 
//		"\tfrom: "+hilf+
//		"\tstartTime:
		ArrayList<String[]> arl = new ArrayList<String[]>();
		for(int i = 0;i<list.length();i++){
			String [] temp = auctions[i].split("\t");
			arl.add(temp);
			
//			String id = temp[0].substring(3,temp[0].length());
//			String desc = temp[1].substring(12,temp[1].length());
//			String hbid = temp[2].substring(12,temp[2].length());;
//			String from = temp[3].substring(6,temp[1].length());;
//			String sTime = temp[4].substring(11,temp[1].length());;
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			try {
//				Date startTime = sdf.parse(sTime);
//			} catch (ParseException e) {
//				System.err.println("Fehler beim umformatieren des Datums in ListThread");
//			}
			
			
	
		}
		
		try {
			Thread.sleep(testingClient.getUpdateIntervalSec()*1000);
		} catch (InterruptedException e) {
			System.err.println("Fehler beim schlafen legen des ListThreads");
		}
		testingClient.setAuctionsIDs(arl);
		
	}

}
