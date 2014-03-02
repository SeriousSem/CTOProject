package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.CustomerDAO;
import org.omazon.CTO.DAO.interfaces.EmployeeDAO;
import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Employee;
import org.omazon.CTO.services.sockets.DBSender;
import org.omazon.CTO.services.sockets.TwoPCServer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: vishn_000
 * Date: 04.11.13
 * Time: 13:03
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginController {
    private String password;
    private String userLogin;

    @ManagedProperty("#{employee}")
    public Employee employee;

    @ManagedProperty("#{customer}")
    public Customer customer;

    @Inject
    private CustomerDAO customerDAO;
    @Inject
    private EmployeeDAO employeeDAO;

    private TwoPCServer twoPCServer;
    private DBSender dbSender;

    public String doUserLogin() {

        /**
         * initialize server socket for dbDump
         * initialize server socket for 2PC
         */
        if (dbSender == null) {
            dbSender = new DBSender();
            dbSender.openSocketToSendDump();
        }
        if (twoPCServer == null) {
            twoPCServer = new TwoPCServer();
            twoPCServer.startDBServerSocket();
        }

        customer = customerDAO.getCustomerByLogin(userLogin, password);
        employee = employeeDAO.getEmployeeByLogin(userLogin, password);
        if (customer != null) {
            return "customerStartPage";
        } else if (employee != null) {
            return "employeeStartPage";
        } else
            return null;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
