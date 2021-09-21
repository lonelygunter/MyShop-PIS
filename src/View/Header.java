package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Business.SessionManager;
import DAO.CategoryDAO;
import Model.User;
import View.Listener.JComboBoxListener;
import View.Listener.LoginListener;
import View.Listener.SearchBarListener;

public class Header extends JPanel{

    private JPanel loggedOut;
    private JPanel loggedIn;

    private ImageIcon myShopLogo;
    private JLabel scaledlogo;

    private ImageIcon blindPanel;
    private JLabel scaledBlindPanel;

    private String[] categories;
    
    private JComboBox categoryBoxLogin;
    private SearchBarPanel searchBarPanelLogin;

    private JComboBox categoryBoxLogout;
    private SearchBarPanel searchBarPanelLogout;

    private JButton login;
    private JButton logout;

    private JLabel welcome;

    public Header(LoginListener logList, JComboBoxListener jcbList, SearchBarListener sbList) {
        this.loggedIn = new JPanel();
        this.loggedOut = new JPanel();

        this.myShopLogo = new ImageIcon("/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/myshoplogo.png");
        this.scaledlogo = new JLabel(new ImageIcon(myShopLogo.getImage().getScaledInstance(myShopLogo.getIconWidth(), myShopLogo.getIconHeight(), Image.SCALE_DEFAULT)));
        
        this.blindPanel = new ImageIcon("/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/blindPanel.png");
        this.scaledBlindPanel = new JLabel(new ImageIcon(blindPanel.getImage().getScaledInstance(blindPanel.getIconWidth()/9, blindPanel.getIconHeight()/9, Image.SCALE_DEFAULT)));

        categories = CategoryDAO.getInstance().getAllNames();

        this.categoryBoxLogin = new JComboBox(categories);
        categoryBoxLogin.addActionListener(jcbList);
        this.searchBarPanelLogin = new SearchBarPanel(sbList);

        this.categoryBoxLogout = new JComboBox(categories);   
        categoryBoxLogout.addActionListener(jcbList);
        this.searchBarPanelLogout = new SearchBarPanel(sbList); 

        this.login = new JButton("Login");
        login.setActionCommand("loginButton");
        login.addActionListener(logList);

        this.logout = new JButton("Logout");
        logout.setActionCommand("logoutHeaderButton");
        logout.addActionListener(logList);

        this.welcome = new JLabel();


        setLayout(new BorderLayout());
        loggedIn.setLayout(new FlowLayout());
        loggedOut.setLayout(new FlowLayout());

                
        loggedIn.add(categoryBoxLogout);
        loggedIn.add(searchBarPanelLogout);
        loggedIn.add(logout);

        loggedOut.add(categoryBoxLogin);
        loggedOut.add(searchBarPanelLogin);
        loggedOut.add(login);

        add(loggedIn, BorderLayout.NORTH);
        add(loggedOut, BorderLayout.CENTER);
        add(scaledlogo, BorderLayout.WEST);
        add(scaledBlindPanel, BorderLayout.EAST);

        setLoggedOutStatus();
    }

    public void setLoggedInStatus() {
        loggedIn.setVisible(true);
        loggedOut.setVisible(false);
    }

    public void setLoggedOutStatus() {
        loggedIn.setVisible(false);
        loggedOut.setVisible(true);
    }

    public void refresh() {
        // 1. prendere l'utente loggato u
        User u = (User) SessionManager.getInstance().getSession().get("loggedUser");

        // 2. se u e' null -> chiamare setLoggedOutStatus();
        if (u == null) {
            this.add(new JLabel(), BorderLayout.SOUTH);
            setLoggedOutStatus();
        }

        // 3. altrimenti usare setLoggedInStatus();
        else {
            this.welcome.setText("Benvenuto/a " + u.getName() + " in MyshopLecce");
            this.add(welcome, BorderLayout.SOUTH);
            setLoggedInStatus();
        }

    }
}
