package managmentclient;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * 
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @author Alexander Rieppel <alexander.rieppel@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class ManagementClient implements ManagementClientInterface,Serializable, Runnable {

    private String username;
    private boolean loggedIn;
    private boolean active;
    private Queue<String> unprintedMessages;
    private boolean autoprint;
    private CLI cli;
    private TaskExecuter t;

    /**
     * Constructor for setting default Settings
     * @param billing Address of Remote BillingServer
     * @param analytics Address of Remote AnalyticsServer
     */
    public ManagementClient(String billing, String analytics) {
        unprintedMessages = new LinkedList<String>();
        active = true;
        loggedIn = false;
        username = "";
        cli = new CLI();
        autoprint = true;
        t = new TaskExecuter(this, billing, analytics);
    }

    @Override
    // TODO Testing and optimize this
    public void run() {
        cli.outln("Client startet");
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
            // -- LOGIN --
            if (eingabe.startsWith("!login")) {
                String[] werte = original.split(" ");		//Original is used
                if (loggedIn == false) {
                    if (werte.length == 3) {
                        boolean b = t.login(werte[1], werte[2]);
                        if (b) {
                            cli.outln("Successfully logged in as " + werte[1]);
                            loggedIn = true;
                            username = werte[1];
                        } else {
                            cli.outln("Access denied for " + werte[1] + " " + werte[2]);
                        }
                    } else {
                        cli.out("Please enter User like:\n!login Username passwort");
                    }
                } else {
                    cli.out("Already logged in, logout first!");
                }
                // -- STEPS --
            } else if (eingabe.startsWith("!steps")) {
                if (loggedIn == true) {
                    t.steps();
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }
                // -- ADDSTEP --
            } else if (eingabe.startsWith("!addstep")) {
                if (loggedIn == true) {
                    String[] werte = eingabe.split(" ");
                    if (werte.length == 5) {
                        boolean b = false;
                        try {
                           b = t.addStep(Double.parseDouble(werte[1]), Double.parseDouble(werte[2]),
                                    Double.parseDouble(werte[3]), Double.parseDouble(werte[4]));
                           if(b){
                               cli.outln("Pricestepp added successfully");
                           }else{
                               cli.outln("Cannot add Pricestep. Check Input.");
                           }
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                        
                    } else {
                        cli.out("Please enter ID and Amount like:\n!bid ID Amount");
                    }
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }
        // -- REMOVE STEPS --
            } else if (eingabe.startsWith("!removestep")) {
                String[] werte = eingabe.split(" ");
                if (loggedIn == true) {
                    if (werte.length == 3) {
                        try {
                            boolean b = false;
                            b = t.remove(Double.parseDouble(werte[1]), Double.parseDouble(werte[2]));
                            if(b){
                               cli.outln("Pricestepp removed successfully");
                           }else{
                               cli.outln("Cannot remove Pricestep. Check Input.");
                           }
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                    } else {
                        cli.out("Please enter ID and Amount like:\n!bid ID Amount");
                    }
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }
                // -- BILL --
            } else if (eingabe.startsWith("!bill")) {
                String[] werte = eingabe.split(" ");
                if (loggedIn == true) {
                    if (werte.length == 2) {
                        try {
                            boolean b = false;
                            t.bill(werte[1]);
                            if(!b){
                                cli.outln("No Bill found");
                            }
                        } catch (NumberFormatException e) {
                            cli.out("Values entered incorrect");
                        }
                    } else {
                        cli.out("Please enter ID and Amount like:\n!bid ID Amount");
                    }
                } else {
                    cli.out("Currently not logged in\nPlease login first");
                }
                // -- LOGOUT --
            } else if (eingabe.startsWith("!logout")) {
                if (loggedIn == true) {
                    t.logout(username);
                    username = "";
                    loggedIn = false;
                } else {
                    cli.out("Logout not possible, not logged in!");
                }
            } // befehle analytics server
            // -- SUBSCRIBE --
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
                // -- UNSUBSCRIBE --
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

    /**
     * Set Printmode to AUTO
     */
    public void auto() {
        autoprint = true;
    }

    /**
     * Set Printmode to HIDE
     */
    public void hide() {
        autoprint = false;
    }

    /**
     * Printing all saved Event-Messages
     */
    public void print() {
        Iterator it = unprintedMessages.iterator();
        while (it.hasNext()) {
            String iteratorValue = (String) it.next();
            cli.outln("Event: "+iteratorValue);
        }
        unprintedMessages = new LinkedList<String>();
        
    }

    /**
     * Settup and start the Remote Object Server of the Managment-CLient
     * @return Service-Startus
     */
    public boolean startService() {
        try {
            ManagementClientInterface stub = (ManagementClientInterface) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry();
            // setting up Name of Remote
            registry.rebind("ManagmentClient", stub);
            cli.outln("Service bound");
        } catch (RemoteException ex) {
            cli.outln("Error: "+ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Prints or saves the occurred Server-Event
     * @param event incoming Server-Event
     */
    @Override
    public void processEvent(String event) {
        // direct output      
        if (autoprint == true) {
            // TODO optimize output format
            cli.outln("Event: "+event);
            // message-save mode
        } else {
            unprintedMessages.add(event);
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the unprintedMessages
     */
    public Queue<String> getUnprintedMessages() {
        return unprintedMessages;
    }

    /**
     * @param unprintedMessages the unprintedMessages to set
     */
    public void setUnprintedMessages(Queue<String> unprintedMessages) {
        this.unprintedMessages = unprintedMessages;
    }

    /**
     * @return the cli
     */
    public CLI getCli() {
        return cli;
    }

    /**
     * @param cli the cli to set
     */
    public void setCli(CLI cli) {
        this.cli = cli;
    }

    /**
     * @return the t
     */
    public TaskExecuter getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(TaskExecuter t) {
        this.t = t;
    }

    /**
     * @return the autoprint
     */
    public boolean isAutoprint() {
        return autoprint;
    }

    /**
     * @param autoprint the autoprint to set
     */
    public void setAutoprint(boolean autoprint) {
        this.autoprint = autoprint;
    }
}
