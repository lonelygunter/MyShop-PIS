package View;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WelcomePanel extends JPanel {

    private JLabel welcome;

    private ImageIcon blindPanel;
    private JLabel scaledImage;

    public WelcomePanel() {
        this.welcome = new JLabel("<html>Benvenuto nel nostro punto vendita!<br>Effettua il login o continua come ospite sfogliando il catalogo in altro a sinistra.</html>");

        this.blindPanel = new ImageIcon("/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/blindPanel.png");
        this.scaledImage = new JLabel(new ImageIcon(blindPanel.getImage().getScaledInstance(blindPanel.getIconWidth()/7, blindPanel.getIconHeight()/7, Image.SCALE_DEFAULT)));

        setLayout(new FlowLayout());
        
        
        add(welcome);
        add(scaledImage);
    }
}
