package org.moazon.CTO.remote;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.remote.interfaces.OrderRemoteInter;


@Stateless
public class OrderRemote implements OrderRemoteInter {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public Order order;

	@Inject
    private OrderDAO orderDAO;

    private long orderId;

    @Override
    public List<Order> getOrders() {
        return orderDAO.getAll();
    }

    @Override
    public long getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public void doAdd() {
        orderDAO.save(order);
    }

    @Override
    public Order getOrder() {
        return order;
    }
    
    @Override
    public Order getOrderById(long orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }
    
    @Override
    public void update() {
    	orderDAO.merge(order);
    }
}
