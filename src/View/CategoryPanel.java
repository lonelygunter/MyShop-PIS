package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import View.Listener.CategoryListener;
import View.Listener.JComboBoxListener;
import View.Listener.NormalCatalogListener;

public class CategoryPanel extends JPanel{
    
    private JPanel buttonPanel;
    private JButton create;
    private JButton modify;
    private JButton delete;
    private String[] filterList = {"", "Id crescente", "Id decrescente", "Nome"};
    private JComboBox filter;

    private JTable catalog;
    private JScrollPane scrollp;

    public CategoryPanel(CategoryListener cList, NormalCatalogListener ncList, JComboBoxListener jcbList, String sql) {
        this.buttonPanel = new JPanel();
        
        this.create = new JButton("Crea");
        create.setActionCommand("createCategoryButton");
        create.addActionListener(cList);

        this.modify = new JButton("Modifica");
        modify.setActionCommand("modifyCategoryButton");
        modify.addActionListener(cList);

        this.delete = new JButton("Cancella");
        delete.setActionCommand("deleteCategoryButton");
        delete.addActionListener(ncList);

        this.filter = new JComboBox(filterList);
        filter.addActionListener(jcbList);

        this.catalog = new JTable(new CategoriesTable(sql));
        catalog.setEnabled(false);

        this.scrollp = new JScrollPane(catalog);


        buttonPanel.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        buttonPanel.add(create);
        buttonPanel.add(modify);
        buttonPanel.add(delete);
        buttonPanel.add(filter);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
}

