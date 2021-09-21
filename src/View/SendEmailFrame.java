package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import View.Listener.ManagerListener;

public class SendEmailFrame extends JFrame {

    private JPanel emailPanel;

    private JLabel recipientLabel;
    private JTextField recipient;

    private JLabel objectLabel;
    private JTextField object;

    private JLabel messageLabel;
    private JTextField message;

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    public SendEmailFrame(ManagerListener mList) {
        super("Invia un e-mail");

        this.emailPanel = new JPanel();

        this.recipientLabel = new JLabel("<html>Destinatario:</html>", SwingConstants.CENTER);
        this.recipient = new JTextField(20);

        this.objectLabel = new JLabel("<html>Oggetto:</html>", SwingConstants.CENTER);
        this.object = new JTextField(20);

        this.messageLabel = new JLabel("<html>Messaggio:</html>", SwingConstants.CENTER);
        this.message = new JTextField(20);

        this.buttons = new JPanel();
        this.confirm = new JButton("Conferma");
        confirm.setActionCommand("confirmSendEmailButton");
        confirm.addActionListener(mList);

        this.cancel = new JButton("Annulla");
        cancel.addActionListener(e -> {dispose();});


        emailPanel.setLayout(new GridLayout(4, 2));
        buttons.setLayout(new FlowLayout());
        setLayout(new BorderLayout());


        emailPanel.add(recipientLabel);
        emailPanel.add(recipient);
        emailPanel.add(objectLabel);
        emailPanel.add(object);
        emailPanel.add(messageLabel);
        emailPanel.add(message);

        buttons.add(confirm);
        buttons.add(cancel);

        add(emailPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setSize(new Dimension(350, 200));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public String getRecipient() {
        System.out.println(recipient.getText());
        return recipient.getText();
    }

    public String getObject() {
        return object.getText();
    }

    public String getMessage() {
        return message.getText();
    }

    public void clearFields() {
        recipient.setText("");
        object.setText("");
        message.setText("");
    }
}
