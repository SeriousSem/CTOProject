package org.omazon.CTO.services;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;
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
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 17.11.13
 * Time: 18:05
 */
@MessageDriven(
        mappedName = "positionQueue",
        name = "PositionMessageListener",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "positionQueue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class PositionMessageListener implements MessageListener {

    @Inject
    private OrderDAO orderDAO;

    public void onMessage(Message message) {
        TextMessage txtMessage = (TextMessage) message;

        try {
            String txt = txtMessage.getText();
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(txt.getBytes()));

            String truckId = parse.getChildNodes().item(0).getChildNodes().item(0).getTextContent();
            String longitude = parse.getChildNodes().item(0).getChildNodes().item(1).getTextContent();
            String lat = parse.getChildNodes().item(0).getChildNodes().item(2).getTextContent();

            System.out.println("POSITION Got message:" + truckId + "  |  " + longitude + "  |  " + lat);

            List<Order> orderList = orderDAO.getOrdersByTruckId(Integer.parseInt(truckId));

            if (orderList != null && !orderList.isEmpty()) {
                Iterator<Order> orderIterator = orderList.iterator();
                while (orderIterator.hasNext()) {
                    Order order = orderIterator.next();
                    order.setLatitude(lat);
                    order.setLongitude(longitude);
                    System.out.println("UPDATE ORDER WITH SHIPMENT ID: " + order.getShipmentId());
                    orderDAO.update(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
