package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.List;
import View.Listener.ListListener;
import View.Listener.NormalCatalogListener;

public class ListPanel extends JPanel {

    private JLabel listName;

    private JButton deleteList;

    public ListPanel(NormalCatalogListener ncList, ListListener lList, List list) {

        this.listName = new JLabel("<html><u>" + list.getName() + "</u></html>", SwingConstants.CENTER);
        listName.addMouseListener(ncList);

        this.deleteList = new JButton("-");
        deleteList.setActionCommand("deleteListButton");
        deleteList.addActionListener(lList);
        deleteList.setPreferredSize(new Dimension(20, 20));

        lList.setCurrentList(list);

        setLayout(new BorderLayout());

        add(listName, BorderLayout.CENTER);
        add(deleteList, BorderLayout.EAST);
    }
}
