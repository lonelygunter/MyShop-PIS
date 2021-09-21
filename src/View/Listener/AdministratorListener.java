package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Business.ItemBusiness;
import Business.ManagerBusiness;
import Business.SessionManager;
import Business.StoreBusiness;
import Business.WholesalerBusiness;
import DAO.CategoryDAO;
import DAO.ItemDAO;
import DAO.ManagerDAO;
import DAO.StoreDAO;
import DAO.UserDAO;
import DAO.WholesalerDAO;
import Model.Item;
import Model.ItemResponce;
import Model.Manager;
import Model.ManagerResponce;
import Model.Store;
import Model.StoreResponce;
import Model.Wholesaler;
import Model.WholesalerResponce;
import View.AppFrame;
import View.CreateItem;
import View.CreateManager;
import View.CreateStore;
import View.CreateWholesaler;
import View.ModifyItem;
import View.ModifyManager;
import View.ModifyStore;
import View.ModifyWholesaler;

public class AdministratorListener implements ActionListener{

    private AppFrame appFrame;

    private CreateItem createItem;
    private ModifyItem modifyItem;

    private CreateWholesaler createWholesaler;
    private ModifyWholesaler modifyWholesaler;
    private CreateStore createStore;
    private ModifyStore modifyStore;
    private CreateManager createManager;
    private ModifyManager modifyManager;

    private boolean isInWholesalerPanel;
    private boolean isInStorePanel;
    private boolean isInManageManagerPanel;

    public AdministratorListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();


        // ITEM BUTTON

