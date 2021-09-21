package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Business.PswEncryption;
import View.Listener.LoginListener;

public class SignInPanel extends JPanel{

    private JPanel centerPanel;

    private JLabel message;

    private JLabel email;
    private JTextField emailField;

    private JLabel psw;
    private JPasswordField pswField;

    private JLabel username;
    private JTextField usernameField;

    private JLabel name;
    private JTextField nameField;

    private JLabel surname;
    private JTextField surnameField;

    private JLabel dateDay;
    private JTextField dateDayField;
    private JLabel dateMonth;
    private JTextField dateMonthField;
    private JLabel dateYear;
    private JTextField dateYearField;

    private JLabel telephone;
    private JTextField telephoneField;

    private JLabel street;
    private JTextField streetField;

    private JLabel cap;
    private JTextField capField;
    
    private JButton signin;


    public SignInPanel(LoginListener logList) {
        this.centerPanel = new JPanel();

        this.message = new JLabel("Effettua la registrazione in MyShop!", SwingConstants.CENTER);

        this.email = new JLabel("Email", SwingConstants.CENTER);
        this.emailField = new JTextField();

        this.psw = new JLabel("Password", SwingConstants.CENTER);
        this.pswField = new JPasswordField();

        this.username = new JLabel("Username", SwingConstants.CENTER);
        this.usernameField = new JTextField();

        this.name = new JLabel("Nome", SwingConstants.CENTER);
        this.nameField = new JTextField();

        this.surname = new JLabel("Cognome", SwingConstants.CENTER);
        this.surnameField = new JTextField();

        this.dateDay= new JLabel("Giorno di nascita", SwingConstants.CENTER);
        this.dateDayField = new JTextField();

        this.dateMonth= new JLabel("Mese di nascita", SwingConstants.CENTER);
        this.dateMonthField = new JTextField();

        this.dateYear= new JLabel("Anno di nascita", SwingConstants.CENTER);
        this.dateYearField = new JTextField();

        this.telephone = new JLabel("Numero di telefono", SwingConstants.CENTER);
        this.telephoneField = new JTextField();

        this.street = new JLabel("Indirizzo", SwingConstants.CENTER);
        this.streetField = new JTextField();

        this.cap = new JLabel("CAP", SwingConstants.CENTER);
        this.capField = new JTextField();

        this.signin = new JButton("Registrati");
        signin.setActionCommand("signinButton");
        signin.addActionListener(logList);


        setLayout(new BorderLayout());
        centerPanel.setLayout(new GridLayout(12, 2));


        centerPanel.add(email);
        centerPanel.add(emailField);

        centerPanel.add(psw);
        centerPanel.add(pswField);

        centerPanel.add(username);
        centerPanel.add(usernameField);

        centerPanel.add(name);
        centerPanel.add(nameField);

        centerPanel.add(surname);
        centerPanel.add(surnameField);

        centerPanel.add(dateDay);
        centerPanel.add(dateDayField);
        centerPanel.add(dateMonth);
        centerPanel.add(dateMonthField);
        centerPanel.add(dateYear);
        centerPanel.add(dateYearField);

        centerPanel.add(telephone);
        centerPanel.add(telephoneField);

        centerPanel.add(street);
        centerPanel.add(streetField);

        centerPanel.add(cap);
        centerPanel.add(capField);

        add(message, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(signin, BorderLayout.SOUTH);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        PswEncryption pe = new PswEncryption();
        pe.setPassword(new String(pswField.getPassword()));
        return pe.getHashed();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getName() {
        return nameField.getText();
    }

    public String getSurname() {
        return surnameField.getText();
    }

    public int getDateYear() {
        return Integer.parseInt(dateYearField.getText());
    }

    public int getDateMonth() {
        return Integer.parseInt(dateMonthField.getText());
    }

    public int getDateDay() {
        return Integer.parseInt(dateDayField.getText());
    }

    public Date getDateAge() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getDateYear());
        cal.set(Calendar.MONTH, (getDateMonth() - 1));
        cal.set(Calendar.DAY_OF_MONTH, getDateDay());
        Date date = cal.getTime();
        System.out.println(date);
        return date;
    }

    public String getTelephone() {
        return telephoneField.getText();
    }

    public String getStreet() {
        return streetField.getText();
    }

    public String getCap() {
        return capField.getText();
    }

    public void clearFields() {
        emailField.setText("");
        pswField.setText("");
        usernameField.setText("");
        nameField.setText("");
        surnameField.setText("");

        dateDayField.setText("");
        dateMonthField.setText("");
        dateYearField.setText("");

        telephoneField.setText("");
        streetField.setText("");
        capField.setText("");
    }
}
