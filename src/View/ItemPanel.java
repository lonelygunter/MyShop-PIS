package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import DAO.ProductDAO;
import Model.Image;
import Model.Item;
import Model.Product;
import View.Listener.NormalCatalogListener;

public class ItemPanel extends JPanel {

    private Product product;
    
    private ImageIcon itemImage;
    private JLabel itemScaledImage;

    private JPanel centerPanel;
    private JLabel name;
    private JLabel description;
    private JLabel price;

    private JLabel availability;

    public ItemPanel(NormalCatalogListener ncList, int scaledImage, Item item, Image image, boolean seeingItemPage) {
        
        this.product = ProductDAO.getInstance().findById(item.getId());
        
        this.itemImage = new ImageIcon(image.getJavaAwtImage());
        this.itemScaledImage = new JLabel(new ImageIcon(itemImage.getImage().getScaledInstance(itemImage.getIconWidth()/scaledImage, itemImage.getIconHeight()/scaledImage, java.awt.Image.SCALE_DEFAULT)));

        this.centerPanel = new JPanel();

        this.name = new JLabel("<html><u>" + item.getName() + "</u></html>", SwingConstants.CENTER);
        name.addMouseListener(ncList);

        this.description = new JLabel("<html>" + item.getDescription() + "</htlm>", SwingConstants.CENTER);
        this.price = new JLabel("Prezzo: " + item.getPrice() + " €", SwingConstants.CENTER);

        if (product != null) {
            this.availability = new JLabel("Disponibilità: " + product.getQuantity() + " unità", SwingConstants.CENTER);
        }


        setLayout(new BorderLayout());
        centerPanel.setLayout(new GridLayout(4, 1));


        centerPanel.add(name);
        if (seeingItemPage) {
            centerPanel.add(description);
        }
        centerPanel.add(price);
        if (product != null) {
            centerPanel.add(availability);
        }

        add(centerPanel, BorderLayout.CENTER);
        add(itemScaledImage, BorderLayout.WEST);
    }
}
