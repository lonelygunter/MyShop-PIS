package Business;

import java.util.ArrayList;

import DAO.FeedbackDAO;
import DAO.IItemDAO;
import DAO.IListDAO;
import DAO.IOrderDAO;
import DAO.ItemDAO;
import DAO.ListDAO;
import DAO.OrderDAO;
import Model.Feedback;
import Model.Item;
import Model.ItemResponce;
import Model.List;

public class ItemBusiness {

    private static ItemBusiness instance;

    IListDAO lDao = ListDAO.getInstance();
    IItemDAO iDao = ItemDAO.getInstance();
    IOrderDAO oDao = OrderDAO.getInstance();

    public ArrayList<Feedback> allFeedbacks = FeedbackDAO.getInstance().findAll();
    public ArrayList<Feedback> feedbacks = new ArrayList<>();

    public enum Privilege {MANAGE_SHOP, ADMIN_SYSTEM}

    /**
     * promette di sinconzzare l'accesso
     * (cioe' mette in fila i processi che vogliono usare il metodo)
     */
    public static synchronized ItemBusiness getInstance() {
        if (instance == null) {
            instance = new ItemBusiness();
        }
        return instance;
    }

    private ItemBusiness() {
    }

    public void setLDao(IListDAO lDao) {
        this.lDao = lDao;
    }

    public void setIDao(IItemDAO iDao) {
        this.iDao = iDao;
    }

    public void setODao(IOrderDAO oDao) {
        this.oDao = oDao;
    }

    public ItemResponce deleteItem(List currentList, String itemName){
        ItemResponce res = new ItemResponce();
        res.setMessage("Errore non definito");

        // 1. controllare se l'item è già presente
        if (!lDao.itemExists(ItemDAO.getInstance().findByName(itemName).getId(), currentList.getId())) {
            res.setMessage("Articolo già presente all'interno della lista.");
            res.setResult(ItemResponce.ItemResult.ITEM_NOT_EXISTS);
            res.setItem(null);
            return res;
        }

        // 2. Ottenere i dati dell'item
        Item i = ItemDAO.getInstance().findByName(itemName);
        if (i != null) {
            res.setMessage("Item rimosso con successo");
            res.setItem(i);
            res.setResult(ItemResponce.ItemResult.ITEM_DELETED);
        }

        return res;
    }

    public ItemResponce addToOrder(String itemName) {
        ItemResponce res = new ItemResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (iDao.findByName(itemName) == null) {
            res.setMessage("Articolo non esistente");
            res.setResult(ItemResponce.ItemResult.ITEM_NOT_EXISTS);
            res.setItem(null);
            return res;
        }

        // 2. Ottenere i dati dell'ordine
        Item i = iDao.findByName(itemName);
        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (i != null) {
            res.setMessage("Articolo aggiunto");
            res.setItem(i);
            res.setResult(ItemResponce.ItemResult.ITEM_EXISTS);
        }

        return res;
    }

    public ItemResponce removeToOrder(String itemName, int currentUserId) {
        ItemResponce res = new ItemResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non presente nell'ordine
        if (oDao.findByNameAndUserId(itemName, currentUserId) == null) {
            res.setMessage("Articolo non presente nell'ordine");
            res.setResult(ItemResponce.ItemResult.ITEM_NOT_IN_ORDER);
            res.setItem(null);
            return res;
        }

        // 2. Ottenere i dati dell'ordine
        Item i = iDao.findByName(itemName);
        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (i != null) {
            res.setMessage("Articolo rimosso");
            res.setItem(i);
            res.setResult(ItemResponce.ItemResult.ITEM_EXISTS);
        }

        return res;
    }

    public ItemResponce delete(String itemName){
        ItemResponce res = new ItemResponce();
        res.setMessage("Errore non definito");

        // 1. controllare se l'item è esiste
        if (!iDao.itemExists(ItemDAO.getInstance().findByName(itemName))) {
            res.setMessage("Articolo non esistente.");
            res.setResult(ItemResponce.ItemResult.ITEM_NOT_EXISTS);
            res.setItem(null);
            return res;
        }

        // 2. Ottenere i dati dell'item
        Item i = ItemDAO.getInstance().findByName(itemName);
        if (i != null) {
            res.setMessage("Item rimosso con successo");
            res.setItem(i);
            res.setResult(ItemResponce.ItemResult.ITEM_DELETED);
        }

        return res;
    }

    public ItemResponce add(Item item) {
        ItemResponce res = new ItemResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (iDao.itemNameExists(item)) {
            res.setMessage("Articolo già esistente");
            res.setResult(ItemResponce.ItemResult.ITEM_EXISTS);
            res.setItem(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (item != null) {
            res.setMessage("Articolo aggiunto");
            res.setItem(item);
            res.setResult(ItemResponce.ItemResult.ITEM_CREATED);
        }

        return res;
    }

    public ItemResponce modify(Item item) {
        ItemResponce res = new ItemResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (!iDao.itemExists(item)) {
            res.setMessage("Articolo non esistente");
            res.setResult(ItemResponce.ItemResult.ITEM_NOT_EXISTS);
            res.setItem(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (item != null) {
            res.setMessage("Articolo modificato");
            res.setItem(item);
            res.setResult(ItemResponce.ItemResult.ITEM_MODIFIED);
        }

        return res;
    }
    
    public ArrayList<Feedback> getAllFeedbackLists(Item item){
        for (int i = 0; i < allFeedbacks.size(); i++) {
            if((item.getId() == FeedbackDAO.getInstance().getItemIdById(allFeedbacks.get(i).getId()))){
                feedbacks.add(allFeedbacks.get(i));
            }
        }
        return feedbacks;
    }
}
