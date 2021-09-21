package Business;

import Model.EmailNotification;

public abstract class SendEmailNotificationClient {

    public SendEmailNotificationClient(String recipient, String object, String body, String path) throws Exception {

        EmailNotification mail = new EmailNotification();
        mail.setupServerProperties();
        mail.sendEmail(recipient, object, body, null);
    }

    abstract protected EmailNotification createNotification(String recipient, String subject, String body, String path);

}