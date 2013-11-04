package org.omazon.CTO.DAO.interfaces;

import org.omazon.CTO.entities.Employee;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeDAO extends IDao<Employee> {
    public Employee getEmployeeByLogin(String login, String password);
}
