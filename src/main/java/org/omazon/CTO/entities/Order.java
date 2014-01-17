package org.omazon.CTO.entities;

import org.hibernate.annotations.Cascade;
import org.omazon.CTO.enums.Status;

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

    private static final long serialVersionUID = 7294013076862412989L;

    @Id
    @GeneratedValue
    private long orderId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Customer.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pkorder")
    private Set<OrderProducts> orderProductses = new HashSet<OrderProducts>();

    @Column(name = "trackId")
    private long trackId = 0;

    @Column(name = "shipmentId")
    private int shipmentId = 0;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Status[] getStatusValues() {
        return Status.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (shipmentId != order.shipmentId) return false;
        if (trackId != order.trackId) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (status != order.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (int) (trackId ^ (trackId >>> 32));
        result = 31 * result + shipmentId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
