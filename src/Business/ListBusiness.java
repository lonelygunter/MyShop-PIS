package Business;

import java.util.ArrayList;
import java.util.Date;

import DAO.IListDAO;
import DAO.ListDAO;
import Model.Item;
import Model.List;
import Model.ListResponce;
import Model.User;

public class ListBusiness {

    private static ListBusiness instance;

    IListDAO lDao = ListDAO.getInstance();

    public enum Privilege {MANAGE_SHOP, ADMIN_SYSTEM}

    /**
     * promette di sinconzzare l'accesso
     * (cioe' mette in fila i processi che vogliono usare il metodo)
     */
    public static synchronized ListBusiness getInstance() {
        if (instance == null) {
            instance = new ListBusiness();
        }
        return instance;
    }

    private ListBusiness() {
    }

    public void setLDao(IListDAO lDao) {
        this.lDao = lDao;
    }

    public ListResponce add(String name, User buyer, User currentUser){
        ListResponce res = new ListResponce();
        res.setMessage("Errore non definito");

        // 1. Listname già registrato
        if (lDao.listExists(name) && (lDao.getByName(name).getBuyerId() == currentUser.getId())) {
            res.setMessage("Lista già presente, inserire un nome diverso.");
            res.setResult(ListResponce.ListResult.LISTNAME_EXISTS);
            res.setList(null);
            return res;
        }

        // 3. Ottenere i dati della lista
        List l = new List(name, new Date(), 0, buyer, "N", new ArrayList<>());
        if (l != null) {
            res.setMessage("Lista creata con successo");
            res.setList(l);
            res.setResult(ListResponce.ListResult.LIST_CREATED);
        }

        return res;
    }

    public ListResponce insertItem(Item currentItem, String listName){
        ListResponce res = new ListResponce();
        res.setMessage("Errore non definito");

        // 1. Listname non registrato
        if (!lDao.listExists(listName)) {
            res.setMessage("Lista non esistente, inserire una lista valida.");
            res.setResult(ListResponce.ListResult.LISTNAME_NOT_EXISTS);
            res.setList(null);
            return res;
        }

        // 2. controllare se l'item è già presente
        if (lDao.itemExists(currentItem.getId(), lDao.getByName(listName).getId())) {
            res.setMessage("Articolo già presente all'interno della lista.");
            res.setResult(ListResponce.ListResult.ITEM_EXISTS);
            res.setList(null);
            return res;
        }

        // 3. Ottenere i dati dell'utente
        List l = ListDAO.getInstance().getByName(listName);
        if (l != null) {
            res.setMessage("Item aggiunto con successo");
            res.setList(l);
            res.setResult(ListResponce.ListResult.LIST_CREATED);
        }

        return res;
    }
}
