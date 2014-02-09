package managmentclient;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Event;

/**
 * 
 * @author alexander auradnik
 * @author Alexander Rieppel
 */
public class ManagmentClient extends Thread implements RMI {

    private String username;
    private boolean loggedIn;
    private boolean active;
    private Queue<Event> unprintedMessages;
    private boolean autoprint;
    private CLI cli;
    private TaskExecuter t;

    // attribute?
    public ManagmentClient(String billing,String analytics) {
        unprintedMessages = new LinkedList<Event>();
        active = true;
        loggedIn = false;
        username = "";
        cli = new CLI();
        t = new TaskExecuter(this, billing, analytics);
    }

    @Override
    public void run() {
        System.out.println("Client startet");
        String eingabe = "";
        Scanner in;
        in = new Scanner(System.in);
        while (active) {
            cli.outln("\n" + username + "> ");
            eingabe = in.nextLine();	//The current command saved as String

            if (eingabe.startsWith(" ")) {
                eingabe = eingabe.substring(1);
            }
            //If first char of command string is empty, it will be deleted

            String original = eingabe;	//Copy of Command, with original Format, lower/upper Case
            eingabe = eingabe.toLowerCase(); 	//To make any lower/upper case writing possible


            // befehle billing server
            if (eingabe.startsWith("!login")) {
                String[] werte = original.split(" ");		//Original is used
                if (loggedIn == false) {
                    if (werte.length == 3) {
                        t.login(werte[1], werte[2]);
                        //es besteht keine moeglickeit zu ueberprufen ob login serverseitig
                        // funktioniert hat!!
                        loggedIn = true;
                    } else {
                        cli.out("Please enter User like:\n!login Username passwort");
                    }
                } else {
                    cli.out("Already logged in, logout first!");
                }
            } else if (eingabe.startsWith("!steps")) {
                if (loggedIn == true) {
                    t.steps();
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }
            } else if (eingabe.startsWith("!startprice")) {
                if (loggedIn == true) {
                    String[] werte = eingabe.split(" ");
                    if (werte.length == 5) {
                        try {
                            t.addStep(Double.parseDouble(werte[1]), Double.parseDouble(werte[2]),
                                    Double.parseDouble(werte[3]), Integer.parseInt(werte[4]));
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                    } else {
                        cli.out("Please enter ID and Amount like:\n!bid ID Amount");
                    }
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }

            } else if (eingabe.startsWith("!removestep")) {
                String[] werte = eingabe.split(" ");
                if (loggedIn == true) {
                    if (werte.length == 3) {
                        try {
                            t.remove(Double.parseDouble(werte[1]), Double.parseDouble(werte[2]));
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                    } else {
                        cli.out("Please enter ID and Amount like:\n!bid ID Amount");
                    }
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }
            } else if (eingabe.startsWith("!bill")) {
                String[] werte = eingabe.split(" ");
                if (loggedIn == true) {
                    if (werte.length == 3) {
                        try {
                            t.bill(werte[1]);
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                    } else {
                        cli.out("Please enter ID and Amount like:\n!bid ID Amount");
                    }
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }
            } else if (eingabe.startsWith("!logout")) {
                if (loggedIn == true) {
                    t.logout(username);
                    username = "";
                    loggedIn = false;


                } else {
                    cli.out("Logout not possible, not logged in!");
                }
            } // befehle analytics server
            else if (eingabe.startsWith("!subscribe")) {
                String[] werte = eingabe.split(" ");
                if (loggedIn == true) {
                    if (werte.length == 2) {
                        try {
                            t.subscribe(werte[1]);
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                    }
                }
            } else if (eingabe.startsWith("!unsubscribe")) {
                String[] werte = eingabe.split(" ");
                if (loggedIn == true) {
                    if (werte.length == 2) {
                        try {
                            t.unsubscribe(Integer.parseInt(werte[1]));
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                    }
                }
            } // befehle ausgabe modus
            else if (eingabe.startsWith("!auto")) {
                auto();
            } else if (eingabe.startsWith("!hide")) {
                hide();
            } else if (eingabe.startsWith("!print")) {
                print();
            } // intern
            else if (eingabe.startsWith("!end")) {
                active = false;
            } else {
                cli.out("Could not recognize input\nPlease try again");
            }
        }
    }

    public void auto() {
        autoprint = true;
    }

    public void hide() {
        autoprint = false;
    }

    public void print() {
        Iterator it = unprintedMessages.iterator();

        while (it.hasNext()) {
            //String iteratorValue = (String) it.next();
            //System.out.println(iteratorValue);
            Event iteratorValue = (Event) it.next();
            System.out.println(iteratorValue.getTimestamp()+" "+iteratorValue.getType()+" with ID "+iteratorValue.getID());
        }
    }

    public boolean startService(){
        try {
            RMI stub = (RMI) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry();
	    registry.bind("ManagmentClient", stub);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;           
        } catch (AlreadyBoundException ex) {
            ex.printStackTrace();
            return false;  
        } 
        return true;
    }

    @Override
    public void processEvent(Event event) {
        if(autoprint==true){
            System.out.println(event.getTimestamp()+" "+event.getType()+" with ID "+event.getID());
        }else{
            unprintedMessages.add(event);
        }
    }
}
