package org.omazon.CTO.hibernateServices;

import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 01:39
 */
public abstract class HibernateSession {

    public Session getSession() {
        return HibernateFacroryProvider.getSessionFactory().getCurrentSession();
    }
}
