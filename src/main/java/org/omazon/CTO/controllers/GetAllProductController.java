package org.omazon.CTO.controllers;

import java.util.List;

import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;


/**
 * @author ashleeeeee
 *
 */
@ManagedBean( name = "showAllProductController")
@RequestScoped
public class GetAllProductController {

	public GetAllProductController(){};
    @Inject
    private ProductDAO productDAO;

    public List<Product> getProducts() {
        return productDAO.getAll();
    }
    
    public String addOrder(){
    	return "products";
    }
}
