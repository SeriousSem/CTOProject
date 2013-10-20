package org.omazon.CTO.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 13:16
 */
@Entity
@Table(name = "order_product")
@IdClass(OrderProductsId.class)
@ManagedBean(name = "orderProducts")
@SessionScoped
public class OrderProducts implements Serializable {


    private static final long serialVersionUID = -3524138307828865918L;
    @Id
    @ManyToOne
    private Order order;

    @Id
    @ManyToOne
    private Product product;

    @Column(name = "count", nullable = false, length = 10)
    private int count;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = order != null ? order.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
}
