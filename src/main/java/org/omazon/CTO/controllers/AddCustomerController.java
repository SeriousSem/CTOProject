package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.entities.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 22:35
 */

@ManagedBean(name = "addCustomer")
@RequestScoped
public class AddCustomerController {

    @ManagedProperty("#{customer}")
    public Customer customer;

    //COmmit
private String tes=null;    
    @Inject
    private CustomerDAO customerDAO;

    public String doAdd(Customer name) {
        customerDAO.save(customer);
        return "products";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
