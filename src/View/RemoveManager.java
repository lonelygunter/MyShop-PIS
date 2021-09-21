package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import View.Listener.AdministratorListener;

public class RemoveManager extends JFrame {
    
    private JPanel singleManager;
    private JCheckBox checkBox;
    private JLabel manager;
    

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public RemoveManager(AdministratorListener adminList) {
        super("Elenco dei Manager");

        this.singleManager = new JPanel();
        
        this.manager = new JLabel("<html>manager</html>");
        this.checkBox = new JCheckBox(manager.getText());

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmDeleteManagerButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        singleManager.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        singleManager.add(checkBox);

        buttons.add(confirm);
        buttons.add(cancel);

        add(singleManager, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
