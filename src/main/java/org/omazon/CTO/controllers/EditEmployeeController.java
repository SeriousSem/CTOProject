package org.omazon.CTO.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Employee;


@ManagedBean(name = "editEmployee")
@RequestScoped
public class EditEmployeeController {
	
	@ManagedProperty("#{employee}")
    public Employee employee;

    @Inject
    private EmployeeDAO employeeDAO;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setEmployeeByName(String name) {
		setEmployee(employeeDAO.getBySurname(name));
	}
	
	public String update(Employee name) {
		employeeDAO.update(employee);
		return "startPage";
	}

}
