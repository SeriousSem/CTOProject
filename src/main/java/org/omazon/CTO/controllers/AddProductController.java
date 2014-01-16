package org.omazon.CTO.controllers;

import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@ManagedBean(name = "addProduct")
@RequestScoped
public class AddProductController {

    @ManagedProperty("#{product}")
    public Product product;

    @Inject
    private ProductDAO productDAO;

    public String doAdd() {
        productDAO.save(product);
        return "employeeStartPage";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
