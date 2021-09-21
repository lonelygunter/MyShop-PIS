package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Model.Image;
import Model.Item;
import View.Listener.NormalCatalogListener;

public class ItemCatalogPanel extends JPanel {

    private ItemPanel itemPanel;
    
    protected Item item;

    public ItemCatalogPanel(NormalCatalogListener ncList, Item item, Image image){

        this.itemPanel = new ItemPanel(ncList, 3, item, image, false);

        this.item = item;
        ncList.setCurrentItem(item);
        
        
        setLayout(new BorderLayout());


        add(itemPanel, BorderLayout.CENTER);

        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}

