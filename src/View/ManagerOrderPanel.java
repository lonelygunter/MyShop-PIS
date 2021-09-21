package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import View.Listener.JComboBoxListener;
import View.Listener.ManagerListener;
import View.Listener.NormalCatalogListener;
import View.Listener.SearchBarListener;

public class ManagerOrderPanel extends JPanel {
    private JPanel listButtonPanel;

    private JButton add;
    private JButton remove;
    private JButton order;
    private String[] filterList = {"", "Id crescente", "Id decrescente", "Prezzo crescente", "Prezzo decrescente"};
    private JComboBox filter;

    private JTable orderTable;
    private JScrollPane scrollp;

    public ManagerOrderPanel(SearchBarListener sbList, NormalCatalogListener ncList, ManagerListener mList, JComboBoxListener jcbList, String sql) {

        this.listButtonPanel = new JPanel();

        this.add = new JButton("Aggiungi");
        add.setActionCommand("addToOrderButton");
        add.addActionListener(ncList);

        this.remove = new JButton("Rimuovi");
        remove.setActionCommand("removeFromOrderButton");
        remove.addActionListener(ncList);
        
        this.order = new JButton("Ordina");
        order.setActionCommand("managerOrderButton");
        order.addActionListener(mList);

        this.filter = new JComboBox(filterList);
        filter.addActionListener(jcbList);

        this.orderTable= new JTable(new OrderTable(sql));
        orderTable.setEnabled(false);

        this.scrollp = new JScrollPane(orderTable);


        listButtonPanel.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        listButtonPanel.add(add);
        listButtonPanel.add(remove);
        listButtonPanel.add(order);
        listButtonPanel.add(filter);

        add(listButtonPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
}
