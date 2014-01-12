package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@ManagedBean(name = "addEmployee")
@RequestScoped
public class AddEmployeeController {

    @ManagedProperty("#{employee}")
	public Employee employee;

    @Inject
    private EmployeeDAO employeeDAO;

    public String doAdd(Employee name) {
        employeeDAO.save(employee);
        return "employeeStartPage";
    }

    public Employee getemployee() {
        return employee;
    }

    public void setemployee(Employee employee) {
        this.employee = employee;
    }
}
