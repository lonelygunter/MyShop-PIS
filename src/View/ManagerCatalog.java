package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import View.Listener.JComboBoxListener;


public class ManagerCatalog extends JPanel{
    
    private JPanel buttonPanel;
    private String[] filterList = {"", "Id crescente", "Id decrescente", "Nome", "Prezzo crescente", "Prezzo decrescente"};
    private JComboBox filter;

    private JTable catalog;
    private JScrollPane scrollp;

    public ManagerCatalog(JComboBoxListener jcbList, String sql) {

        this.buttonPanel = new JPanel();
        this.filter = new JComboBox(filterList);
        filter.addActionListener(jcbList);

        this.catalog = new JTable(new ItemsTable(sql));
        catalog.setEnabled(false);

        this.scrollp = new JScrollPane(catalog);


        buttonPanel.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        buttonPanel.add(filter);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
}
