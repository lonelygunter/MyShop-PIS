package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import DAO.CategoryDAO;
import DAO.ItemDAO;
import DAO.UserDAO;
import Model.Category;
import Model.Item;
import View.AdministratorCatalog;
import View.AppFrame;
import View.CategoryPanel;
import View.ManageManagerPanel;
import View.ManageUserPanel;
import View.ManagerCatalog;
import View.NormalCatalog;
import View.StorePanel;
import View.WholesalerPanel;

public class JComboBoxListener implements ActionListener {

    private AppFrame appFrame;
    private NormalCatalogListener ncList;
    private LoginListener logList;
    private AdministratorListener adminList;
    private ManagerListener mList;
    private CategoryListener cList;

    public JComboBoxListener(AppFrame appFrame, NormalCatalogListener ncList, LoginListener logList, AdministratorListener adminList, ManagerListener mList, CategoryListener cList) {
        this.appFrame = appFrame;
        this.ncList = ncList;
        this.logList = logList;
        this.adminList = adminList;
        this.mList = mList;
        this.cList = cList;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cBox  = (JComboBox) e.getSource();
        String cBoxCmd = (String) cBox.getSelectedItem();
        String sqlItem = "";
        String sqlUser = "";
        String sqlWholesaler = "";
        String sqlCategory = "";
        String sqlStore = "";
        String sqlManager = "";


        // JCOMBOBOX NORMAL CATALOG
        
        switch (cBoxCmd) {
            case "Nome":
                sqlItem = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` ORDER BY `Name`;";
                sqlUser = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable` FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'U') ORDER BY `Name`;";
                sqlWholesaler = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler ORDER BY `Name`;";
                sqlCategory = "SELECT Id, Name, Category_father_Id FROM myshopmf.Category ORDER BY `Name`;";
                sqlManager = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable` FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'M') ORDER BY `Name`;";


                executeSQLjcb(sqlItem, sqlUser, sqlWholesaler, sqlCategory, sqlStore, sqlManager);
                break;

            case "Prezzo crescente":
                sqlItem = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` ORDER BY `Price`;";

                executeSQLjcb(sqlItem, sqlUser, sqlWholesaler, sqlCategory, sqlStore, sqlManager);
                break;

            case "Prezzo decrescente":
                sqlItem = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` ORDER BY `Price` DESC;";

                executeSQLjcb(sqlItem, sqlUser, sqlWholesaler, sqlCategory, sqlStore, sqlManager);
                break;

            case "Id crescente":
                sqlItem = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` ORDER BY `Id`;";
                sqlUser = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable`  FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'U') ORDER BY `Id`;";
                sqlWholesaler = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler ORDER BY `Id`;";
                sqlCategory = "SELECT Id, Name, Category_father_Id FROM myshopmf.Category ORDER BY `Id`;";
                sqlStore = "SELECT Id, Telephone, Street, Cap, Nation, Manager_Id FROM myshopmf.Store ORDER BY `Id`;";
                sqlManager = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable` FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'M') ORDER BY `Id`;";


                executeSQLjcb(sqlItem, sqlUser, sqlWholesaler, sqlCategory, sqlStore, sqlManager);
                break;

