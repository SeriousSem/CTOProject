package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;


@ManagedBean(name = "editEmployee")
@RequestScoped
public class EditEmployeeController {

    @ManagedProperty("#{login}")
    public LoginController login;

    @Inject
    private EmployeeDAO employeeDAO;

    public void setLogin(LoginController login) {
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

    public LoginController getLogin() {
        return login;
    }

}
