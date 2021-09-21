package View;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import DAO.ImageDAO;
import Model.Item;
import View.Listener.NormalCatalogListener;

public class ItemListPanel extends JPanel {
    
    private ItemCatalogPanel itemPanel;

    public ItemListPanel(NormalCatalogListener ncList, Item item) {
        
        this.itemPanel = new ItemCatalogPanel(ncList, item, ImageDAO.getInstance().findByItemId(item.getId()));
        itemPanel.setPreferredSize(new Dimension(900, 200));


        setLayout(new FlowLayout());


        add(itemPanel);
    }
}
