package View;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BuyerMenu extends CustomMenuDecorator {

    public BuyerMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public JMenuBar getMenuBar() {
        JMenu buyerItemsMenuBar = new JMenu("Catalogo");
        buyerItemsMenuBar.setMnemonic(KeyEvent.VK_1);
        menuBar.add(buyerItemsMenuBar);

        JMenu userListsMenuBar = new JMenu("Le mie liste");
        userListsMenuBar.setMnemonic(KeyEvent.VK_2);
        menuBar.add(userListsMenuBar);

        return menuBar;
    }

    @Override
    public List<JMenu> getMenuList() {
        int menusCount = menuBar.getMenuCount();

        for (int i = 0; i < menusCount; i++) {
            menuList.add(menuBar.getMenu(i));
        }
        return menuList;
    }
}
