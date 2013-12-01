package org.omazon.CTO.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.Product;


@ManagedBean(name = "showOrder")
@RequestScoped
public class OrderController {
	
	@ManagedProperty("#{orders}")
    public Order order;

    @Inject
    private OrderDAO orderDAO;

	public Order getOrder() {
		System.out.println("TEST");
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String getProducts() {
		List<Product> products = orderDAO.getOrderProducts(1L);
		System.out.println("TEST");
		System.out.println(products.get(0).getName());
		return "startPage";
	}
	

}