            case "Id decrescente":
                sqlItem = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` ORDER BY `Id` DESC;";
                sqlUser = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable`  FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'U') ORDER BY `Id` DESC;";
                sqlWholesaler = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler ORDER BY `Id` DESC;";
                sqlCategory = "SELECT Id, Name, Category_father_Id FROM myshopmf.Category ORDER BY `Id` DESC;";
                sqlStore = "SELECT Id, Telephone, Street, Cap, Nation, Manager_Id FROM myshopmf.Store ORDER BY `Id` DESC;";
                sqlManager = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable` FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'M') ORDER BY `Id` DESC;";


                executeSQLjcb(sqlItem, sqlUser, sqlWholesaler, sqlCategory, sqlStore, sqlManager);
                break;

            case "Città":
                sqlWholesaler = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler ORDER BY `City`;";

                executeSQLjcb(sqlItem, sqlUser, sqlWholesaler, sqlCategory, sqlStore, sqlManager);
                break;
        
            default:
                // JCOMBOBOX SEARCH BAR LOGIN AND LOGOUT

                /**
                 * per poter iterare delle stringhe presenti del database
                 */
                ArrayList<Category> categories = CategoryDAO.getInstance().findAll();
                ArrayList<Category> categoriesIdMatch;
                ArrayList<Item> items = ItemDAO.getInstance().findAll();
                String sql = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` AS `i` WHERE (`i`.`Category_Id` = ";
                

                //per verificare se il nome del comando è uguale a quello della categoria
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getName().equals(cBoxCmd)) {
                        if (categories.get(i).getId() == categories.get(i).getFatherId()) {
                            categoriesIdMatch = CategoryDAO.getInstance().getAllByFatherId(categories.get(i).getId());
                            
                            //per trovare le super categorie
                            for (int j = 0; j < categoriesIdMatch.size(); j++) {
                                for (int k = 0; k < items.size(); k++) {
                                    if (categoriesIdMatch.get(j).getId() == items.get(k).getCategoryId()) {
                                        sql += "'" + items.get(k).getCategoryId() + "'";

                                        if ((j + 1) < (categoriesIdMatch.size())) {
                                            sql += " OR `i`.`Category_Id` = ";
                                        }

                                        break;
                                    }
                                }
                            }
                        } else {
                            //per trovare le sottocategorie
                            for (int j = 0; j < items.size(); j++) {
                                if (items.get(j).getCategoryId() == categories.get(i).getId()) {
                                    sql += "'" + items.get(j).getCategoryId() + "'";

                                    break;
                                }
                            }

                        }
                    }
                }
                sql += ");";

                executeSQLjcb(sql, sql, sql, sql, sql, sql);
                

                break;
        }
    }

    public void executeSQLjcb(String sqlItem, String sqlUser, String sqlWholesaler, String sqlCategory, String sqlStore, String sqlManager) {
        if (logList.getCurrentUser() != null) {
            if (((logList.getCurrentUser().getRole().equals("U")))) {
                appFrame.setCurrentMainPanel(new NormalCatalog(ncList, this, sqlItem));
                return;
            }
        }
        if (((logList.getCurrentUser() == null) || (UserDAO.getInstance().managerExists(logList.getCurrentUser())) || (UserDAO.getInstance().administratorExists(logList.getCurrentUser()))) && ncList.isInNormalCatalog()) {
            appFrame.setCurrentMainPanel(new NormalCatalog(ncList, this, sqlItem));
            return;
        }
        if (((UserDAO.getInstance().administratorExists(logList.getCurrentUser()))) && adminList.isInManageManagerPanel()) {
            appFrame.setCurrentMainPanel(new ManageManagerPanel(adminList, ncList, this, sqlManager));
            return;
        }
        if (((UserDAO.getInstance().administratorExists(logList.getCurrentUser()))) && adminList.isInStorePanel()) {
            appFrame.setCurrentMainPanel(new StorePanel(adminList, ncList, this, sqlStore));
            return;
        }
        if (((UserDAO.getInstance().administratorExists(logList.getCurrentUser()))) && cList.isInCategoryPanel()) {
            appFrame.setCurrentMainPanel(new CategoryPanel(cList, ncList, this, sqlCategory));
            return;
        }
        if (((UserDAO.getInstance().administratorExists(logList.getCurrentUser()))) && adminList.isInWholesalerPanel()) {
            appFrame.setCurrentMainPanel(new WholesalerPanel(adminList, ncList, this, sqlWholesaler));
            return;
        }
        if (((UserDAO.getInstance().managerExists(logList.getCurrentUser()))) && mList.isInManagerUser()) {
            appFrame.setCurrentMainPanel(new ManageUserPanel(mList, ncList, this, sqlUser));
            return;
        }
        if (UserDAO.getInstance().managerExists(logList.getCurrentUser())) {
            appFrame.setCurrentMainPanel(new ManagerCatalog(this, sqlItem));
            return;
        }
        if (UserDAO.getInstance().administratorExists(logList.getCurrentUser())) {
            appFrame.setCurrentMainPanel(new AdministratorCatalog(adminList, ncList, this, sqlItem));
            return;
        }
    }
}
