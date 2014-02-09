package managmentclient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author j
 */
public class TaskExecuter {

    private ManagmentClient c;
    private RMI_Billing objb;
    private RMI_Analytics obja;

    public TaskExecuter(ManagmentClient c, String billingServer, String analyticsServer) {
        this.c = c;
        try {
            // example: objb = (RMI_Billing)Naming.lookup("//localhost/RmiServer");
            objb = (RMI_Billing) Naming.lookup(billingServer);
            obja = (RMI_Analytics) Naming.lookup(analyticsServer);
        } catch (Exception ex) {
            System.out.println("Client exit: " + ex.getMessage());
        }
       
    }

    public void logout(String username) {
        try {
            objb.logout(username);
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("logout");
    }

    public void login(String username, String password) {
        try {
            objb.login(username, password);
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("login");
    }

    public void steps() {
        try {
            PriceSteps ps = objb.getPriceStep();
            //TODO ausgabe mit getter Mehtoden
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("steps");
    }

    public boolean addStep(double startPrice, double endPrice, double fixedPrice,
            double variablePricePercent) {
        try {
            objb.createPriceSteps(startPrice, endPrice, fixedPrice, fixedPrice);
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("addstep");
        return false;
    }

    public boolean remove(double startPrice, double endPrice) {
        try {
            objb.deletePriceStep(startPrice, endPrice);
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("remove");
        return false;
    }

    public void bill(String username) {
        try {
            Bill b = objb.getBill(username);
            // TODO Ausgabe mittels getter Methoden
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("bill");
    }

    public void subscribe(String filter) {
        try {
            // filter pruefen
            obja.subscribe(filter);
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sub");
    }

    public void unsubscribe(int subscriptionID) {
        try {
            obja.unsubscribe(""+subscriptionID);
        } catch (RemoteException ex) {
            Logger.getLogger(TaskExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("unsubs");
    }
}
