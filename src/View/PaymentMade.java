package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PaymentMade extends JFrame{
    
    private JLabel warning;
    private JButton ok;

    public PaymentMade() {
        super("Ordine effettuato");

        this.warning = new JLabel("Hai effettuato l'ordine con successo.");

        this.ok = new JButton("Ok");
        ok.addActionListener(e -> {dispose();});


        setLayout(new BorderLayout());


        add(warning, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);

        setSize(new Dimension(400, 100));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
