package org.omazon.CTO.DAO.impl;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeDAOImpl extends GenericDAO<Employee> implements EmployeeDAO {

    @Override
    public Employee getEmployeeByLogin(String login, String password) {
        Query query = getSession().createQuery("SELECT * FROM employee WHERE userLogin = :login AND password = :pass")
                .setParameter("login", login)
                .setParameter("pass", password);

        return (Employee) query.uniqueResult();
    }
    
    @Override
    public Employee getBySurname(String surname) {
        return (Employee) getSession().createCriteria(Employee.class).add(Restrictions.eqProperty("surname", surname)).uniqueResult();
    }
}
