package org.omazon.CTO.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 13:16
 */
@Entity
@Table(name = "orderProducts")
@ManagedBean(name = "orderProducts")
@RequestScoped
public class OrderProducts implements Serializable {

    private static final long serialVersionUID = 7882955941764904930L;

    @Id
    @Column(name = "orderProductsId")
    private long orderProductsId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order pkorder;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product pkproduct;


    @Column(name = "count", nullable = false, length = 10)
    private int count = 0;

    public long getOrderProductsId() {
        return orderProductsId;
    }

    public void setOrderProductsId(long orderProductsId) {
        this.orderProductsId = orderProductsId;
    }

    public Order getOrder() {
        return pkorder;
    }

    public void setOrder(Order pkorder) {
        this.pkorder = pkorder;
    }

    public Product getProduct() {
        return pkproduct;
    }

    public void setProduct(Product pkproduct) {
        this.pkproduct = pkproduct;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProducts)) return false;

        OrderProducts that = (OrderProducts) o;

        if (count != that.count) return false;
        if (orderProductsId != that.orderProductsId) return false;
        if (pkorder != null ? !pkorder.equals(that.pkorder) : that.pkorder != null) return false;
        if (pkproduct != null ? !pkproduct.equals(that.pkproduct) : that.pkproduct != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderProductsId ^ (orderProductsId >>> 32));
        result = 31 * result + (pkorder != null ? pkorder.hashCode() : 0);
        result = 31 * result + (pkproduct != null ? pkproduct.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
}
