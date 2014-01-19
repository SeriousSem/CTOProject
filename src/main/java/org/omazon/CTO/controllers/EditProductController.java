package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "editProduct")
@ViewScoped
public class EditProductController {

    public Product product;

    private long productId;

    @Inject
    private ProductDAO productDAO;

    public String doUpdate() {
        productDAO.update(product);
        return "showAllProducts";
    }

    public Product getProduct() {
        return product;
    }

    public String render() {
        product = productDAO.getById(getProductId());
        return null;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
