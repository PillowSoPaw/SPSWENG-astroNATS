package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.JTable;

import model.Consumable;
import model.Employee;
import model.Product;
import model.Service;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import controller.AddServicesController;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class AddServicesGUI extends JFrame implements ActionListener, FocusListener, MouseListener, KeyListener
{
	private AddServicesController addServicesController;
	private AddTransactionGUI addTransactionGUI;
	
	private JPanel employeePanel;
	private JPanel productPanel;
	private JPanel servicePanel;
	private JPanel addDiscountPanel;
	private Border blackline;
	private JButton cancelButton;
	private JButton addServiceButton;
	private JButton updateServiceButton;
	private JScrollPane servicesScrollPane;
	private JTextField customerNameTextField;
	private JTextField quantityTextField;
	private JTextField discountTextField;
	private JComboBox<String> serviceComboBox;
	private JComboBox<String> productComboBox;
	private JComboBox<String> seniorComboBox;
	private JComboBox<String> juniorComboBox;
	private JButton finishButton;
	private JButton addProductButton;
	private JButton setSeniorButton;
	private JButton addJuniorButton;
	private JScrollPane productsScrollPane;
	private JScrollPane juniorStaffsScrollPane;
	private JLabel productLabel;
	private JLabel customerNameLabel;
	private JLabel servicesAddedLabel;
	private JLabel seniorStaffLabel;
	private JLabel juniorStaffsLabel;
	private JLabel quantityLabel;
	private JLabel serviceLabel;
	private JLabel productsUsedLabel;
	private JLabel employeesAssignedLabel;
	private JLabel newServiceLabel;
	private JTable productTable;
	private JTable servicesTable;
	private JLabel nameWarningLabel;
	private JLabel quantityWarningLabel;
	private JLabel instructionsLabel;
	private JLabel discountLabel;
	private JLabel percentLabel;
	private JLabel editInstructionsLabel;
	private JLabel addDiscountLabel;
	private JLabel discountWarningLabel;
	private DefaultTableModel productTableModel;
	private DefaultTableModel serviceTableModel;
	private DefaultComboBoxModel<String> productComboBoxModel;
	private DefaultComboBoxModel<String> serviceComboBoxModel;
	private DefaultComboBoxModel<String> seniorComboBoxModel;
	private DefaultComboBoxModel<String> juniorComboBoxModel;
	
//	private Iterator<Service> iServices;
//	private Iterator<Consumable> iConsumables;
//	private Iterator<Employee> iSeniorEmployees;
//	private Iterator<Employee> iJuniorEmployees;
	
	private ArrayList<Object[]> servicesAvailed;
	private ArrayList<ArrayList<Object[]>> consumablesUsed;
	private ArrayList<Object[]> tempConsumablesUsed;
	private ArrayList<Object[]> editConsumablesUsed;
	private String[] productOptions;
	private String[] serviceOptions;
	private String[] seniorEmployees;
	private String[] juniorEmployees;
	private double[] servicePrice;
	private int clientIndex;
	private boolean bCustomerNameInDB = false;
	private boolean bValidQuantityInput = false;
	private boolean bEmployeeAdded = false;
	private boolean bIsEditing = false;
	private boolean bSeniorSelected = false;
	private boolean bJuniorSelected = false;
	private boolean bHasDiscount = false;
	
	public AddServicesGUI( AddServicesController addServicesController, AddTransactionGUI addTransactionGUI )
	{
		this.addServicesController = addServicesController;
		this.addTransactionGUI = addTransactionGUI;
		
		servicesAvailed = new ArrayList<>(0);
		consumablesUsed = new ArrayList<>(0);
		tempConsumablesUsed = new ArrayList<>(0);
		editConsumablesUsed = new ArrayList<>(0);
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(189, 183, 107));
		//this.setTitle("Add Services");
		this.setLocation(325, 100);
		this.setVisible(true);
		this.setResizable(false);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(741, 600);
				
		servicePanel = new JPanel();
		servicePanel.setBackground(new Color(189, 183, 107));
		servicePanel.setBounds(10, 11, 424, 90);
		servicePanel.setLayout(null);
		servicePanel.setBorder(blackline);
		getContentPane().add(servicePanel);
		
		customerNameLabel = new JLabel("Customer Name:");
		customerNameLabel.setForeground(Color.WHITE);
		customerNameLabel.setBounds(10, 11, 98, 14);
		servicePanel.add(customerNameLabel);
		
		customerNameTextField = new JTextField();
		customerNameTextField.setText("Enter customer name here...");
		customerNameTextField.setBounds(116, 8, 196, 19);
		customerNameTextField.setColumns(10);
		customerNameTextField.addFocusListener(this);
		customerNameTextField.addKeyListener(this);
		servicePanel.add(customerNameTextField);
		
		nameWarningLabel = new JLabel("Customer Name not in the database.");
		nameWarningLabel.setBounds(116, 34, 245, 14);
		nameWarningLabel.setVisible(false);
		servicePanel.add(nameWarningLabel);
		
		serviceComboBox = new JComboBox<>();
		serviceComboBox.setBounds(116, 59, 196, 20);
		servicePanel.add(serviceComboBox);
		
		serviceLabel = new JLabel("Service:");
		serviceLabel.setForeground(Color.WHITE);
		serviceLabel.setBounds(50, 62, 46, 14);
		servicePanel.add(serviceLabel);
		
		productsUsedLabel = new JLabel("Products Used");
		productsUsedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		productsUsedLabel.setBounds(10, 104, 97, 14);
		getContentPane().add(productsUsedLabel);

		productPanel = new JPanel();
		productPanel.setBackground(new Color(189, 183, 107));
		productPanel.setBounds(10, 120, 424, 142);
		productPanel.setLayout(null);
		productPanel.setBorder(blackline);
		getContentPane().add(productPanel);
		
		productTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		productTableModel.addColumn("Product");
		productTableModel.addColumn("Quantity");
		
		productTable = new JTable(productTableModel);
		productTable.getTableHeader().setReorderingAllowed(false);
		productTable.getTableHeader().setResizingAllowed(false);
		productsScrollPane = new JScrollPane(productTable);
		productsScrollPane.setBounds(187, 11, 227, 120);
		productsScrollPane.setBorder(blackline);
		productPanel.add(productsScrollPane);
		
		productComboBox = new JComboBox<>();
		productComboBox.setBounds(66, 11, 114, 20);
		productPanel.add(productComboBox);
		
		productLabel = new JLabel("Product:");
		productLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		productLabel.setForeground(Color.WHITE);
		productLabel.setBounds(10, 14, 46, 14);
		productPanel.add(productLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(66, 43, 111, 20);
		quantityTextField.setText("Positive Integer");
		quantityTextField.setColumns(10);
		quantityTextField.addFocusListener(this);
		quantityTextField.addKeyListener(this);
		productPanel.add(quantityTextField);
		
		quantityLabel = new JLabel("Quantity:");
		quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		quantityLabel.setForeground(Color.WHITE);
		quantityLabel.setBounds(7, 46, 46, 14);
		productPanel.add(quantityLabel);
		
		addProductButton = new JButton("Add");
		addProductButton.addActionListener(this);
		addProductButton.setBounds(66, 105, 90, 23);
		addProductButton.setEnabled(false);
		productPanel.add(addProductButton);
		
		quantityWarningLabel = new JLabel("Please input positive integer");
		quantityWarningLabel.setBounds(10, 74, 167, 14);
		quantityWarningLabel.setVisible(false);
		productPanel.add(quantityWarningLabel);
		
		employeesAssignedLabel = new JLabel("Employees assigned");
		employeesAssignedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		employeesAssignedLabel.setBounds(10, 266, 138, 14);
		getContentPane().add(employeesAssignedLabel);
		
		employeePanel = new JPanel();
		employeePanel.setBackground(new Color(189, 183, 107));
		employeePanel.setBounds(10, 283, 424, 141);
		employeePanel.setLayout(null);
		employeePanel.setBorder(blackline);
		getContentPane().add(employeePanel);
		
		juniorStaffsScrollPane = new JScrollPane();
		juniorStaffsScrollPane.setBounds(238, 42, 176, 88);
		juniorStaffsScrollPane.setBorder(blackline);
		employeePanel.add(juniorStaffsScrollPane);
		
		seniorStaffLabel = new JLabel("Senior Staff: <none>");
		seniorStaffLabel.setForeground(Color.WHITE);
		seniorStaffLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		seniorStaffLabel.setBounds(238, 11, 217, 14);
		employeePanel.add(seniorStaffLabel);
		
		juniorStaffsLabel = new JLabel("Junior Staff: <none>");
		juniorStaffsLabel.setForeground(Color.WHITE);
		juniorStaffsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		juniorStaffsLabel.setBounds(237, 25, 218, 14);
		employeePanel.add(juniorStaffsLabel);
		
		seniorComboBox = new JComboBox<>();
		seniorComboBox.setBounds(10, 43, 117, 20);
		seniorComboBox.addActionListener(this);
		employeePanel.add(seniorComboBox);
		
		setSeniorButton = new JButton("Set Senior");
		setSeniorButton.setFont(new Font("Tahoma", Font.BOLD, 9));
		setSeniorButton.setBounds(137, 42, 94, 23);
		setSeniorButton.addActionListener(this);
		employeePanel.add(setSeniorButton);
		
		juniorComboBox = new JComboBox<>();
		juniorComboBox.setBounds(10, 91, 117, 20);
		juniorComboBox.addActionListener(this);
		employeePanel.add(juniorComboBox);
		
		addJuniorButton = new JButton("Add Junior");
		addJuniorButton.setFont(new Font("Tahoma", Font.BOLD, 9));
		addJuniorButton.setBounds(137, 90, 94, 23);
		addJuniorButton.addActionListener(this);
		employeePanel.add(addJuniorButton);
		
		newServiceLabel = new JLabel("+ New Service");
		newServiceLabel.setBounds(10, 496, 100, 14);
		//newServiceLabel.setBounds(10, 505, 100, 14);
		newServiceLabel.addMouseListener(this);
		getContentPane().add(newServiceLabel);
		
		addServiceButton = new JButton("Add Service");
		addServiceButton.setForeground(Color.WHITE);
		addServiceButton.setBackground(new Color(34, 139, 34));
		addServiceButton.setBounds(306, 492, 127, 23);
		//addServiceButton.setBounds(307, 501, 127, 23);
		addServiceButton.setEnabled(false);
		addServiceButton.addActionListener(this);
		getContentPane().add(addServiceButton);
		
		updateServiceButton = new JButton("Update Service");
		updateServiceButton.setForeground(Color.WHITE);
		updateServiceButton.setBackground(new Color(34, 139, 34));
		updateServiceButton.setBounds(259, 492, 175, 23);
		//updateServiceButton.setBounds(259, 501, 175, 23);
		updateServiceButton.setVisible(false);
		updateServiceButton.addActionListener(this);
		getContentPane().add(updateServiceButton);
		
		servicesAddedLabel = new JLabel("Services Added");
		servicesAddedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		servicesAddedLabel.setBounds(536, 11, 97, 14);
		getContentPane().add(servicesAddedLabel);
		
		serviceTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		serviceTableModel.addColumn("Service");
		serviceTableModel.addColumn("Price");
		
		servicesTable = new JTable(serviceTableModel);
		servicesTable.getTableHeader().setReorderingAllowed(false);
		servicesTable.getTableHeader().setResizingAllowed(false);
		servicesScrollPane = new JScrollPane(servicesTable);
		servicesScrollPane.setBackground(new Color(189, 183, 107));
		servicesScrollPane.setBorder(blackline);
		servicesScrollPane.setBounds(444, 36, 271, 454);
		getContentPane().add(servicesScrollPane);
		
		ListSelectionModel listSelectionModel = servicesTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{ 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if(!lsm.isSelectionEmpty())
				{
					reviewServiceDetails();
				}
			};
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(514, 519, 97, 23);
		cancelButton.addActionListener(this);
		getContentPane().add(cancelButton);
		
		finishButton = new JButton("Finish");
		finishButton.setForeground(Color.WHITE);
		finishButton.setBackground(new Color(34, 139, 34));
		finishButton.setBounds(615, 519, 100, 23);
		finishButton.setEnabled(false);
		finishButton.addActionListener(this);
		getContentPane().add(finishButton);
		
		instructionsLabel = new JLabel("Input client name, add at least 1 product and assign at least 1 employee to add service. Click new service to clear form.");
		instructionsLabel.setBounds(10, 546, 705, 14);
		getContentPane().add(instructionsLabel);
		
		editInstructionsLabel = new JLabel("Click on items on the table to edit.");
		editInstructionsLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		editInstructionsLabel.setBounds(444, 501, 271, 14);
		getContentPane().add(editInstructionsLabel);
		
		addDiscountPanel = new JPanel();
		addDiscountPanel.setBackground(new Color(189, 183, 107));
		addDiscountPanel.setBounds(10, 447, 424, 43);
		addDiscountPanel.setLayout(null);
		addDiscountPanel.setBorder(blackline);
		getContentPane().add(addDiscountPanel);
		
		discountTextField = new JTextField();
		discountTextField.setBounds(208, 11, 44, 20);
		discountTextField.setText(Integer.toString(0));
		discountTextField.addKeyListener(this);
		discountTextField.addFocusListener(this);
		addDiscountPanel.add(discountTextField);
		discountTextField.setColumns(10);
		
		discountLabel = new JLabel("Discount (in %) :");
		discountLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		discountLabel.setBounds(84, 14, 114, 14);
		addDiscountPanel.add(discountLabel);
		
		percentLabel = new JLabel("%");
		percentLabel.setBounds(258, 14, 25, 14);
		addDiscountPanel.add(percentLabel);
		
		discountWarningLabel = new JLabel("Input 0 - 100 only");
		discountWarningLabel.setBounds(300, 14, 114, 14);
		discountWarningLabel.setVisible(false);
		addDiscountPanel.add(discountWarningLabel);
		
		addDiscountLabel = new JLabel("Add Discount");
		addDiscountLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		addDiscountLabel.setBounds(10, 429, 138, 14);
		getContentPane().add(addDiscountLabel);
		
	}
	
//	void itemStateChanged(ItemEvent e)
//	{
//		if( e.getSource() == addDiscountCheckBox )
//		{
//			boolean selected = addDiscountCheckBox.getModel().isSelected();
//			System.out.println(selected);
//			if(selected == true)
//			{
//				addDiscountPanel.setVisible(true);
//				addDiscountLabel.setVisible(true);
//				newServiceLabel.setBounds(10, 505, 100, 14);
//				addServiceButton.setBounds(307, 501, 127, 23);
//				updateServiceButton.setBounds(259, 501, 175, 23);
//			}
//			else if(selected == true)
//			{
//				addDiscountPanel.setVisible(false);
//				addDiscountLabel.setVisible(false);
//				newServiceLabel.setBounds(10, 450, 100, 14);
//				addServiceButton.setBounds(307, 454, 127, 23);
//				updateServiceButton.setBounds(259, 454, 175, 23);
//			}
//			
//		}
//		
//	}
	
	//ACTION LISTENER FUNCTION
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		double price = 0;
		
		if( e.getSource() == addProductButton )
		{
			int temp = 0;
			if( checkQuantityInput() == true )
			{
				temp = Integer.parseInt(quantityTextField.getText());
				if( checkProductQuantity(productOptions[productComboBox.getSelectedIndex()], temp) == true && bValidQuantityInput == true )
				{
					addToProductTable(productOptions[productComboBox.getSelectedIndex()], temp);
					if( bIsEditing == false )
					{
						tempConsumablesUsed.add(new Object[] {productOptions[productComboBox.getSelectedIndex()], temp});
					}
					else if( bIsEditing == true )
					{
						editConsumablesUsed.add(new Object[] {productOptions[productComboBox.getSelectedIndex()], temp});
					}
					productComboBox.setSelectedIndex(0);
					quantityTextField.setText("Positive Integer");
					quantityTextField.setBackground(Color.WHITE);
					bValidQuantityInput = false;
					addProductButton.setEnabled(false);
				}
				temp = 0;
			}
		}
		else if( e.getSource() == setSeniorButton || e.getSource() == addJuniorButton )
		{
			if( e.getSource() == setSeniorButton && seniorComboBox.getSelectedIndex() == 0 )
			{
				bSeniorSelected = false;
				seniorStaffLabel.setText("Senior Staff: " + "None");
			}
			else if ( e.getSource() == addJuniorButton && juniorComboBox.getSelectedIndex() == 0 )
			{
				bJuniorSelected = false;
				juniorStaffsLabel.setText("Junior Staff: " + "None");
			}
			else if( e.getSource() == setSeniorButton && seniorComboBox.getSelectedIndex() != 0)
			{
				bSeniorSelected = true;
				seniorStaffLabel.setText("Senior Staff: " + seniorEmployees[seniorComboBox.getSelectedIndex()]);
			}
			else if( e.getSource() == addJuniorButton && juniorComboBox.getSelectedIndex() != 0 )
			{
				bJuniorSelected = true;
				juniorStaffsLabel.setText("Junior Staff: " + juniorEmployees[juniorComboBox.getSelectedIndex()]);
			}
			
			if( bSeniorSelected == true || bJuniorSelected == true )
				bEmployeeAdded = true;
			else bEmployeeAdded = false;
		}
		else if( e.getSource() == addServiceButton )
		{	
			price = servicePrice[serviceComboBox.getSelectedIndex()];
			
			if( bHasDiscount == true )
				price *= ( 1 - (Double.parseDouble(discountTextField.getText()) / 100) );
			
			addToServiceTable(addTransactionGUI.getClients().get(clientIndex), serviceOptions[serviceComboBox.getSelectedIndex()], 
							 seniorEmployees[seniorComboBox.getSelectedIndex()], juniorEmployees[juniorComboBox.getSelectedIndex()],
							 price);
			
			consumablesUsed.add(tempConsumablesUsed);
			resetForm();
		}
		else if( e.getSource() == updateServiceButton )
		{
			updateServiceDetails();
		}
		else if( e.getSource() == cancelButton )
		{
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else if( e.getSource() == finishButton )
		{
			this.addTransactionGUI.addService(servicesAvailed);
			this.addTransactionGUI.addConsumables(consumablesUsed);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
		if( servicesAvailed.size() > 0 )
			finishButton.setEnabled(true);
		else finishButton.setEnabled(false);
		
		if( bCustomerNameInDB == true && bEmployeeAdded == true && (tempConsumablesUsed.size() > 0 || editConsumablesUsed.size() > 0) )
		{
			addServiceButton.setEnabled(true);
			updateServiceButton.setEnabled(true);
		}
		else
		{
			addServiceButton.setEnabled(false);
			updateServiceButton.setEnabled(false);
		}
	}
	
	//FOCUS LISTENER FUNCTIONS
	@Override
	public void focusGained(FocusEvent e)
	{
		if( e.getSource() == customerNameTextField )
		{
			if( customerNameTextField.getText().equals("Enter customer name here...") )		
				customerNameTextField.setText("");
		}
		else if( e.getSource() == quantityTextField )
		{
			if( quantityTextField.getText().equals("Positive Integer") )
				quantityTextField.setText("");
		}
		else if( e.getSource() == discountTextField )
		{
			if( discountTextField.getText().equals("0") )
				discountTextField.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if( e.getSource() == customerNameTextField )
		{
			if( customerNameTextField.getText().equals("") )
				customerNameTextField.setText("Enter customer name here...");
			
			checkClient();
		}
		else if( e.getSource() == quantityTextField )
		{
			if( quantityTextField.getText().equals("") )
				quantityTextField.setText("Positive Integer");
		}
		else if( e.getSource() == discountTextField )
		{
			if( discountTextField.getText().equals("") )
			{
				discountTextField.setText(Integer.toString(0));
				discountTextField.setBackground(Color.WHITE);
				discountWarningLabel.setVisible(false);
			}
		}
	}
	
	//MOUSE LISTENER FUNCTIONS
	@Override
	public void mouseClicked(MouseEvent arg0){}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		newServiceLabel.setText("<HTML><U>+ New Service</U></HTML>");
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		newServiceLabel.setText("+ New Service");
	}

	@Override
	public void mousePressed(MouseEvent arg0){}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		addServiceButton.setVisible(true);
		updateServiceButton.setVisible(false);
		//newServiceLabel.setBounds(197, 432, 100, 14);
		instructionsLabel.setText("Input client name, add at least 1 product and assign at least 1 employee to add service. Click new service to clear form.");
		resetForm();
		servicesTable.getSelectionModel().clearSelection();
	}
	
	//KEY LISTENER FUNCTIONS
	@Override
	public void keyPressed(KeyEvent e)
	{
		checkInputs(e);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		checkInputs(e);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		checkInputs(e);
	}
	
	public void checkInputs(KeyEvent e)
	{
		if( e.getSource() == customerNameTextField )
			checkClient();
		else if( e.getSource() == quantityTextField )
			checkQuantityInput();
		else if( e.getSource() == discountTextField )
			checkDiscountInput();
		
		if( (bCustomerNameInDB == false || bEmployeeAdded == false) && (tempConsumablesUsed.size() > 0 || editConsumablesUsed.size() > 0) || checkDiscountInput() == false )
		{
			addServiceButton.setEnabled(false);
			updateServiceButton.setEnabled(false);
		}
		else if( bCustomerNameInDB == true && (tempConsumablesUsed.size() > 0 || editConsumablesUsed.size() > 0) && checkDiscountInput() == true )
		{
			addServiceButton.setEnabled(bEmployeeAdded);
			updateServiceButton.setEnabled(bEmployeeAdded);
		}
		
	}
	
	public void resetForm()
	{
		clientIndex = -1;
		customerNameTextField.setText("Enter customer name here...");
		serviceComboBox.setSelectedIndex(0);
		productComboBox.setSelectedIndex(0);
		quantityTextField.setText("Positive Integer");
		
		productTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		productTableModel.addColumn("Product");
		productTableModel.addColumn("Quantity");
		productTable.setModel(productTableModel);
		
		seniorComboBox.setSelectedIndex(0);
		juniorComboBox.setSelectedIndex(0);
		seniorStaffLabel.setText("Senior Staff: <none>");
		juniorStaffsLabel.setText("Junior Staff: <none>");
		
		tempConsumablesUsed = new ArrayList<>(0);
		
		bCustomerNameInDB = false;
		bValidQuantityInput = false;
		bEmployeeAdded = false;
		bIsEditing = false;
		customerNameTextField.setBackground(Color.WHITE);
		quantityTextField.setBackground(Color.WHITE);
	}
	
	public void addToProductTable(String name, int quantity)
	{
		Object[] row = {name, quantity};
		productTableModel.addRow(row);
	}
	
	public void addToServiceTable( String customer, String service, String senior, String junior, double price )
	{
		if( senior.equals("") == true )
		{
			senior = "None";
		}
		
		if( junior.equals("") == true )
		{
			junior = "None";
		}
		
		Object[] serviceItem = { customer, service, senior, junior, price };
		servicesAvailed.add(serviceItem);
		
		Object[] row = { service, price };
		serviceTableModel.addRow(row);
	}
	
	//display details of service in the form
	public void reviewServiceDetails()
	{
		instructionsLabel.setText("After making changes, click update service to save changes.  Click new service to discard changes and clear form.");
		updateServiceButton.setVisible(true);
		addServiceButton.setVisible(false);
		newServiceLabel.setBounds(160, 432, 100, 14);
		resetForm();
		bCustomerNameInDB = true;
		bEmployeeAdded = true;
		bIsEditing = true;
		int x = servicesTable.getSelectedRow();
		//{"Customer Name", "Service", "Senior E.", "Junior E.", "Price"};
		customerNameTextField.setText(servicesAvailed.get(x)[0].toString());
		serviceComboBox.setSelectedItem(servicesAvailed.get(x)[1].toString());
		productComboBox.setSelectedIndex(0);
		quantityTextField.setText("Positive Integer");
		customerNameTextField.setBackground(Color.GREEN);
		for( int i = 0; i < consumablesUsed.get(x).size(); i++ )
		{
			Object[] row = {consumablesUsed.get(x).get(i)[0], consumablesUsed.get(x).get(i)[1]};
			productTableModel.addRow(row);
		}
		
		seniorComboBox.setSelectedItem(servicesAvailed.get(x)[2].toString());
		juniorComboBox.setSelectedItem(servicesAvailed.get(x)[3].toString());
		seniorStaffLabel.setText("Senior Staff: " + servicesAvailed.get(x)[2].toString());
		juniorStaffsLabel.setText("Junior Staff: " + servicesAvailed.get(x)[3].toString());
		
		editConsumablesUsed = new ArrayList<>(0);
		editConsumablesUsed = consumablesUsed.get(x);
	}
	
	//record any changes made in service details
	public void updateServiceDetails()
	{
		addServiceButton.setVisible(true);
		updateServiceButton.setVisible(false);
		newServiceLabel.setBounds(197, 432, 100, 14);
		
		int x = servicesTable.getSelectedRow();
		
		Object[] service = { addTransactionGUI.getClients().get(clientIndex), serviceOptions[serviceComboBox.getSelectedIndex()], 
						 seniorEmployees[seniorComboBox.getSelectedIndex()], juniorEmployees[juniorComboBox.getSelectedIndex()],
						 servicePrice[serviceComboBox.getSelectedIndex()] };
		
		servicesAvailed.set(x, service);
		
		consumablesUsed.set(x, editConsumablesUsed);
		
		resetForm();
		instructionsLabel.setText("Input client name, add at least 1 product and assign at least 1 employee to add service. Click new service to clear form.");
		servicesTable.getSelectionModel().clearSelection();
	}
	
	//INPUT VALIDATION METHODS
	public void checkClient()
	{
		boolean hasClient = false;
		String s = customerNameTextField.getText();
		
		for( int i = 0; i < addTransactionGUI.getClients().size(); i++ )
			if( s.equalsIgnoreCase(addTransactionGUI.getClients().get(i)) == true )
			{
				clientIndex = i;
				hasClient = true;
			}
		
		if( hasClient == true  )
		{
			customerNameTextField.setBackground(Color.GREEN);
			bCustomerNameInDB = true;
			nameWarningLabel.setVisible(false);
		}
		else
		{
			customerNameTextField.setBackground(Color.RED);
			bCustomerNameInDB = false;
			nameWarningLabel.setVisible(true);
		}
	}
	
	public boolean checkQuantityInput()
	{
		try
		{
			int temp = Integer.parseInt(quantityTextField.getText());
			
			if( temp <= 0 )
				throw new IllegalArgumentException();
			
			addProductButton.setEnabled(true);
			quantityTextField.setBackground(Color.GREEN);
			quantityWarningLabel.setVisible(false);
			bValidQuantityInput = true;
			
			return true;
		} 
		catch (NumberFormatException ex)
		{
			quantityTextField.setBackground(Color.RED);
			quantityWarningLabel.setVisible(true);
			bValidQuantityInput = false;
			addProductButton.setEnabled(false);
		}
		catch (IllegalArgumentException ex)
		{
			quantityTextField.setBackground(Color.RED);
			quantityWarningLabel.setVisible(true);
			bValidQuantityInput = false;
			addProductButton.setEnabled(false);
		}
		return false;
	}
	
	public boolean checkDiscountInput()
	{
		try
		{
			int temp = Integer.parseInt(discountTextField.getText());
			
			if( temp < 0 || temp >= 101 )
				throw new IllegalArgumentException();
			
			discountTextField.setBackground(Color.GREEN);
			discountWarningLabel.setVisible(false);
			if( temp != 0 )
				bHasDiscount = true;
			else
				discountTextField.setBackground(Color.WHITE);
			return true;
		} 
		catch (NumberFormatException ex)
		{
			discountTextField.setBackground(Color.RED);
			discountWarningLabel.setVisible(true);
			bHasDiscount = false;
		}
		catch (IllegalArgumentException ex)
		{
			discountTextField.setBackground(Color.RED);
			discountWarningLabel.setVisible(true);
			bValidQuantityInput = false;
		}
		return false;
	}
	
	public boolean checkProductQuantity( String name, int temp )
	{
		Product p = addServicesController.getProduct(name);
		int quantity = 0;
		
		quantity += this.addTransactionGUI.getConsumableQuantityUsed(name);
		quantity += this.addTransactionGUI.getProductQuantity(name);
		
		for( int i = 0; i < consumablesUsed.size(); i++ )
		{
			for( int j = 0; j < consumablesUsed.get(i).size(); j++ )
			{
				if( ((String) consumablesUsed.get(i).get(j)[0]).equalsIgnoreCase(name) == true )
					quantity += ((int) consumablesUsed.get(i).get(j)[1]);
			}
		}
		
		for( int i = 0; i < tempConsumablesUsed.size(); i++ )
		{
			if( ((String) tempConsumablesUsed.get(i)[0]).equalsIgnoreCase(name) == true )
				quantity += ((int) tempConsumablesUsed.get(i)[1]);
		}
		
		if( p.getnQuantity() - quantity - temp >= 0 )
			return true;
		else
		{
			int x;
			if( p.getnQuantity() - quantity < 0 )
				x = 0;
			else x = p.getnQuantity() - quantity;
			JOptionPane.showMessageDialog(null, "Error " + Integer.toString(x) + " " + name + " remaining. "
					+ "Not enough supply on consumable to use on service.", "Insufficient Supply", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	//GET DATA FROM THE DATABASE
	public void getData()
	{
		addServicesController.getServices();
		addServicesController.getConsumables();
		addServicesController.getEmployees();
		this.repaint();
		this.revalidate();
	}
	
	public void getServiceList(Iterator i)
	{
		ArrayList<Object> s = new ArrayList<>(0);
		
		while (i.hasNext() == true)
		{
			s.add((Service) i.next());
		}

		serviceOptions = new String[s.size()];
		servicePrice = new double[s.size()];
		
		for( int x = 0; x < s.size(); x++ )
		{
			serviceOptions[x] = ((Service) s.get(x)).getsName();
			servicePrice[x] = ((Service) s.get(x)).getdPrice();
		}
		
		serviceComboBoxModel = new DefaultComboBoxModel<>(serviceOptions);
		serviceComboBox.setModel(serviceComboBoxModel);
	}
	
	public void getConsumableList(Iterator i)
	{
		ArrayList<Object> p = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			p.add((Consumable) i.next());
		}
		
		productOptions = new String[p.size()];
		
		for( int x = 0; x < p.size(); x++ )
		{
			productOptions[x] = ((Consumable) p.get(x)).getsName();
		}
		
		productComboBoxModel = new DefaultComboBoxModel<>(productOptions);
		productComboBox.setModel(productComboBoxModel);
	}

	public void getSeniorEmployeeList(Iterator i)
	{
		ArrayList<Object> se = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			se.add((Employee) i.next());
		}
		
		seniorEmployees = new String[se.size()+1];
		seniorEmployees[0] = "";
		for( int x = 0; x < se.size(); x++ )
			seniorEmployees[x+1] = ((Employee) se.get(x)).getsName();
		
		seniorComboBoxModel = new DefaultComboBoxModel<>(seniorEmployees);
		seniorComboBox.setModel(seniorComboBoxModel);
	}
	
	public void getJuniorEmployeeList(Iterator i)
	{
		ArrayList<Object> je = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			je.add((Employee) i.next());
		}
		
		juniorEmployees = new String[je.size()+1];
		juniorEmployees[0] = "";
		for( int x = 0; x < je.size(); x++ )
			juniorEmployees[x+1] = ((Employee) je.get(x)).getsName();
		
		juniorComboBoxModel = new DefaultComboBoxModel<>(juniorEmployees);
		juniorComboBox.setModel(juniorComboBoxModel);
	}
}

//Note: Create JList and JTables for scrollPanes. Make it bigger and then reposition other panels and components
//		Then, rework on some of the layout and design. Then connect to button within addTransaction
