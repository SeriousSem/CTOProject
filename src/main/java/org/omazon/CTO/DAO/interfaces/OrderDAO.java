package org.omazon.CTO.DAO.interfaces;

import java.util.List;

import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.Product;

public interface OrderDAO extends IDao<Order> {
	
	public List<Product> getOrderProducts(long orderId);
	
	public List<Order> getAllByCustomerId(long customerId);

}
