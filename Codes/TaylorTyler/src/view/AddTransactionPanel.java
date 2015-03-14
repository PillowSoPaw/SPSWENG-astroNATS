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
import model.Product;
import model.Service;
import model.Transaction;

public class AddTransactionPanel extends JPanel 
{
    private Controller controller;
    private int nEntries; //TURN THIS BLOCK INTO A CLASS called TransactionEntry
//    private ArrayList<String> services;
//    private ArrayList<String> customerNames;
//    private ArrayList<String> prices;
    
    private Iterator<Service> services;
    private Iterator<Product> products;
    private Iterator<Product> consumables;
    
    private JPanel leftPanel;
    private JScrollPane transScrollPane;
    private JPanel pTransactionDetail;
    private JTextField customerNameLabel;
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
           
    private String[] testOptions = {"Shampoo", "Nail Polish", "Hair Dye Product"}; //Should get from DATABASE for DEVS
    private String[] testOptions2 = {"Manicure", "Pedicure", "Haircut", "Dye Hair"}; //Should get from DATABASE for DEVS
    
    private double[] testPrice = {124.99, 99.99, 249.99};
    	private double[] testPrice2 = {200.00, 200.00, 100.00, 300.00};
    
    private boolean isOpen;
    
    private AddTransactionPanel reference;

	public AddTransactionPanel()
	{
		setBounds(183, 120, 600, 440);
		setLayout(null);
		// setBounds()
		pTransactionDetail = new JPanel();

		nEntries = 0;

		isOpen = false;
        
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
		controller.getServices();
		controller.getConsumables();
		controller.getProducts();

		Border blackline = BorderFactory.createLineBorder(Color.black);

		setBorder(blackline);
        
//        services = new ArrayList();
//        customerNames = new ArrayList();
//        prices = new ArrayList();
        
		leftPanel = new JPanel();
		customerNameLabel = new JTextField("Customer Name"); // Retrieve
													// Customer
													// Name from
													// Database
		customerNameLabel.setBounds(10, 10, 200, 20);
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
		chooseServiceComboBox = new JComboBox(testOptions2);

		// initialize the add employee button
		buttonListener ButtonListener = new buttonListener();

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
		productsButton.addActionListener(ButtonListener);
        
		// initialize the add service button
		addServiceButton = new JButton("Add Service");
		addServiceButton.addActionListener(ButtonListener);

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
		chooseProductComboBox = new JComboBox(testOptions);
		lQuantityLabel = new JLabel("Quantity:");
		quantityTextArea = new JTextArea("Enter quantity here");
		addProductButton = new JButton("Add Product");

		addProductButton.addActionListener(ButtonListener);

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
		add(customerNameLabel);
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

	public class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == addProductButton)
			{
				try
				{
					int temp = Integer.parseInt(quantityTextArea.getText());

					services.add(testOptions[chooseProductComboBox.getSelectedIndex()] + " (" + temp + ")");
					prices.add("" + (testPrice[chooseProductComboBox.getSelectedIndex()] * temp));
					customerNames.add(customerNameLabel.getText());

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
				services.add(testOptions2[chooseServiceComboBox.getSelectedIndex()]);
				prices.add("" + testPrice2[chooseServiceComboBox.getSelectedIndex()]);
				customerNames.add(customerNameLabel.getText());

				nEntries++;

				updateTable();
			} 
			else if (e.getSource() == productsButton)
			{
				if (isOpen == false)
				{
					ProductListFrame temp = new ProductListFrame(
							reference,
							serviceReference[chooseServiceComboBox
									.getSelectedIndex()]);
					temp.addWindowListener(new WindowCloser());
					isOpen = true;
				}
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

	public void getServiceList(Iterator i)
	{
		this.services = i;
//		ArrayList<Object> s = new ArrayList<>(0);
//
//		while (i.hasNext() == true)
//		{
//			s.add((Service) i.next());
//		}
//
//		serviceReference = new Service[s.size()];
//		serviceReference = s.toArray(serviceReference);
	}
	
	public void getConsumableList(Iterator i)
	{
		this.consumables = i;
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
	
	public void getProductList(Iterator i)
	{
		this.products = i;
	}
	
	public void addTransaction()
	{
		Transaction t = null;
		controller.addTransaction(t);
	}

	//GETTERS
	public Controller getController()
	{
		return controller;
	}
	
	public Iterator<Service> getServices()
	{
		return services;
	}

	public Iterator<Product> getProducts()
	{
		return products;
	}

	public Iterator<Product> getConsumables()
	{
		return consumables;
	}
	
	//SETTERS
	public void setController(Controller c)
	{
		this.controller = c;
	}
}
