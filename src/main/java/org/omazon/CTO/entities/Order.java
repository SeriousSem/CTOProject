package org.omazon.CTO.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import enums.Status;

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

    private static final long serialVersionUID = 7294013076862412989L;

    @Id
    @GeneratedValue
    private long orderId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Customer.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderProducts> orderProductses = new HashSet<OrderProducts>();

    @Column(name = "trackId")
    private long trackId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

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

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }
    
    public Status getShipmentStatus() {
    	return status;
    }
    
    public void setShipmentStatus(Status status) {
    	this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (trackId != order.trackId) return false;
        if (!customer.equals(order.customer)) return false;
        if (orderProductses != null ? !orderProductses.equals(order.orderProductses) : order.orderProductses != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + customer.hashCode();
        result = 31 * result + (orderProductses != null ? orderProductses.hashCode() : 0);
        result = 31 * result + (int) (trackId ^ (trackId >>> 32));
        return result;
    }
}
