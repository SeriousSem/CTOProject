package org.omazon.CTO.hibernateServices;

import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 01:39
 */
public abstract class HibernateSession {
	
	private Session session = HibernateFacroryProvider.getSessionFactory().openSession();

    public Session getSession() {
//        return HibernateFacroryProvider.getSessionFactory().getCurrentSession();
    	return session;
    }
    
    public Boolean isSessionOpen() {
//    	return HibernateFacroryProvider.getSessionFactory().getCurrentSession().isOpen();
    	return session.isOpen();
    }
    
    public void startSession() {
    	session = HibernateFacroryProvider.getSessionFactory().openSession();
    }
    
    public void flushSession() {
    	session.flush();
    }
    
    public void closeSession() {
    	session.close();
    }
}
