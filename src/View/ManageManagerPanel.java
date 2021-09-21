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

public class ManageManagerPanel extends JPanel{
    
    private JPanel buttonPanel;
    private JButton create;
    private JButton modify;
    private JButton delete;
    private String[] filterList = {"", "Id crescente", "Id decrescente", "Nome"};
    private JComboBox filter;

    private JTable catalog;
    private JScrollPane scrollp;

    public ManageManagerPanel(AdministratorListener adminiList, NormalCatalogListener ncList, JComboBoxListener jcbList, String sql) {
        this.buttonPanel = new JPanel();
        
        this.create = new JButton("Crea");
        create.setActionCommand("createManagerButton");
        create.addActionListener(adminiList);

        this.modify = new JButton("Modifica");
        modify.setActionCommand("modifyManagerButton");
        modify.addActionListener(adminiList);

        this.delete = new JButton("Cancella");
        delete.setActionCommand("deleteManagerButton");
        delete.addActionListener(ncList);

        this.filter = new JComboBox(filterList);
        this.filter.addActionListener(jcbList);

        this.catalog = new JTable(new UsersTable(sql));
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

