package analyticserver;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EventType;

public class Spielwiese {
	public static void main(String[] args) {
		ConcurrentHashMap<Integer,String> wert = new ConcurrentHashMap();
		//HashMap<Integer,String> wert = new HashMap<Integer,String>();
		wert.put(1, "Hallo");
		wert.put(-1, "Hallo2");
		int i = 1;
		String wert1 = wert.get(i);
		String wert2 = wert.get(-i);
		System.out.println(wert1);
		System.out.println(wert2);
		
		System.out.println("");
		
		Enumeration<Integer> wert3 = wert.keys();
		while(wert3.hasMoreElements()) {
			System.out.println(wert3.nextElement());
			
		}
		
		System.out.println(wert.size()); 
		
		long wert12 = System.currentTimeMillis();
		long wert13 = System.currentTimeMillis();
		System.out.println(wert12-wert13);
		System.out.println(wert12);
		System.out.println( new Date().getTime());
		System.out.println("--------------------------------");
		ArrayList<String> pattern = new ArrayList();
		
		pattern.add(EventType.USER_SESSIONTIME_MIN.toString());
		pattern.add(EventType.USER_SESSIONTIME_MAX.toString());
		pattern.add(EventType.USER_SESSIONTIME_AVG.toString());
		pattern.add(EventType.BID_PRICE_MAX.toString());
		pattern.add(EventType.BID_COUNT_PER_MINUTE.toString());
		pattern.add(EventType.AUCTION_TIME_AVG.toString());
		pattern.add(EventType.ACUTION_SUCCESS_RATIO.toString());
		
		pattern.add(EventType.AUCTION_STARTED.toString());
		pattern.add(EventType.AUCTION_ENDED.toString());
		
		pattern.add(EventType.USER_LOGIN.toString());
		pattern.add(EventType.USER_LOGOUT.toString());
		pattern.add(EventType.USER_DISCONNECTED.toString());
		
		pattern.add(EventType.BID_PLACED.toString());
		pattern.add(EventType.BID_OVERBID.toString());
		pattern.add(EventType.BID_WON.toString());
		
		Pattern p = Pattern.compile("(USER_.*)|(BID_.*)");
		
		for (i = 0; i < pattern.size();i++) {
			System.out.println(Pattern.matches("(USER_.*)|(BID_.*)",pattern.get(i)));
			
			
		}
		System.out.println(UUID.randomUUID().toString());
		
		long hilf = System.currentTimeMillis();
		Date date = new Date(hilf);
		Format format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:ms");
		System.out.println(format.format(date).toString());
	}
}
