package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.DAO.interfaces.EmployeeDAO;

import javax.faces.bean.ManagedBean;
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
//
//    @ManagedProperty("#{employee}")
//    public Employee employee;
//
//    @ManagedProperty("#{customer}")
//    public Customer customer;

    @Inject
    private CustomerDAO customerDAO;
	@Inject
    private EmployeeDAO employeeDAO;
	
    public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}


	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}


	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}


	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}




    public String doLogin() {
    	System.out.println("Username = "+userId+"password = "+password);
    	if(customerDAO.getById(Long.valueOf(userId)) != null){
    		return "products";
    	}
    	else if(employeeDAO.getById(Long.valueOf(userId)) != null){
    		return "indext";
    	}
    	return "index";
//        customer = customerDAO.getCustomerByLogin(userLogin, password);
//        employee = employeeDAO.getEmployeeByLogin(userLogin, password);
//
//        if (customer != null || employee != null) {
//            //thats ok. you have now customer or employee which you can get on all pages by annotate your object with  @ManagedProperty("#{customer or employee}")
//            //here you decide which page will be shown furhther: for employees or for customers
//        } else {
//            //try one more time
//        }
//        return null;
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

}
