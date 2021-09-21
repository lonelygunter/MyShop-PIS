package View;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JMenu;

public class GuestMenu extends Menu {

    public GuestMenu() {
        JMenu guestItemsMenuBar = new JMenu("Catalogo");
        guestItemsMenuBar.setMnemonic(KeyEvent.VK_0);
        menuBar.add(guestItemsMenuBar);
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
