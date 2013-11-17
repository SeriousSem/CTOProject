package org.omazon.CTO.services;

import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.InitialContext;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 17.11.13
 * Time: 17:11
 */
@Stateless
public class MessageProducer {

    public void produceMessages() {
        javax.jms.MessageProducer messageProducer;
        TextMessage textMessage;
        try {

            InitialContext ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/firstQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/firstQueue");

            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            textMessage = session.createTextMessage();

            textMessage.setText("Test message");
            System.out.println("Send: " + textMessage.getText());

            messageProducer.send(textMessage);

            messageProducer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
