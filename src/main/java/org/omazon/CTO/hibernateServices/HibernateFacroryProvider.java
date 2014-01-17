package org.omazon.CTO.hibernateServices;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.omazon.CTO.entities.*;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 24.10.13
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
public final class HibernateFacroryProvider {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private HibernateFacroryProvider() {
    }

    static {
        try {
            Configuration configuration = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addPackage("com.omazon.CTOProject.entities")
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(OrderProducts.class)
                    .addAnnotatedClass(Employee.class);
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
