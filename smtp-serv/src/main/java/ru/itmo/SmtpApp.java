package ru.itmo;


public class SmtpApp {
    private static final Sender sender = new Sender("v.lisitsina@gmail.com", "ykabadueqlizbssj");

    public static void main(String[] args){
        sender.send("v.lisitsina@gmail.com", "aa", "aa");
    }
}
