package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Business.SessionManager;
import Business.UserBusiness;
import DAO.UserDAO;
import Model.LoginResponse;
import Model.SigninResponce;
import Model.User;
import View.AppFrame;
import View.LoginPanel;
import View.SignInPanel;
import View.WelcomePanel;

public class LoginListener implements MouseListener, ActionListener {

    private AppFrame appFrame;
    private LoginPanel loginPanel;
    private SignInPanel signinPanel;
    private JPanel panel;

    private String currentUserLogged;

    public LoginListener(AppFrame appFrame) {
        this.appFrame = appFrame;
        this.loginPanel = new LoginPanel(this);
        this.signinPanel = new SignInPanel(this);
        this.panel = new JPanel();
        this.currentUserLogged = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        /**
         * per EFFETTUARE il login
         */
        if ("loginButton".equals(cmd)) {
            JPanel panel = new JPanel();
            panel.add(loginPanel);
            appFrame.setCurrentMainPanel(panel);

        }

        /**
         * per APRIRE la schermata di login
         */
        if ("loginHeaderButton".equals(cmd)) {
            // impostare l'utente appena loggato
            currentUserLogged = loginPanel.getUsername();
            
            //chiamare la classe di business per login
            LoginResponse res = UserBusiness.getInstance().login(loginPanel.getUsername(), loginPanel.getPassword());
            loginPanel.clearFields();
            appFrame.setCurrentMainPanel(new WelcomePanel());
            User u = res.getUser(); // potrebbe essere null in caso di login fallito
            //System.out.println(res.getMessage());

            if (u == null) {
                // il login è fallito -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                // dalle slide su java swing, prendere l'istruzione JDialog
                JOptionPane.showMessageDialog(appFrame, reason, "Login error", JOptionPane.ERROR_MESSAGE);
            } else {
                // il login è andato a buon fine
                SessionManager.getInstance().getSession().put("loggedUser", u);
                appFrame.getHeader().refresh();
                appFrame.getTopMenu().refresh();
                
            }
        }
        
        /**
         * per EFFETTUARE il logout
         */
        if ("logoutHeaderButton".equals(cmd)) {
            // reset della view mostrando interfaccia guest
            SessionManager.getInstance().getSession().remove("loggedUser");
            appFrame.getHeader().refresh();
            appFrame.getTopMenu().refresh();
            appFrame.setCurrentMainPanel(new WelcomePanel());
            this.currentUserLogged = null;
        }

        /**
         * per EFFETTUARE la registrazione
         */
        if ("signinButton".equals(cmd)) {
            //1. prendere i datei dai text filed
            SigninResponce res = UserBusiness.getInstance().signin(signinPanel.getEmail(), signinPanel.getPassword(), signinPanel.getUsername(), signinPanel.getName(), signinPanel.getSurname(), signinPanel.getDateAge(), signinPanel.getTelephone(), signinPanel.getStreet(), signinPanel.getCap());
            appFrame.setCurrentMainPanel(new WelcomePanel());
            User u = res.getUser();

            //2. verificare che l'utente non sia già registrato
            if (u == null) {
                // il signin è fallito -> avvisare l'utente tramite un messaggio
                String reason = res.getMessage(); 
                System.out.println(reason);
                // dalle slide su java swing, prendere l'istruzione JDialog
                JOptionPane.showMessageDialog(appFrame, reason, "Signin error", JOptionPane.ERROR_MESSAGE);
            } else {
                // il signin è andato a buon fine
                SessionManager.getInstance().getSession().put("loggedUser", u);
                appFrame.getHeader().refresh();
                appFrame.getTopMenu().refresh();
                

                // aggiungere l'utente nel db
                UserDAO uDaoToAdd = UserDAO.getInstance();
                User userToAdd = new User(signinPanel.getUsername(), signinPanel.getPassword(), signinPanel.getName(), signinPanel.getSurname(), signinPanel.getDateAge(), signinPanel.getEmail(), signinPanel.getTelephone(), signinPanel.getStreet(), signinPanel.getCap(), "U", 0);
                uDaoToAdd.add(userToAdd);
                signinPanel.clearFields();
                this.currentUserLogged = u.getUsername();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();

        /**
         * per EFFETTUARE la registrazione
         */
        if (clickedLabel.getText().contains("<html>Non hai ancora un account? Clicca <font color=\"blue\"><u>qui</u></font>.</html>")) {
            panel.removeAll();
            panel.add(signinPanel);
            appFrame.setCurrentMainPanel(panel);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public User getCurrentUser() {
        return UserDAO.getInstance().getByUsername(currentUserLogged);
    }
}
