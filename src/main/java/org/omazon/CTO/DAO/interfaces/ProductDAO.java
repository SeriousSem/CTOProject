package org.omazon.CTO.DAO.interfaces;

import org.omazon.CTO.entities.Product;

public interface ProductDAO extends IDao<Product> {
	 public Product getBySurname(String name);

	    public Product getCustomerByLogin(String login, String password);
}
