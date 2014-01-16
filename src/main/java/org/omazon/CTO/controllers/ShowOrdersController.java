package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "showOrdersController")
@RequestScoped
public class ShowOrdersController {

    //@ManagedProperty("#{order}")
    //private Order order;

    @Inject
	private OrderDAO orderDAO;
	
	@Inject
	private ProductDAO productDAO;
	
	@ManagedProperty("#{login}")
    public LoginController login;
	
	public List<Order> getOrders(){
		if (login.getCustomer() != null){
			return orderDAO.getAllByCustomerId(login.getCustomer().getCustomerId());
		}
		else{
			return orderDAO.getAll();
		}
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}
}
