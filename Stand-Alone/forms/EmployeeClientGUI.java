package forms;

import java.awt.Component;
import java.awt.EventQueue;




import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

import src.EmployeeClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class EmployeeClientGUI extends JFrame {

	private EmployeeClient emClient = new EmployeeClient();

	private JMenuBar menuBar = new JMenuBar();
	private JPanel contentPane;
	private JTextPane showProductsPanel = new JTextPane();
	private JPanel employeePanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel newProductPanel = new JPanel();
	private JPanel editEmpanel = new JPanel();
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeClientGUI frame = new EmployeeClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void disableAll() {
		showProductsPanel.setVisible(false);
		newProductPanel.setVisible(false);
		employeePanel.setVisible(false);
		loginPanel.setVisible(false);
		editEmpanel.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public EmployeeClientGUI() {
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
				emUpdateNameField.setText(emClient.getEmployee().getName());
				emUpdateSurnameField.setText(emClient.getEmployee().getSurname());
				emUpdatePswField.setText(emClient.getEmployee().getPassword());
				emUpdateUsernameField.setText(emClient.getEmployee().getUserLogin());
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
				showProductsPanel.setVisible(true);
				showProductsPanel.setText(emClient.getProducts());
			}
		});
		mnProducts.add(mntmShowAll);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		newProductPanel.setBounds(10, 11, 756, 497);
		newProductPanel.setVisible(false);
		
		
		employeePanel.setBounds(10, 11, 756, 497);
		employeePanel.setVisible(false);
		
		
		editEmpanel.setBounds(10, 11, 756, 497);
		editEmpanel.setVisible(false);
		contentPane.add(editEmpanel);
		editEmpanel.setLayout(null);
		
		JButton emUpdatebtn = new JButton("Update");
		emUpdatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emClient.updateEmployee(emUpdateNameField.getText(), emUpdateSurnameField.getText(), String.valueOf(emUpdatePswField.getPassword()), emUpdateUsernameField.getText());
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
		contentPane.add(employeePanel);
		employeePanel.setLayout(null);
		
		JButton emSave = new JButton("Save");
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
				emClient.login(liNameField.getText(), psw);
				menuBar.setVisible(true);
			}
		});
		liButton.setBounds(95, 91, 89, 23);
		loginPanel.add(liButton);
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
				emClient.addProduct(nPNameField.getText(), nPDescrField.getText(), nPPriceField.getText());
			}
		});
		nPSave.setBounds(10, 141, 89, 23);
		newProductPanel.add(nPSave);
		
		
		showProductsPanel.setEditable(false);
		showProductsPanel.setBounds(10, 11, 756, 497);
		showProductsPanel.setVisible(false);
		contentPane.add(showProductsPanel);
	}
}
