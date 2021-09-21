package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import View.Listener.NormalCatalogListener;

public class UreSureOrderPanel extends JFrame{
    
    private JLabel warning;

    private JPanel buttonPanel;
    private JButton confirm;
    private JButton cancel;

    public UreSureOrderPanel(NormalCatalogListener ncList) {
        super("Ordine di un articolo");

        this.warning = new JLabel("Stai per ordinare quest'articolo. Sei sicuro?", SwingConstants.CENTER);

        this.buttonPanel = new JPanel();

        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmOrderButton");
        confirm.addActionListener(ncList);

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
