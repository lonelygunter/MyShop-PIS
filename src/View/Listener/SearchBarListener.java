package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import DAO.UserDAO;
import View.AdministratorCatalog;
import View.AppFrame;
import View.ManagerCatalog;
import View.NormalCatalog;

public class SearchBarListener implements ActionListener {
    
    private AppFrame appFrame;
    private JComboBoxListener jcbList;
    private NormalCatalogListener ncList;
    private LoginListener logList;
    private AdministratorListener adminList;

    public SearchBarListener(AppFrame appFrame, JComboBoxListener jcbList, NormalCatalogListener ncList, LoginListener logList, AdministratorListener adminList) {
        this.appFrame = appFrame;
        this.jcbList = jcbList;
        this.ncList = ncList;
        this.logList = logList;
        this.adminList = adminList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        /**
         * per CERCARE nel database degli articoli specifici
         */
        if ("searchField".equals(cmd)) {
            if ((logList.getCurrentUser() == null) || (logList.getCurrentUser().getRole().equals("U"))) {
                appFrame.setCurrentMainPanel(new NormalCatalog(ncList, jcbList, "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` AS `i` WHERE (`i`.`Name` = '" + ((JTextField) e.getSource()).getText() + "');"));
                return;
            }
            if (((UserDAO.getInstance().managerExists(logList.getCurrentUser())) || (UserDAO.getInstance().administratorExists(logList.getCurrentUser()))) && ncList.isInNormalCatalog()) {
                appFrame.setCurrentMainPanel(new NormalCatalog(ncList, jcbList, "SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` AS `i` WHERE (`i`.`Name` = '" + ((JTextField) e.getSource()).getText() + "');"));
                return;
            }
            if (UserDAO.getInstance().managerExists(logList.getCurrentUser())) {
                appFrame.setCurrentMainPanel(new ManagerCatalog(jcbList,"SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` AS `i` WHERE (`i`.`Name` = '" + ((JTextField) e.getSource()).getText() + "');"));
                return;
            }
            if (UserDAO.getInstance().administratorExists(logList.getCurrentUser())) {
                appFrame.setCurrentMainPanel(new AdministratorCatalog(adminList, ncList, jcbList,"SELECT Id, Name, Description, Price, Type, Wholesaler_Id, Category_Id FROM `myshopmf`.`Item` AS `i` WHERE (`i`.`Name` = '" + ((JTextField) e.getSource()).getText() + "');"));
                return;
            }
        }
    }
}
