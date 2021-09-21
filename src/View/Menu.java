package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public abstract class Menu {

    JMenuBar menuBar = new JMenuBar();
    List<JMenu> menuList = new ArrayList<>();

    public JMenuBar getMenuBar(){
        return menuBar;
    }

    public List<JMenu> getMenuList(){
        return menuList;
    }
}
