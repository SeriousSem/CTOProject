package org.omazon.CTO.DAO.impl;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeDAOImplImpl extends GenericDAOImpl<Employee> implements EmployeeDAO {

    @Override
    public Employee getEmployeeByLogin(String login, String password) {
        if (!isSessionOpen()) {
            startSession();
        }
        Transaction tx = getSession().beginTransaction();
        Query query = getSession().createQuery("select e from Employee e where e.userLogin=:lg and e.password=:pass")
                .setParameter("lg", login)
                .setParameter("pass", password);
        List<Employee> employees = query.list();
        tx.commit();

        return employees.isEmpty() ? null : employees.get(0);
    }

    @Override
    public Employee getBySurname(String surname) {
        if (!isSessionOpen()) {
            startSession();
        }
        Transaction tx = getSession().beginTransaction();
        List<Employee> employees = getSession().createCriteria(Employee.class).add(Restrictions.eqProperty("surname", surname)).list();
        tx.commit();
        return employees.isEmpty() ? null : employees.get(0);
    }
}
