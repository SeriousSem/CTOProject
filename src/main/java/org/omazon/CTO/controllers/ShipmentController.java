package org.omazon.CTO.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.omazon.CTO.entities.Order;


@ManagedBean(name = "updateShipment")
@RequestScoped
public class ShipmentController {
	
	@ManagedProperty("#{orders}")
    public Order order;

	public Order getOrder() {
		System.out.println("TEST");
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String getShipmentStatus() {
		String status = order.getShipmentStatus();
		System.out.println("TEST");
		System.out.println(status);
		return "startPage";
	}
	
	public void setShipmentStatus(String status) {
		order.setShipmentStatus(status);
	}
	

}
