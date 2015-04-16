package oldview;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import controller.Controller;
import model.Client;
import model.Employee;
import model.Product;
import model.Service;
import model.Transaction;
import model.Consumable;
import model.OverTheCounter;

public class AddTransactionPanel extends JPanel 
{
	private Controller					controller;
	private int						nEntries;
	private boolean					isOpen;
	private boolean					isEmpty	= true;

	private double[]					productPrice;
	private double[]					servicePrice;
	private String						selectedProduct;
	private String[]					productOptions;
	private String[]					serviceOptions;
	private String[]					selectedEmployee;

	private ArrayList<Integer>			productsQuantity;
	private ArrayList<String>			services;
	private ArrayList<String>			customerNames;
	private ArrayList<String>			prices;
	private ArrayList<String>			servicesAvailed;
	private ArrayList<String>			productsBought;
	private ArrayList<String>			consumablesUsed;
	private ArrayList<String[]>			employeesAssigned;

	private Iterator<Service>			iServices;
	private Iterator<Consumable>			iConsumables;
	private Iterator<OverTheCounter>		iOverTheCounters;
	private Iterator<Employee>			iWorkingEmployees;

	private JButton					employeeButton;
	private JButton					addServiceButton;
	private JButton					productsButton;
	private JButton					addProductButton;
	private JButton					cancelButton;
	private JButton					saveButton;

	private DefaultComboBoxModel<String>	serviceComboBoxModel;
	private JComboBox					chooseServiceComboBox;
	private DefaultComboBoxModel<String>	productComboBoxModel;
	private JComboBox					chooseProductComboBox;

	private JLabel						titleServiceLabel;
	private JLabel						titleLineLabel;
	private JLabel						iChooseLabel;
	private JLabel						seniorEmployeeLabel;
	private JLabel						juniorEmployeeLabel;
	private JLabel						productUsedLabel;
	private JLabel						titleProductLabel;
	private JLabel						titleLine2Label;
	private JLabel						iChooseProductLabel;
	private JLabel						lQuantityLabel;

	private JPanel						servicePanel;
	private JPanel						productsPanel;

	private JScrollPane					transScrollPane;

	private JTable						transactionDetailTable;

	private JTextField					customerNameTextField;
	private JTextArea					quantityTextArea;

	private AddTransactionPanel			reference;
	
