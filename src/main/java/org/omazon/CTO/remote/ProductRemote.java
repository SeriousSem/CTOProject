package org.omazon.CTO.remote;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Product;
import org.omazon.CTO.remote.interfaces.ProductRemoteInter;


@Stateless
public class ProductRemote implements ProductRemoteInter {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public Product product;

	@Inject
    private ProductDAO productDAO;

    private long productId;

    @Override
    public List<List<String>> getProducts() {
         List<Product> products = productDAO.getAll();
         List<List<String>> returnList = new ArrayList<List<String>>();
         for (Product product : products) {
        	 List<String> act = new ArrayList<String>();
        	 act.add(String.valueOf(product.getProductId()));
        	 act.add(product.getName());
        	 act.add(product.getDescription());
        	 act.add(String.valueOf(product.getPrice()));
        	 returnList.add(act);
         }
         return returnList;
    }

    @Override
    public long getProductId() {
        return productId;
    }

    @Override
    public void setProductId(long productId) {
        this.productId = productId;
    }
    
    @Override
    public void doAdd() {
        productDAO.save(product);
    }

    @Override
    public List<String> getProduct() {
    	List<String> pr = new ArrayList<String>();
    	pr.add(String.valueOf(product.getProductId()));
    	pr.add(product.getName());
    	pr.add(product.getDescription());
    	pr.add(String.valueOf(product.getPrice()));
        return pr;
    }
    
    @Override
    public List<String> getProductById(long productId) {
    	product = productDAO.getById(productId);
    	List<String> pr = new ArrayList<String>();
    	pr.add(String.valueOf(product.getProductId()));
    	pr.add(product.getName());
    	pr.add(product.getDescription());
    	pr.add(String.valueOf(product.getPrice()));
        return pr;
    }

    @Override
    public void setProduct(List<String> product) {
		Product pr = new Product();
    	if (product.size() == 4) {

    		pr.setProductId(Long.parseLong(product.get(0)));
    		pr.setName(product.get(1));
    		pr.setDescription(product.get(2));
    		pr.setPrice(Long.parseLong(product.get(3)));
    	}
    	else {
    		pr.setName(product.get(0));
    		pr.setDescription(product.get(1));
    		pr.setPrice(Long.parseLong(product.get(2)));
    	}
    	this.product = pr;
    }
    
    @Override
    public void update() {
    	productDAO.merge(product);
    }
}
