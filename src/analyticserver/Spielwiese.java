package analyticserver;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	}
}
