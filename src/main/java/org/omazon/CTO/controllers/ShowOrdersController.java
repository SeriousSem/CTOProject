package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@ManagedBean(name = "showOrdersController")
@RequestScoped
public class ShowOrdersController {

    @Inject
    private OrderDAO orderDAO;

    private List<Order> orders;

    @ManagedProperty("#{login}")
    public LoginController login;

    public String doUpdate() {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            orderDAO.update(order);
        }
        return "employeeStartPage";
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public List<Order> getOrders() {
        if (orders == null) {
            if (login.getCustomer() != null) {
                orders = orderDAO.getAllByCustomerId(login.getCustomer().getCustomerId());
            } else {
                orders = orderDAO.getAll();
            }
        }
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isOrdersEmpty() {
        return getOrders().isEmpty();
    }
}
