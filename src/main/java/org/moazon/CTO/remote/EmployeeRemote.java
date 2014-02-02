package org.moazon.CTO.remote;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;


@Stateless
public class EmployeeRemote {

    @ManagedProperty("#{login}")
    public LoginRemote login;

    @Inject
    private EmployeeDAO employeeDAO;

    public void setLogin(LoginRemote login) {
        this.login = login;
    }

    public String update() {
        employeeDAO.update(login.getEmployee());
        return "employeeStartPage";
    }

    public Employee getEmployee() {
        return login.getEmployee();
    }

    public void setEmployee(Employee employee) {
        login.setEmployee(employee);
    }

    public LoginRemote getLogin() {
        return login;
    }

}
