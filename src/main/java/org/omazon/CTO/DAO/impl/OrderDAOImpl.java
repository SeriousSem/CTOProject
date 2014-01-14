package org.omazon.CTO.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.Product;

public class OrderDAOImpl extends GenericDAO<Order> implements OrderDAO {

	@Override
	public List<Product> getOrderProducts(long orderId) {
		List<Product> products = new ArrayList<Product>();
		Transaction transaction = getSession().beginTransaction();
		Query query = getSession().createQuery("SELECT p FROM OrderProducts AS op INNER JOIN op.product AS p");
		System.out.println("HERE");
		while (query.iterate().hasNext()) {
			products.add((Product) query.iterate().next());
		}
		transaction.commit();
		return products;
	}
	
	public List<Order> getAllByCustomerId(long customerId){
		Transaction transaction = getSession().beginTransaction();

		List<Order> orderList = getSession().createQuery("SELECT * FROM Order as o WHERE o.customer.id=:customerId")
				.setParameter("customerId", customerId).list();
		
		transaction.commit();
		
		return orderList;
	}

}
