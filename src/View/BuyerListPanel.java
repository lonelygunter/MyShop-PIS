package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DAO.ListDAO;
import Model.List;
import View.Listener.ListListener;
import View.Listener.LoginListener;
import View.Listener.NormalCatalogListener;

public class BuyerListPanel extends JPanel {

    private JPanel listButtonPanel;

    private JPanel listPanels;
    private ArrayList<List> lists;
    private ArrayList<ListPanel> singleList;

    private JButton create;
    private JButton sendPdf;

    private JScrollPane scrollp;
    
    public BuyerListPanel(LoginListener logList, ListListener lList, NormalCatalogListener ncList) {
        
        this.listButtonPanel = new JPanel();

        this.listPanels = new JPanel();

        this.lists = ListDAO.getInstance().findAll();
        this.singleList = new ArrayList<>();

        this.create = new JButton("Crea");
        create.setActionCommand("listCreateButton");
        create.addActionListener(lList);

        this.sendPdf = new JButton("Ricevi il PDF");
        sendPdf.setActionCommand("sendPdfButton");
        sendPdf.addActionListener(ncList);

        this.scrollp = new JScrollPane(listPanels);


        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getBuyerId() == logList.getCurrentUser().getId()) {
                singleList.add(new ListPanel(ncList, lList, lists.get(i)));
            }
        }


        listButtonPanel.setLayout(new FlowLayout());
        listPanels.setLayout(new GridLayout(singleList.size(), 1));
        setLayout(new BorderLayout());


        listButtonPanel.add(create);
        listButtonPanel.add(sendPdf);

        for (int i = 0; i < singleList.size(); i++) {
            listPanels.add(singleList.get(i));
        }

        add(listButtonPanel, BorderLayout.NORTH);
        add(scrollp, BorderLayout.CENTER);
    }
}
