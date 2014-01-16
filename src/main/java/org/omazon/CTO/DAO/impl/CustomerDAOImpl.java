package org.omazon.CTO.DAO.impl;

import org.hibernate.Query;
import org.hibernate.Transaction;
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
        if (!isSessionOpen()) {
            startSession();
        }
        Transaction tx = getSession().beginTransaction();
        Customer customer = (Customer) getSession().createCriteria(Customer.class).add(Restrictions.eqProperty("surname", surname)).uniqueResult();
        tx.commit();
        return customer;
    }

    @Override
    public Customer getCustomerByLogin(String userLogin, String password) {
        if (!isSessionOpen()) {
            startSession();
        }
        Transaction tx = getSession().beginTransaction();
        Query query = getSession().createQuery("select c from Customer c where c.userLogin=:lg and c.password=:pass")
                .setParameter("lg", userLogin)
                .setParameter("pass", password);
        Customer customer = (Customer) query.uniqueResult();
        tx.commit();

        return customer;
    }
}
