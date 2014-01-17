package org.omazon.CTO.controllers;

import org.omazon.CTO.entities.Order;
import org.omazon.CTO.enums.Status;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "updateShipment")
@RequestScoped
public class ShipmentController {

    @ManagedProperty("#{orders}")
    public Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getShipmentStatus() {
        Status status = order.getStatus();
        return "startPage";
    }

    public void setShipmentStatus(Status status) {
        order.setStatus(status);
    }


}
