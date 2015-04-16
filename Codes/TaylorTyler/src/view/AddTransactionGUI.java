package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import model.Client;
import controller.AddProductsController;
import controller.AddTransactionController;
import controller.AddServicesController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

public class AddTransactionGUI extends JPanel implements ActionListener
{	
	private AddTransactionController addTransactionController;
	private AddServicesController addServicesController;
	private AddProductsController addProductsController;
	
	private String title;
	private Border blackline;
	private JTextField nameTextField;
	private JScrollPane servicesTransactionScrollPane;
	private JScrollPane productsTransactionScrollPane;
	private JPanel imagePanel;
	private JLabel nameLabel;
	private JButton addServiceButton;
	private JButton addProductButton;
	private JButton addPromoButton;
	private JButton clearButton;
	private JButton saveTransactionButton;
	private JLabel servicesLabel;
	private JLabel productsLabel;
	private JTable servicesTransactionTable;
	private JTable productsTransactionTable;
	private JLabel servicesSubtotalLabel;
	private JLabel productsSubtotalLabel;
	private JLabel totalLabel;
	private JButton removeServiceButton;
	private JButton removeProductButton;
	private DefaultTableModel serviceModel;
	private DefaultTableModel productModel;
	
	private boolean addServicesHandler;
	private boolean addProductsHandler;
	private String[] serviceTableColumn = {"Customer Name", "Service", "Senior E.", "Junior E.", "Price"};
	private String[] productTableColumn = {"Product", "Quantity", "Price(per unit)", "Subtotal"};
	private ArrayList<Object[]> serviceTableRows;
	private ArrayList<Object[]> productTableRows;
	private ArrayList<ArrayList<Object[]>> consumables;
	
	public AddTransactionGUI( AddTransactionController addTransactionController, AddServicesController addServicesController, AddProductsController addProductsController )
	{
		this.addTransactionController = addTransactionController;
		this.addServicesController = addServicesController;
		this.addProductsController = addProductsController;
		
		serviceTableRows = new ArrayList<>(0);
		productTableRows = new ArrayList<>(0);
		consumables = new ArrayList<>(0);
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		this.title = "Inventory";
		this.setBackground(new Color(128, 128, 0));
		//this.setBackground(new Color(189, 183, 107));
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		this.setLayout(null);
		this.repaint();
		
		servicesLabel = new JLabel("Services");
		servicesLabel.setForeground(Color.WHITE);
		servicesLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		servicesLabel.setBounds(521, 11, 89, 20);
		add(servicesLabel);
		
		productsLabel = new JLabel("Products");
		productsLabel.setForeground(Color.WHITE);
		productsLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		productsLabel.setBounds(745, 14, 76, 14);
		add(productsLabel);
		
		serviceModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < serviceTableColumn.length; i++ )
		{
			serviceModel.addColumn(serviceTableColumn[i]);
		}
		
		servicesTransactionTable = new JTable(serviceModel);
		servicesTransactionScrollPane = new JScrollPane(servicesTransactionTable);
		servicesTransactionScrollPane.setBounds(161, 42, 429, 398);
		servicesTransactionScrollPane.setBorder(blackline);
		add(servicesTransactionScrollPane);
		
		productModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < productTableColumn.length; i++ )
		{
			productModel.addColumn(productTableColumn[i]);
		}
		
		productsTransactionTable = new JTable(productModel);
		productsTransactionScrollPane = new JScrollPane(productsTransactionTable);
		productsTransactionScrollPane.setBounds(600, 42, 211, 398);
		productsTransactionScrollPane.setBorder(blackline);
		add(productsTransactionScrollPane);
		
		imagePanel = new JPanel();
		imagePanel.setBounds(10, 42, 142, 130);
		imagePanel.setBorder(blackline);
		add(imagePanel);
		
		Iterator<Client> clients = addTransactionController.getAllClients();
		ArrayList<String> clientNames = new ArrayList();
		
		while(clients.hasNext())
		{
			Client c = clients.next();
			clientNames.add(c.getsName());
		}	
	
		nameTextField = new Java2sAutoTextField(clientNames.subList(0, clientNames.size()));
		nameTextField.setBounds(10, 183, 141, 20);
		add(nameTextField);
		nameTextField.setColumns(10);
		
