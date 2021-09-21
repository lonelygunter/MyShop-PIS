package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.Listener.AdministratorListener;

public class CreateManager extends JFrame {

    private JPanel dataPanel;

    private JLabel usernameLabel;
    private JTextField username;

    private JLabel passwordLabel;
    private JTextField password;

    private JLabel nameLabel;
    private JTextField name;

    private JLabel surnameLabel;
    private JTextField surname;

    private JLabel ageLabel;
    private JTextField age;

    private JLabel emailLabel;
    private JTextField email;

    private JLabel telephoneLabel;
    private JTextField telephone;

    private JLabel streetLabel;
    private JTextField street;

    private JLabel capLabel;
    private JTextField cap;

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public CreateManager(AdministratorListener adminList) {
        super("Crea un Manager");

        this.dataPanel = new JPanel();
        
        this.usernameLabel = new JLabel("<html>Username:</html>");
        this.username = new JTextField(10);

        this.passwordLabel = new JLabel("<html>Password:</html>");
        this.password = new JTextField(10);

        this.nameLabel = new JLabel("<html>Nome:</html>");
        this.name = new JTextField(10);

        this.surnameLabel = new JLabel("<html>Cognome:</html>");
        this.surname = new JTextField(10);

        this.ageLabel= new JLabel("<html>Anno di nascita:</html>");
        this.age = new JTextField(10);

        this.emailLabel = new JLabel("<html>Email:</html>");
        this.email = new JTextField(10);

        this.telephoneLabel = new JLabel("<html>Telefono:</html>");
        this.telephone = new JTextField(10);

        this.streetLabel = new JLabel("<html>Indirizzo:</html>");
        this.street = new JTextField(10);

        this.capLabel = new JLabel("<html>CAP:</html>");
        this.cap = new JTextField(10);

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmCreateManagerButton");
        confirm.addActionListener(adminList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        dataPanel.setLayout(new GridLayout(10, 2));
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        dataPanel.add(usernameLabel);
        dataPanel.add(username);
        dataPanel.add(passwordLabel);
        dataPanel.add(password);
        dataPanel.add(nameLabel);
        dataPanel.add(name);
        dataPanel.add(surnameLabel);
        dataPanel.add(surname);
        dataPanel.add(ageLabel);
        dataPanel.add(age);
        dataPanel.add(emailLabel);
        dataPanel.add(email);
        dataPanel.add(telephoneLabel);
        dataPanel.add(telephone);
        dataPanel.add(streetLabel);
        dataPanel.add(street);
        dataPanel.add(capLabel);
        dataPanel.add(cap);

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

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public String getName() {
        return name.getText();
    }

    public String getSurname() {
        return surname.getText();
    }

    public Date getAge() {
        return new Date(Integer.parseInt(age.getText()));
    }

    public String getEmail() {
        return email.getText();
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

    public void clearFields() {
        username.setText("");
        password.setText("");
        name.setText("");
        surname.setText("");
        age.setText("");
        email.setText("");
        telephone.setText("");
        street.setText("");
        cap.setText("");
    }
}
