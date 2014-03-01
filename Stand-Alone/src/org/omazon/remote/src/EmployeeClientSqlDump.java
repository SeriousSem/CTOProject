package src.org.omazon.remote.src;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omazon.CTO.enums.Status;

import src.db.DAO.DbConnector;


public class EmployeeClientSqlDump {
	
	// Set database name if you want to
	private String DB_NAME = "ctodbFromDump";
	
	// Change path to the dump file
	private String PATH_TO_DUMP = "Stand-Alone/SQLDump/dump.sql";
	
	private DbConnector dbc;
	
	private int pId = 0;
	private int eId = 0;
	private int oId = 0;
	
	public EmployeeClientSqlDump() {
		
		
		dbc = new DbConnector();
		dbc.createDBIfNotExists(DB_NAME);
		
		
		dbc.executeDump(PATH_TO_DUMP, DB_NAME);
		
		dbc.openConnectionToDB(DB_NAME);
		
	}
	
	public String login(String loginname, String psw) {
		ResultSet rs = dbc.executeQuery("SELECT * FROM employee WHERE (username = '" + loginname + "' AND password = '" + psw +"');");
		try {
			if(rs.first()) {
				eId = (int) rs.getLong("employeeId");
				return "Login success";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public List<List<String>> getProducts() {
		List<List<String>> products = new ArrayList<List<String>>();
		ResultSet rs = dbc.executeQuery("SELECT * FROM product;");
		try {
			while(rs.next()) {
				List<String> product = new ArrayList<String>();
				product.add(rs.getString("productId"));
				product.add(rs.getString("name"));
				product.add(rs.getString("description"));
				product.add(rs.getString("price"));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	public List<String> getProduct(int productId) {
		pId = productId;
		List<String> product = new ArrayList<String>();
		ResultSet rs = dbc.executeQuery("SELECT * FROM product WHERE productId = "+ productId +";");
		try {
			while(rs.next()) {
				product.add(rs.getString("productId"));
				product.add(rs.getString("name"));
				product.add(rs.getString("description"));
				product.add(rs.getString("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	public void addProduct(String name, String desc, String price) {
		dbc.executeUpdate("INSERT INTO product (name, description, price) VALUES ('"+ name + "', '"+ desc + "', " + price + ");");
	}
	
	public int productsCount() {
		return getProducts().size();
	}
	
	public void updateProduct(String name, String desc, String price) {
		dbc.executeUpdate("UPDATE product SET name = '"+ name + "', description = '"+ desc + "', price = " + price + " WHERE productId = " + pId +";");
	}
	
	public List<String> getEmployee() {
		List<String> employee = new ArrayList<String>();
		ResultSet rs = dbc.executeQuery("SELECT * FROM employee WHERE employeeId = "+ eId +";");
		try {
			while(rs.next()) {
				employee.add(rs.getString("employeeId"));
				employee.add(rs.getString("name"));
				employee.add(rs.getString("surname"));
				employee.add(rs.getString("password"));
				employee.add(rs.getString("userLogin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
//	public List<String> getCustomerById(long id) {
//		return customer.getCustomerById(id);
//	}
//	
//	public String getCustomerUsername(long customerId) {
//		return customer.getCustomerById(customerId).get(6);
//	}
//	
	public long getCustomerId() {
//		orders.setOrder(order);
//		return orders.getCustomerId();
		return 1L;
	}
	
	public void updateEmployee(String name, String surname, String psw, String username) {
		dbc.executeUpdate("UPDATE employee SET name = '"+ name + "', surname = '"+ surname + "', password = '" + psw + "', userLogin = '" + username + "' WHERE employeeId = " + eId +";");
	}
	
	public void addEmployee(String name, String surname, String psw, String username) {
		dbc.executeUpdate("INSERT INTO employee (name, surname, password, userLogin) VALUES ('"+ name + "', '"+ surname + "', '" + psw + "', '" + username + "');");
	}
	
	public List<List<String>> getOrders() {
		List<List<String>> orders = new ArrayList<List<String>>();
		ResultSet rs = dbc.executeQuery("SELECT * FROM orders;");
		try {
			while(rs.next()) {
				int custId = (int) rs.getLong("customer_customerId");
				ResultSet customer = dbc.executeQuery("SELECT * FROM customer WHERE customerId = " + custId + ";");
				List<String> order = new ArrayList<String>();
				order.add(rs.getString("orderId"));
				customer.next();
				order.add(customer.getString("userLogin"));
				order.add(rs.getString("status"));
				order.add(rs.getString("exceptionDescription"));
				order.add(rs.getString("latitude"));
				order.add(rs.getString("longitude"));
				order.add(rs.getString("shipmentId"));
				order.add(rs.getString("truckId"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	public void setOrderById(long orderId) {
		oId = (int) orderId;
	}
	
	public int ordersCount() {
		return getOrders().size();
	}
	
	public void setOrderStatus(Status status) {
		dbc.executeUpdate("UPDATE orders SET status = '"+ status.name() + "' WHERE orderId = " + oId +";");
	}

}
