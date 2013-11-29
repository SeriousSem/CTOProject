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
        mappedName = "shipmentQueue",
        name = "ShipmentMessageListener",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "shipmentQueue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class ShipmentMessageListener implements MessageListener {

    public void onMessage(Message message) {
        TextMessage txtMessage = (TextMessage) message;

        try {
            //do something with message
            System.out.println("Got message:" + txtMessage.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
