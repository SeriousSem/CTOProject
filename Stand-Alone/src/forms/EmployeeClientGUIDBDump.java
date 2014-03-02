package src.forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import src.org.omazon.remote.src.EmployeeClient;
import src.org.omazon.remote.src.EmployeeClientSqlDump;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.omazon.CTO.enums.Status;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EmployeeClientGUIDBDump extends JFrame {

//	private EmployeeClient emClientDump = new EmployeeClient();
	private EmployeeClientSqlDump emClientDump = new EmployeeClientSqlDump();

	private JMenuBar menuBar = new JMenuBar();
	private JPanel contentPane;
	private JTextPane showProductPanel = new JTextPane();
	private JPanel employeePanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel newProductPanel = new JPanel();
	private JPanel editEmpanel = new JPanel();
	private JPanel showProductsPanel = new JPanel();
	private JPanel editProductPanel = new JPanel();
	private JPanel showOrdersPanel = new JPanel();
	
	private JTextField nPNameField;
	private JTextField nPDescrField;
	private JTextField nPPriceField;

	private JTextField emNameField;
	private JTextField emSurnameField;
	private JTextField emUsernameField;
	private JPasswordField emPswField;
	private JTextField liNameField;
	private JPasswordField liPswField;
	private JPasswordField emUpdatePswField;
	private JTextField emUpdateUsernameField;
	private JTextField emUpdateNameField;
	private JTextField emUpdateSurnameField;
	private JTable showProcutsTable;
	private Vector<String> productColumns = new Vector<String>();
	private Vector<Vector<Object>> productTableData = new Vector<Vector<Object>>();
	
	private List<List<String>> products;
	private List<List<String>> orders;
	
	private JTextField productIDField;
	private JTextField editProductName;
	private JTextField editProductDesc;
	private JTextField editProductPrice;
	
	private JTable showOrdersTable;
	private Vector<String> ordersColumns = new Vector<String>();
	private Vector<Vector<Object>> ordersTableData = new Vector<Vector<Object>>();
	private JTextField editOrderField;
	
	private JComboBox selectStatusBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeClientGUIDBDump frame = new EmployeeClientGUIDBDump();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void disableAll() {
		showProductPanel.setVisible(false);
		showProductsPanel.setVisible(false);
		newProductPanel.setVisible(false);
		employeePanel.setVisible(false);
		loginPanel.setVisible(false);
		editEmpanel.setVisible(false);
		editProductPanel.setVisible(false);
		showOrdersPanel.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public EmployeeClientGUIDBDump() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				emClientDump.closeConnection();
			}
		});
		
		productColumns.add("ID");
		productColumns.add("Name");
		productColumns.add("Description");
		productColumns.add("Price");
		
		ordersColumns.add("ID");
		ordersColumns.add("Customer");
		ordersColumns.add("Status");
		ordersColumns.add("ExcDesc");
		ordersColumns.add("Lat");
		ordersColumns.add("Long");
		ordersColumns.add("ShipmentID");
		ordersColumns.add("TruckID");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 578);
		
		menuBar.setVisible(false);
		setJMenuBar(menuBar);
		
		JMenu mnEmployees = new JMenu("Employees");
		menuBar.add(mnEmployees);
		
		JMenuItem mntmAddNewEmployee = new JMenuItem("Add new Employee");
		mntmAddNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAll();
				employeePanel.setVisible(true);
			}
		});
		mnEmployees.add(mntmAddNewEmployee);
		
		JMenuItem mntmEditMyData = new JMenuItem("Edit my Data");
		mntmEditMyData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAll();
				editEmpanel.setVisible(true);
				emUpdateNameField.setText(emClientDump.getEmployee().get(1));
				emUpdateSurnameField.setText(emClientDump.getEmployee().get(2));
				emUpdatePswField.setText(emClientDump.getEmployee().get(3));
				emUpdateUsernameField.setText(emClientDump.getEmployee().get(4));
			}
		});
		mnEmployees.add(mntmEditMyData);
		
		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);
		
		JMenuItem mntmAddNew = new JMenuItem("Add New");
		mntmAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAll();
				newProductPanel.setVisible(true);
			}
		});
		mnProducts.add(mntmAddNew);
		
		JMenuItem mntmShowAll = new JMenuItem("Show All");
		mntmShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAll();
				products = emClientDump.getProducts();
				DefaultTableModel model = (DefaultTableModel) showProcutsTable.getModel();
				int rowCount=model.getRowCount();
				for (int i = 0;i<rowCount;i++) {
					model.removeRow(0);
				}
				Vector<Object> columnTitle = new Vector<Object>();
				columnTitle.add("ID");
				columnTitle.add("Name");
				columnTitle.add("Description");
				columnTitle.add("Price");
				productTableData.add(columnTitle);
				for (int i=0;i<emClientDump.productsCount();i++) {
					List<String> p = products.get(i);
					Vector<Object> data = new Vector<Object>();
					data.add(p.get(0));
					data.add(p.get(1));
					data.add(p.get(2));
					data.add(p.get(3));
					productTableData.add(data);
				}
				showProductsPanel.setVisible(true);
			}
		});
		mnProducts.add(mntmShowAll);
		
		JMenu mnOrders = new JMenu("Orders");
		menuBar.add(mnOrders);
		
		JMenuItem mntmShowAllOrders = new JMenuItem("show all");
		mntmShowAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAll();
				
				orders = emClientDump.getOrders();
				DefaultTableModel model = (DefaultTableModel) showOrdersTable.getModel();
				int rowCount=model.getRowCount();
				for (int i = 0;i<rowCount;i++) {
					model.removeRow(0);
				}
				Vector<Object> columnTitle = new Vector<Object>();
				columnTitle.add("ID");
				columnTitle.add("Customer Username");
				columnTitle.add("Status");
				columnTitle.add("Exception");
				columnTitle.add("Lat");
				columnTitle.add("Long");
				columnTitle.add("Shipment ID");
				columnTitle.add("Truck ID");
				ordersTableData.add(columnTitle);
				for (int i=0;i<emClientDump.ordersCount();i++) {
					List<String> o = orders.get(i);
					Vector<Object> data = new Vector<Object>();
					data.add(o.get(0));
					data.add(o.get(1)); //TODO add real customer
					data.add(o.get(2));
					if (o.get(3) == null) {
						data.add("");
					}
					else {
						data.add(o.get(3));
					}
					if (o.get(4) == null) {
						data.add("");
					}
					else {
						data.add(o.get(4));
					}
					if (o.get(5) == null) {
						data.add("");
					}
					else {
						data.add(o.get(5));
					}
					data.add(o.get(6));
					data.add(o.get(6));
					ordersTableData.add(data);
				}
				
				showOrdersPanel.setVisible(true);
				
			}
		});
		mnOrders.add(mntmShowAllOrders);
		
		JMenu mnNewMenu = new JMenu("Dump");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("get Dump");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClientDump.closeConnection();
				emClientDump.getDump();
				emClientDump.openConnection();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		newProductPanel.setBounds(10, 11, 756, 497);
		newProductPanel.setVisible(false);
		
		
		editEmpanel.setBounds(10, 11, 756, 497);
		editEmpanel.setVisible(false);
		
		showOrdersPanel.setBounds(10, 11, 756, 497);
		showOrdersPanel.setVisible(false);
		
		editProductPanel.setBounds(10, 11, 756, 497);
		editProductPanel.setVisible(false);
		
		showProductsPanel.setBounds(10, 11, 756, 497);
		showProductsPanel.setVisible(false);
		
		
		employeePanel.setBounds(10, 11, 756, 497);
		employeePanel.setVisible(false);
		
		
		loginPanel.setBounds(10, 11, 756, 497);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel liName = new JLabel("Login:");
		liName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		liName.setBounds(10, 11, 54, 19);
		loginPanel.add(liName);
		
		JLabel liPsw = new JLabel("Password:");
		liPsw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		liPsw.setBounds(10, 41, 71, 19);
		loginPanel.add(liPsw);
		
		liNameField = new JTextField();
		liNameField.setBounds(98, 12, 86, 20);
		loginPanel.add(liNameField);
		liNameField.setColumns(10);
		
		liPswField = new JPasswordField();
		liPswField.setBounds(98, 42, 86, 20);
		loginPanel.add(liPswField);
		
		JButton liButton = new JButton("login");
		liButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psw = String.valueOf(liPswField.getPassword());
				if (emClientDump.login(liNameField.getText(), psw).equals("Login success")) {
					menuBar.setVisible(true);
				}
			}
		});
		liButton.setBounds(95, 91, 89, 23);
		loginPanel.add(liButton);
		
		JButton btnGetDump = new JButton("GetDump");
		btnGetDump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClientDump.closeConnection();
				emClientDump.getDump();
				emClientDump.openConnection();
			}
		});
		btnGetDump.setBounds(28, 429, 89, 23);
		loginPanel.add(btnGetDump);
		
		JLabel lblNewLabel_2 = new JLabel("Klick on first start");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(18, 404, 129, 14);
		loginPanel.add(lblNewLabel_2);
		contentPane.add(employeePanel);
		employeePanel.setLayout(null);
		
		JButton emSave = new JButton("Save");
		emSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClientDump.addEmployee(emNameField.getText(), emSurnameField.getText(), String.valueOf(emPswField.getPassword()), emUsernameField.getText());
			}
		});
		emSave.setBounds(10, 141, 89, 23);
		employeePanel.add(emSave);
		
		emPswField = new JPasswordField();
		emPswField.setBounds(113, 102, 86, 19);
		employeePanel.add(emPswField);
		
		emSurnameField = new JTextField();
		emSurnameField.setBounds(113, 42, 86, 20);
		employeePanel.add(emSurnameField);
		emSurnameField.setColumns(10);
		
		emNameField = new JTextField();
		emNameField.setBounds(113, 12, 86, 20);
		employeePanel.add(emNameField);
		emNameField.setColumns(10);
		
		emUsernameField = new JTextField();
		emUsernameField.setBounds(113, 72, 86, 20);
		employeePanel.add(emUsernameField);
		emUsernameField.setColumns(10);
		
		JLabel emSurname = new JLabel("Surname:");
		emSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emSurname.setBounds(10, 41, 71, 19);
		employeePanel.add(emSurname);
		
		JLabel emPassword = new JLabel("Password:");
		emPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emPassword.setBounds(10, 101, 66, 19);
		employeePanel.add(emPassword);
		
		JLabel emUsername = new JLabel("Username:");
		emUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emUsername.setBounds(10, 71, 77, 19);
		employeePanel.add(emUsername);
		
		JLabel emName = new JLabel("Name:");
		emName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emName.setBounds(10, 11, 57, 19);
		employeePanel.add(emName);
		contentPane.add(showProductsPanel);
		showProductsPanel.setLayout(null);
		
		showProcutsTable = new JTable();
		showProcutsTable.setModel(new DefaultTableModel(
			productTableData,
			productColumns
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		showProcutsTable.setBounds(0, 0, 756, 346);
		showProductsPanel.add(showProcutsTable);
		
		JLabel lblCPID = new JLabel("Choose Product ID");
		lblCPID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCPID.setBounds(10, 378, 137, 31);
		showProductsPanel.add(lblCPID);
		
		productIDField = new JTextField();
		productIDField.setBounds(168, 385, 86, 20);
		showProductsPanel.add(productIDField);
		productIDField.setColumns(10);
		
		JButton editProdcutButton = new JButton("Edit Product");
		editProdcutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int productID = Integer.parseInt(productIDField.getText());
				disableAll();
				List<String> product = emClientDump.getProduct(productID);
				editProductName.setText(product.get(1));
				editProductDesc.setText(product.get(2));
				editProductPrice.setText(product.get(3));
				editProductPanel.setVisible(true);
			}
		});
		editProdcutButton.setBounds(296, 384, 113, 23);
		showProductsPanel.add(editProdcutButton);
		contentPane.add(editProductPanel);
		editProductPanel.setLayout(null);
		
		JLabel label_4 = new JLabel("Name:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(23, 11, 49, 32);
		editProductPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Description:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBounds(23, 54, 84, 19);
		editProductPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Price:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(23, 84, 49, 19);
		editProductPanel.add(label_6);
		
		editProductName = new JTextField();
		editProductName.setColumns(10);
		editProductName.setBounds(136, 19, 86, 20);
		editProductPanel.add(editProductName);
		
		editProductDesc = new JTextField();
		editProductDesc.setColumns(10);
		editProductDesc.setBounds(136, 55, 86, 20);
		editProductPanel.add(editProductDesc);
		
		editProductPrice = new JTextField();
		editProductPrice.setColumns(10);
		editProductPrice.setBounds(136, 85, 86, 20);
		editProductPanel.add(editProductPrice);
		
		JButton editProductButton = new JButton("Update");
		editProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClientDump.updateProduct(editProductName.getText(), editProductDesc.getText(), editProductPrice.getText());
			}
		});
		editProductButton.setBounds(23, 141, 89, 23);
		editProductPanel.add(editProductButton);
		contentPane.add(showOrdersPanel);
		showOrdersPanel.setLayout(null);
		
		showOrdersTable = new JTable();
		showOrdersTable.setModel(new DefaultTableModel(
			ordersTableData,
			ordersColumns
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Long.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		showOrdersTable.setBounds(0, 0, 756, 318);
		showOrdersPanel.add(showOrdersTable);
		
		editOrderField = new JTextField();
		editOrderField.setBounds(101, 359, 86, 20);
		showOrdersPanel.add(editOrderField);
		editOrderField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Order ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(21, 362, 70, 14);
		showOrdersPanel.add(lblNewLabel);
		
		
		selectStatusBox.setModel(new DefaultComboBoxModel(Status.values()));
		selectStatusBox.setBounds(309, 359, 86, 20);
		showOrdersPanel.add(selectStatusBox);
		
		JLabel lblNewLabel_1 = new JLabel("Set Status");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(217, 362, 68, 14);
		showOrdersPanel.add(lblNewLabel_1);
		
		JButton updateOrderBtn = new JButton("Update");
		updateOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClientDump.setOrderById(Long.parseLong(editOrderField.getText()));
				emClientDump.setOrderStatus((Status) selectStatusBox.getSelectedItem());
			}
		});
		updateOrderBtn.setBounds(444, 358, 89, 23);
		showOrdersPanel.add(updateOrderBtn);
		contentPane.add(editEmpanel);
		editEmpanel.setLayout(null);
		
		JButton emUpdatebtn = new JButton("Update");
		emUpdatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClientDump.updateEmployee(emUpdateNameField.getText(), emUpdateSurnameField.getText(), String.valueOf(emUpdatePswField.getPassword()), emUpdateUsernameField.getText());
			}
		});
		emUpdatebtn.setBounds(10, 154, 89, 23);
		editEmpanel.add(emUpdatebtn);
		
		emUpdatePswField = new JPasswordField();
		emUpdatePswField.setBounds(104, 104, 86, 19);
		editEmpanel.add(emUpdatePswField);
		
		emUpdateUsernameField = new JTextField();
		emUpdateUsernameField.setBounds(104, 73, 86, 20);
		editEmpanel.add(emUpdateUsernameField);
		emUpdateUsernameField.setColumns(10);
		
		emUpdateNameField = new JTextField();
		emUpdateNameField.setBounds(104, 12, 86, 20);
		editEmpanel.add(emUpdateNameField);
		emUpdateNameField.setColumns(10);
		
		emUpdateSurnameField = new JTextField();
		emUpdateSurnameField.setBounds(104, 42, 86, 20);
		editEmpanel.add(emUpdateSurnameField);
		emUpdateSurnameField.setColumns(10);
		
		JLabel label = new JLabel("Surname:");
		label.setBounds(10, 41, 71, 19);
		editEmpanel.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setBounds(10, 101, 66, 19);
		editEmpanel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_2 = new JLabel("Username:");
		label_2.setBounds(10, 71, 77, 19);
		editEmpanel.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_3 = new JLabel("Name:");
		label_3.setBounds(10, 11, 57, 19);
		editEmpanel.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(newProductPanel);
		newProductPanel.setLayout(null);
		
		JLabel nPName = new JLabel("Name:");
		nPName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nPName.setBounds(10, 11, 49, 32);
		newProductPanel.add(nPName);
		
		JLabel nPDescription = new JLabel("Description:");
		nPDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nPDescription.setBounds(10, 54, 84, 19);
		newProductPanel.add(nPDescription);
		
		JLabel nPPrice = new JLabel("Price:");
		nPPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nPPrice.setBounds(10, 84, 49, 19);
		newProductPanel.add(nPPrice);
		
		nPNameField = new JTextField();
		nPNameField.setBounds(123, 19, 86, 20);
		newProductPanel.add(nPNameField);
		nPNameField.setColumns(10);
		
		nPDescrField = new JTextField();
		nPDescrField.setBounds(123, 55, 86, 20);
		newProductPanel.add(nPDescrField);
		nPDescrField.setColumns(10);
		
		nPPriceField = new JTextField();
		nPPriceField.setBounds(123, 85, 86, 20);
		newProductPanel.add(nPPriceField);
		nPPriceField.setColumns(10);
		
		JButton nPSave = new JButton("Save");
		nPSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClientDump.addProduct(nPNameField.getText(), nPDescrField.getText(), nPPriceField.getText());
			}
		});
		nPSave.setBounds(10, 141, 89, 23);
		newProductPanel.add(nPSave);
		
		
		showProductPanel.setEditable(false);
		showProductPanel.setBounds(10, 11, 756, 497);
		showProductPanel.setVisible(false);
		contentPane.add(showProductPanel);
	}
}
