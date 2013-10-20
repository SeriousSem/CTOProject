package org.omazon.CTO;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.OrderProducts;
import org.omazon.CTO.entities.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 01:39
 */
public class HibernateSession {

    private static SessionFactory sessionFactory;
    private static Session session;

    private static SessionFactory configureSessionFactory() throws HibernateException {

        Configuration configuration = new Configuration();
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addPackage("com.omazon.CTOProject.entities")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(OrderProducts.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            sessionFactory = configureSessionFactory();
        }
        if (session == null || !session.isConnected()) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static <T> long save(T object) {
        Session session = getSession();
        Transaction tx = null;
        Long id = null;
        try {
            tx = session.beginTransaction();
            id = (Long) session.save(object);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public static <T> long update(T object) {
        Session session = getSession();
        Transaction tx = null;
        Long id = null;
        try {
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public static <T> void delete(T object) {
        Session session = getSession();
        Transaction tx = null;
        Long id = null;
        try {
            tx = session.beginTransaction();
            session.delete(object);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Object getById(Class entityForSearch, long id) {
        Session session = getSession();
        Transaction tx = null;
        Object obj = null;
        try {
            tx = session.beginTransaction();
            obj = session.get(entityForSearch, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return obj;
    }

    public static List<?> getAll(Class entityForReturn) {
        Criteria criteria = getSession().createCriteria(entityForReturn);
        List<?> objects = criteria.list();
        return objects;
    }


}
