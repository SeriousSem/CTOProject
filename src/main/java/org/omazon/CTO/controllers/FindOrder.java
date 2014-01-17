package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vishn_000 on 17.01.14.
 */
@ManagedBean(name = "findOrder")
@RequestScoped
public class FindOrder {

    @Inject
    private OrderDAO orderDAO;

    private List<Order> orders = new ArrayList<>();

    private int shipmentIdToSearch = 0;

    @ManagedProperty("#{login}")
    public LoginController login;

    public void doUpdate() {
        if (orders != null && !orders.isEmpty()) {
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();
                orderDAO.update(order);
            }
        }
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public void doFind(int shipmentId) {
        Order order = orderDAO.getByShipmentId(shipmentIdToSearch);
        if (order != null) {
            orders.add(order);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getShipmentIdToSearch() {
        return shipmentIdToSearch;
    }

    public void setShipmentIdToSearch(int shipmentIdToSearch) {
        this.shipmentIdToSearch = shipmentIdToSearch;
    }
}

