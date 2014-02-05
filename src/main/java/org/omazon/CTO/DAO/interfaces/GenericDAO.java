package org.omazon.CTO.DAO.interfaces;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 23.10.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDAO<T> {

    public void save(T object);

    public void update(T object);

    public void delete(T object);

    public void saveOrUpdate(T object);
    
    public void merge (T object);

    public T getById(long id);

    public List<T> getAll();

}