//		nameTextField = new JTextField();
//		nameTextField.setText("Last name, First name");
//		nameTextField.setBounds(10, 183, 141, 20);
//		add(nameTextField);
//		nameTextField.setColumns(10);
		
		nameLabel = new JLabel("Name");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nameLabel.setBounds(66, 204, 46, 14);
		add(nameLabel);
		
		addServicesHandler = false;
		addServiceButton = new JButton("Add Services");
		addServiceButton.setBounds(10, 246, 141, 23);
		addServiceButton.addActionListener(this);
		add(addServiceButton);
		
		addProductsHandler = false;
		addProductButton = new JButton("Add Products");
		addProductButton.setBounds(10, 280, 141, 20);
		addProductButton.addActionListener(this);
		add(addProductButton);
		
		addPromoButton = new JButton("Add Promo");
		addPromoButton.setBounds(10, 311, 141, 20);
		addPromoButton.addActionListener(this);
		addPromoButton.setEnabled(false);
		add(addPromoButton);
		
		clearButton = new JButton("Clear All");
		clearButton.setForeground(Color.BLACK);
		clearButton.setBackground(Color.RED);
		clearButton.setBounds(11, 383, 141, 23);
		clearButton.addActionListener(this);
		add(clearButton);
		
		saveTransactionButton = new JButton("Save Transaction");
		saveTransactionButton.setForeground(Color.BLACK);
		saveTransactionButton.setBackground(new Color(50, 205, 50));
		saveTransactionButton.setBounds(10, 414, 141, 23);
		saveTransactionButton.addActionListener(this);
		add(saveTransactionButton);
		
		servicesSubtotalLabel = new JLabel("Services Subtotal:");
		servicesSubtotalLabel.setForeground(Color.WHITE);
		servicesSubtotalLabel.setBounds(161, 462, 211, 14);
		add(servicesSubtotalLabel);
		
		productsSubtotalLabel = new JLabel("Products Subtotal: ");
		productsSubtotalLabel.setForeground(Color.WHITE);
		productsSubtotalLabel.setBounds(600, 462, 211, 14);
		add(productsSubtotalLabel);
		
		totalLabel = new JLabel("Total:");
		totalLabel.setForeground(Color.WHITE);
		totalLabel.setBounds(10, 462, 142, 14);
		add(totalLabel);
		
		removeServiceButton = new JButton("Remove Service");
		removeServiceButton.setBounds(161, 443, 130, 19);
		removeServiceButton.addActionListener(this);
		removeServiceButton.setEnabled(false);
		add(removeServiceButton);
		
		removeProductButton = new JButton("Remove Product");
		removeProductButton.setBounds(600, 443, 130, 19);
		removeProductButton.addActionListener(this);
		removeProductButton.setEnabled(false);
		add(removeProductButton);
	}

	public void getData()
	{
//		updateServiceTable();
//		updateProductTable();
//		
//		this.repaint();
//		this.revalidate();
	}
	
	public void resetAll()
	{
		serviceTableRows.clear();
		productTableRows.clear();
		consumables.clear();
		resetTables();
		updateServiceTable();
		updateProductTable();
	}
	
	public void resetTables()
	{
		serviceModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < serviceTableColumn.length; i++ )
		{
			serviceModel.addColumn(serviceTableColumn[i]);
		}
		
		productModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < productTableColumn.length; i++ )
		{
			productModel.addColumn(productTableColumn[i]);
		}
	}
	
	public void addService(ArrayList<Object[]> services)
	{
		for( int i = 0; i < services.size(); i++ )
			serviceTableRows.add(services.get(i));
		
		updateServiceTable();
	}
	
	public void updateServiceTable()
	{	
		resetTables();
		
		for( int i = 0; i < serviceTableRows.size(); i++ )
		{
			for( int j = 0; j < serviceTableRows.get(i).length; j++ )
				System.out.println(serviceTableRows.get(i)[j]);
			System.out.println();
			serviceModel.addRow(serviceTableRows.get(i));
		}
		servicesTransactionTable.setModel(serviceModel);
	}
	
	public void addConsumables(ArrayList<ArrayList<Object[]>> consumables)
	{
		for( int i = 0; i < consumables.size(); i++ )
		{
//			for( int j = 0; j < consumables.get(i).size(); j++ )
//			{
//				Object[] row = {consumables.get(i).get(j), consumablesQuantity.get(i).get(j), "N/A", "N/A"};
//				productTableRows.add(row);
//			}
			this.consumables.add(consumables.get(i));
		}
		
//		updateProductTable();
	}
	
	public void addOverTheCounter(ArrayList<Object[]> products)
	{
		for( int i = 0; i < products.size(); i++ )
		{
			productTableRows.add(products.get(i));
		}
		updateProductTable();
	}
	
	public void updateProductTable()
	{	
		resetTables();
		
		for( int i = 0; i < productTableRows.size(); i++ )
		{
			productModel.addRow(productTableRows.get(i));
		}
		productsTransactionTable.setModel(productModel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == addServiceButton)
		{	
			if(addServicesHandler == true)
			{
				JOptionPane.showMessageDialog(null, "You cannot open multiple \"Add Service\" windows");
			}
			else
			{
				AddServicesGUI addServicesGUI = new AddServicesGUI( addServicesController, this );
				addServicesController.setView(addServicesGUI);
				addServicesHandler = true;
				addServicesGUI.addWindowListener(new WindowListener()
				{
					//format not followed to conserve space. functions only contained:
					// TODO Auto-generated method stub
					@Override
					public void windowOpened(WindowEvent arg0) {}
					
					@Override
					public void windowIconified(WindowEvent arg0) {}
					
					@Override
					public void windowDeiconified(WindowEvent arg0) {}
					
					@Override
					public void windowDeactivated(WindowEvent arg0) {}
					
					@Override
					public void windowClosing(WindowEvent e) 
					{
						// TODO Auto-generated method stub
						if( e.getSource() == addServicesGUI )
						{
							addServicesHandler = false;
						}
					}
					
					@Override
					public void windowClosed(WindowEvent e) {}
					
					@Override
					public void windowActivated(WindowEvent arg0) {}
				});
			}
		}
		else if( e.getSource() == addProductButton )
		{
			if(addProductsHandler == true)
			{
				JOptionPane.showMessageDialog(null, "You cannot open multiple \"Add Product\" windows");
			}
			else
			{
				AddProductsGUI addProductsGUI = new AddProductsGUI( addProductsController, this );
				addProductsController.setView(addProductsGUI);
				addProductsHandler = true;
				addProductsGUI.addWindowListener(new WindowListener()
				{
					//format not followed to conserve space. functions only contained:
					// TODO Auto-generated method stub
					@Override
					public void windowOpened(WindowEvent arg0) {}
					
					@Override
					public void windowIconified(WindowEvent arg0) {}
					
					@Override
					public void windowDeiconified(WindowEvent arg0) {}
					
					@Override
					public void windowDeactivated(WindowEvent arg0) {}
					
					@Override
					public void windowClosing(WindowEvent e) 
					{
						// TODO Auto-generated method stub
						if( e.getSource() == addProductsGUI )
						{
							addProductsHandler = false;
						}
					}
					
					@Override
					public void windowClosed(WindowEvent e) {}
					
					@Override
					public void windowActivated(WindowEvent arg0) {}
				});
			}
		}
		else if( e.getSource() == addPromoButton )
		{
			
		}
		else if( e.getSource() == clearButton )
		{
			resetAll();
		}
		else if( e.getSource() == saveTransactionButton )
		{
			
		}
		else if( e.getSource() == removeServiceButton )
		{
			
		}
		else if( e.getSource() == removeProductButton )
		{
			
		}
	}
	
	//GETTERS
	public int getConsumableQuantityUsed(String name)
	{
		int quantity = 0;
		
		for( int i = 0; i < consumables.size(); i++ )
		{
			for( int j = 0; j < consumables.get(i).size(); j++ )
			{
				if( ((String) consumables.get(i).get(j)[0]).equalsIgnoreCase(name) == true )
					quantity += ((int) consumables.get(i).get(j)[1]);
			}
		}
		
		return quantity;
	}
	
	public int getProductQuantity(String name)
	{
		int quantity = 0;
		
		for( int i = 0; i < productTableRows.size(); i++ )
		{
			if( ((String) productTableRows.get(i)[0]).equalsIgnoreCase(name) == true )
			{
				quantity += ((int) productTableRows.get(i)[1]);
			}
		}
		
		return quantity;
	}
	
	public ArrayList<ArrayList<Object[]>> getConsumables()
	{
		return consumables;
	}
}