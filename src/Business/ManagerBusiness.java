package Business;

import DAO.IListDAO;
import DAO.IManagerDAO;
import DAO.IUserDAO;
import DAO.ListDAO;
import DAO.ManagerDAO;
import DAO.UserDAO;
import Model.Manager;
import Model.ManagerResponce;

public class ManagerBusiness {

    private static ManagerBusiness instance;

    IListDAO lDao = ListDAO.getInstance();
    IManagerDAO mDao = ManagerDAO.getInstance();
    IUserDAO uDao = UserDAO.getInstance();

    public enum Privilege {MANAGE_SHOP, ADMIN_SYSTEM}

    /**
     * promette di sinconzzare l'accesso
     * (cioe' mette in fila i processi che vogliono usare il metodo)
     */
    public static synchronized ManagerBusiness getInstance() {
        if (instance == null) {
            instance = new ManagerBusiness();
        }
        return instance;
    }

    private ManagerBusiness() {
    }

    public void setLDao(IListDAO lDao) {
        this.lDao = lDao;
    }

    public void setIDao(IManagerDAO mDao) {
        this.mDao = mDao;
    }

    public void setODao(IUserDAO uDao) {
        this.uDao = uDao;
    }

    public ManagerResponce add(Manager manager) {
        ManagerResponce res = new ManagerResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (uDao.userExists(manager.getUsername())) {
            res.setMessage("Manager già esistente");
            res.setResult(ManagerResponce.ManagerResult.M_EXISTS);
            res.setManager(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (manager != null) {
            res.setMessage("Manager aggiunto");
            res.setManager(manager);
            res.setResult(ManagerResponce.ManagerResult.M_CREATED);
        }

        return res;
    }

    public ManagerResponce modify(Manager manager) {
        ManagerResponce res = new ManagerResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (uDao.findById(manager.getId()) == null) {
            res.setMessage("Manager non esistente");
            res.setResult(ManagerResponce.ManagerResult.M_NOT_EXISTS);
            res.setManager(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (manager != null) {
            res.setMessage("Manager modificato");
            res.setManager(manager);
            res.setResult(ManagerResponce.ManagerResult.M_MODIFIED);
        }

        return res;
    }
}
