package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@RequestScoped
public class LoginController {

    @ManagedProperty("#{employee}")
    public Employee employee;

    @ManagedProperty("#{customer}")
    public Customer customer;

    @Inject
    private CustomerDAO customerDAO;

    @Inject
    private EmployeeDAO employeeDAO;
    
    private String login;
    private String password; 


    public Object doLogin(String userLogin, String password) {
        customer = customerDAO.getCustomerByLogin(userLogin, password);
        employee = employeeDAO.getEmployeeByLogin(userLogin, password);

        if (customer != null || employee != null) {
            //thats ok. you have now customer or employee which you can get on all pages by annotate your object with  @ManagedProperty("#{customer or employee}")
            //here you decide which page will be shown furhther: for employees or for customers
        } else {
            //try one more time
        }
		return null;
    }


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
    

}
