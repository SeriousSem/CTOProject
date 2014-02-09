package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import org.omazon.CTO.entities.Order;

@Remote
public interface OrderRemoteInter extends Serializable {
	
	public List<Order> getOrders();
	
    public long getOrderId();
    
    public void setOrderId(long orderId);
    
    public void doAdd();
    
    public Order getOrder();
    
    public Order getOrderById(long orderId);
    
    public void setOrder(Order order);
 
    public void update();

}
