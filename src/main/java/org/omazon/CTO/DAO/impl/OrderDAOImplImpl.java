package org.omazon.CTO.DAO.impl;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImplImpl extends GenericDAOImpl<Order> implements OrderDAO {

    @Override
    public List<Product> getOrderProducts(long orderId) {
        List<Product> products = new ArrayList<Product>();
        Transaction transaction = getSession().beginTransaction();
        Query query = getSession().createQuery("SELECT p FROM OrderProducts AS op INNER JOIN op.product AS p");
        while (query.iterate().hasNext()) {
            products.add((Product) query.iterate().next());
        }
        transaction.commit();
        return products;
    }

    public List<Order> getAllByCustomerId(long customerId) {
        Transaction transaction = getSession().beginTransaction();

        List<Order> orderList = getSession().createQuery("SELECT o FROM Order AS o WHERE o.customer.customerId=:id")
                .setParameter("id", customerId).list();

        transaction.commit();

        return orderList;
    }

    @Override
    public Order getByShipmentId(int shipmentId) {
        Transaction transaction = getSession().beginTransaction();

        Order order = (Order) getSession().createQuery("SELECT o FROM Order AS o WHERE o.shipmentId=:id")
                .setParameter("id", shipmentId).uniqueResult();

        transaction.commit();
        return order;
    }

    @Override
    public List<Order> getOrdersByTruckId(long truckId) {
        Transaction transaction = getSession().beginTransaction();

        List<Order> orderList = getSession().createQuery("SELECT o FROM Order AS o WHERE o.truckId=:id")
                .setParameter("id", truckId).list();

        transaction.commit();
        return orderList;
    }

	@Override
	public long getCustomerId(long orderId) {
		Transaction transaction = getSession().beginTransaction();

        Customer customer = (Customer) getSession().createQuery("SELECT o.customer FROM Order AS o WHERE o.orderId=:id")
                .setParameter("id", orderId).uniqueResult();

        transaction.commit();
        long customerId = customer.getCustomerId();
        return customerId;
	}


}
