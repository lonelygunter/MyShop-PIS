package View;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ManagerMenu extends CustomMenuDecorator {

    public ManagerMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public JMenuBar getMenuBar() {
        JMenu managerItemsMenuBar = new JMenu("Catalogo");
        managerItemsMenuBar.setMnemonic(KeyEvent.VK_3);
        menuBar.add(managerItemsMenuBar);

        JMenu normalItemsMenuBar = new JMenu("Feedback");
        normalItemsMenuBar.setMnemonic(KeyEvent.VK_C);
        menuBar.add(normalItemsMenuBar);

        JMenu manageUserMenuBar = new JMenu("Utenza");
        manageUserMenuBar.setMnemonic(KeyEvent.VK_4);
        menuBar.add(manageUserMenuBar);

        JMenu orderMenuBar = new JMenu("Ordini");
        orderMenuBar.setMnemonic(KeyEvent.VK_5);
        menuBar.add(orderMenuBar);

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
