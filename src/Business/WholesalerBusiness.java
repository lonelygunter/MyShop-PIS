package Business;

import DAO.IListDAO;
import DAO.IOrderDAO;
import DAO.IWholesalerDAO;
import DAO.ListDAO;
import DAO.OrderDAO;
import DAO.WholesalerDAO;
import Model.Wholesaler;
import Model.WholesalerResponce;

public class WholesalerBusiness {

    private static WholesalerBusiness instance;

    IListDAO lDao = ListDAO.getInstance();
    IWholesalerDAO wDao = WholesalerDAO.getInstance();
    IOrderDAO oDao = OrderDAO.getInstance();

    public enum Privilege {MANAGE_SHOP, ADMIN_SYSTEM}

    /**
     * promette di sinconzzare l'accesso
     * (cioe' mette in fila i processi che vogliono usare il metodo)
     */
    public static synchronized WholesalerBusiness getInstance() {
        if (instance == null) {
            instance = new WholesalerBusiness();
        }
        return instance;
    }

    private WholesalerBusiness() {
    }

    public void setLDao(IListDAO lDao) {
        this.lDao = lDao;
    }

    public void setIDao(IWholesalerDAO wDao) {
        this.wDao = wDao;
    }

    public void setODao(IOrderDAO oDao) {
        this.oDao = oDao;
    }

    public WholesalerResponce delete(String wholesalerName){
        WholesalerResponce res = new WholesalerResponce();
        res.setMessage("Errore non definito");

        // 1. controllare se il grossista esiste
        if (!wDao.wholesalerExists(WholesalerDAO.getInstance().findByName(wholesalerName))) {
            res.setMessage("Grossista non esistente.");
            res.setResult(WholesalerResponce.WholesalerResult.W_NOT_EXISTS);
            res.setWholesaler(null);
            return res;
        }

        // 2. Ottenere i dati dell'wholesaler
        Wholesaler i = WholesalerDAO.getInstance().findByName(wholesalerName);
        if (i != null) {
            res.setMessage("Wholesaler rimosso con successo");
            res.setWholesaler(i);
            res.setResult(WholesalerResponce.WholesalerResult.W_DELETED);
        }

        return res;
    }

    public WholesalerResponce add(Wholesaler wholesaler) {
        WholesalerResponce res = new WholesalerResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (wDao.wholesalerNameExists(wholesaler)) {
            res.setMessage("Grossista già esistente");
            res.setResult(WholesalerResponce.WholesalerResult.W_EXISTS);
            res.setWholesaler(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (wholesaler != null) {
            res.setMessage("Grossista aggiunto");
            res.setWholesaler(wholesaler);
            res.setResult(WholesalerResponce.WholesalerResult.W_CREATED);
        }

        return res;
    }

    public WholesalerResponce modify(Wholesaler wholesaler) {
        WholesalerResponce res = new WholesalerResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (!wDao.wholesalerExists(wholesaler)) {
            res.setMessage("Grossista non esistente");
            res.setResult(WholesalerResponce.WholesalerResult.W_NOT_EXISTS);
            res.setWholesaler(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (wholesaler != null) {
            res.setMessage("Grossista modificato");
            res.setWholesaler(wholesaler);
            res.setResult(WholesalerResponce.WholesalerResult.W_MODIFIED);
        }

        return res;
    }
}
