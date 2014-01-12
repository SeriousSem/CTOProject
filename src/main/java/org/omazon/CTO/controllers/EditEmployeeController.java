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
	
//	@ManagedProperty("#{employee}")
//    public Employee employee;
	
	@ManagedProperty("#{login}")
    public LoginController login;

	@Inject
    private EmployeeDAO employeeDAO;

	public void setLogin(LoginController login) {
		this.login = login;
	}
	
	public Employee getEmployee() {
		return login.getEmployee();
	}

	public void setEmployee(Employee employee) {
		login.setEmployee(employee);
	}
	
	public void setEmployeeId(String id) {
		login.getEmployee().setEmployeeId(Long.parseLong(id, 10));
	}
	
	public String getEmployeeId() {
		return String.valueOf(login.getEmployee().getEmployeeId());
	}
	
	public void setName(String name) {
		login.getEmployee().setName(name);
	}
	
	public String getName() {
		return String.valueOf(login.getEmployee().getName());
	}
	
	public void setSurname(String surname) {
		login.getEmployee().setSurname(surname);
	}
	
	public String getSurname() {
		return String.valueOf(login.getEmployee().getSurname());
	}
	
	public void setPsw(String psw) {
		login.getEmployee().setPsw(psw);
	}
	
	public String getPsw() {
		return String.valueOf(login.getEmployee().getPsw());
	}
	
	public void setEmployeeByName(String name) {
		login.setEmployee(employeeDAO.getBySurname(name));
	}
	
	public String update() {
		employeeDAO.update(login.getEmployee());
		return "employeeStartPage";
	}

}
