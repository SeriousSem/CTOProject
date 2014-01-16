package org.omazon.CTO.controllers;

import org.omazon.CTO.entities.Customer;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.OrderProducts;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Set;


@ManagedBean(name = "showOrder")
@RequestScoped
public class OrderController {

    @ManagedProperty("#{orders}")
    public Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return order.getCustomer();
    }

    public void setCustomer(Customer customer) {
        order.setCustomer(customer);
    }

    public Set<OrderProducts> getProducts() {
        Set<OrderProducts> products = order.getOrderProductses();
        return products;
    }

    public OrderProducts getOrderProduct(long productId) {
        OrderProducts orderproduct = null;
        Set<OrderProducts> products = order.getOrderProductses();
        for (OrderProducts p : products) {
            if (p.getProduct().getProductId() == productId) {
                orderproduct = p;
                break;
            }
        }
        return orderproduct;
    }

    public void setProductCount(long productId, int count) {
        OrderProducts product = getOrderProduct(productId);
        product.setCount(count);
    }

    public int getProductCount(long productId) {
        OrderProducts product = getOrderProduct(productId);
        return product.getCount();
    }


}
