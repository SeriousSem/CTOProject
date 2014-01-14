package org.omazon.CTO.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.OrderProducts;
import org.omazon.CTO.entities.Product;

import enums.Status;

@ManagedBean(name = "addOrderController")
@RequestScoped
public class AddOrderController {
	
	@ManagedProperty("#{order}")
	private Order order;
	
	@Inject
	private OrderDAO orderDAO;
	
	@Inject
	private ProductDAO productDAO;
	
	@ManagedProperty("#{login}")
    public LoginController login;
	
	private long trackId;
	private List<Product> allProducts = new ArrayList();
	
	public void addOrder(){
		order.setCustomer(login.getCustomer());
		Set<OrderProducts> selectedProducts = new HashSet();
		for (Product p: allProducts) {
			if (p.isChecked()){
				OrderProducts orderProducts = new OrderProducts();
				orderProducts.setOrder(order);
				orderProducts.setProduct(p);
				orderProducts.setCount(1);
				selectedProducts.add(orderProducts);
			}
		}
		order.setOrderProductses(selectedProducts);
		Random random = new Random();
		order.setTrackId(random.nextInt());
		order.setShipmentStatus(Status.CREATED);
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getTrackId() {
		return trackId;
	}

	public void setTrackId(long trackId) {
		this.trackId = trackId;
	}

	public List<Product> getAllProducts() {
		return productDAO.getAll();
	}

	public void setAllProducts(List<Product> allProducts) {
		this.allProducts = allProducts;
	}
	
	
	
}
