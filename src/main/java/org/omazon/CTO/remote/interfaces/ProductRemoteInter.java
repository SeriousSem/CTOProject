package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import org.omazon.CTO.entities.Product;

@Remote
public interface ProductRemoteInter extends Serializable {
	
	public List<Product> getProducts();

    public String goToEdit(long productId);

    public long getProductId();

    public void setProductId(long productId);
    
    public void doAdd();

    public Product getProduct();

    public void setProduct(Product product);

}
