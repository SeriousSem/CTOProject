package org.omazon.CTO;

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

@ManagedBean
@RequestScoped
public class CustomerAdd {

    @ManagedProperty("#{customer}")
    public Customer customer;

    @Inject
    private CustomerDAO customerDAO;

    private long id = 0;

    public void doAdd(Customer name) {
        customerDAO.save(customer);
    }

    public String getStatus() {
        if (id != 0) {
            return "success";
        }
        return "fail";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
