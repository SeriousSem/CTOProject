package org.omazon.CTO.entities;

import org.hibernate.annotations.Cascade;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 00:34
 */
@Table(name = "orders")
@Entity
@ManagedBean(name = "order")
@RequestScoped
public class Order implements Serializable {


    private static final long serialVersionUID = 1216805668289018398L;
    @Id
    @GeneratedValue
    private long orderId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Customer.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderProducts> orderProductses = new HashSet<OrderProducts>();

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderProducts> getOrderProductses() {
        return orderProductses;
    }

    public void setOrderProductses(Set<OrderProducts> orderProductses) {
        this.orderProductses = orderProductses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (orderProductses != null ? !orderProductses.equals(order.orderProductses) : order.orderProductses != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (orderProductses != null ? orderProductses.hashCode() : 0);
        return result;
    }
}
