package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Business.BuyingListDocument;
import Business.CategoryBusiness;
import Business.ItemBusiness;
import Business.ListBusiness;
import Business.SessionManager;
import Business.StoreBusiness;
import Business.UserBusiness;
import Business.WholesalerBusiness;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.ImageDAO;
import DAO.ItemDAO;
import DAO.ListDAO;
import DAO.ManagerDAO;
import DAO.OrderDAO;
import DAO.PurchaseDAO;
import DAO.StoreDAO;
import DAO.UserDAO;
import DAO.WholesalerDAO;
import Model.Category;
import Model.CategoryResponce;
import Model.Feedback;
import Model.Item;
import Model.ItemResponce;
import Model.List;
import Model.ListResponce;
import Model.Order;
import Model.Purchase;
import Model.Store;
import Model.StoreResponce;
import Model.User;
import Model.UserResponce;
import Model.Wholesaler;
import Model.WholesalerResponce;
import View.AppFrame;
import View.ItemPagePanel;
import View.LeaveFeedback;
import View.SeeListPanel;
import View.UreSureOrderPanel;
import View.UserInteract;

public class NormalCatalogListener implements ActionListener, MouseListener{

    private AppFrame appFrame;
    private LoginListener logList;
    private ListListener lList;

    private Item currentItem;
    private UserInteract userInteract;
    private LeaveFeedback leaveFeedback;
    private boolean replyfeedback;
    private UreSureOrderPanel ureSureOrderPanel;
    private ItemPagePanel itemPagePanel;

    private boolean isInNormalCatalog;

