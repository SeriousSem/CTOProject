package org.omazon.CTO.DAO.impl;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.omazon.CTO.DAO.interfaces.IDao;
import org.omazon.CTO.hibernateServices.HibernateSession;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 23.10.13
 * Time: 13:52
 */
public abstract class GenericDAO<T> extends HibernateSession implements IDao<T> {

    T daoClass;

    @Override
    public void save(T object) {
        Transaction transaction = getSession().beginTransaction();
        getSession().save(object);
        transaction.commit();
    }

    @Override
    public void update(T object) {
        Transaction transaction = getSession().beginTransaction();
        getSession().update(object);
        transaction.commit();
    }

    @Override
    public <T> void delete(T object) {

        Transaction transaction = getSession().beginTransaction();
        getSession().delete(object);
        transaction.commit();
    }

    @Override
    public T getById(long id) {
        T obj = (T) getSession().get(daoClass.getClass(), id);
        return obj;
    }

    @Override
    public List<T> getAll() {
        Criteria criteria = getSession().createCriteria(daoClass.getClass());
        List<T> objects = criteria.list();
        return objects;
    }
}

