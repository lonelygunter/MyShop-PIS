package Business;

import DAO.IListDAO;
import DAO.IOrderDAO;
import DAO.IStoreDAO;
import DAO.ListDAO;
import DAO.OrderDAO;
import DAO.StoreDAO;
import Model.Store;
import Model.StoreResponce;

public class StoreBusiness {

    private static StoreBusiness instance;

    IListDAO lDao = ListDAO.getInstance();
    IStoreDAO sDao = StoreDAO.getInstance();
    IOrderDAO oDao = OrderDAO.getInstance();

    public enum Privilege {MANAGE_SHOP, ADMIN_SYSTEM}

    /**
     * promette di sinconzzare l'accesso
     * (cioe' mette in fila i processi che vogliono usare il metodo)
     */
    public static synchronized StoreBusiness getInstance() {
        if (instance == null) {
            instance = new StoreBusiness();
        }
        return instance;
    }

    private StoreBusiness() {
    }

    public void setLDao(IListDAO lDao) {
        this.lDao = lDao;
    }

    public void setIDao(IStoreDAO sDao) {
        this.sDao = sDao;
    }

    public void setODao(IOrderDAO oDao) {
        this.oDao = oDao;
    }

    public StoreResponce delete(int storeId){
        StoreResponce res = new StoreResponce();
        res.setMessage("Errore non definito");

        // 1. controllare se il grossista esiste
        if (!sDao.storeExists(StoreDAO.getInstance().findById(storeId))) {
            res.setMessage("Il punto vendita non esistente.");
            res.setResult(StoreResponce.StoreResult.S_NOT_EXISTS);
            res.setStore(null);
            return res;
        }

        // 2. Ottenere i dati dell'store
        Store i = StoreDAO.getInstance().findById(storeId);
        if (i != null) {
            res.setMessage("Store rimosso con successo");
            res.setStore(i);
            res.setResult(StoreResponce.StoreResult.S_DELETED);
        }

        return res;
    }

    public StoreResponce add(Store store) {
        StoreResponce res = new StoreResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (sDao.storeExists(store)) {
            res.setMessage("Il punto vendita già esistente");
            res.setResult(StoreResponce.StoreResult.S_EXISTS);
            res.setStore(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (store != null) {
            res.setMessage("Il punto vendita aggiunto");
            res.setStore(store);
            res.setResult(StoreResponce.StoreResult.S_CREATED);
        }

        return res;
    }

    public StoreResponce modify(Store store) {
        StoreResponce res = new StoreResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (!sDao.storeExists(store)) {
            res.setMessage("Il punto vendita non esistente");
            res.setResult(StoreResponce.StoreResult.S_NOT_EXISTS);
            res.setStore(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (store != null) {
            res.setMessage("Il punto vendita modificato");
            res.setStore(store);
            res.setResult(StoreResponce.StoreResult.S_MODIFIED);
        }

        return res;
    }
}
