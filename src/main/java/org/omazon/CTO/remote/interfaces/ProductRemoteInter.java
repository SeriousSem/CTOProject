package org.omazon.CTO.remote.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;


@Remote
public interface ProductRemoteInter extends Serializable {
	
	public List<List<String>> getProducts();

    public long getProductId();

    public void setProductId(long productId);
    
    public void doAdd();

    public List<String> getProduct();

    public void setProduct(List<String> product);
    
    public void update();
    
    public List<String> getProductById(long productId);

}
