package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Listener.ManagerListener;

public class PaymentData extends JFrame{
    
    private JLabel warning;

    private JPanel dataPanel;
    private JLabel nCardLabel;
    private JTextField nCard;
    private JLabel vDateLabel;
    private JTextField vDate;
    private JLabel cvvLabel;
    private JTextField cvv;

    private JPanel buttonPanel;
    private JButton confirm;
    private JButton cancel;

    public PaymentData(ManagerListener mList) {
        super("Ordine in transizione");
        
        this.warning = new JLabel("<html>Per continuare inserisci i seguenti dati:</html>");

        this.dataPanel = new JPanel();

        this.nCardLabel = new JLabel("Numero carta:");
        this.nCard = new JTextField(10);

        this.vDateLabel = new JLabel("Data di verifica:");
        this.vDate = new JTextField(10);

        this.cvvLabel = new JLabel("CVV:");
        this.cvv = new JTextField(10);

        this.buttonPanel = new JPanel();

        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmOrderDataButton");
        confirm.addActionListener(mList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        setLayout(new BorderLayout());
        dataPanel.setLayout(new GridLayout(3, 2));
        buttonPanel.setLayout(new FlowLayout());

        dataPanel.add(nCardLabel);
        dataPanel.add(nCard);
        dataPanel.add(vDateLabel);
        dataPanel.add(vDate);
        dataPanel.add(cvvLabel);
        dataPanel.add(cvv);
        
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        add(warning, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(new Dimension(400, 200));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
