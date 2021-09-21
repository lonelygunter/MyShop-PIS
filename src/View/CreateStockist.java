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

import View.Listener.AdministratorListener;

public class CreateStockist extends JFrame {

    private JPanel dataPanel;

    private JLabel nameLabel;
    private JTextField name;

    private JLabel emailPanel;
    private JTextField email;

    private JLabel telephoneLabel;
    private JTextField telephone;

    private JLabel websiteLabel;
    private JTextField website;

    private JLabel cityLabel;
    private JTextField city;

    private JLabel nationLabel;
    private JTextField nation;

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public CreateStockist(AdministratorListener adminList) {
        super("Crea un fornitore");

        this.dataPanel = new JPanel();
        
        this.nameLabel = new JLabel("<html>Nome:</html>");
        this.name = new JTextField(10);

        this.emailPanel = new JLabel("<html>Email:</html>");
        this.email = new JTextField(10);

        this.telephoneLabel = new JLabel("<html>Telefono:</html>");
        this.telephone = new JTextField(10);

        this.websiteLabel = new JLabel("<html>Sito Web:</html>");
        this.website = new JTextField(10);

        this.cityLabel = new JLabel("<html>Città:</html>");
        this.city = new JTextField(10);

        this.nationLabel = new JLabel("<html>Nazione:</html>");
        this.nation = new JTextField(10);

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmCreateStockistButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        dataPanel.setLayout(new GridLayout(7, 2));
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        dataPanel.add(nameLabel);
        dataPanel.add(name);
        dataPanel.add(emailPanel);
        dataPanel.add(email);
        dataPanel.add(telephoneLabel);
        dataPanel.add(telephone);
        dataPanel.add(websiteLabel);
        dataPanel.add(website);
        dataPanel.add(cityLabel);
        dataPanel.add(city);
        dataPanel.add(nationLabel);
        dataPanel.add(nation);

        buttons.add(confirm);
        buttons.add(cancel);

        add(dataPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
