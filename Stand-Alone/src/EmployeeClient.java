package src;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.omazon.CTO.entities.Employee;
import org.omazon.CTO.entities.Product;
import org.omazon.CTO.remote.interfaces.EmployeeRemoteInter;
import org.omazon.CTO.remote.interfaces.LoginRemoteInter;
import org.omazon.CTO.remote.interfaces.ProductRemoteInter;

public class EmployeeClient {
	
	private Context ctx;
	private ProductRemoteInter allProducts;
	private LoginRemoteInter login;
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
        } catch (Exception e) {
        	System.out.println(e);
        }

	}
	
	public void login(String loginname, String psw) {
		login.setUserLogin(loginname);
		login.setPassword(psw);
		System.out.println(login.doUserLogin());
	}
	
	public String getProducts() {
		String ap = "";
	    for (Product product: allProducts.getProducts()) {
	        	ap += product.getName() + "\n";
	        }
		return ap;
	}
	
	public void addProduct(String name, String desc, String price) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(desc);
		product.setPrice(Long.parseLong(price));
		allProducts.setProduct(product);
		allProducts.doAdd();
	}
	
	public Employee getEmployee() {
		return login.getEmployee();
	}
	
	public void updateEmployee(String name, String surname, String psw, String username) {
		Employee em = login.getEmployee();
		em.setName(name);
		em.setPassword(psw);
		em.setSurname(surname);
		em.setUserLogin(username);
		login.setEmployee(em);
		employee.update();
	}

}
