package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Client;
import model.Employee;
import model.Product;
import model.Service;
import model.Transaction;

public class AddTransactionPanel extends JPanel 
{
    private Controller controller;
    private int nEntries; //TURN THIS BLOCK INTO A CLASS called TransactionEntry
    private ArrayList<String> services;
    private ArrayList<String> customerNames;
    private ArrayList<String> prices;
    
    private ArrayList<String> servicesAvailed;
    private ArrayList<String> productsBought;
    
    private Iterator<Service> iServices;
    private Iterator<Product> iProducts;
    private Iterator<Product> iConsumables;
    private Iterator<Employee> iEmployees;
    
    private JPanel leftPanel;
    private JScrollPane transScrollPane;
    private JPanel pTransactionDetail;
    private JTextField customerNameTextField;
    private JTable transactionDetail;
    private JButton cancelButton;
    private JButton saveButton;
    
    private JPanel rightPanel;
    private JLabel titleServiceLabel;
    private JLabel titleLineLabel;
    private JLabel titleLine2Label;
    private JLabel titleProductLabel;

    private JPanel servicePanel;
    private JLabel iChooseLabel;
    private JComboBox chooseServiceComboBox;
    private JButton employeeButton;
    private JButton productsButton;
    private JButton addServiceButton;

    private JPanel productsPanel;
    private JLabel iChooseProductLabel;
    private JComboBox chooseProductComboBox;
    private JLabel lQuantityLabel;
    private JTextArea quantityTextArea;
    private JButton addProductButton;
           
    private String[] productOptions;// = {"Shampoo", "Nail Polish", "Hair Dye Product"}; //Should get from DATABASE for DEVS
    private String[] serviceOptions;// = {"Manicure", "Pedicure", "Haircut", "Dye Hair"}; //Should get from DATABASE for DEVS
    
    private int[] productQuantity;// = {124.99, 99.99, 249.99};
    private double[] servicePrice;// = {200.00, 200.00, 100.00, 300.00};
    
    private boolean isOpen;
    
    private DefaultComboBoxModel<String> serviceComboBoxModel;
    private DefaultComboBoxModel<String> productComboBoxModel;
    
    private AddTransactionPanel reference;

