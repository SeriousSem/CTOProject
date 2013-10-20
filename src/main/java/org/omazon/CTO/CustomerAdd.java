package org.omazon.CTO;

import org.omazon.CTO.entities.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 22:35
 */
@ViewScoped
@ManagedBean
public class CustomerAdd {

    @ManagedProperty("#{customer}")
    public Customer customer;

    private long id = 0;

    public void setDoAdd(Object name) {
        id = HibernateSession.save(customer);
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
