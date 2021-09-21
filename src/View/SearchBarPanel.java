package View;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Listener.SearchBarListener;

public class SearchBarPanel extends JPanel {

    private JTextField search;


    public SearchBarPanel(SearchBarListener sbList) {

        this.search = new JTextField(20);
        search.setActionCommand("searchField");
        search.addActionListener(sbList);

        setLayout(new FlowLayout());

        add(search);
    }
}
