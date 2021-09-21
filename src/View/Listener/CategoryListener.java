package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Business.CategoryBusiness;
import Business.SessionManager;
import DAO.CategoryDAO;
import Model.Category;
import Model.CategoryResponce;
import View.AppFrame;
import View.CreateCategory;
import View.ModifyCategory;
import View.UserInteract;

public class CategoryListener implements ActionListener {
    
    private AppFrame appFrame;
    private boolean isInCategoryPanel;
    private NormalCatalogListener ncList;

    private CreateCategory createCategory;
    private ModifyCategory modifyCategory;
    private UserInteract userInteract;

    public CategoryListener(AppFrame appFrame, NormalCatalogListener ncList) {
        this.appFrame = appFrame;
        this.ncList = ncList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        /**
         * per CREARE un categoria
         */
        if ("createCategoryButton".equals(cmd)) {
            createCategory = new CreateCategory(this);
        }  //per confermare di voler CREARE un categoria
        if ("confirmCreateCategoryButton".equals(cmd)) {
            Category w = new Category(createCategory.getName(), createCategory.getFatherId());
            CategoryResponce res = CategoryBusiness.getInstance().add(w);
            Category i = res.getCategory();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmCreateCategoryButton", i);
                
                // articolo eliminato
                CategoryDAO.getInstance().add(i);

                if (i.getFatherId() == 0) {
                    Category c = CategoryDAO.getInstance().findByName(i.getName());
                    c.setFatherId(c.getId());
                    CategoryDAO.getInstance().update(c);
                }
            
                createCategory.dispose();
            }
        }

        /**
         * per MODIFICARE un categoria
         */
        if ("modifyCategoryButton".equals(cmd)) {
            modifyCategory = new ModifyCategory(this);
        } // per confermare di voler MODIFICARE un categoria
        if ("confirmModifyCategoryButton".equals(cmd)) {
            Category c = new Category(modifyCategory.getId(), modifyCategory.getName(), modifyCategory.getFatherId());
            CategoryResponce res = CategoryBusiness.getInstance().modify(c);
            Category i = res.getCategory();

            if (i == null) {
                // l'articolo non esiste -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                JOptionPane.showMessageDialog(appFrame, reason, "Creation error", JOptionPane.ERROR_MESSAGE);
            } else {
                // l'articolo esiste
                SessionManager.getInstance().getSession().put("confirmModifyCategoryButton", i);
                
                // articolo eliminato
                CategoryDAO.getInstance().update(i);
                modifyCategory.dispose();
            }
        }

    }

    public boolean isInCategoryPanel() {
        return isInCategoryPanel;
    }

    public void setIsInCategoryPanel(boolean isInCategoryPanel) {
        this.isInCategoryPanel = isInCategoryPanel;
    }
}
