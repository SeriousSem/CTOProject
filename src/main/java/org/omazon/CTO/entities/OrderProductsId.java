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
}
