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
        mappedName = "exceptionQueue",
        name = "ExceptionMessageListener",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "exceptionQueue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class ExceptionMessageListener implements MessageListener {

    @Inject
    private OrderDAO orderDAO;

    public void onMessage(Message message) {
        TextMessage txtMessage = (TextMessage) message;

        try {
            String txt = txtMessage.getText();
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(txt.getBytes()));

            String truckId = parse.getChildNodes().item(0).getFirstChild().getTextContent();
            String description = parse.getChildNodes().item(0).getLastChild().getTextContent();
            System.out.println("EXCEPTION Got message:" + truckId + "  |  " + description);

            List<Order> orderList = orderDAO.getOrdersByTruckId(Integer.parseInt(truckId));

            EmailService emailService = new EmailService();

            if (orderList != null && !orderList.isEmpty()) {
                Iterator<Order> orderIterator = orderList.iterator();
                while (orderIterator.hasNext()) {
                    Order order = orderIterator.next();
                    order.setExceptionDescription(description);

                    //send exception email to customer
                    emailService.sendMessage(order.getCustomer().getEmail(), order.getCustomer().getSurname() + " " + order.getCustomer().getSurname(), "Delivery Exception", description);

                    orderDAO.update(order);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
