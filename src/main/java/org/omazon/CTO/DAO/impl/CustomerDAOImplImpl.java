package org.omazon.CTO.DAO.impl;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.entities.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 23.10.13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class CustomerDAOImplImpl extends GenericDAOImpl<Customer> implements CustomerDAO {

    @Override
    public Customer getBySurname(String surname) {
        if (!isSessionOpen()) {
            startSession();
        }
        Transaction tx = getSession().beginTransaction();
        List<Customer> customers = getSession().createCriteria(Customer.class).add(Restrictions.eqProperty("surname", surname)).list();
        tx.commit();
        return customers.isEmpty() ? null : customers.get(0);
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
        List<Customer> customers = query.list();
        tx.commit();

        return customers.isEmpty() ? null : customers.get(0);
    }
}
