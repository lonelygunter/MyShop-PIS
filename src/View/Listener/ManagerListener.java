package View.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.MessagingException;

import DAO.UserDAO;
import View.AllUsers;
import View.AppFrame;
import View.ConfirmToDoOperation;
import View.PaymentMade;
import View.SendEmailFrame;

public class ManagerListener implements ActionListener {

    private AppFrame appFrame;
    private ListListener lList;
    private UserListener uList;
    
    private boolean isInManagerUser;
    private SendEmailFrame sendEmailFrame;

    public ManagerListener(AppFrame appFrame, ListListener lList, UserListener uList) {
        this.appFrame = appFrame;
        this.lList = lList;
        this.uList = uList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        

        // ORDER BUTTON

        /**
         * per INSERIRE i dati del pagamento
         */
        if ("managerOrderButton".equals(cmd)) {
            new ConfirmToDoOperation(lList, "effettua il pagamento"); 
        }

        /**
         * per EFFETTUARE l'ordine
         */
        if ("confirmOrderDataButton".equals(cmd)) {
            //TODO effettuare l'ordine dal manager
            new PaymentMade(); 
        }

        // USERS BUTTON

        /**
         * per INVIARE email agli utenti
         */
        if ("confirmSendEmailButton".equals(cmd)) {
            try {
                UserDAO.getInstance().sendEmail(sendEmailFrame.getRecipient(), sendEmailFrame.getObject(), sendEmailFrame.getMessage(), "");
            } catch (MessagingException | IOException e1) {
                e1.printStackTrace();
            }
            sendEmailFrame.dispose();
        }

        /**
         * per INVIARE i dati dell'email
         */
        if ("sendEmailUserButton".equals(cmd)) {
            sendEmailFrame = new SendEmailFrame(this);
        }

        /**
         * per ELIMINARE un utente
         */
        if ("deleteUserButton".equals(cmd)) {
            new AllUsers(uList); 
        }
    }

    public boolean isInManagerUser() {
        return isInManagerUser;
    }

    public void setIsInManagerUser(boolean isInManagerUser) {
        this.isInManagerUser = isInManagerUser;
    }
}
