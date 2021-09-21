/*package Test;

import Model.EmailNotification;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class EmailNotificationTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        public static void sendMail(String recepient) throws Exception {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();

            properties.put("mail.smtp.auth", true);
            properties.put(" mail.smtp.starttls.enable", true);
            properties.put("mail.smtp.host", "stmp.gmail.com");
            properties.put(" mail.smtp.port ", "587");

            String myAccountEmail = "myshopmatteofilippo@gmail.com";
            String password = "matteofilippo";

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });

            Message message = prepareMassage(session, myAccountEmail, recepient);

            Transport.send(message);
            System.out.println("Message sent successfully");
        }

        private static Message prepareMassage(Session session, String myAccountEmail, String recepient) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
                message.setSubject("Scusa Bro :<");
                message.setText("Non e' colpa tua sono stupido <3 ");
            } catch (Exception e) {
                Logger.getLogger(EmailNotification.class.getName()).log(Level.SEVERE, null,
        }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void notifyUser() {
    }

    @org.junit.jupiter.api.Test
    void send(){


    }
}
*/
