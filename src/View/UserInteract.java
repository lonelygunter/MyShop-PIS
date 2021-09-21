package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Listener.NormalCatalogListener;

public class UserInteract extends JFrame {
    
    private JPanel centerPanel;

    private JLabel message;
    private JTextField field;

    private JButton done;

    public UserInteract(NormalCatalogListener ncList, String actionCommand, String object) {

        this.centerPanel = new JPanel();

        this.message = new JLabel("Inserire " + object + " e cliccare su \"Fatto\".");
        this.field = new JTextField(10);


        this.done = new JButton("Fatto");
        done.setActionCommand(actionCommand);
        done.addActionListener(ncList);
        

        centerPanel.setLayout(new GridLayout(2, 1));
        setLayout(new BorderLayout());


        centerPanel.add(message);
        centerPanel.add(field);

        add(centerPanel, BorderLayout.CENTER);
        add(done, BorderLayout.SOUTH);

        setSize(new Dimension(400, 100));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public String getField() {
        return field.getText();
    }

    public void clearFields() {
        field.setText("");
    }
}
