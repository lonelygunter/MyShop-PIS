package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DAO.ImageDAO;
import DAO.ItemDAO;
import Model.Image;
import Model.Item;
import View.Listener.JComboBoxListener;
import View.Listener.NormalCatalogListener;

public class NormalCatalog extends JPanel {

    private JPanel buttonPanel;
    private String[] filterList = {"", "Nome", "Prezzo crescente", "Prezzo decrescente"};
    private JComboBox filter;
    

    private JPanel itemPanel;
    private ArrayList<Image> images;
    private ArrayList<Item> items;
    private ArrayList<ItemCatalogPanel> catalog;
    
    private JScrollPane scrollp;

    public NormalCatalog(NormalCatalogListener ncList, JComboBoxListener jcbList, String sql) {

        this.buttonPanel = new JPanel();
        this.filter = new JComboBox(filterList);
        filter.addActionListener(jcbList);

        this.itemPanel = new JPanel();
        
        this.images = ImageDAO.getInstance().findAll();
        this.items = ItemDAO.getInstance().findAllOrderBy(sql);
        
        this.catalog = new ArrayList<>();

        this.scrollp = new JScrollPane(itemPanel);

        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < images.size(); j++) {
                if (images.get(j).getItemId() == items.get(i).getId()) {
                    catalog.add(new ItemCatalogPanel(ncList, items.get(i), images.get(j)));
                    break;
                }
            }
        }


        buttonPanel.setLayout(new FlowLayout());
        itemPanel.setLayout(new GridLayout(catalog.size()/2, 2));
        setLayout(new BorderLayout());


        buttonPanel.add(filter);

        for (int a = 0; a < catalog.size(); a++) {
            itemPanel.add(catalog.get(a));
        }

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
    
}
