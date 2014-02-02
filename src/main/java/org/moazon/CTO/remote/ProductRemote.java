package org.moazon.CTO.remote;

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
    public List<Product> getProducts() {
        return productDAO.getAll();
    }

    @Override
    public String goToEdit(long productId) {
        setProductId(productId);
        return "editProduct";
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
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }
}