	public AddTransactionPanel()
	{
		setBounds(183, 120, 600, 440);
		setLayout(null);
		// setBounds()
		pTransactionDetail = new JPanel();

		nEntries = 0;

		isOpen = false;
        
		serviceComboBoxModel = new DefaultComboBoxModel<>();
		productComboBoxModel = new DefaultComboBoxModel<>();
		
        //serviceReference = new Service[4]; // Should load from Database all the Services
        
        /* Instantiating Services, REMOVE AFTER TESTING 
            serviceReference[0] = new Service(testOptions2[0]);
            serviceReference[1] = new Service(testOptions2[1]);
            serviceReference[2] = new Service(testOptions2[2]);
            serviceReference[3] = new Service(testOptions2[3]);
            
            serviceReference[0].addProduct(new Product("Nail Polish", "Liter"));
            serviceReference[0].addProduct(new Product("Nail Color", "Liter"));
            
            serviceReference[1].addProduct(new Product("Nail Polish", "Liter"));
            serviceReference[1].addProduct(new Product("Nail Color", "Liter"));
            
            serviceReference[2].addProduct(new Product("Shampoo", "Liter"));
            serviceReference[2].addProduct(new Product("Hair Spray", "Liter"));
            
            serviceReference[3].addProduct(new Product("Hair Dye Product", "Liter"));
            serviceReference[3].addProduct(new Product("Bleach", "Liter"));
        /* Instantiating Services, REMOVE AFTER TESTING */
        //get data from database
		
		Border blackline = BorderFactory.createLineBorder(Color.black);

		setBorder(blackline);
        
		services = new ArrayList<>(0);
		customerNames = new ArrayList<>(0);
		prices = new ArrayList<>(0);
		servicesAvailed = new ArrayList<>(0);
		productsBought = new ArrayList<>(0);
		
		leftPanel = new JPanel();
		customerNameTextField = new JTextField("Customer Name"); // Retrieve
													// Customer
													// Name from
													// Database
		customerNameTextField.setBounds(10, 10, 200, 20);
		cancelButton = new JButton("Cancel");
		saveButton = new JButton("Save Transaction");
		titleServiceLabel = new JLabel("Service Rendered");
		titleLineLabel = new JLabel("______________________________________");
		titleProductLabel = new JLabel("Product Availed");
		titleLine2Label = new JLabel("______________________________________");

		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Service / Product");
		tModel.addColumn("Customer Name");
		tModel.addColumn("Price");

		transactionDetail = new JTable(tModel);
		transactionDetail.setBounds(10, 50, 300, 330);
		transactionDetail.setRowHeight(20);
		pTransactionDetail.add(transactionDetail);
		cancelButton.setBounds(10, 400, 75, 30);
		saveButton.setBounds(175, 400, 135, 30);
		titleServiceLabel.setBounds(480, 10, 120, 20);
		titleProductLabel.setBounds(490, 240, 100, 20);
		titleLineLabel.setBounds(325, 20, 300, 20);
		titleLine2Label.setBounds(325, 250, 300, 20);
		transScrollPane = new JScrollPane(transactionDetail);
		transScrollPane.setBounds(10, 50, 300, 330);

		servicePanel = new JPanel();
		iChooseLabel = new JLabel("Choose Service:");
		chooseServiceComboBox = new JComboBox(serviceComboBoxModel);

		// initialize the add employee button
		ButtonListener buttonListener = new ButtonListener();
		saveButton.addActionListener(buttonListener);
		reference = this;
        
		employeeButton = new JButton("Add Employee");
		employeeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				EmployeeListFrame empList = new EmployeeListFrame(reference);
				empList.addWindowListener(new WindowCloser());
			}
		});
        
		// initialize add Products button
		productsButton = new JButton("Products Used");
		productsButton.addActionListener(buttonListener);
        
		// initialize the add service button
		addServiceButton = new JButton("Add Service");
		addServiceButton.addActionListener(buttonListener);

		servicePanel.setBorder(blackline);
		servicePanel.setLayout(null);
		servicePanel.setBounds(325, 50, 266, 180);
		iChooseLabel.setBounds(87, 10, 100, 25);
		chooseServiceComboBox.setBounds(59, 40, 150, 25);
		employeeButton.setBounds(59, 70, 150, 25);
		productsButton.setBounds(59, 100, 150, 25);
		addServiceButton.setBounds(59, 140, 150, 25);

		servicePanel.add(iChooseLabel);
		servicePanel.add(chooseServiceComboBox);
		servicePanel.add(employeeButton);
		servicePanel.add(productsButton);
		servicePanel.add(addServiceButton);
		add(servicePanel);

		productsPanel = new JPanel();
		iChooseProductLabel = new JLabel("Choose Product:");
		chooseProductComboBox = new JComboBox(productComboBoxModel);
		lQuantityLabel = new JLabel("Quantity:");
		quantityTextArea = new JTextArea("Enter quantity here");
		addProductButton = new JButton("Add Product");

		addProductButton.addActionListener(buttonListener);
		
		
		productsPanel.setBorder(blackline);
		productsPanel.setLayout(null);
		productsPanel.setBounds(325, 280, 266, 150);
		iChooseProductLabel.setBounds(87, 10, 100, 25);
		chooseProductComboBox.setBounds(59, 40, 150, 25);
		lQuantityLabel.setBounds(106, 65, 150, 25);
		quantityTextArea.setBounds(59, 85, 150, 18);
		quantityTextArea.setBorder(blackline);
		addProductButton.setBounds(59, 110, 150, 25);

		productsPanel.add(iChooseProductLabel);
		productsPanel.add(chooseProductComboBox);
		productsPanel.add(lQuantityLabel);
		productsPanel.add(quantityTextArea);
		productsPanel.add(addProductButton);

		add(productsPanel);

		add(titleLine2Label);
		add(titleProductLabel);
		add(titleLineLabel);
		add(titleServiceLabel);
		add(saveButton);
		add(cancelButton);
		add(customerNameTextField);
		add(transScrollPane);
	}
    
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

		transactionDetail.setModel(tModel);
	}
    
	public void toggleOpen()
	{
		isOpen = false;
	}

	public class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == addProductButton)
			{
				try
				{
					int temp = Integer.parseInt(quantityTextArea.getText());

					services.add(productOptions[chooseProductComboBox.getSelectedIndex()] + " (" + temp + ")");
					productsBought.add(productOptions[chooseProductComboBox.getSelectedIndex()]);
					prices.add("" + (productQuantity[chooseProductComboBox.getSelectedIndex()] * temp));
					customerNames.add(customerNameTextField.getText());

					nEntries++;

					updateTable();
				} 
				catch (NumberFormatException ex)
				{
					System.out.println("Quantity has to be double!");

					quantityTextArea.setText("This has to be a number.");
				}
			} 
			else if (e.getSource() == addServiceButton)
			{
				services.add(serviceOptions[chooseServiceComboBox.getSelectedIndex()]);
				servicesAvailed.add(serviceOptions[chooseServiceComboBox.getSelectedIndex()]);
				prices.add("" + servicePrice[chooseServiceComboBox.getSelectedIndex()]);
				customerNames.add(customerNameTextField.getText());
				
				nEntries++;

				updateTable();
			} 
			else if (e.getSource() == productsButton)
			{
				if (isOpen == false)
				{
					ProductListFrame temp = new ProductListFrame(reference);
					temp.addWindowListener(new WindowCloser());
					isOpen = true;
				}
			}
			else if( e.getSource() == saveButton )
			{
				ArrayList<Service> s = new ArrayList<>(0);
				
				Client c = getInputClient(customerNameTextField.getText());
				Transaction t = new Transaction("1", c, "");
				
				for( int i = 0; i < servicesAvailed.size(); i++ )
				{
					s.add(getAvailedService(servicesAvailed.get(i)));
				}
				
				//t.add
				
//				int index = str.indexOf("(");
//				System.out.println(str.substring(0, index));
				//addTransaction(t);
			}
		}
		
	}
    
	public class WindowCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			toggleOpen();
		}
	}

	public void getData()
	{
		controller.getServices();
		controller.getConsumables();
		controller.getProducts();
		controller.getEmployees();
		this.repaint();
		this.revalidate();
	}
	
	public Client getInputClient( String name )
	{
		return controller.getClient(name);
	}
	
	public Service getAvailedService( String name )
	{
		return controller.getService(name);
	}
	
	public void getProductList(Iterator i)
	{	
		ArrayList<Product> p = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			p.add((Product) i.next());
		}
		
		productOptions = new String[p.size()];
		productQuantity = new int[p.size()];
		
		for( int x = 0; x < p.size(); x++ )
		{
			productOptions[x] = ((Product) p.get(x)).getsName();
			productQuantity[x] = ((Product) p.get(x)).getnQuantity();
		}
		
		this.iProducts = p.iterator();
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
	
	public void getConsumableList(Iterator i)
	{
		this.iConsumables = i;
//		ArrayList<Object> c = new ArrayList<>(0);
//
//		while (i.hasNext() == true)
//		{
//			c.add((Product) i.next());
//		}
//
//		consumables = new Product[c.size()];
//		consumables = c.toArray(consumables);
	}
	
	public void getEmployeeList(Iterator i)
	{
		this.iEmployees = i;
	}
	
	public void addTransaction( Transaction t )
	{
		controller.addTransaction(t);
	}
	
	//GETTERS
	public Controller getController()
	{
		return controller;
	}
	
	public Iterator<Service> getServices()
	{
		return iServices;
	}

	public Iterator<Product> getProducts()
	{
		return iProducts;
	}

	public Iterator<Product> getConsumables()
	{
		return iConsumables;
	}
	
	public Iterator<Employee> getEmployees()
	{
		controller.getEmployees();
		return iEmployees;
	}
	
	//SETTERS
	public void setController(Controller c)
	{
		this.controller = c;
	}
}
