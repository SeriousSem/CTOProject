package org.omazon.CTO.remote;

import java.util.ArrayList;
import java.util.List;

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
    public List<String> getCustomer() {
    	List<String> cuStr = new ArrayList<String>();
		Customer cu = customer;
		cuStr.add(String.valueOf(cu.getCustomerId()));
		cuStr.add(cu.getAddress());
		cuStr.add(cu.getEmail());
		cuStr.add(cu.getName());
		cuStr.add(cu.getSurname());
		cuStr.add(cu.getPassword());
		cuStr.add(cu.getUserLogin());
        return cuStr;
    }

	@Override
	public List<String> getCustomerById(long id) {
		List<String> cuStr = new ArrayList<String>();
		Customer cu = customerDAO.getById(id);
		cuStr.add(String.valueOf(cu.getCustomerId()));
		cuStr.add(cu.getAddress());
		cuStr.add(cu.getEmail());
		cuStr.add(cu.getName());
		cuStr.add(cu.getSurname());
		cuStr.add(cu.getPassword());
		cuStr.add(cu.getUserLogin());
		return cuStr;
	}
	
	@Override
	public long getCustomerId() {
		return customer.getCustomerId();
	}

	@Override
	public void setCustomer(List<String> customer) {
		this.customer.setCustomerId(Long.parseLong(customer.get(0)));
		this.customer.setAddress(customer.get(1));
		this.customer.setEmail(customer.get(2));
		this.customer.setName(customer.get(3));
		this.customer.setSurname(customer.get(4));
		this.customer.setPassword(customer.get(5));
		this.customer.setUserLogin(customer.get(6));
	}

}
