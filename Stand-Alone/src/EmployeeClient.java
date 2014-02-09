package src;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.omazon.CTO.entities.Employee;
import org.omazon.CTO.entities.Order;
import org.omazon.CTO.entities.Product;
import org.omazon.CTO.remote.interfaces.EmployeeRemoteInter;
import org.omazon.CTO.remote.interfaces.LoginRemoteInter;
import org.omazon.CTO.remote.interfaces.OrderRemoteInter;
import org.omazon.CTO.remote.interfaces.ProductRemoteInter;

public class EmployeeClient {
	
	private Context ctx;
	private ProductRemoteInter allProducts;
	private LoginRemoteInter login;
	private OrderRemoteInter orders;
	
	private EmployeeRemoteInter employee;

	public EmployeeClient() {
		try {
            Properties jndiProps = new Properties();
            jndiProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
            jndiProps.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            jndiProps.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            jndiProps.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            jndiProps.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            ctx = new InitialContext(jndiProps);
//            NamingEnumeration<NameClassPair> list = ctx.list("");
//            while (list.hasMore()) {
//              System.out.println(list.next().getName());
//            }
    		allProducts = (ProductRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.ProductRemoteInter");
    		login = (LoginRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.LoginRemoteInter");
    		employee = (EmployeeRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.EmployeeRemoteInter");
    		orders = (OrderRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.OrderRemoteInter");
        } catch (Exception e) {
        	System.out.println(e);
        }

	}
	
	public void login(String loginname, String psw) {
		login.setUserLogin(loginname);
		login.setPassword(psw);
		System.out.println(login.doUserLogin());
		employee.setEmployee(login.getEmployee());
	}
	
	public List<Product> getProducts() {
		return allProducts.getProducts();
	}
	
	public Product getProduct(int productId) {
		allProducts.setProduct(allProducts.getProductById(productId));
		return allProducts.getProduct();
	}
	
	public void addProduct(String name, String desc, String price) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(desc);
		product.setPrice(Long.parseLong(price));
		allProducts.setProduct(product);
		allProducts.doAdd();
	}
	
	public int productsCount() {
//		System.out.println(allProducts.getProducts().size());
		return allProducts.getProducts().size();
	}
	
	public void updateProduct(String name, String desc, String price) {
		Product product = allProducts.getProduct();
		product.setName(name);
		product.setDescription(desc);
		product.setPrice(Long.parseLong(price));
		allProducts.setProduct(product);
		allProducts.update();
	}
	
	public Employee getEmployee() {
		return employee.getEmployee();
	}
	
	public void updateEmployee(String name, String surname, String psw, String username) {
		Employee em = employee.getEmployee();
		em.setName(name);
		em.setPassword(psw);
		em.setSurname(surname);
		em.setUserLogin(username);
		employee.setEmployee(em);
		employee.update();
	}
	
	public List<Order> getOrders() {
		return orders.getOrders();
	}
	
	public int ordersCount() {
		return orders.getOrders().size();
	}

}
