package org.omazon.CTO.DAO.impl;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.omazon.CTO.DAO.interfaces.IDao;
import org.omazon.CTO.hibernateServices.HibernateSession;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 23.10.13
 * Time: 13:52
 */
public abstract class GenericDAO<T> extends HibernateSession implements IDao<T> {

    private Class<T> persistentClass;

    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

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
    public void delete(T object) {

        Transaction transaction = getSession().beginTransaction();
        getSession().delete(object);
        transaction.commit();
    }

    @Override
    public T getById(long id) {
        Transaction transaction = getSession().beginTransaction();
        T obj = (T) getSession().get(persistentClass, id);
        transaction.commit();
        return obj;
    }

    @Override
    public List<T> getAll() {
        Transaction transaction = getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(persistentClass);
        List<T> objects = criteria.list();
        transaction.commit();
        return objects;
    }
}

