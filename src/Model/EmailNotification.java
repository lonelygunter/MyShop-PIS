package Model;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EmailNotification {
    private Properties properties;

    public EmailNotification() {
    }

    public void setupServerProperties() {
        properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");

        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }

    public MimeMessage setMineMessage(String recipent, String subject, String body, Session session) throws AddressException, MessagingException {
        MimeMessage m = new MimeMessage(session);
        m.setFrom(new InternetAddress("myshopmatteofilippo@gmail.com"));
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
        m.setSubject(subject);
        m.setContent(body, "pdf/text");
        return m;
    }

    public void composeEmail(String path, String body, MimeMessage m) throws MessagingException {
        if(path != "") {
            BodyPart messageBp = new MimeBodyPart();
            messageBp.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBp);
            messageBp = new MimeBodyPart();

            DataSource s = new FileDataSource(path);
            messageBp.setDataHandler(new DataHandler(s));
            messageBp.setFileName(path);
            multipart.addBodyPart(messageBp);
            m.setContent(multipart);
        }
    }

    public void sendEmail(String recipent, String subject, String body, String path){
        try{
            Session session = Session.getDefaultInstance(properties, new Authenticator(){ protected PasswordAuthentication getPasswordAuthentication(){ return new PasswordAuthentication("myshopmatteofilippo@gmail.com", "matteofilippo");}});
            
            MimeMessage mimeMessage = setMineMessage(recipent, subject, body, session);

                composeEmail(path, body, mimeMessage);
            
            Transport.send(mimeMessage);
            System.out.println("Email successfully sent!!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Email non inviata", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}