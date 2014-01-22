package Client;

import java.util.Scanner;

import model.LoginMessage;

/**
 * This class handles the client input and transfers the commands to the server
 * 
 * @author Dominik Valka <dvalka@student.tgm.ac.at>
 * @version 2013-12-10
 */
public class Client{
	private String username;
	private boolean loggedIn;
	private String host;
	private int tcpPort;
	private int udpPort;
	private TaskExecuter t;
	private TCPConnector tcp;
	private CLI cli;
	//private NotificationReceiver nr;
	private boolean active;
	/**
	 * Constructor sets Server-IP,TCP-Port and UDP-Port
	 * @param host
	 * @param tcpPort
	 * @param udpPort
	 */
	public Client(String host,int tcpPort,int udpPort){
		active=true;
		loggedIn=false;
		username="";
		this.host=host;
		this.tcpPort=tcpPort;
		this.udpPort=udpPort;
		cli=new CLI();
		tcp=new TCPConnector(tcpPort, cli, this);
		t=new TaskExecuter(this);
		new NotificationReceiver(this);
		
	}

	
	/**
	 * Reads permantly the user input and calls the methods
	 */
	public void run() {
		String eingabe="";
		Scanner in;
		in=new Scanner(System.in);
		while(active){
			cli.outln("\n"+username+"> ");
			eingabe=in.nextLine();	//The current command saved as String
			
			if(eingabe.startsWith(" ")) eingabe=eingabe.substring(1);
			//If first char of command string is empty, it will be deleted
			
			String original=eingabe;	//Copy of Command, with original Format, lower/upper Case
			eingabe=eingabe.toLowerCase(); 	//To make any lower/upper case writing possible
			
				//If command is list
			if(eingabe.startsWith("!list")){
				t.list();
				
				//If command is bid
			}else if(eingabe.startsWith("!bid")){
				if(loggedIn==true){
					String[] werte=eingabe.split(" ");
					if(werte.length==3){
						try{
							String[] ab=werte[2].split("\\.");
							if(werte[2].contains(".")&&ab[0].length()>7){
								werte[2]=ab[0].substring(ab[0].length()-7)+"."+ab[1];
								System.out.println("Too large amount, max 7 numbers before '.'");
							}
							double erg=Double.parseDouble(werte[2]);
							erg=Math.rint(erg*100)/100;
							t.bid(Integer.parseInt(werte[1]),erg);
						}catch(NumberFormatException e){
							cli.out("ID or Amount entered incorrect");
						}
					}else{
						cli.out("Please enter ID and Amount like:\n!bid ID Amount");
					}
				}else{
					cli.out("Currently not logged in\nPlease login first");
				}
				//If command is login
			}else if(eingabe.startsWith("!login")){
				String[] werte=original.split(" ");		//Original is used
				if(loggedIn==false){
					if(werte.length==2){
						t.login(werte[1],tcpPort,udpPort);
						//Wait for Server response and then: set Username und loggedIn=true
//						username=werte[1];
//						loggedIn=true;
					}else{
					cli.out("Please enter User like:\n!login Username");
					}
				}else{
					cli.out("Already logged in, logout first!");
				}
				//If command is create
			}else if(eingabe.startsWith("!create")){
				if(loggedIn==true){
				String[] werte=original.split(" ");		//Original is used
				String desc="";
				for(int i=2;i<=werte.length-1;i++){
					desc=desc+" "+werte[i];
				}
				if(desc.startsWith(" ")) desc=desc.substring(1);
				t.create(Long.parseLong(werte[1]),desc);
				}else{
					cli.out("Currently not logged in\nPlease login first");
				}
				
				//If command is logout
			}else if(eingabe.startsWith("!logout")){
				if(loggedIn==true){
					t.logout();
					username="";
					loggedIn=false;
					
					
				}else{
					cli.out("Logout not possible, not logged in!");
				}
				
				//If command is end
			}else if(eingabe.startsWith("!end")){
				in.close();	//Free ressources
				active=false;		//Exits the loop
				tcp.sendMessage(new LoginMessage());//Tell TCP something is new
				//TcpConnector isActive:false, NotificationReceiver isActive:false
			}
				//If command is not recognized, another try will be granted
			else{
				cli.out("Could not recognize input\nPlease try again");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public TCPConnector getTcp() {
		return tcp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public int getUdpPort() {
		return udpPort;
	}
	public CLI getCli() {
		return cli;
	}
	public String getHost() {
		return host;
	}
	public int getTcpPort() {
		return tcpPort;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active=active;
	}
}