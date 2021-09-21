package View.Listener;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import View.AdministratorCatalog;
import View.AppFrame;
import View.BuyerListPanel;
import View.CategoryPanel;
import View.CategoryStorePanel;
import View.ManageManagerPanel;
import View.ManageUserPanel;
import View.ManagerCatalog;
import View.ManagerOrderPanel;
import View.NormalCatalog;
import View.StorePanel;
import View.WholesalerPanel;

public class TopMenuListener implements MenuListener {

    private AppFrame appFrame;
    private LoginListener logList;
    private ListListener lList;
    private JComboBoxListener jcbList;
    private AdministratorListener adminList;
    private NormalCatalogListener ncList;
    private SearchBarListener sbList;
    private ManagerListener mList;
    private CategoryListener cList;

    public TopMenuListener(AppFrame appFrame, LoginListener logList, ListListener lList, JComboBoxListener jcbList, AdministratorListener adminList, NormalCatalogListener ncList, SearchBarListener sbList, ManagerListener mList, CategoryListener cList) {
        this.appFrame = appFrame;
        this.logList = logList;
        this.lList = lList;
        this.jcbList = jcbList;
        this.adminList = adminList;
        this.ncList = ncList;
        this.sbList = sbList;
        this.mList = mList;
        this.cList = cList;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu menu = (JMenu) e.getSource();
        String sqlItem = "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` ORDER BY `Name`;";
        String sqlUser = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable` FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'U') ORDER BY `Name`;";
        String sqlOrder = "";
        try {
            sqlOrder = "SELECT `Id`, `Date`, `Price`, `User_Id`, `Item_Id` FROM `myshopmf`.`Order` AS `o` WHERE (`o`.`User_Id` = '" + logList.getCurrentUser().getId() + "') ORDER BY `o`.`Id`;";
        } catch (Exception exeption) {
            System.out.println("User: Guest");
        }
        String sqlWholesaler = "SELECT Id, Name, Email, Telephone, Website, City, Nation FROM myshopmf.Wholesaler ORDER BY `Name`;";
        String sqlCategory = "SELECT Id, Name, Category_father_Id FROM myshopmf.Category ORDER BY `Name`;";
        String sqlStore = "SELECT Id, Telephone, Street, Cap, Nation, Manager_Id FROM myshopmf.Store ORDER BY `Id`;";
        String sqlManager = "SELECT `Id`, `Username`, `Name`, `Surname`, `Age`, `Email`, `Telephone`, `Street`, `Cap`, `Role`, `Disable` FROM `myshopmf`.`User` AS `u` WHERE (`u`.`Role` = 'M') ORDER BY `Name`;";


        /**
         * GUEST action listener
         */
        if (menu.getMnemonic() == KeyEvent.VK_0) {
            ncList.setIsInNormalCatalog(true);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new NormalCatalog(ncList, jcbList, sqlItem));
        }
        
        /**
         * BUYER action listener
         */
        if (menu.getMnemonic() == KeyEvent.VK_1) {
            ncList.setIsInNormalCatalog(true);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new NormalCatalog(ncList, jcbList, sqlItem));
        }
        if (menu.getMnemonic() == KeyEvent.VK_2) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new BuyerListPanel(logList, lList, ncList));
        }

        /**
         * MANAGER action listener
         */
        if (menu.getMnemonic() == KeyEvent.VK_3) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new ManagerCatalog(jcbList, sqlItem));
        }
        if (menu.getMnemonic() == KeyEvent.VK_4) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(true);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new ManageUserPanel(mList, ncList, jcbList, sqlUser));
        }
        if (menu.getMnemonic() == KeyEvent.VK_5) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new ManagerOrderPanel(sbList, ncList, mList, jcbList, sqlOrder));
        }
        if (menu.getMnemonic() == KeyEvent.VK_C) {
            ncList.setIsInNormalCatalog(true);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new NormalCatalog(ncList, jcbList, sqlItem));
        }

        /**
         * ADMINISTRATOR action listener
         */
        if (menu.getMnemonic() == KeyEvent.VK_6) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new AdministratorCatalog(adminList, ncList, jcbList, sqlItem));
        }
        if (menu.getMnemonic() == KeyEvent.VK_7) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(true);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new WholesalerPanel(adminList, ncList, jcbList, sqlWholesaler));
        }
        if (menu.getMnemonic() == KeyEvent.VK_9) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(true);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new CategoryPanel(cList, ncList, jcbList, sqlCategory));
        }
        if (menu.getMnemonic() == KeyEvent.VK_A) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(true);
            adminList.setIsInManageManagerPanel(false);
            appFrame.setCurrentMainPanel(new StorePanel(adminList, ncList, jcbList, sqlStore));
        }
        if (menu.getMnemonic() == KeyEvent.VK_B) {
            ncList.setIsInNormalCatalog(false);
            mList.setIsInManagerUser(false);
            adminList.setIsInWholesalerPanel(false);
            cList.setIsInCategoryPanel(false);
            adminList.setIsInStorePanel(false);
            adminList.setIsInManageManagerPanel(true);
            appFrame.setCurrentMainPanel(new ManageManagerPanel(adminList, ncList, jcbList, sqlManager));
        }
        if (menu.getMnemonic() == KeyEvent.VK_D) {
            appFrame.setCurrentMainPanel(new CategoryStorePanel(ncList, sqlCategory));
        }
        
        appFrame.invalidate();
        appFrame.validate();
        appFrame.repaint();
    }

    @Override
    public void menuDeselected(MenuEvent e) {}

    @Override
    public void menuCanceled(MenuEvent e) {}
}
