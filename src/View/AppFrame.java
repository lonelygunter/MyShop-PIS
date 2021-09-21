package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import View.Listener.AdministratorListener;
import View.Listener.CategoryListener;
import View.Listener.JComboBoxListener;
import View.Listener.ListListener;
import View.Listener.LoginListener;
import View.Listener.ManagerListener;
import View.Listener.NormalCatalogListener;
import View.Listener.SearchBarListener;
import View.Listener.TopMenuListener;
import View.Listener.UserListener;

public class AppFrame extends JFrame{

    private AdministratorListener adminList;
    private UserListener uList;
    private SearchBarListener sbList;
    private NormalCatalogListener ncList;
    private ManagerListener mList;
    private CategoryListener cList;

    private LoginListener logList;
    private ListListener lList;
    private TopMenuListener topMenuList;
    private JComboBoxListener jcbList;

    private JPanel currentMainPanel;
    private WelcomePanel welcomePanel;

    private TopMenu topMenu;

    private Header header;
    private WestPanel westPanel;
    private EastPanel eastPanel;
    private Footer footer;


    public AppFrame() {
        super("MyShop");

                this.uList = new UserListener(this);


        this.logList = new LoginListener(this);
        this.lList = new ListListener(this, logList);


                this.ncList = new NormalCatalogListener(this, logList, lList);
                this.mList = new ManagerListener(this, lList, uList);
                this.cList = new CategoryListener(this, ncList);


        this.jcbList = new JComboBoxListener(this, ncList, logList, adminList, mList, cList);

            
                this.adminList = new AdministratorListener(this);
                this.sbList = new SearchBarListener(this, jcbList, ncList, logList, adminList);


        this.topMenuList = new TopMenuListener(this, logList, lList, jcbList, adminList, ncList, sbList, mList, cList);


        this.currentMainPanel = new JPanel();
        this.welcomePanel = new WelcomePanel();

        this.topMenu = new TopMenu(topMenuList);
        setJMenuBar(topMenu);

        this.header = new Header(logList, jcbList, sbList);
        
        this.westPanel = new WestPanel();
        this.eastPanel = new EastPanel();
        this.footer = new Footer();

        
        setLayout(new BorderLayout());

        add(header, BorderLayout.NORTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(footer, BorderLayout.SOUTH);
        setCurrentMainPanel(welcomePanel);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setCurrentMainPanel(JPanel panel) {
        if (currentMainPanel != null) {
            remove(currentMainPanel);
        }

        add(panel, BorderLayout.CENTER);

        currentMainPanel = panel;

        invalidate();
        validate();
        repaint();
    }

    public Header getHeader() {
        return this.header;
    }

    public TopMenu getTopMenu() {
        return this.topMenu;
    }
}
