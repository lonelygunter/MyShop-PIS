package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Listener.ListListener;

public class CreateList extends JFrame{

    private JPanel centerPanel;

    private JLabel confirm;
    private JTextField listName;

    private JButton cancel;

    public CreateList(AppFrame appFrame, ListListener lList) {
        super("Crea una lista");

        this.centerPanel = new JPanel();

        this.confirm = new JLabel("Inserisci il nome della lista e premi invio per confermare.");
        this.listName = new JTextField(20);
        

        this.cancel = new JButton("Fatto");
        cancel.setActionCommand("listNameField");
        cancel.addActionListener(lList);

        setLayout(new BorderLayout());
        centerPanel.setLayout(new FlowLayout());

        centerPanel.add(confirm);
        centerPanel.add(listName);

        add(centerPanel, BorderLayout.CENTER);
        add(cancel, BorderLayout.SOUTH);

        setSize(new Dimension(400, 110));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public String getListName() {
        return listName.getText();
    }

    public void clearFields() {
        listName.setText("");
    }
}
