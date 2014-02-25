package managmentclient;

import Exceptions.InvalidFilterException;
import Exceptions.InvalidInputException;
import Exceptions.InvalidParameterException;
import Exceptions.OverlappedPricestepException;
import Exceptions.UserInputException;
import analyticserver.AnalyticServerInterface;
import billingServer.BillingServerInterface;
import billingServer.BillingServerSecureInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import model.Bill;

import model.Properties;

/**
 * Managmant of Remote Object calls (Analytics + Billing Server)
 * 
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class TaskExecuter {

    private BillingServerInterface objb;
    private AnalyticServerInterface obja;
    private BillingServerSecureInterface secure;
    private ManagementClientInterface managementClientInterface;
    private CLI cli;
    // ---
    private ManagementClient c;
    private String analyticsServer;
    private String billingServer;
    private Properties p;
    private String host;
    private int port;
    // ---
    private boolean analyticsCall = false;

    public TaskExecuter(ManagementClient c, String analyticsServer, String billingServer) {
        this.analyticsServer = analyticsServer;
        this.billingServer = billingServer;

        this.c = c;
        cli = new CLI();
        secure = null;

        try {
            p = new Properties("registry.properties");
            host = p.getProperty("registry.host");
            port = Integer.parseInt(p.getProperty("registry.port"));
        } catch (Exception ex) {
            cli.outln("Properties file not found!");
            end();
        }


    }

    public void end() {
        try {
            UnicastRemoteObject.unexportObject(c, false);
            c.setActive(false);
        } catch (Exception ex) {
            System.exit(0);
        }
    }

    /**
     * Logout User by reseting Interface reference
     * @param username user to be logged out
     */
    public void logout(String username) {
        secure = null;
        cli.outln("logout " + username);
    }

    /**
     * Login a user by tyring to permit acces to the secure interface
     * @param username
     * @param password 
     */
    public boolean login(String username, String password) {
        try {
            objb = (BillingServerInterface) Naming.lookup("rmi://" + host + ":" + port + "/" + billingServer);
        } catch (Exception ex) {
            cli.outln("Client exit: Cannot connect to BillingServer: " + billingServer);
            end();
        }
        boolean ret = false;
        try {
            secure = (BillingServerSecureInterface) objb.login(username, password);
            if (secure != null) {
                ret = true;
            }
        } catch (UserInputException ex) {
        } catch (RemoteException ex) {
        } catch (NullPointerException npe) {
        }
        return ret;
    }

    /**
     * Printing the existing pricesteps of the billingserver
     */
    public void steps() {
        try {
            // PriceSteps ps = secure.getPriceSteps();
            cli.outln("Price Steps:\n" + secure.getPriceSteps().toString());
        } catch (RemoteException ex) {
        }
    }

    /**
     * Add Pricestep 
     * @param startPrice
     * @param endPrice
     * @param fixedPrice
     * @param variablePricePercent
     * @return result of remote operation
     */
    public boolean addStep(double startPrice, double endPrice, double fixedPrice,
            double variablePricePercent) {
        boolean ret = false;

        try {
            ret = secure.createPriceStep(startPrice, endPrice, fixedPrice, variablePricePercent);

        } catch (InvalidParameterException ex) {
            cli.outln("Fehler: " + ex.getMessage());
        } catch (InvalidInputException ex) {
            cli.outln("Fehler: " + ex.getMessage());
        } catch (OverlappedPricestepException ex) {
            cli.outln("Fehler: " + ex.getMessage());
        } catch (RemoteException ex) {
            cli.outln("Fehler: " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Remove existing Pricestep
     * @param startPrice
     * @param endPrice
     * @return result of remote operation
     */
    public boolean remove(double startPrice, double endPrice) {
        cli.outln("remove");
        boolean ret = false;
        try {
            ret = secure.deletePriceStep(startPrice, endPrice);
        } catch (RemoteException ex) {
        }

        return ret;
    }

    /**
     * Get Bills of the User
     * @param username 
     */
    public void bill(String username) {
        try {
            ArrayList<Bill> al = secure.getBill(username);
            // Bill b = secure.getBill(username);
            if (al == null) {
                cli.outln("No Bills for User " + username);
            } else {
                // cli.outln(b.toString());
                for (Bill b : al) {
                    cli.outln(b.toString());
                }

            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Subscribe Client to a specific Event of the AnalyticsServer
     * @param filter 
     */
    public void subscribe(String filter) {

        if (!analyticsCall) {
            try {
                managementClientInterface = (ManagementClientInterface) UnicastRemoteObject.exportObject(c, 0);
                obja = (AnalyticServerInterface) Naming.lookup("rmi://" + host + ":" + port + "/" + analyticsServer);
            } catch (Exception ex) {
                cli.outln("Client exit: Cannot connect to AnalyticsServer: " + analyticsServer);
                end();
            }
            analyticsCall=true;
        }

        try {
            filter = filter.replace("'", "");
            cli.outln("Lookup completed ");
            cli.outln("Event ID: " + obja.suscribe(filter, managementClientInterface));
            cli.outln("Registered for Event callback.");
        } catch (InvalidFilterException ex) {
            cli.outln(ex.getMessage());
            // ex.printStackTrace();
        } catch (Exception ex) {
            cli.outln("failed to subscribe " + filter + ": " + ex.getMessage());
        }

    }

    /**
     * Unsubscribe Client to a specific Event of the AnalyticsServer
     * @param subscriptionID 
     */
    public void unsubscribe(String subscriptionID) {
        cli.outln("Unsubscribe: " + subscriptionID);
        try {
            obja.unsuscribe(subscriptionID);
        } catch (Exception ex) {
            cli.outln("Failed unsubscribe " + subscriptionID + ": " + ex.getMessage());
        }

    }
}
