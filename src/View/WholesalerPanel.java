package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import View.Listener.AdministratorListener;
import View.Listener.JComboBoxListener;
import View.Listener.NormalCatalogListener;

public class WholesalerPanel extends JPanel{
    
    private JPanel buttonPanel;
    private JButton create;
    private JButton modify;
    private JButton delete;
    private String[] filterList = {"", "Id crescente", "Id decrescente", "Nome", "Città"};
    private JComboBox filter;

    private JTable catalog;
    private JScrollPane scrollp;

    public WholesalerPanel(AdministratorListener adminList, NormalCatalogListener ncList, JComboBoxListener jcbList, String sql) {
        this.buttonPanel = new JPanel();
        
        this.create = new JButton("Crea");
        create.setActionCommand("createWholesalerButton");
        create.addActionListener(adminList);

        this.modify = new JButton("Modifica");
        modify.setActionCommand("modifyWholesalerButton");
        modify.addActionListener(adminList);

        this.delete = new JButton("Cancella");
        delete.setActionCommand("deleteWholesalerButton");
        delete.addActionListener(ncList);

        this.filter = new JComboBox(filterList);
        filter.addActionListener(jcbList);

        this.catalog = new JTable(new WholesalersTable(sql));
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
