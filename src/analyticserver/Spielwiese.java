package analyticserver;

import java.util.concurrent.ConcurrentHashMap;

public class Spielwiese {
	public static void main(String[] args) {
		ConcurrentHashMap<Integer,String> wert = new ConcurrentHashMap();
		wert.put(1, "Hallo");
		wert.put(1, "Hallo2");
		String wert1 = wert.get(1);
		System.out.println(wert1);
	}
}
