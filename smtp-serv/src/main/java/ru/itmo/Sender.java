package ru.itmo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Sender {
    private final String senderEmailID;
    private final String senderPassword;
    private final String emailSMTPserver = "smtp.gmail.com";
    private final String emailServerPort = "465";
    private final Properties props;

    public Sender(String senderEmail, String senderPassword) {
        this.senderPassword = senderPassword;
        this.senderEmailID = senderEmail;
        props = new Properties();
        props.put("mail.smtp.user", this.senderEmailID);
        props.put("mail.smtp.host", this.emailSMTPserver);
        props.put("mail.smtp.port", this.emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", this.emailServerPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

    }

    public void send(String receiverEmailID, String emailSubject, String emailBody) {
        try {
            Authenticator auth = new SMTPAuthenticator(senderEmailID, senderPassword);
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmailID));
            Transport.send(msg);
            System.out.println("Message send Successfully:)");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}