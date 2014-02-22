package src.org.omazon.remote.src;

import org.omazon.CTO.enums.Status;
import org.omazon.CTO.remote.interfaces.*;
import src.db.DbConnector;
import src.db.SocketDBDump;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeClient {
	
	private Context ctx;
	private ProductRemoteInter products;
	private LoginRemoteInter login;
	private OrderRemoteInter orders;
	private EmployeeRemoteInter employee;
	private CustomerRemoteInter customer;

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
    		products = (ProductRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.ProductRemoteInter");
    		login = (LoginRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.LoginRemoteInter");
    		employee = (EmployeeRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.EmployeeRemoteInter");
    		customer = (CustomerRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.CustomerRemoteInter");
    		orders = (OrderRemoteInter) ctx.lookup("org.omazon.CTO.remote.interfaces.OrderRemoteInter");

            /**
             * get dump from server and import to local
             */
            File dump = SocketDBDump.getDump();
            if (dump != null){
                DbConnector dbConnector = new DbConnector();
                dbConnector.executeDump(dump.getPath(), "clientDB");
            }


        } catch (Exception e) {
        	System.out.println(e);
        }

	}
	
	public String login(String loginname, String psw) {
		login.setUserLogin(loginname);
		login.setPassword(psw);
		String li = login.doUserLogin();
		employee.setEmployee(login.getEmployee());
		return li;
	}
	
	public List<List<String>> getProducts() {
		return products.getProducts();
	}
	
	public List<String> getProduct(int productId) {
		products.setProduct(products.getProductById(productId));
		return products.getProduct();
	}
	
	public void addProduct(String name, String desc, String price) {
		List<String> product = new ArrayList<String>();
		product.add(name);
		product.add(desc);
		product.add(price);
		products.setProduct(product);
		products.doAdd();
	}
	
	public int productsCount() {
//		System.out.println(allProducts.getProducts().size());
		return products.getProducts().size();
	}
	
	public void updateProduct(String name, String desc, String price) {
		List<String> product = products.getProduct();
		String id = product.get(0);
		product.clear();
		product.add(id);
		product.add(name);
		product.add(desc);
		product.add(price);
		products.setProduct(product);
		products.update();
	}
	
	public List<String> getEmployee() {
		return employee.getEmployee();
	}
	
	public List<String> getCustomerById(long id) {
		return customer.getCustomerById(id);
	}
	
	public String getCustomerUsername(long customerId) {
		return customer.getCustomerById(customerId).get(6);
	}
	
	public long getCustomerId() {
//		orders.setOrder(order);
//		return orders.getCustomerId();
		return 1L;
	}
	
	public void updateEmployee(String name, String surname, String psw, String username) {
		List<String> em = employee.getEmployee();
		String id = em.get(0);
		em.clear();
		em.add(id);
		em.add(name);
		em.add(surname);
		em.add(psw);
		em.add(username);
		employee.setEmployee(em);
		employee.update();
	}
	
	public void addEmployee(String name, String surname, String psw, String username) {
		List<String> em = new ArrayList<String>();
		em.add(name);
		em.add(surname);
		em.add(psw);
		em.add(username);
		employee.setEmployee(em);
		employee.update();
	}
	
	public List<List<String>> getOrders() {
		return orders.getOrders();
	}
	
	public void setOrderById(long orderId) {
		orders.setOrder(orders.getOrderById(orderId));
	}
	
	public int ordersCount() {
		return orders.getOrders().size();
	}
	
	public void setOrderStatus(Status status) {
		orders.setStatus(status);
		orders.update();
	}

}
