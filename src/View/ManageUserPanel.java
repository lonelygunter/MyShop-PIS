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

public class ManageUserPanel extends JPanel {
    
    private JPanel buttonPanel;

    private JButton checkList;
    private JButton sendEmail;
    private JButton disable;
    private String[] filterList = {"", "Id crescente", "Id decrescente", "Nome"};
    private JComboBox filter;

    private JTable orderTable;
    private JScrollPane scrollp;

    public ManageUserPanel(ManagerListener mList, NormalCatalogListener ncList, JComboBoxListener jcbList, String sql) {

        this.buttonPanel = new JPanel();

        this.checkList = new JButton("Lista pagata");
        checkList.setActionCommand("checkUserListButton");
        checkList.addActionListener(ncList);

        this.sendEmail = new JButton("Invia un e-mail");
        sendEmail.setActionCommand("sendEmailUserButton");
        sendEmail.addActionListener(mList);

        this.disable = new JButton("Disabilita");
        disable.setActionCommand("disableUserButton");
        disable.addActionListener(ncList);

        this.filter = new JComboBox(filterList);
        filter.addActionListener(jcbList);

        this.orderTable= new JTable(new UsersTable(sql));
        orderTable.setEnabled(false);

        this.scrollp = new JScrollPane(orderTable);


        setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout());


        buttonPanel.add(checkList);
        buttonPanel.add(sendEmail);
        buttonPanel.add(disable);
        buttonPanel.add(filter);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
}
