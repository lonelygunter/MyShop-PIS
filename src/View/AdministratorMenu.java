package View;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class AdministratorMenu extends CustomMenuDecorator {

    public AdministratorMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
        public JMenuBar getMenuBar() {
        JMenu administratorItemsMenuBar = new JMenu("Catalogo");
        administratorItemsMenuBar.setMnemonic(KeyEvent.VK_6);
        menuBar.add(administratorItemsMenuBar);

        JMenu producerMenuBar = new JMenu("Grossisti");
        producerMenuBar.setMnemonic(KeyEvent.VK_7);
        menuBar.add(producerMenuBar);

        JMenu categoriesMenuBar = new JMenu("Categorie");
        categoriesMenuBar.setMnemonic(KeyEvent.VK_9);
        menuBar.add(categoriesMenuBar);

        JMenu storeMenuBar = new JMenu("Punti vendita");
        storeMenuBar.setMnemonic(KeyEvent.VK_A);
        menuBar.add(storeMenuBar);

        JMenu manageManagerMenuBar = new JMenu("Manager");
        manageManagerMenuBar.setMnemonic(KeyEvent.VK_B);
        menuBar.add(manageManagerMenuBar);

        JMenu categoryStoreMenuBar = new JMenu("Categorie del PV");
        categoryStoreMenuBar.setMnemonic(KeyEvent.VK_D);
        menuBar.add(categoryStoreMenuBar);

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
