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

    private static final long serialVersionUID = 3143612974077451357L;

    @Id
    @GeneratedValue
    private long orderId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Customer.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pkorder")
    private Set<OrderProducts> orderProductses = new HashSet<OrderProducts>();

    @Column(name = "truckId")
    private long truckId = 0;

    @Column(name = "shipmentId")
    private int shipmentId = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "exceptionDescription")
    private String exceptionDescription;

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

    public long getTruckId() {
        return truckId;
    }

    public void setTruckId(long truckId) {
        this.truckId = truckId;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (shipmentId != order.shipmentId) return false;
        if (truckId != order.truckId) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (status != order.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (int) (truckId ^ (truckId >>> 32));
        result = 31 * result + shipmentId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
