package server;


import model.*;

public class Testmain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//User erstellen
//		User user = new User();
//		user.setActive(true);
//		user.setAdresse("123");
//		user.setName("name");
//		ArrayList<User> users = new ArrayList();
//		users.add(user);
		////////////////////////////
		
		
//		Auction auction = new Auction(user,"laptop1",duration);
//		Auction auction1 = new Auction(user,"laptop",duration);
//		ArrayList<Auction> auctions = new ArrayList();
//		auctions.add(auction1);
//		auctions.add(auction);
		
		//Server erstellen
		Server server = new Server();
//		server.setAuction(auctions);
//		server.setUser(users);
		///////////////////////
		
		//User erstellen mit LoginMessage
		LoginMessage login = new LoginMessage();
		login.setName("name");
		login.setAdresse("123"); 
		login.setTcpPort(123);
//		login.setUdpPort(123);
		
		String wert3 = server.request(login);
		System.out.println(wert3);
		wert3 = server.request(login); //testet das doppelt anmelden Funktionier (Fehlermeldung)
		System.out.println(wert3);
		login.setName("name2");
		wert3 = server.request(login);
		System.out.println(wert3); //anmelden mit zweitem user
		///////////////////////////
		
		
		//Auctionen erstellen mit CreateMessage
		Long duration = 10L;
//		System.out.println(duration);
		CreateMessage create = new CreateMessage();
		create.setDesc("laptop");
		create.setName("name");
		create.setDuration(duration);
		/////
		String wert1 = server.request(create);
		System.out.println(wert1); //TODO warum hier Could not send Paket?
		
		//System.out.println(server.getAuction().get(0).getDeadline());
		
		create.setDesc("Hand");
		wert1 = server.request(create);
		System.out.println(wert1);
		////////////////////////
		
		//Bieten mit BidMessage
		BidMessage bid = new BidMessage();
		
		bid.setAmount(123.123);
		bid.setId(1);
		bid.setName("name");
		///

		String wert = server.request(bid);
		System.out.println(wert);
		
		bid.setAmount(200.20);
		bid.setName("name2");
		wert = server.request(bid);
		System.out.println(wert);
		///////////////////////
		
		//Auflisten mit ListMessage
		ListMessage list = new ListMessage();
		list.setName("name");
		
		String wert2 = server.request(list);
		System.out.println(wert2);
		///////////////////////
		
		//auslogen mit LogoutMessage
		LogoutMessage logout = new LogoutMessage();
		logout.setName("name");
		
		String wert4 = server.request(logout);
		System.out.println(wert4);
		wert4 = server.request(logout); //Testet das doppelt auslogen funktioniert (Fehlermeldung)
		System.out.println(wert4);
		wert3 = server.request(login); //Nur zum testen des erneuten logins funtkioniert
		System.out.println(wert3);
		////////////////////////
	}

}