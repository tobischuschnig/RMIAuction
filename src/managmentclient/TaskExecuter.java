package managmentclient;

import Exceptions.InvalidFilterException;
import Exceptions.UserInputException;
import analyticserver.AnalyticServerInterface;
import billingServer.BillingServerInterface;
import billingServer.BillingServerSecureInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.Bill;

/**
 * Managmant of Remote Object calls (Analytics + Billing Server)
 * 
 * @author alexander auradnik <alexander.auradnik@student.tgm.ac.at>
 * @author Alexander Rieppel <alexander.rieppel@student.tgm.ac.at>
 * @version 2014-02-14
 */
public class TaskExecuter {

    private BillingServerInterface objb;
    private AnalyticServerInterface obja;
    private BillingServerSecureInterface secure;
    private ManagementClientInterface managementClientInterface;
    private CLI cli;
    // not in use yet
    private ManagementClient c;

    public TaskExecuter(ManagementClient c, String billingServer, String analyticsServer) {
        this.c = c;
        cli = new CLI();
        secure = null;
        // example: objb = (RMI_Billing)Naming.lookup("//localhost/RmiServer");
        try {
            managementClientInterface = (ManagementClientInterface) UnicastRemoteObject.exportObject(c, 0);
            obja = (AnalyticServerInterface) Naming.lookup(analyticsServer);
        } catch (Exception ex) {
            cli.outln("Client exit: Cannot connect to AnalyticsServer." + "\n(" + ex.getMessage() + ")");
            System.exit(0);
        }
        try {
            objb = (BillingServerInterface) Naming.lookup(billingServer);
        } catch (Exception ex) {
            cli.outln("Client exit: Cannot connect to BillingServer." + "\n(" + ex.getMessage() + ")");
            System.exit(0);
        }

    }

    /**
     * Logout User by reseting Interface reference
     * @param username user to be logged out
     */
    public void logout(String username) {
        // TODO update this ?  
        secure = null;
        cli.outln("logout " + username);
    }

    /**
     * Login a user by tyring to permit acces to the secure interface
     * @param username
     * @param password 
     */
    public boolean login(String username, String password) {
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
            ret = secure.createPriceStep(startPrice, endPrice, fixedPrice, fixedPrice);
        } catch (RemoteException ex) {
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
            Bill b = secure.getBill(username);
            if (b == null) {
                cli.outln("No Bills for User " + username);
            } else {
                cli.outln(b.toString());
            }
        } catch (RemoteException ex) {
        }
    }

    /**
     * Subscribe Client to a specific Event of the AnalyticsServer
     * @param filter 
     */
    public void subscribe(String filter) {
        try {
            //TODO for tobi ip maybe useless? because of lookup port would be 1099


            //TODO Brauche die IP des AnalyticServers eingelesen //Edid tobi
            String ip = "127.0.0.1"; //TODO wenn gemacht variable entfernen //Edid tobi
            String registryURL = "";
            cli.outln("Subscribe: ");
            // TODO filter pruefen ? //Edid tobi: mache ich exceptions pruefen und ausgeben
            // AnalyticServerInterface analyticServerInterface = (AnalyticServerInterface)Naming.lookup(registryURL); //Edid tobi
            //TODO Exceptions bei verbinden abfangen //Edid tobi
            System.out.println("Lookup completed ");

            System.out.println("Server said: " + obja.suscribe(filter, managementClientInterface));
            //TODO change ausgabe

            System.out.println("Registered for callback.");
            //cli.outln(obja.subscribe(filter)); //TODO now useless pls change to the correct output
            //TODO UUID speichern diese braucht man zum abmelden
            //TODO abmelden mit UUID
        } catch (InvalidFilterException ex) {
            // !subscribe (USER_.*)|(BID_.*)
            cli.outln(ex.getMessage());
            // ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Unsubscribe Client to a specific Event of the AnalyticsServer
     * @param subscriptionID 
     */
    public void unsubscribe(int subscriptionID) {
        cli.outln("Unsubscribe:");
        try {
            obja.unsuscribe("" + subscriptionID);
        } catch (Exception ex) {
        }

    }
}
