package org.omazon.CTO.remote;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.Product;
import org.omazon.CTO.remote.interfaces.OrderRemoteInter;
import org.omazon.CTO.enums.Status;


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
    public List<List<String>> getOrders() {
    	List<Order> orders = orderDAO.getAll();
        List<List<String>> returnList = new ArrayList<List<String>>();
        for (Order order : orders) {
	       	List<String> act = new ArrayList<String>();
	       	act.add(String.valueOf(order.getOrderId()));
	       	act.add(order.getCustomer().getUserLogin());
	       	act.add(order.getStatus().name());
	       	act.add(order.getExceptionDescription());
	       	act.add(order.getLatitude());
	       	act.add(order.getLongitude());
	       	act.add(String.valueOf(order.getShipmentId()));
	       	act.add(String.valueOf(order.getTruckId()));
	       	returnList.add(act);
        }
        return returnList;
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
    public List<String> getOrder() {
    	List<String> or = new ArrayList<String>();
    	or.add(String.valueOf(order.getOrderId()));
    	or.add(order.getCustomer().getUserLogin());
    	or.add(order.getStatus().name());
    	or.add(order.getExceptionDescription());
    	or.add(order.getLatitude());
    	or.add(order.getLongitude());
    	or.add(String.valueOf(order.getShipmentId()));
    	or.add(String.valueOf(order.getTruckId()));
        return or;
    }
    
    @Override
    public List<String> getOrderById(long orderId) {
    	order = orderDAO.getById(orderId);
    	List<String> or = new ArrayList<String>();
    	or.add(String.valueOf(order.getOrderId()));
    	or.add(order.getCustomer().getUserLogin());
    	or.add(order.getStatus().name());
    	or.add(order.getExceptionDescription());
    	or.add(order.getLatitude());
    	or.add(order.getLongitude());
    	or.add(String.valueOf(order.getShipmentId()));
    	or.add(String.valueOf(order.getTruckId()));
        return or;
    }

    @Override
    public void setOrder(List<String> order) {
        this.order.setOrderId(Long.parseLong(order.get(0)));
//        this.order.
    }
    
    @Override
    public void update() {
    	orderDAO.merge(order);
    }

	@Override
	public long getCustomerId() {
		return orderDAO.getCustomerId(order.getOrderId());
	}

	@Override
	public String getStatus() {
		return order.getStatus().name();
	}

	@Override
	public void setStatus(Status status) {
		order.setStatus(status);		
	}
}
