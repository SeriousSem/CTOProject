package org.omazon.CTO.hibernateServices;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.OrderProducts;
import org.omazon.CTO.entities.Product;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 24.10.13
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
public final class HibernateFacroryProvider {
    private static SessionFactory sessionFactory;

    private HibernateFacroryProvider() {
    }

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addPackage("com.omazon.CTOProject.entities")
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(OrderProducts.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
