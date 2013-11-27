package org.omazon.CTO.DAO.impl;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.omazon.CTO.DAO.interfaces.ProductDAO;
import org.omazon.CTO.entities.Product;
/**
 * @author ashleeeeee
 *
 */
//@Repository
public class ProductDAOImpl extends GenericDAO<Product> implements ProductDAO {
	 @Override
	    public Product getBySurname(String name) {
	        return (Product) getSession().createCriteria(Product.class).add(Restrictions.eqProperty("name", name)).uniqueResult();
	    }

	    @Override
	    public Product getCustomerByLogin(String login, String password) {
	        Query query = getSession().createQuery("SELECT * FROM product WHERE userLogin = :login AND password = :pass")
	                .setParameter("login", login)
	                .setParameter("pass", password);

	        return (Product) query.uniqueResult();
	    }
}
