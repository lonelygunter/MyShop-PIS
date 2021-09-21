package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Business.ListBusiness;
import Business.SessionManager;
import DAO.ListDAO;
import DAO.OrderDAO;
import Model.Item;
import Model.List;
import Model.ListResponce;
import View.AppFrame;
import View.ConfirmToDoOperation;
import View.CreateList;

public class ListListener implements ActionListener {

    private AppFrame appFrame;
    private CreateList createList;
    private LoginListener logList;

    private Item currentItem;
    private List currentList;
    private ConfirmToDoOperation ctdo;
    

    public ListListener(AppFrame appFrame, LoginListener logList) {
        this.appFrame = appFrame;
        this.logList = logList;
        this.currentItem = new Item();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        /**
         * per chiamare chi CREARE una nuova lista
         */
        if ("listCreateButton".equals(cmd)) {
            createList = new CreateList(appFrame, this);
        }

        /**
         * per CREARE una nuova lista
         */
        if ("listNameField".equals(cmd)) {
            ListResponce res = ListBusiness.getInstance().add(createList.getListName(), logList.getCurrentUser(), logList.getCurrentUser());
            List l = res.getList();

            //2. verificare che l'utente non sia già registrato
            if (l == null) {
                // la creazione è fallita -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                // dalle slide su java swing, prendere l'istruzione JDialog
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // la creazione è andato a buon fine
                SessionManager.getInstance().getSession().put("listCreation", l);
                appFrame.getHeader().refresh();
                appFrame.getTopMenu().refresh();

                // aggiungere la lista nel db
                ListDAO lDaoToAdd = ListDAO.getInstance();
                List listToAdd = new List(createList.getListName(), new Date(), 0, logList.getCurrentUser(), "N", new ArrayList<>());
                lDaoToAdd.add(listToAdd);
                createList.clearFields();
                createList.dispose();
            }
        }

        /**
         * per ELIMINARE la lista
         */
        if ("deleteListButton".equals(cmd)) {
            ctdo = new ConfirmToDoOperation(this, "eliminare la lista");
        } //per confermare di eliminare la lista
        if ("eliminare la lista".equals(cmd)) {
            ListDAO.getInstance().removeAllListHasItemById(getCurrentList().getId());
            ListDAO.getInstance().removeById(getCurrentList().getId());
            
            ctdo.dispose();
        }

        /**
         * per confermare il pagamento dell'ordine
         */
        if ("effettua il pagamento".equals(cmd)) {
            OrderDAO.getInstance().removeAll(logList.getCurrentUser().getId());
        }

        
    }

    public void setCurrentItem(Item item) {
        this.currentItem = item;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentList(List currentList) {
        this.currentList = currentList;
    }

    public List getCurrentList() {
        return currentList;
    }
}