        /**
         * per CREARE un nuovo articolo
         */
        if ("createItemButton".equals(cmd)) {
            createItem = new CreateItem(this);
        } //per confermare di voler CREARE un articolo
        if ("confirmCreateItemButton".equals(cmd)) {
            Item item = new Item(createItem.getName(), createItem.getDescription(), createItem.getPrice(), createItem.getUserType(), createItem.getAvailability(), WholesalerDAO.getInstance().findById(createItem.getWholesalerId()), CategoryDAO.getInstance().findById(createItem.getCategoryId()));
            ItemResponce res = ItemBusiness.getInstance().add(item);
            Item i = res.getItem();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmCreateItemButton", i);
                
                // articolo eliminato
                ItemDAO.getInstance().add(i);
                createItem.dispose();
            }
        }

        /**
         * per MODIFICARE un articolo
         */
        if ("modifyItemButton".equals(cmd)) {
            modifyItem = new ModifyItem(this);
        } //per confermare di voler MODIFICARE un articolo
        if ("confirmModifyItemButton".equals(cmd)) {
            Item item = new Item(modifyItem.getId(), modifyItem.getName(), modifyItem.getDescription(), modifyItem.getPrice(), modifyItem.getUserType(), modifyItem.getAvailability(), WholesalerDAO.getInstance().findById(modifyItem.getWholesalerId()), CategoryDAO.getInstance().findById(modifyItem.getCategoryId()));
            ItemResponce res = ItemBusiness.getInstance().modify(item);
            Item i = res.getItem();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmModifyItemButton", i);
                
                // articolo eliminato
                ItemDAO.getInstance().update(i);
                modifyItem.dispose();
            }
        }
        

        // MANAGER BUTTON
        
        /**
         * per CREARE un Manager
         */
        if ("createManagerButton".equals(cmd)) {
            createManager = new CreateManager(this);
        } //per confermare di voler CREARE un Manager
        if ("confirmCreateManagerButton".equals(cmd)) {
            Manager item = new Manager(createManager.getUsername(), createManager.getPassword(), createManager.getName(), createManager.getSurname(), createManager.getAge(), createManager.getEmail(), createManager.getTelephone(), createManager.getStreet(), createManager.getCap(), "M", 0);
            ManagerResponce res = ManagerBusiness.getInstance().add(item);
            Manager i = res.getManager();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmCreateManagerButton", i);
                
                // articolo eliminato
                ManagerDAO.getInstance().add(i);
                createManager.dispose();
            }
        }

        /**
         * per MODIFICARE un Manager
         */
        if ("modifyManagerButton".equals(cmd)) {
            modifyManager = new ModifyManager(this);
        } //per confermare di voler MODIFICARE un Manager
        if ("confirmModifyManagerButton".equals(cmd)) {
            Manager item = new Manager(modifyManager.getId(), modifyManager.getUsername(), modifyManager.getPassword(), modifyManager.getName(), modifyManager.getSurname(), modifyManager.getAge(), modifyManager.getEmail(), modifyManager.getTelephone(), modifyManager.getStreet(), modifyManager.getCap(), "M", 0);
            ManagerResponce res = ManagerBusiness.getInstance().modify(item);
            Manager i = res.getManager();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmModifyManagerButton", i);
                
                // articolo eliminato
                ManagerDAO.getInstance().update(i);
                modifyManager.dispose();
            }
        }


        // WHOLSALER BUTTON

        /**
         * per CREARE un grossista
         */
        if ("createWholesalerButton".equals(cmd)) {
            createWholesaler = new CreateWholesaler(this);
        } //per confermare di voler CREARE un grossista
        if ("confirmCreateWholesalerButton".equals(cmd)) {
            Wholesaler w = new Wholesaler(createWholesaler.getName(), createWholesaler.getEmail(), createWholesaler.getTelephone(), createWholesaler.getWebsite(), createWholesaler.getCity(), createWholesaler.getNation());
            WholesalerResponce res = WholesalerBusiness.getInstance().add(w);
            Wholesaler i = res.getWholesaler();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmCreateWholesalerButton", i);
                
                // articolo eliminato
                WholesalerDAO.getInstance().add(i);
                createWholesaler.dispose();
            }
        }

        /**
         * per MODIFICARE un grossista
         */
        if ("modifyWholesalerButton".equals(cmd)) {
            modifyWholesaler = new ModifyWholesaler(this);
        } //per confermare di voler MODIFICARE un grossista
        if ("confirmModifyWholesalerButton".equals(cmd)) {
            Wholesaler w = new Wholesaler(modifyWholesaler.getId(), modifyWholesaler.getName(), modifyWholesaler.getEmail(), modifyWholesaler.getTelephone(), modifyWholesaler.getWebsite(), modifyWholesaler.getCity(), modifyWholesaler.getNation());
            WholesalerResponce res = WholesalerBusiness.getInstance().modify(w);
            Wholesaler i = res.getWholesaler();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmModifyWholesalerButton", i);
                
                // articolo eliminato
                WholesalerDAO.getInstance().update(i);
                modifyWholesaler.dispose();
            }
        }

        
        // STORE BUTTON

        /**
         * per CREARE un punto vendita
         */
        if ("createStoreButton".equals(cmd)) {
            createStore = new CreateStore(this);
        } //per confermare di voler CREARE un punto venditax
        if ("confirmCreateStoreButton".equals(cmd)) {
            Store w = new Store(createStore.getTelephone(), createStore.getStreet(), createStore.getCap(), createStore.getNation(), UserDAO.getInstance().getByUsername(createStore.getManager()));
            StoreResponce res = StoreBusiness.getInstance().add(w);
            Store i = res.getStore();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmCreateStoreButton", i);
                
                // articolo eliminato
                StoreDAO.getInstance().add(i);
                createStore.dispose();
            }
        }

        /**
         * per MODIFICARE un punto vendita
         */
        if ("modifyStoreButton".equals(cmd)) {
            modifyStore = new ModifyStore(this);
        } //per confermare di voler MODIFICARE un punto vendita
        if ("confirmModifyStoreButton".equals(cmd)) {
            Store w = new Store(modifyStore.getId(), modifyStore.getTelephone(), modifyStore.getStreet(), modifyStore.getCap(), modifyStore.getNation(), UserDAO.getInstance().getByUsername(modifyStore.getManager()));
            StoreResponce res = StoreBusiness.getInstance().modify(w);
            Store i = res.getStore();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmModifyStoreButton", i);
                
                // articolo eliminato
                StoreDAO.getInstance().update(i);
                modifyStore.dispose();
            }
        }
    }

    public boolean isInWholesalerPanel() {
        return isInWholesalerPanel;
    }

    public void setIsInWholesalerPanel(boolean isInWholesalerPanel) {
        this.isInWholesalerPanel = isInWholesalerPanel;
    }

    public boolean isInStorePanel() {
        return isInStorePanel;
    }

    public void setIsInStorePanel(boolean isInStorePanel) {
        this.isInStorePanel = isInStorePanel;
    }

    public boolean isInManageManagerPanel() {
        return isInManageManagerPanel;
    }

    public void setIsInManageManagerPanel(boolean isInManageManagerPanel) {
        this.isInManageManagerPanel = isInManageManagerPanel;
    }
}
