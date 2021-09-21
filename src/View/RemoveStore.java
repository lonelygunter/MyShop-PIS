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

public class RemoveStore extends JFrame {
    
    private JPanel singleStore;
    private JCheckBox checkBox;
    private JLabel store;
    

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public RemoveStore(AdministratorListener adminList) {
        super("Elenco dei punti vendita");

        this.singleStore = new JPanel();
        
        this.store = new JLabel("<html>punto vendita</html>");
        this.checkBox = new JCheckBox(store.getText());

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmDeleteStoreButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        singleStore.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        singleStore.add(checkBox);

        buttons.add(confirm);
        buttons.add(cancel);

        add(singleStore, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
