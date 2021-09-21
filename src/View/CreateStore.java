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

public class CreateStore extends JFrame {

    private JPanel dataPanel;

    private JLabel telephoneLabel;
    private JTextField telephone;

    private JLabel streetLabel;
    private JTextField street;

    private JLabel capLabel;
    private JTextField cap;

    private JLabel nationLabel;
    private JTextField nation;

    private JLabel usernameLabel;
    private JTextField username;

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public CreateStore(AdministratorListener adminList) {
        super("Crea un punto vendita");

        this.dataPanel = new JPanel();
        
        this.telephoneLabel = new JLabel("<html>Telefono:</html>");
        this.telephone = new JTextField(10);

        this.streetLabel = new JLabel("<html>Indirizzo:</html>");
        this.street = new JTextField(10);

        this.capLabel = new JLabel("<html>CAP:</html>");
        this.cap = new JTextField(10);

        this.nationLabel = new JLabel("<html>Città:</html>");
        this.nation = new JTextField(10);

        this.usernameLabel= new JLabel("<html>Username Manager in carico:</html>");
        this.username = new JTextField(10);

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmCreateStoreButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        dataPanel.setLayout(new GridLayout(5, 2));
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        dataPanel.add(telephoneLabel);
        dataPanel.add(telephone);
        dataPanel.add(streetLabel);
        dataPanel.add(street);
        dataPanel.add(capLabel);
        dataPanel.add(cap);
        dataPanel.add(nationLabel);
        dataPanel.add(nation);
        dataPanel.add(usernameLabel);
        dataPanel.add(username);

        buttons.add(confirm);
        buttons.add(cancel);

        add(dataPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(400, 200));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public String getTelephone() {
        return telephone.getText();
    }

    public String getStreet() {
        return street.getText();
    }

    public String getCap() {
        return cap.getText();
    }

    public String getNation() {
        return nation.getText();
    }

    public String getManager() {
        return username.getText();
    }

    public void clearFields() {
        telephone.setText("");
        street.setText("");
        cap.setText("");
        nation.setText("");
        username.setText("");
    }
}
