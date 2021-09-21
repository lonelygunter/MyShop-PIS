package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import DAO.ListDAO;
import Model.Item;
import View.Listener.ListListener;
import View.Listener.NormalCatalogListener;

public class SeeListPanel extends JPanel {

    private JPanel topPanel;

    float totalPricefloat;
    private JLabel totalPrice;
    private JButton deleteItem;

    private JPanel itemPanel;
    private JScrollPane scrollp;

    private ArrayList<ItemListPanel> itemListPanels;
    private ArrayList<Item> allItemsList;

    public SeeListPanel(ListListener lList, NormalCatalogListener ncList) {

        this.topPanel = new JPanel();

        this.totalPricefloat = 0;

        this.itemPanel = new JPanel();
        this.scrollp = new JScrollPane(itemPanel);

        this.itemListPanels = new ArrayList<>();
        this.allItemsList = ListDAO.getInstance().getListItemsById(lList.getCurrentList().getId());

        for (int i = 0; i < allItemsList.size(); i++) {
            itemListPanels.add(new ItemListPanel(ncList, allItemsList.get(i)));
            this.totalPricefloat += allItemsList.get(i).getPrice();
        }

        this.totalPrice = new JLabel("Prezzo totale: " + totalPricefloat + " €", SwingConstants.RIGHT);
        ListDAO.getInstance().updatePrice(lList.getCurrentList().getId(), totalPricefloat);

        this.deleteItem = new JButton("Elimina un articolo");
        deleteItem.setActionCommand("deleteItemListButton");
        deleteItem.addActionListener(ncList);


        topPanel.setLayout(new FlowLayout());
        itemPanel.setLayout(new GridLayout(itemListPanels.size(), 1));
        setLayout(new BorderLayout());


        topPanel.add(deleteItem);
        topPanel.add(totalPrice);

        for (int a = 0; a < itemListPanels.size(); a++) {
            itemPanel.add(itemListPanels.get(a));
        }

        add(topPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
}
