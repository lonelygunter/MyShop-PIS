package View;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WestPanel extends JPanel{

    private ImageIcon blindPanel;
    private JLabel scaledImage;

    public WestPanel(){
        this.blindPanel = new ImageIcon("/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/blindPanel.png");
        this.scaledImage = new JLabel(new ImageIcon(blindPanel.getImage().getScaledInstance(blindPanel.getIconWidth()/7, blindPanel.getIconHeight()/7, Image.SCALE_DEFAULT)));
        
        add(scaledImage);
    }
}
