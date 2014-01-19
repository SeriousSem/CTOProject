package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;


/**
 * @author ashleeeeee
 */
@ManagedBean(name = "showAllProductController")
@RequestScoped
public class GetAllProductController {


    @Inject
    private ProductDAO productDAO;

    private long productId;

    public List<Product> getProducts() {
        return productDAO.getAll();
    }

    public String goToEdit(long productId) {
        setProductId(productId);
        return "editProduct";
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
