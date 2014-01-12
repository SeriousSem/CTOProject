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
	
	public void setEmployeeId(String id) {
		this.employee.setEmployeeId(Long.parseLong(id, 10));
	}
	
	public String getEmployeeId() {
		return String.valueOf(employee.getEmployeeId());
	}
	
	public void setEmployeeName(String name) {
		this.employee.setName(name);
	}
	
	public String getEmployeeName() {
		return String.valueOf(employee.getName());
	}
	
	public void setEmployeeByName(String name) {
		setEmployee(employeeDAO.getBySurname(name));
	}
	
	public String update(Employee name) {
		employeeDAO.update(employee);
		return "employeeStartPage";
	}

}
