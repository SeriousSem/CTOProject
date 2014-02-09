package org.moazon.CTO.remote;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.remote.interfaces.CustomerRemoteInter;


@Stateless
public class CustomerRemote implements CustomerRemoteInter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
	private Customer customer;

    @Inject
    private CustomerDAO customerDAO;

    @Override
    public void update() {
    	customerDAO.merge(customer);
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

	@Override
	public Customer getCustomerById(long id) {
		return customerDAO.getById(id);
	}
	
	@Override
	public long getCustomerId() {
		return customer.getCustomerId();
	}

}
