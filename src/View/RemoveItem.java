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

public class RemoveItem extends JFrame {
    
    private JPanel singleItem;
    private JCheckBox checkBox;
    private JLabel item;
    

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public RemoveItem(AdministratorListener adminList) {
        super("Elenco degli articoli");

        this.singleItem = new JPanel();
        
        this.item = new JLabel("<html>articolo</html>");
        this.checkBox = new JCheckBox(item.getText());

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmDeleteItemButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        singleItem.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        singleItem.add(checkBox);

        buttons.add(confirm);
        buttons.add(cancel);

        add(singleItem, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
