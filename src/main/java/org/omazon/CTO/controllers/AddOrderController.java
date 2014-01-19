package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.OrderDAO;
import org.omazon.CTO.DAO.interfaces.OrderProductsDAO;
import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.OrderProducts;
import org.omazon.CTO.entities.Product;
import org.omazon.CTO.enums.Status;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.*;

@ManagedBean(name = "addOrderController")
@RequestScoped
public class AddOrderController {

    @Inject
    private OrderDAO orderDAO;

    @Inject
    private ProductDAO productDAO;

    @Inject
    private OrderProductsDAO orderProductsDAO;

    @ManagedProperty("#{login}")
    public LoginController login;

    @ManagedProperty("#{order}")
    public Order order;

    private List<Product> allProducts;

    public String addOrder() {
        order.setCustomer(login.getCustomer());
        Set<OrderProducts> selectedProducts = new HashSet();
        Iterator<Product> productIterator = getAllProducts().iterator();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            if (product.isChecked()) {
                OrderProducts orderProducts = new OrderProducts();
                orderProducts.setOrder(order);
                orderProducts.setProduct(product);
                orderProducts.setCount(product.getCount());
                selectedProducts.add(orderProducts);
            }
        }
        order.setOrderProductses(selectedProducts);
        Random random = new Random();
        order.setTrackId(random.nextInt(10000));
        order.setShipmentId(random.nextInt(10000));
        order.setStatus(Status.CREATED);
        orderDAO.save(order);
        Iterator<OrderProducts> orderProductsIterator = selectedProducts.iterator();
        while (orderProductsIterator.hasNext()) {
            OrderProducts orderProducts = orderProductsIterator.next();
            orderProductsDAO.saveOrUpdate(orderProducts);
        }
        return "customerStartPage";
    }

    public List<Product> getAllProducts() {
        if (allProducts == null) {
            allProducts = productDAO.getAll();
        }
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
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
}
