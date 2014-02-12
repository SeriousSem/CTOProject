package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import org.omazon.CTO.enums.Status;

@Remote
public interface OrderRemoteInter extends Serializable {
	
	public List<List<String>> getOrders();
	
    public long getOrderId();
    
    public void setOrderId(long orderId);
    
    public void doAdd();
    
    public List<String> getOrder();
    
    public List<String> getOrderById(long orderId);
    
    public void setOrder(List<String> order);
 
    public void update();
    
    public long getCustomerId();
    
    public String getStatus();
    
    public void setStatus(Status status);

}
