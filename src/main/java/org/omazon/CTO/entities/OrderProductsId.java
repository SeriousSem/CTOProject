package org.omazon.CTO.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: All
 * Date: 29.09.13
 * Time: 15:39
 */
@SuppressWarnings("JpaAttributeTypeInspection")
@Embeddable
public class OrderProductsId implements Serializable {

    private static final long serialVersionUID = 6310908805971315154L;
    private int order;
    private int product;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductsId)) return false;

        OrderProductsId that = (OrderProductsId) o;

        if (order != that.order) return false;
        if (product != that.product) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = order;
        result = 31 * result + product;
        return result;
    }
}
