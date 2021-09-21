package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.AppFrame;

public class UserListener implements ActionListener {

    AppFrame appFrame;

    public UserListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        /**
         * per confermare di aver selezionato un utente
         */
        if ("confirmUserSelectedOrder".equals(cmd)) {
            //TODO confermare di aver selezionato un utente
        }
    }
    
}
