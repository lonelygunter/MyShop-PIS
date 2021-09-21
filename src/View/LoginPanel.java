package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import View.Listener.LoginListener;

public class LoginPanel extends JPanel {

    private JLabel l;
    private JLabel username;
    private JTextField usernameField;
    private JLabel psw;
    private JPasswordField pswField;
    private JButton login;
    private JLabel signIn;


    public LoginPanel(LoginListener logList) {
        this.l = new JLabel("Effettua il login in MyShop!", SwingConstants.CENTER);
        this.username = new JLabel("Username", SwingConstants.CENTER);
        this.usernameField = new JTextField();
        this.psw = new JLabel("Password", SwingConstants.CENTER);
        this.pswField = new JPasswordField();
        this.login = new JButton("Login");
        login.setActionCommand("loginHeaderButton");
        login.addActionListener(logList);
        
        this.signIn = new JLabel("<html>Non hai ancora un account? Clicca <font color=\"blue\"><u>qui</u></font>.</html>", SwingConstants.CENTER);
        signIn.addMouseListener(logList);
        
        
        setLayout(new GridLayout(8, 1));

        add(l);
        add(username);
        add(usernameField);
        add(psw);
        add(pswField);
        add(login);
        add(signIn);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(pswField.getPassword());
    }

    public void clearFields() {
        usernameField.setText("");
        pswField.setText("");
    }
}
