package managmentclient;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Managemend of Userinput and TaskExecuter calls
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @author Alexander Rieppel <alexander.rieppel@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class ManagementClient implements ManagementClientInterface, Serializable, Runnable {

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
    public ManagementClient(String analytics, String billing) {
        unprintedMessages = new LinkedList<String>();
        active = true;
        loggedIn = false;
        username = "";
        cli = new CLI();
        autoprint = true;
        t = new TaskExecuter(this, analytics, billing);
    }

    @Override
    // TODO Testing and optimize this
    public void run() {
        if (active) {
            cli.outln("Client startet");
            String eingabe = "";
            Scanner in;
            in = new Scanner(System.in);
            while (active) {
                cli.out("\n" + username + "> ");
                eingabe = in.nextLine();	//The current command saved as String

                input(eingabe);
            }
        }
    }

    public boolean input(String eingabe) {
        boolean success = false;
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
                        success = true;
                    } else {
                        cli.outln("Access denied for " + werte[1] + " " + werte[2]);
                    }
                } else {
                    cli.outln("Please enter User like:\n!login <Username> <Passwort>");
                }
            } else {
                cli.outln("Already logged in, logout first!");
            }
            // -- STEPS --
        } else if (eingabe.startsWith("!steps")) {
            if (loggedIn == true) {
                t.steps();
                success = true;
            } else {
                cli.outln("Currently not logged in\nPlease login first");
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
                        if (b) {
                            cli.outln("Pricestepp added successfully");
                            success = true;
                        }
                    } catch (NumberFormatException e) {
                        cli.outln("Values entered incorrect\nPlease enter command "
                                + "like:\n!addstep <Min_Price> <Max_Price> <Fee_Fixed> "
                                + "<Fee_Variable>!removeStep <startPrice> <endPrice>");
                    }

                } else {
                    cli.outln("Please enter command like:\n!addstep <Min_Price> <Max_Price>	<Fee_Fixed> <Fee_Variable>!removeStep <startPrice> <endPrice>");
                }
            } else {
                cli.outln("Currently not logged in\nPlease login first");
            }
            // -- REMOVE STEPS --
        } else if (eingabe.startsWith("!removestep")) {
            String[] werte = eingabe.split(" ");
            if (loggedIn == true) {
                if (werte.length == 3) {
                    try {
                        boolean b = false;
                        b = t.remove(Double.parseDouble(werte[1]), Double.parseDouble(werte[2]));
                        if (b) {
                            cli.outln("Pricestepp removed successfully");
                            success = true;
                        } else {
                            cli.outln("Cannot remove Pricestep. Check Input.");
                        }
                    } catch (NumberFormatException e) {
                        cli.out("Values entered incorrect");
                        cli.outln("Please enter !removeStep like:\n!removeStep <startPrice> <endPrice>");
                    }
                } else {
                    cli.outln("Please enter !removeStep like:\n!removeStep <startPrice> <endPrice>");
                }
            } else {
                cli.outln("Currently not logged in\nPlease login first");
            }
            // -- BILL --
        } else if (eingabe.startsWith("!bill")) {
            String[] werte = eingabe.split(" ");
            if (loggedIn == true) {
                if (werte.length == 2) {
                    boolean b = false;
                    t.bill(werte[1]);
                    success = true;
                } else {
                    cli.outln("Please enter !bill like:\n!bill <userName>");
                }
            } else {
                cli.outln("Currently not logged in\nPlease login first");
            }
            // -- LOGOUT --
        } else if (eingabe.startsWith("!logout")) {
            if (loggedIn == true) {
                t.logout(username);
                username = "";
                loggedIn = false;
                success = true;
            } else {
                cli.outln("Logout not possible, not logged in!");
            }
        } // befehle analytics server
        // -- SUBSCRIBE --
        else if (eingabe.startsWith("!subscribe")) {
            String[] werte = eingabe.split(" ");

            if (werte.length == 2) {
                success = true;
                t.subscribe(werte[1]);
            } else {
                cli.outln("Please enter !subscribe like:\n!subscribe "
                        + "<filterRegex>\nExample: !subscribe '(USER_.*)|(BID_.*)'");
            }

            // -- UNSUBSCRIBE --
        } else if (eingabe.startsWith("!unsubscribe")) {
            String[] werte = eingabe.split(" ");
            if (werte.length == 2) {
                t.unsubscribe(werte[1]);
                success = true;
            } else {
                cli.outln("Please enter !unsubscribe like:\n!unsubscribe <subscriptionID>");
            }
        } // befehle ausgabe modus
        else if (eingabe.startsWith("!auto")) {
            auto();
            success = true;
        } else if (eingabe.startsWith("!hide")) {
            hide();
            success = true;
        } else if (eingabe.startsWith("!print")) {
            print();
            success = true;
        } // intern
        else if (eingabe.startsWith("!end")) {
            success = true;
            active = false;
            t.end();
        } else {
            cli.outln("Could not recognize input\nPlease try again");
        }
        return success;
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
            cli.outln("Event: " + iteratorValue);
        }
        unprintedMessages = new LinkedList<String>();

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
            cli.outln("Event: " + event);
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
