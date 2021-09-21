package Business;

import DAO.CategoryDAO;
import DAO.ICategoryDAO;
import DAO.IListDAO;
import DAO.IOrderDAO;
import DAO.ListDAO;
import DAO.OrderDAO;
import Model.Category;
import Model.CategoryResponce;

public class CategoryBusiness {

    private static CategoryBusiness instance;

    IListDAO lDao = ListDAO.getInstance();
    ICategoryDAO wDao = CategoryDAO.getInstance();
    IOrderDAO oDao = OrderDAO.getInstance();

    public enum Privilege {MANAGE_SHOP, ADMIN_SYSTEM}

    /**
     * promette di sinconzzare l'accesso
     * (cioe' mette in fila i processi che vogliono usare il metodo)
     */
    public static synchronized CategoryBusiness getInstance() {
        if (instance == null) {
            instance = new CategoryBusiness();
        }
        return instance;
    }

    private CategoryBusiness() {
    }

    public void setLDao(IListDAO lDao) {
        this.lDao = lDao;
    }

    public void setIDao(ICategoryDAO wDao) {
        this.wDao = wDao;
    }

    public void setODao(IOrderDAO oDao) {
        this.oDao = oDao;
    }

    public CategoryResponce delete(String categoryName){
        CategoryResponce res = new CategoryResponce();
        res.setMessage("Errore non definito");

        // 1. controllare se il grossista esiste
        if (!wDao.categoryExists(CategoryDAO.getInstance().findByName(categoryName))) {
            res.setMessage("Categoria non esistente.");
            res.setResult(CategoryResponce.CategoryResult.C_NOT_EXISTS);
            res.setCategory(null);
            return res;
        }

        // 2. Ottenere i dati dell'category
        Category i = CategoryDAO.getInstance().findByName(categoryName);
        if (i != null) {
            res.setMessage("Category rimosso con successo");
            res.setCategory(i);
            res.setResult(CategoryResponce.CategoryResult.C_DELETED);
        }

        return res;
    }

    public CategoryResponce add(Category category) {
        CategoryResponce res = new CategoryResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (wDao.categoryNameExists(category)) {
            res.setMessage("Categoria già esistente");
            res.setResult(CategoryResponce.CategoryResult.C_EXISTS);
            res.setCategory(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (category != null) {
            res.setMessage("Categoria aggiunto");
            res.setCategory(category);
            res.setResult(CategoryResponce.CategoryResult.C_CREATED);
        }

        return res;
    }

    public CategoryResponce modify(Category category) {
        CategoryResponce res = new CategoryResponce();
        res.setMessage("Errore non definito");

        // 1. Ordername non esistente
        if (!wDao.categoryExists(category)) {
            res.setMessage("Categoria non esistente");
            res.setResult(CategoryResponce.CategoryResult.C_NOT_EXISTS);
            res.setCategory(null);
            return res;
        }

        // alternativa: restituire istanza specifica di Cliente, Manager o Amministratore
        if (category != null) {
            res.setMessage("Categoria modificato");
            res.setCategory(category);
            res.setResult(CategoryResponce.CategoryResult.C_MODIFIED);
        }

        return res;
    }
}
