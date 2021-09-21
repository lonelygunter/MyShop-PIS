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

public class RemoveProducer extends JFrame {
    
    private JPanel singleProducer;
    private JCheckBox checkBox;
    private JLabel producer;
    

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public RemoveProducer(AdministratorListener adminList) {
        super("Elenco dei produttori");

        this.singleProducer = new JPanel();
        
        this.producer = new JLabel("<html>produttore</html>");
        this.checkBox = new JCheckBox(producer.getText());

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmDeleteWholesalerButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        singleProducer.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        singleProducer.add(checkBox);

        buttons.add(confirm);
        buttons.add(cancel);

        add(singleProducer, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
