package org.omazon.CTO.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 17.11.13
 * Time: 18:05
 */
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/firstQueue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")},
        mappedName = "jms/firstQueue")
public class MessageConsumer implements MessageListener {

    public void onMessage(Message message) {

        try {
            message.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextMessage txtMessage = (TextMessage) message;

        try {
            //do something with message
            System.out.println(txtMessage.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
