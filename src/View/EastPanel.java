package View;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EastPanel extends JPanel{

    private ImageIcon panelGap;
    private JLabel scaledImage;

    public EastPanel(){
        this.panelGap = new ImageIcon("/Users/matt/Documents/GitHub/MyShop-PIS/AllFiles/blindPanel.png");
        this.scaledImage = new JLabel(new ImageIcon(panelGap.getImage().getScaledInstance(panelGap.getIconWidth()/7, panelGap.getIconHeight()/7, Image.SCALE_DEFAULT)));
        
        add(scaledImage);
    }
}
