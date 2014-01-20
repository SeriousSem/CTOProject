package org.omazon.CTO.services;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 17.11.13
 * Time: 11:50
 */
public class EmailService {
    static String fromEmail = "middlewareTest@yandex.ru";
    static String fromName = "CTO";

    public void sendMessage(String emailRecepient, String nameOfRecepient, String subject, String text) throws Exception {
        InitialContext ctx = new InitialContext();
        Session session = (Session) ctx.lookup("mail/mailresource");
        Message msg = new MimeMessage(session);
        msg.setSubject(subject);
        msg.setRecipient(Message.RecipientType.TO,
                new InternetAddress(
                        emailRecepient,
                        nameOfRecepient));

        msg.setFrom(new InternetAddress(
                fromEmail,
                fromName));

        msg.setText(text);
        Transport.send(msg);
    }

}