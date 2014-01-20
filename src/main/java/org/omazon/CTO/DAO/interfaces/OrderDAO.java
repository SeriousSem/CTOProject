package org.omazon.CTO.DAO.interfaces;

import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.Product;

import java.util.List;

public interface OrderDAO extends IDao<Order> {

    public List<Product> getOrderProducts(long orderId);

    public List<Order> getAllByCustomerId(long customerId);

    public Order getByShipmentId(int shipmentId);

    public List<Order> getOrdersByTruckId(long truckId);

}