	public AddTransactionPanel()
	{
		this.setBounds(183, 120, 600, 440);
		this.setLayout(null);
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		
		//Variable Declarations
		ButtonListener buttonListener = new ButtonListener();
		nEntries = 0;
		isOpen = false;
		reference = this;
		
		//ArrayList Declarations
		services = new ArrayList<>(0);
		customerNames = new ArrayList<>(0);
		prices = new ArrayList<>(0);
		servicesAvailed = new ArrayList<>(0);
		productsBought = new ArrayList<>(0);
		productsQuantity = new ArrayList<>(0);
		employeesAssigned = new ArrayList<>(0);
		consumablesUsed = new ArrayList<>(0);
		selectedEmployee = new String[2];
		selectedEmployee[0] = null;
		selectedEmployee[1] = null;
		
		//UI element declarations
		customerNameTextField = new JTextField("Customer Name");
		customerNameTextField.setBounds(10, 10, 200, 20);
		this.add(customerNameTextField);
		
		titleServiceLabel = new JLabel("Service Rendered");
		titleServiceLabel.setBounds(480, 10, 120, 20);
		this.add(titleServiceLabel);
		
		titleLineLabel = new JLabel("______________________________________");
		titleLineLabel.setBounds(325, 20, 300, 20);
		this.add(titleLineLabel);
		
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Service / Product");
		tModel.addColumn("Customer Name");
		tModel.addColumn("Price");
		
		transactionDetailTable = new JTable(tModel);
		transactionDetailTable.setBounds(10, 50, 300, 330);
		transactionDetailTable.setRowHeight(20);
		transScrollPane = new JScrollPane(transactionDetailTable);
		transScrollPane.setBounds(10, 50, 300, 330);
		this.add(transScrollPane);
		
		servicePanel = new JPanel();
		servicePanel.setBorder(blackline);
		servicePanel.setLayout(null);
		servicePanel.setBounds(325, 50, 266, 180);
		
		iChooseLabel = new JLabel("Choose Service:");
		iChooseLabel.setBounds(87, 10, 100, 25);
		servicePanel.add(iChooseLabel);
		
		serviceComboBoxModel = new DefaultComboBoxModel<>();
		chooseServiceComboBox = new JComboBox<>(serviceComboBoxModel);
		chooseServiceComboBox.setBounds(59, 40, 150, 25);
		servicePanel.add(chooseServiceComboBox);
		
		employeeButton = new JButton("Add Employee");
		employeeButton.setBounds(20, 70, 150, 25);
		employeeButton.addActionListener(buttonListener);
		servicePanel.add(employeeButton);
		
		seniorEmployeeLabel = new JLabel("E1:");
		seniorEmployeeLabel.setBounds(180, 70, 76, 14);
		servicePanel.add(seniorEmployeeLabel);
		
		juniorEmployeeLabel = new JLabel("E2:");
		juniorEmployeeLabel.setBounds(180, 83, 76, 14);
		servicePanel.add(juniorEmployeeLabel);
		
		productsButton = new JButton("Products Used");
		productsButton.setBounds(20, 100, 150, 25);
		productsButton.addActionListener(buttonListener);
		servicePanel.add(productsButton);
		
		productUsedLabel = new JLabel("P:");
		productUsedLabel.setBounds(180, 105, 76, 14);
		servicePanel.add(productUsedLabel);
		
		addServiceButton = new JButton("Add Service");
		addServiceButton.setBounds(59, 140, 150, 25);
		addServiceButton.addActionListener(buttonListener);
		servicePanel.add(addServiceButton);
		
		this.add(servicePanel);
		
		titleProductLabel = new JLabel("Product Availed");
		titleProductLabel.setBounds(490, 240, 100, 20);
		this.add(titleProductLabel);
		
		titleLine2Label = new JLabel("______________________________________");
		titleLine2Label.setBounds(325, 250, 300, 20);
		this.add(titleLine2Label);
		
		productsPanel = new JPanel();
		productsPanel.setBorder(blackline);
		productsPanel.setLayout(null);
		productsPanel.setBounds(325, 280, 266, 150);
		
		iChooseProductLabel = new JLabel("Choose Product:");
		iChooseProductLabel.setBounds(87, 10, 100, 25);
		productsPanel.add(iChooseProductLabel);
		
		productComboBoxModel = new DefaultComboBoxModel<>();
		chooseProductComboBox = new JComboBox<>(productComboBoxModel);
		chooseProductComboBox.setBounds(59, 40, 150, 25);
		productsPanel.add(chooseProductComboBox);
		
		lQuantityLabel = new JLabel("Quantity:");
		lQuantityLabel.setBounds(106, 65, 150, 25);
		productsPanel.add(lQuantityLabel);
		
		quantityTextArea = new JTextArea("Enter quantity here");
		quantityTextArea.setBounds(59, 85, 150, 18);
		quantityTextArea.setBorder(blackline);
		productsPanel.add(quantityTextArea);
		
		addProductButton = new JButton("Add Product");
		addProductButton.setBounds(59, 110, 150, 25);
		addProductButton.addActionListener(buttonListener);
		productsPanel.add(addProductButton);
		
		this.add(productsPanel);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(10, 400, 75, 30);
		cancelButton.addActionListener(buttonListener);
		cancelButton.setEnabled(false);
		this.add(cancelButton);
		
		ListSelectionModel listSelectionModel = transactionDetailTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{ 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				cancelButton.setEnabled(!lsm.isSelectionEmpty());
			};
		});
													
		saveButton = new JButton("Save Transaction");
		saveButton.setBounds(175, 400, 135, 30);
		saveButton.addActionListener(buttonListener);
		this.add(saveButton);
	}
    
	public class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (customerNameTextField.getText().equals(""))
			{
				isEmpty = true;
			}
			else 
			{
				isEmpty = false;
			}
			
			if( e.getSource() == employeeButton )
			{
				if( checkClient() == true )
				{
					EmployeeListFrame empList = new EmployeeListFrame(reference);
					empList.addWindowListener(new WindowCloser());
				}
			}
			else if (e.getSource() == addServiceButton)
			{
				if( isEmpty == false )
				{
					if( checkClient() == true )
					{
						if( (selectedEmployee[0] != null || selectedEmployee[1] != null)  && selectedProduct != null )
						{
							if( checkProductQuantity(selectedProduct, 1, "service") == true )
							{
								employeesAssigned.add(selectedEmployee);
								consumablesUsed.add(selectedProduct);
								addToTable(serviceOptions[chooseServiceComboBox.getSelectedIndex()], 
										"" + servicePrice[chooseServiceComboBox.getSelectedIndex()],
										customerNameTextField.getText());
								servicesAvailed.add(serviceOptions[chooseServiceComboBox.getSelectedIndex()]);
								selectedEmployee = new String[2];
								selectedProduct = "";
								chooseServiceComboBox.setSelectedIndex(0);
								seniorEmployeeLabel.setText("E1 :");
								juniorEmployeeLabel.setText("E2 :");
								productUsedLabel.setText("P:  ");
								customerNameTextField.setEnabled(false);
							}
						}
						else if( (selectedEmployee[0] == null && selectedEmployee[1] == null) && selectedProduct == null )
							JOptionPane.showMessageDialog(null, "No employee and consumable specified. Please choose employee and consumable.", "No Input", JOptionPane.WARNING_MESSAGE);
						else if( (selectedEmployee[0] == null && selectedEmployee[1] == null) )
							JOptionPane.showMessageDialog(null, "No employee specified. Please choose employee.", "No Employee", JOptionPane.WARNING_MESSAGE);
						else if( selectedProduct == null )
							JOptionPane.showMessageDialog(null, "No consumable selected. Please choose a consumable.", "No Consumable", JOptionPane.WARNING_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "No client specified. Please input client name.", "No Client", JOptionPane.WARNING_MESSAGE);
			}
			else if (e.getSource() == productsButton)
			{
				if (isOpen == false)
				{
					ProductListFrame temp = new ProductListFrame(reference);
					temp.addWindowListener(new WindowCloser());
				//	isOpen = true;
				}
			}
			else if (e.getSource() == addProductButton)
			{
				if( isEmpty == false)
				{
					if( checkClient() == true )
					{
						try
						{
							int temp = Integer.parseInt(quantityTextArea.getText());
							
							if( temp <= 0 )
								throw new IllegalArgumentException();
							
							if( checkProductQuantity(productOptions[chooseProductComboBox.getSelectedIndex()], temp, "product") == true )
							{
								addToTable(productOptions[chooseProductComboBox.getSelectedIndex()] + " (" + temp + ")",
										"" + (productPrice[chooseProductComboBox.getSelectedIndex()] * temp),
										customerNameTextField.getText());
								productsBought.add(productOptions[chooseProductComboBox.getSelectedIndex()]);
								productsQuantity.add(temp);
								chooseProductComboBox.setSelectedIndex(0);
								quantityTextArea.setText("Input Positive Integer");
							}
							customerNameTextField.setEnabled(false);
							temp = 0;
						} 
						catch (NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(null, "Input must be a positive integer!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
							quantityTextArea.setText("Input positive integer.");
						}
						catch (IllegalArgumentException ex)
						{
							JOptionPane.showMessageDialog(null, "Input must be a positive integer!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
							quantityTextArea.setText("Input positive integer.");
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "No client specified. Please input client name.", "No Client", JOptionPane.WARNING_MESSAGE);
			}
			else if( e.getSource() == cancelButton )
			{
				int x = transactionDetailTable.getSelectedRow();
				String selectedItem = (String) transactionDetailTable.getModel().getValueAt(x, 0);
				removeElementFromList(selectedItem);
				deleteFromTable(x);
			}
			else if( e.getSource() == saveButton )
			{
				if( nEntries == 0 && isEmpty == true )
					JOptionPane.showMessageDialog(null, "No client and input found. Please enter client name and add service or product.", "No Input", JOptionPane.WARNING_MESSAGE);
				else if( nEntries == 0 )
					JOptionPane.showMessageDialog(null, "No input found. Please add service or product.", "No Input", JOptionPane.WARNING_MESSAGE);
				else if( isEmpty == true )
					JOptionPane.showMessageDialog(null, "No client specified. Please input client name.", "No Client", JOptionPane.WARNING_MESSAGE);
				else
				{
					if( checkClient() == true )
					{
						controller.createTransaction(servicesAvailed, employeesAssigned, consumablesUsed, productsBought, productsQuantity, customerNameTextField.getText());
						resetAll();
					}
				}
			}
		}
		
	}
	
	//other data validation functions
	public boolean checkClient()
	{
		String s = customerNameTextField.getText();
		if( s.equalsIgnoreCase("Client one") || s.equalsIgnoreCase("Client two") ||
		    s.equalsIgnoreCase("Client three") || s.equalsIgnoreCase("Client four") )
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Client cannot be found in the database. Please choose from client one to client four.", "Client Not Found", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	public boolean checkProductQuantity( String name, int temp, String type )
	{
		Product p = controller.getProduct(name);
		int quantity = 0;

		for( int i = 0; i < productsBought.size(); i++ )
		{
			if( productsBought.get(i).equalsIgnoreCase(name) == true )
				quantity += productsQuantity.get(i);
		}
		
		for( int i = 0; i < consumablesUsed.size(); i++ )
		{
			if( consumablesUsed.get(i).equalsIgnoreCase(name) == true )
				quantity++;
		}
		
		if( p.getnQuantity() - quantity - temp >= 0 )
			return true;
		else
		{
			if( type.equalsIgnoreCase("Product") == true )
			{
				JOptionPane.showMessageDialog(null, "Error " + Integer.toString(p.getnQuantity() - quantity) + " " + name + " remaining. "
						+ "Not enough supply to meet client's demand.", "Insufficient Supply", JOptionPane.WARNING_MESSAGE);
			}
			else if( type.equalsIgnoreCase("Service") == true )
			{
				JOptionPane.showMessageDialog(null, "Error " + Integer.toString(p.getnQuantity() - quantity) + " " + name + " remaining. "
						+ "Not enough supply on consumable to use on service.", "Insufficient Supply", JOptionPane.WARNING_MESSAGE);
			}
			return false;
		}
	}
	
	// table related functions
	private void updateTable()
	{
		DefaultTableModel tModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		tModel.addColumn("Service / Product");
		tModel.addColumn("Customer Name");
		tModel.addColumn("Price");

		int i = 0;
		while (i < nEntries)
		{
			String[] entry = { services.get(i), customerNames.get(i),
					prices.get(i) };
			tModel.addRow(entry);

			i++;
		}

		transactionDetailTable.setModel(tModel);
	}
    
	public void addToTable(String service, String price, String customerName)
	{
		services.add(service);
		prices.add(price);
		customerNames.add(customerName);
		
		nEntries++;

		updateTable();
	}
	
	public void deleteFromTable(int item)
	{
		services.remove(item);
		prices.remove(item);
		customerNames.remove(item);
		
		nEntries--;

		updateTable();
	}
	
	public void removeElementFromList(String name)
	{
		System.out.println(name);
		boolean found = false;
		
		for( int i = 0; i < servicesAvailed.size(); i++ )
		{
			if( servicesAvailed.get(i).equalsIgnoreCase(name) == true )
			{
				System.out.println(servicesAvailed.get(i));
				servicesAvailed.remove(name);
				found = true;
				break;
			}
		}
		
		if( found == false )
		{
			for( int i = 0; i < productsBought.size(); i++ )
			{
				if( (name.contains(productsBought.get(i)) && name.contains(productsQuantity.get(i).toString())) )
				{
					productsBought.remove(i);
					productsQuantity.remove(i);
					break;
				}
			}
			
			System.out.println("Remaining:");
			for( int i = 0; i < productsBought.size(); i++ )
			{
				System.out.println(productsBought.get(i) + " - " + productsQuantity.get(i));
			}
		}
		updateTable();
	}
	
	public void resetAll()
	{	
		nEntries = 0;
		customerNameTextField.setText("Customer Name");
		customerNameTextField.setEnabled(true);
		quantityTextArea.setText("Input Positive Integer");
		services.clear();
		customerNames.clear();
		prices.clear();
		productsBought.clear();
		productsQuantity.clear();
		servicesAvailed.clear();
		employeesAssigned.clear();
		selectedEmployee[0] = null;
		selectedEmployee[1] = null;
		chooseProductComboBox.setSelectedIndex(0);
		chooseServiceComboBox.setSelectedIndex(0);
		updateTable();
	}

	//others
	public void toggleOpen()
	{
		isOpen = false;
	}
    
	public class WindowCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			toggleOpen();
		}
	}
	
	
	
	//GETTERS
	public void getData()
	{
		controller.getServices();
		controller.getConsumables();;
		controller.getWorkingEmployees();
		controller.getOverTheCounters();
		this.repaint();
		this.revalidate();
	}
	
	public void addTransaction( Transaction t )
	{
		controller.addTransaction(t);
	}
	
	public Client getInputClient( String name )
	{
		return controller.getClient(name);
	}
	
	public Service getAvailedService( String name )
	{
		return controller.getService(name);
	}
	
	public Product getAvailedProduct( String name )
	{
		return controller.getProduct(name);
	}
	
	public Iterator<Consumable> getConsumables()
	{
		controller.getConsumables();
		return iConsumables;
	}
	
	public Iterator<Employee> getWorkingEmployees()
	{
		controller.getWorkingEmployees();
		return iWorkingEmployees;
	}
	
	public Iterator<OverTheCounter> getOverTheCounters()
	{
		controller.getOverTheCounters();
		return iOverTheCounters;
	}
	
	public void getOverTheCounterList(Iterator i)
	{
		ArrayList<OverTheCounter> otc = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			otc.add((OverTheCounter) i.next());
		}
		
		productOptions = new String[otc.size()];
		productPrice = new double[otc.size()];
		
		for( int x = 0; x < otc.size(); x++ )
		{
			productOptions[x] = ((OverTheCounter) otc.get(x)).getsName();
			productPrice[x] = ((OverTheCounter) otc.get(x)).getdPrice();
		}
		
		this.iOverTheCounters = otc.iterator();
		productComboBoxModel = new DefaultComboBoxModel<>(productOptions);
		chooseProductComboBox.setModel(productComboBoxModel);
	}
	
	public void getServiceList(Iterator i)
	{
		this.iServices = i;
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
		chooseServiceComboBox.setModel(serviceComboBoxModel);
	}
	
	public Controller getController()
	{
		return controller;
	}
	
	public Iterator<Service> getServices()
	{
		return iServices;
	}

	//SETTERS
	public void setController(Controller c)
	{
		this.controller = c;
	}
	
	public void getWorkingEmployeeList(Iterator i)
	{
		this.iWorkingEmployees = i;
	}
	
	public void getConsumableList(Iterator i)
	{
		this.iConsumables = i;
	}
	
	public void setSelectedEmployee1( String selectedEmployee1 )
	{
		this.selectedEmployee[0] = selectedEmployee1;
		seniorEmployeeLabel.setText("E1: " + selectedEmployee1);
	}
	
	public void setSelectedEmployee2( String selectedEmployee2 )
	{
		this.selectedEmployee[1] = selectedEmployee2;
		juniorEmployeeLabel.setText("E2: " + selectedEmployee2);
	}
	
	public void setSelectedProduct( String selectedProduct )
	{
		this.selectedProduct = selectedProduct;
		productUsedLabel.setText("P:  " + selectedProduct);
	}
}
