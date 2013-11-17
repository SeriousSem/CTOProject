package org.omazon.CTO.services;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 17.11.13
 * Time: 18:13
 */
public class AsynchMessReceiver {

    public void getMessages() {
        Connection connection;
        javax.jms.MessageConsumer messageConsumer;
        try {

            InitialContext ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/firstQueueFactory");
            Queue queue = (Queue) ctx.lookup("jms/firstQueue");

            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(queue);
            messageConsumer.setMessageListener(new
                    MessageConsumer());
            connection.start();

            System.out.println("reciever start");
            messageConsumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
