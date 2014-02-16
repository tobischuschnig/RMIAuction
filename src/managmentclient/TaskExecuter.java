package managmentclient;

import Exceptions.UserInputException;
import analyticserver.AnalyticServerInterface;
import billingServer.BillingServerInterface;
import billingServer.BillingServerSecureInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
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
    // not in use yet
    private ManagmentClient c;

    public TaskExecuter(ManagmentClient c, String billingServer, String analyticsServer) {
        this.c = c;
        try {
            // example: objb = (RMI_Billing)Naming.lookup("//localhost/RmiServer");
            objb = (BillingServerInterface) Naming.lookup(billingServer);
            // TODO ! Remove this
            // obja = (AnalyticServerInterface) Naming.lookup(analyticsServer);
            secure = null;
        } catch (Exception ex) {
            System.out.println("Client exit: Cannot connect." + "\n(" + ex.getMessage() + ")");
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
        System.out.println("logout " + username);
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
            System.out.println("Price Steps:\n" + secure.getPriceSteps().toString());
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
        System.out.println("addstep");
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
        System.out.println("remove");
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
        System.out.println("bill");
        try {
            Bill b = secure.getBill(username);
            if (b == null) {
                System.out.println("No Bills for User " + username);
            } else {
                System.out.println(b.getUsername() + " with ID "
                        + b.getAuctionID() + ", Price: " + b.getPrice());
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
            System.out.println("subscribe");
            // TODO filter pruefen ?
            System.out.println(obja.subscribe(filter));
        } catch (Exception ex) {
        }

    }

    /**
     * Unsubscribe Client to a specific Event of the AnalyticsServer
     * @param subscriptionID 
     */
    public void unsubscribe(int subscriptionID) {
        System.out.println("unsubscribe");
        try {
            obja.unsubscribe("" + subscriptionID);
        } catch (Exception ex) {
        }

    }
}
