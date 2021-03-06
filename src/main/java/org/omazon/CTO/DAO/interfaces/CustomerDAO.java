package org.omazon.CTO.DAO.interfaces;

import org.omazon.CTO.entities.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 23.10.13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerDAO extends GenericDAO<Customer> {
    public Customer getBySurname(String name);

    public Customer getCustomerByLogin(String userLogin, String password);

}
