package ru.itmo;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator
{
    private final String senderEmailID;
    private final String senderPassword;

    public SMTPAuthenticator(String senderEmailID, String senderPassword){
        this.senderEmailID = senderEmailID;
        this.senderPassword  = senderPassword;
    }

    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(this.senderEmailID, this.senderPassword);
    }
}