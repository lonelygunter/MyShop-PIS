package View;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Footer extends JPanel{

    private JLabel credits;

    public Footer() {
        this.credits = new JLabel("Software realizzato da Aprile Matteo e Filippo Goffredo, nell'ambito del progetto di Principi di Ingegneria del Software");
        
        add(credits);
    }
}
