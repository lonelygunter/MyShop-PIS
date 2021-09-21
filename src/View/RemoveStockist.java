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

public class RemoveStockist extends JFrame {
    
    private JPanel singleStockist;
    private JCheckBox checkBox;
    private JLabel stockist;
    

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public RemoveStockist(AdministratorListener adminList) {
        super("Elenco dei fornitori");

        this.singleStockist = new JPanel();
        
        this.stockist = new JLabel("<html>fornitore</html>");
        this.checkBox = new JCheckBox(stockist.getText());

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmDeleteStockistButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        singleStockist.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        singleStockist.add(checkBox);

        buttons.add(confirm);
        buttons.add(cancel);

        add(singleStockist, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
