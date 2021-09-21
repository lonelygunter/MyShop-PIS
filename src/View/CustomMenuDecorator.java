package View;

import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public abstract class CustomMenuDecorator extends Menu {
    
    protected Menu menu;

    @Override
    public abstract JMenuBar getMenuBar();

    @Override
    public abstract List<JMenu> getMenuList();
}
