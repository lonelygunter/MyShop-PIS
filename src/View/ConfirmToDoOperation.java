package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import View.Listener.ListListener;

public class ConfirmToDoOperation extends JFrame{

    private JLabel warning;

    private JPanel buttonPanel;
    private JButton confirm;
    private JButton cancel;

    public ConfirmToDoOperation(ListListener lList, String operation) {

        this.warning = new JLabel("Sei sicuro di voler " + operation + " ?", SwingConstants.CENTER);

        this.buttonPanel = new JPanel();

        this.confirm = new JButton("Conferma");
        confirm.setActionCommand(operation);
        confirm.addActionListener(lList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});

        setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        add(warning, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(new Dimension(400, 100));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
