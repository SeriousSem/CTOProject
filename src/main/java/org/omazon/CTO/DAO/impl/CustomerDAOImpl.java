package org.omazon.CTO.DAO.impl;

import org.hibernate.criterion.Restrictions;
import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.entities.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 23.10.13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class CustomerDAOImpl extends GenericDAO<Customer> implements CustomerDAO {

    @Override
    public Customer getBySurname(String surname) {
        return (Customer) getSession().createCriteria(Customer.class).add(Restrictions.eqProperty("surname", surname)).uniqueResult();
    }
}
