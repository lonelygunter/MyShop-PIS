package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import View.Listener.NormalCatalogListener;

public class CategoryStorePanel extends JPanel{
    
    private JPanel buttonPanel;
    private JButton create;
    private JButton delete;

    private JTable catalog;
    private JScrollPane scrollp;

    public CategoryStorePanel(NormalCatalogListener ncList, String sql) {
        this.buttonPanel = new JPanel();
        
        this.create = new JButton("Aggiungi");
        create.setActionCommand("addCategoryStoreButton");
        create.addActionListener(ncList);

        this.delete = new JButton("Cancella");
        delete.setActionCommand("deleteCategoryStoreButton");
        delete.addActionListener(ncList);

        this.catalog = new JTable(new StoreCatTable());
        catalog.setEnabled(false);

        this.scrollp = new JScrollPane(catalog);


        buttonPanel.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        buttonPanel.add(create);
        buttonPanel.add(delete);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
}

