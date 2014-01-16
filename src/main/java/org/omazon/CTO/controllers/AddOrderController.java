package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.OrderProducts;
import org.omazon.CTO.entities.Product;
import org.omazon.CTO.enums.Status;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    private List<Product> allProducts;

    public AddOrderController() {
        allProducts = productDAO.getAll();
    }

    public void addOrder() {
        order.setCustomer(login.getCustomer());
        Set<OrderProducts> selectedProducts = new HashSet();
        for (Product p : allProducts) {
            if (p.isChecked()) {
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
        order.setStatus(Status.CREATED);
        orderDAO.save(order);
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }
}
