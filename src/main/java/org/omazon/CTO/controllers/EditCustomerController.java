package org.omazon.CTO.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.entities.Customer;


@ManagedBean(name = "editCustomer")
@RequestScoped
public class EditCustomerController {
	
	@ManagedProperty("#{customer}")
    public Customer customer;

    @Inject
    private CustomerDAO customerDAO;

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String update(Customer customer) {
		customerDAO.update(customer);
		return "editCustomerSucess";
	}

}
