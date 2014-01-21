package org.omazon.CTO.services.JMS;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.enums.Status;
import org.w3c.dom.Document;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 17.11.13
 * Time: 18:05
 */
@MessageDriven(
        mappedName = "deliveryQueue",
        name = "DeliveryMessageListener",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "deliveryQueue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class DeliveryMessageListener implements MessageListener {

    @Inject
    private OrderDAO orderDAO;

    public void onMessage(Message message) {
        TextMessage txtMessage = (TextMessage) message;

        try {
            String txt = txtMessage.getText();
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(txt.getBytes()));

            String shipmentId = parse.getLastChild().getTextContent();
            System.out.println("DELIVERY Got message:" + parse.getLastChild().getTextContent());

            Order order = orderDAO.getByShipmentId(Integer.parseInt(shipmentId));
            order.setStatus(Status.DELIVERED);
            orderDAO.update(order);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
