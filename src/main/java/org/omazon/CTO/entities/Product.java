package org.omazon.CTO.entities;

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
 * Time: 00:51
 */
@Entity
@Table(name = "product")
@RequestScoped
@ManagedBean(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 6401569395534494046L;
    @Id
    @GeneratedValue
    @Column(name = "productId")
    private long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<OrderProducts> productOrders = new HashSet<OrderProducts>();


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<OrderProducts> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<OrderProducts> productOrders) {
        this.productOrders = productOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (productOrders != null ? !productOrders.equals(product.productOrders) : product.productOrders != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (productOrders != null ? productOrders.hashCode() : 0);
        return result;
    }
}
