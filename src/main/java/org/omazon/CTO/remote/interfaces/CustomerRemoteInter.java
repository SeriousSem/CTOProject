package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;

import javax.ejb.Remote;

import org.omazon.CTO.entities.Customer;

@Remote
public interface CustomerRemoteInter extends Serializable {

    public void update();

    public Customer getCustomer();

    public void setCustomer(Customer customer);
    
    public Customer getCustomerById(long id);
    
    public long getCustomerId();


}
