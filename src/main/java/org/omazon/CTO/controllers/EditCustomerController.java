package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@ManagedBean(name = "editCustomer")
@RequestScoped
public class EditCustomerController {

    @ManagedProperty("#{login}")
    public LoginController login;


    @Inject
    private CustomerDAO customerDAO;

    public String update() {
        customerDAO.update(login.getCustomer());
        return "customerStartPage";
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }
}
