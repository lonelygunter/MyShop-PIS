package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Business.SessionManager;
import Business.UserBusiness;
import Model.User;
import View.Listener.TopMenuListener;

public class TopMenu extends JMenuBar {

    private TopMenuListener topMenuList;
    private JMenuBar menuBar;
    private List<JMenu> menuList;

    public TopMenu(TopMenuListener topMenuList) {
        this.topMenuList = topMenuList;
        this.menuBar = new JMenuBar();
        this.menuList = new ArrayList<>();

        refresh();
    }

    public void refresh() {
        removeAll(); // per rimuovere tutti i pulsanti gia' presenti

        //Menu menu = new AdministratorMenu(new GuestMenu());
        //menuBar = menu.getMenuBar();

        // TODO da trasformare in testo normale e non commento
        Menu menu = new GuestMenu();
        menuBar = menu.getMenuBar();

        User user = (User) SessionManager.getInstance().getSession().get("loggedUser");

        if (user != null) {
            menu = new BuyerMenu(menu);
            menuBar = menu.getMenuBar();

            if (UserBusiness.getInstance().userCan(user, UserBusiness.Privilege.MANAGE_SHOP)) {
                menu = new ManagerMenu(menu);
                menuBar = menu.getMenuBar();
            }
            if (UserBusiness.getInstance().userCan(user, UserBusiness.Privilege.ADMIN_SYSTEM)) {
                menu = new AdministratorMenu(menu);
                menuBar = menu.getMenuBar();
            }
        }

        /**
         * per prendere il numero di menu disponibili per l'utente
         */
        int menusCount = menuBar.getMenuCount();

        /**
         * per aggiungere tutti i menu alla menuList
         */
        menuList = menu.getMenuList();

        for (int i = 0; i < menusCount; i++) {
            JMenu singleMenu = menuList.get(i);
            singleMenu.addMenuListener(topMenuList);
            add(singleMenu);
        }

        invalidate();
        validate();
        repaint();
    }

}
