package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;


@Remote
public interface CustomerRemoteInter extends Serializable {

    public void update();

    public List<String> getCustomer();

    public void setCustomer(List<String> customer);
    
    public List<String> getCustomerById(long id);
    
    public long getCustomerId();


}
