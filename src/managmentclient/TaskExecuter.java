package managmentclient;

import Exceptions.UserInputException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            obja = (AnalyticServerInterface) Naming.lookup(analyticsServer);
            secure = null;
        } catch (Exception ex) {
            System.out.println("Client exit: " + ex.getMessage());
        }

    }

    /**
     * Logout User by reseting Interface reference
     * @param username user to be logged out
     */
    public void logout(String username) {
        // TODO update this ?  
        System.out.println("logout");
        secure = null;
        System.out.println("logout "+username);
    }

    /**
     * Login a user by tyring to permit acces to the secure interface
     * @param username
     * @param password 
     */
    public boolean login(String username, String password) {
        System.out.println("login");
        boolean ret = false;
        try {
            secure = (BillingServerSecureInterface) objb.login(username, password);
            if(secure!=null){
                ret=true;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserInputException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe) {
            // tritt bei fehlerhaftenm login auf
            //TODO handel this
        }
        return ret;
    }

    /**
     * Printing the existing pricesteps of the billingserver
     */
    public void steps() {
        System.out.println("steps");
        try {
            // PriceSteps ps = secure.getPriceSteps();
            System.out.println(secure.getPriceSteps().toString());
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
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
            // TODO Ausgabe mittels getter Methoden 
            if (b == null) {
                System.out.println("No Bills for User " + username);
            } else {
                System.out.println(b.getUsername() + " with ID "
                        + b.getAuctionID() + ", Price: " + b.getPrice());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Subscribe Client to a specific Event of the AnalyticsServer
     * @param filter 
     */
    public void subscribe(String filter) {
        System.out.println("subscribe");
        try {
            // TODO filter pruefen ?
            System.out.println(obja.subscribe(filter));
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
