package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import View.Listener.AdministratorListener;

public class StockistPanel extends JPanel{
    
    private JPanel buttonPanel;
    private JButton create;
    private JButton modify;
    private JButton delete;
    private String[] filterList = {"", "Id crescente", "Id decrescente", "Nome"};
    private JComboBox filter;

    private JTable catalog;
    private JScrollPane scrollp;

    public StockistPanel(AdministratorListener adminList) {
        this.buttonPanel = new JPanel();
        
        this.create = new JButton("Crea");
        create.setActionCommand("createStockistButton");
        create.addActionListener(adminList);

        this.modify = new JButton("Modifica");
        modify.setActionCommand("modifyStockistButton");
        modify.addActionListener(adminList);

        this.delete = new JButton("Cancella");
        delete.setActionCommand("deleteStockistButton");
        delete.addActionListener(adminList);

        this.filter = new JComboBox(filterList);

        //TODO togliere mock
        String[][] data = {
			{ "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
            { "Kundan Kumar Jha", "4031", "CSE" },
			{ "Anand Jha", "6014", "IT" },
		};

        String[] columnNames = { "Name", "Roll Number", "Department" };

        this.catalog = new JTable(data, columnNames);
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
