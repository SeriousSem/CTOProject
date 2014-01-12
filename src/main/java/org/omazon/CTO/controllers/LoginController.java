package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Employee;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 13:03
 */
@ManagedBean(name = "login")
@RequestScoped
public class LoginController {
	private String userId;
	private String password;
	private String username;

    @ManagedProperty("#{employee}")
    public Employee employee;

    @ManagedProperty("#{customer}")
    public Customer customer;

    @Inject
    private CustomerDAO customerDAO;
	@Inject
    private EmployeeDAO employeeDAO;

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

    public String doCustomerLogin() {
//    	System.out.println("Username = "+userId+"password = "+password);
//    	if(customerDAO.getById(Long.valueOf(userId)) != null){
//    		return "products";
//    	}
//    	// go to employee first page
//    	else if(employeeDAO.getById(Long.valueOf(userId)) != null){
//    		return "index";
//    	}
//    	return "index";
        customer = customerDAO.getCustomerByLogin(userId, password);

        if (customer != null) {
            //thats ok. you have now customer or employee which you can get on all pages by annotate your object with  @ManagedProperty("#{customer or employee}")
            //here you decide which page will be shown furhther: for employees or for customers
        	return "products";
        } else {
        	return null;
        }
    }
    
    public String doEmployeeLogin() {
        employee = employeeDAO.getEmployeeByLogin(username, password);
        if (employee != null) {
            //thats ok. you have now customer or employee which you can get on all pages by annotate your object with  @ManagedProperty("#{customer or employee}")
            //here you decide which page will be shown furhther: for employees or for customers
            return "editEmployee";
        } else {
        	return null;
        }
    }

}