    public NormalCatalogListener(AppFrame appFrame, LoginListener logList, ListListener lList) {
        this.appFrame = appFrame;
        this.currentItem = new Item();
        this.logList = logList;
        this.replyfeedback = false;
        this.lList = lList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        /**
         * per ORDINARE un articolo esaurito
         */
        if ("orderNowButton".equals(cmd)) {
            if (logList.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(appFrame, "Effettua il login per poter ordinare degli aricoli", "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                ureSureOrderPanel = new UreSureOrderPanel(this);
            }
        } //per confermare di voler ORDINARE un articolo esaurito
        if ("confirmOrderButton".equals(cmd)) {
            ItemDAO.getInstance().addOrder(getCurrentItem(), null, logList.getCurrentUser());
            ureSureOrderPanel.dispose();
        }


        // FEEDBACK

        /**
         * per SCRIVERE il testo del feedback
         */
        if ("feedbackTextField".equals(cmd)) {
            boolean managerOrAdmin = UserDAO.getInstance().managerExists(logList.getCurrentUser());
            Purchase purchase = PurchaseDAO.getInstance().findByUserId(logList.getCurrentUser().getId());
            if ((purchase != null) || managerOrAdmin) {
                int purchase_has_item = 0;
                if ((purchase != null)) {
                    purchase_has_item = PurchaseDAO.getInstance().findByItemIdAndPurchaseId(getCurrentItem().getId(), purchase.getId());
                }

                if ((purchase_has_item != 0) || managerOrAdmin) {
                    if (!replyfeedback) {
                        Feedback feedback = new Feedback(leaveFeedback.getFeedbackField(), leaveFeedback.getRatingField(), new Date(20010101), getCurrentItem(), purchase_has_item, 0);
                        FeedbackDAO.getInstance().write(feedback);

                        leaveFeedback.clearFields();
                        leaveFeedback.dispose();
                    } else {
                        Feedback feedback = new Feedback(leaveFeedback.getFeedbackField(), leaveFeedback.getRatingField(), new Date(20010101), getCurrentItem(), purchase_has_item, 1);
                        FeedbackDAO.getInstance().answer(feedback, managerOrAdmin);

                        leaveFeedback.clearFields();
                        leaveFeedback.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(appFrame, "Non puoi dare una valutazione ad un articolo se non lo hai comprato", "Creation error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * per RILASCIARE un feedback ad articoli 
         */
        if ("feedbackButton".equals(cmd)) {
            if (logList.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(appFrame, "Effettua il login per poter aggiungere dei commenti agli articoli", "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                leaveFeedback = new LeaveFeedback(this);
                this.replyfeedback = false;
            }
        }

        /**
         * per RILASCIARE un feedback ad altri feedback
         */
        if ("replyToFeedbackButton".equals(cmd)) {
            if (logList.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(appFrame, "Effettua il login per poter rispondere a questo commento", "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                leaveFeedback = new LeaveFeedback(this);
                this.replyfeedback = true;
            }
        }

        /**
         * per AGGIUNGERE item in una lista
         */
        if ("addToListNameField".equals(cmd)) {
            ListResponce res = ListBusiness.getInstance().insertItem(currentItem, userInteract.getField());
            List l = res.getList();

            if (l == null) {
                // la lista non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // la lista esiste
                SessionManager.getInstance().getSession().put("addItemToList", l);
                
                // aggiungere item nella lista (nel db)
                ListDAO.getInstance().insertItem(currentItem, l);
                userInteract.dispose();
            }
        }

        /**
         * per AGGIUNGERE un articolo alla lista
         */
        if ("addToListButton".equals(cmd)) {
            if (logList.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(appFrame, "Effettua il login per poter aggiungere articoli ad una lista", "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                userInteract = new UserInteract(this, "addToListNameField", "il nome della lista");
            }
        }

        /**
         * per inviare il PDF della lista
         */
        if ("sendPdfButton".equals(cmd)) {
            userInteract = new UserInteract(this, "sendPdfField", "il nome della lista");
        }

        /**
         * per inviare il PDF della lista all'email del cliente
         */
        if ("sendPdfField".equals(cmd)) {
            BuyingListDocument bld = new BuyingListDocument(ListDAO.getInstance().getByName(userInteract.getField()), logList.getCurrentUser());
            String subject = "Pdf della lista di MyShop";
            String body = "Quello allegato è il resoconto della sua lista.";

            try {
                bld.send(logList.getCurrentUser().getEmail(), subject, body);
            } catch (Exception exep) {
                exep.printStackTrace();
            }
        }

        /**
         * per ELIMINARE un ITEM da una lista
         */
        if ("deleteItemListButton".equals(cmd)) {
            userInteract = new UserInteract(this, "deleteItemFromList", "il nome dell'articolo");
        }

        /**
         * per ELIMINARE item da una lista
         */
        if ("deleteItemFromList".equals(cmd)) {
            ItemResponce res = ItemBusiness.getInstance().deleteItem(lList.getCurrentList(), userInteract.getField());
            Item i = res.getItem();

            if (i == null) {
                // la item non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'item esiste
                SessionManager.getInstance().getSession().put("deleteItemFromList", i);
                
                // aggiungere item nella lista (nel db)
                ListDAO.getInstance().removeListHasItemById(lList.getCurrentList().getId(), i.getId());
                userInteract.dispose();
            }
        }

        /**
         * per DISABILITARE un utente
         */
        if ("disableUserButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmDesableUser", "lo username dell'utente da disabilitare"); 
        }// per CONFERMARE di voler disabilitare un utente
        if ("confirmDesableUser".equals(cmd)) {
            String username = userInteract.getField();

            UserResponce res = UserBusiness.getInstance().disable(UserDAO.getInstance().getByUsername(username));
            User u = res.getUser();

            if (u == null) {
                // l'utente non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'utente esiste
                SessionManager.getInstance().getSession().put("confirmDesableUser", u);
                
                // disabilitare l'utente
                UserDAO.getInstance().disable(appFrame, UserDAO.getInstance().getByUsername(username));
                userInteract.dispose();
            }
        }


        /**
         * per AGGIUNGERE un  articolo all'ordine
         */
        if ("addToOrderButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmAddToOrder", "il nome dell'articolo");
        } //per confermare di voler AGGIUNGERE un articolo all'ordine
        if ("confirmAddToOrder".equals(cmd)) {
            String itemName = userInteract.getField();

            ItemResponce res = ItemBusiness.getInstance().addToOrder(itemName);
            Item i = res.getItem();

            if (i == null) {
                // l'item non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'item esiste
                SessionManager.getInstance().getSession().put("confirmAddToOrder", i);
                
                // aggiungere item
                OrderDAO.getInstance().add(new Order(new Date(), i.getPrice(), logList.getCurrentUser(), i));
                userInteract.dispose();
            }
        }

        /**
         * per ELIMINARE un  articolo dall'ordine
         */
        if ("removeFromOrderButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmRemoveToOrder", "il nome dell'articolo"); 
        } //per confermare di voler ELIMINARE un articolo all'ordine
        if ("confirmRemoveToOrder".equals(cmd)) {
            String itemName = userInteract.getField();

            ItemResponce res = ItemBusiness.getInstance().removeToOrder(itemName, logList.getCurrentUser().getId());
            Item i = res.getItem();

            if (i == null) {
                // l'item non presnte dell'ordine -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'item esiste
                SessionManager.getInstance().getSession().put("confirmAddToOrder", i);
                
                // rimuovere item
                OrderDAO.getInstance().removeToOrder(ItemDAO.getInstance().findByName(itemName).getId());
                userInteract.dispose();
            }
        }

        /**
         * per ELIMINARE un articolo
         */
        if ("deleteItemButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmDeleteItemButton", "il nome dell'articolo");
        } // per confermare di voler ELIMINARE un articolo
        if ("confirmDeleteItemButton".equals(cmd)) {
            String itemName = userInteract.getField();

            ItemResponce res = ItemBusiness.getInstance().delete(itemName);
            Item i = res.getItem();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmDesableUser", i);
                
                // articolo eliminato
                ItemDAO.getInstance().removeById(i.getId());
                userInteract.dispose();
            }
        }

        /**
         * per ELIMINARE un grossista
         */
        if ("deleteWholesalerButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmDeleteWholesalerButton", "il nome del grossista");
        } //per confermare di voler ELIMINARE un grossista
        if ("confirmDeleteWholesalerButton".equals(cmd)) {
            String wholesalerName = userInteract.getField();

            WholesalerResponce res = WholesalerBusiness.getInstance().delete(wholesalerName);
            Wholesaler i = res.getWholesaler();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmDeleteWholesalerButton", i);
                
                // articolo eliminato
                WholesalerDAO.getInstance().removeById(i.getId());
                userInteract.dispose();
            }
        }

        /**
         * per ELIMINARE un categoria
         */
        if ("deleteCategoryButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmDeleteCategoryButton", "il nome della categoria");
        } //per confermare di voler ELIMINARE un categoria
        if ("confirmDeleteCategoryButton".equals(cmd)) {
            String categoryName = userInteract.getField();

            CategoryResponce res = CategoryBusiness.getInstance().delete(categoryName);
            Category c = res.getCategory();

            if (c == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmDeleteCategoryButton", c);
                
                // articolo eliminato
                CategoryDAO.getInstance().removeById(c.getId());
                userInteract.dispose();
            }
        }

        /**
         * per ELIMINARE un punto vendita
         */
        if ("deleteStoreButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmDeleteStoreButton", "l'id del punto vendita");
        } //per confermare di voler ELIMINARE un punto vendita
        if ("confirmDeleteStoreButton".equals(cmd)) {
            int storeId = Integer.parseInt(userInteract.getField());

            StoreResponce res = StoreBusiness.getInstance().delete(storeId);
            Store c = res.getStore();

            if (c == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmDeleteStoreButton", c);
                
                // articolo eliminato
                StoreDAO.getInstance().removeById(c.getId());
                userInteract.dispose();
            }
        }

        /**
         * per ELIMINARE un Manager
         */
        if ("deleteManagerButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmDeleteManagerButton", "lo username del Manager");
        } //per confermare di voler ELIMINARE un Manager
        if ("confirmDeleteManagerButton".equals(cmd)) {
            String username = userInteract.getField();

            UserResponce res = UserBusiness.getInstance().delete(UserDAO.getInstance().getByUsername(username).getId());
            User u = res.getUser();

            if (u == null) {
                // l'utente non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'utente esiste
                SessionManager.getInstance().getSession().put("confirmDeleteManagerButton", u);
                
                // disabilitare l'utente
                ManagerDAO.getInstance().removeById(u.getId());
                userInteract.dispose();
            }
        }

        
        /**
         * per CREARE un categoria per lo store
         */
        if ("addCategoryStoreButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmAddCategoryStoreButton", "il nome della categoria");
        } //per confermare di voler CREARE un categoria
          if ("confirmAddCategoryStoreButton".equals(cmd)) {
            Category w = CategoryDAO.getInstance().findByName(userInteract.getField());
            CategoryDAO.getInstance().addToStoreCategories(w, "Lecce");

            userInteract.dispose();
        }

        /**
         * per eliminare un categoria per lo store
         */
        if ("deleteCategoryStoreButton".equals(cmd)) {
            userInteract = new UserInteract(this, "deleteAddCategoryStoreButton", "il nome della categoria");
        } //per confermare di voler CREARE un categoria
          if ("deleteAddCategoryStoreButton".equals(cmd)) {
            Category w = CategoryDAO.getInstance().findByName(userInteract.getField());
            CategoryDAO.getInstance().deleteToStoreCategories(w, "Lecce");

            userInteract.dispose();
        }

        /**
         * per confermare il pagamento della lista
         */
        if ("checkUserListButton".equals(cmd)) {
            userInteract = new UserInteract(this, "confirmcheckUserListButton", "l'id della lista");
        } //per confermare il pagamento della lista
          if ("confirmcheckUserListButton".equals(cmd)) {
            List l = ListDAO.getInstance().findById(Integer.parseInt(userInteract.getField()));
            l.setState("P");
            ListDAO.getInstance().update(l);

            userInteract.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();
        
        /**
         * per VISUALIZZARE la pagina dell'articolo
         */
        ArrayList<Item> items = ItemDAO.getInstance().findAll();
        for (Item item : items) {
            if (clickedLabel.getText().contains(item.getName())) {
                setCurrentItem(item);
                itemPagePanel = new ItemPagePanel(this, item, ImageDAO.getInstance().findByItemId(item.getId()));
                appFrame.setCurrentMainPanel(itemPagePanel);
            }
        }

        /**
         * per VISUALIZZARE la lista
         */
        ArrayList<List> lists = ListDAO.getInstance().findAll();
        for (List list : lists) {
            if (clickedLabel.getText().contains(list.getName())) {
                lList.setCurrentList(list);
                appFrame.setCurrentMainPanel(new SeeListPanel(lList, this));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void setCurrentItem(Item item) {
        this.currentItem = item;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setIsInNormalCatalog(Boolean w) {
        this.isInNormalCatalog = w;
    }

    public boolean isInNormalCatalog() {
        return isInNormalCatalog;
    }
}
