package org.omazon.CTO.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.entities.Customer;


@ManagedBean(name = "editCustomer")
@RequestScoped
public class EditCustomerController {
	
	@ManagedProperty("#{login}")
    public LoginController login;

    @Inject
    private CustomerDAO customerDAO;
    
    public void setLogin(LoginController login) {
		this.login = login;
	}

	public Customer getCustomer() {
		return login.getCustomer();
	}

	public void setCustomer(Customer customer) {
		login.setCustomer(customer);
	}
	
	public void setName(String name) {
		login.getCustomer().setName(name);
	}
	
	public String getName() {
		return String.valueOf(login.getCustomer().getName());
	}
	
	public void setSurname(String surname) {
		login.getCustomer().setSurname(surname);
	}
	
	public String getSurname() {
		return String.valueOf(login.getCustomer().getSurname());
	}
	
	public void setPsw(String psw) {
		login.getCustomer().setPsw(psw);
	}
	
	public String getPsw() {
		return String.valueOf(login.getCustomer().getPsw());
	}
	
	public void setAddress(String address) {
		login.getCustomer().setAddress(address);
	}
	
	public String getAddress() {
		return String.valueOf(login.getCustomer().getAddress());
	}
	
	public void setEmail(String email) {
		login.getCustomer().setEmail(email);
	}
	
	public String getEmail() {
		return String.valueOf(login.getCustomer().getEmail());
	}
	
	public void setCustomerByName(String name) {
		login.setCustomer(customerDAO.getBySurname(name));
	}
	
	public void setCustomerById(String id) {
		login.setCustomer(customerDAO.getById(Long.parseLong(id)));
	}
	
	public String update() {
		customerDAO.update(login.getCustomer());
		return "startPage";
	}

}
