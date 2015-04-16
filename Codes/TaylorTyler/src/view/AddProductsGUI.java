package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.AddProductsController;
import controller.AddServicesController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.OverTheCounter;
import model.Product;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class AddProductsGUI extends JFrame implements ActionListener, FocusListener, KeyListener
{
	private AddProductsController addProductsController;
	private AddTransactionGUI addTransactionGUI;
	
	private Border blackline;
	private JPanel addProductPanel;
	private JScrollPane productListScrollPane;
	private JButton finishButton;
	private JButton cancelButton;
	private JComboBox productComboBox;
	private JLabel quantityLabel;
	private JTextField quantityTextField;
	private JLabel piecesLabel;
	private JLabel quantityWarningLabel;
	private JLabel pricePerUnitLabel;
	private JLabel subtotalLabel;
	private JLabel productCountLabel;
	private JLabel totalPriceLabel;
	private JLabel customerNameLabel;
	private JTextField customerNameTextField;
	private JLabel nameWarningLabel;
	private JLabel productsAddedLabel;
	private JButton addProductButton;
	private JButton clearButton;
	private JLabel helpTextLabel;
	//private JLabel lblNewProduct;
	private JLabel productLabel;
	private JPanel addProductSummaryPanel;
	private JPanel productsAddedSummaryPanel;
	private JLabel detailsLabel;
	private JTable productListTable;
	private DefaultTableModel productTableModel;
	private DefaultComboBoxModel<String> productComboBoxModel;
	
	private String[] productListColumn = {"Product", "Quantity", "Price(per unit)", "Subtotal"};
	private ArrayList<Object[]> productsBought;
	private String[] productOptions;
	private double[] productPrices;
	private boolean bValidQuantityInput = false;
	private boolean bCustomerNameInDB = false;
	
	public AddProductsGUI( AddProductsController addProductsController, AddTransactionGUI addTransactionGUI ) 
	{
		this.addProductsController = addProductsController;
		this.addTransactionGUI = addTransactionGUI;
		
		productsBought = new ArrayList<>(0);
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(189, 183, 107));
		
		addProductPanel = new JPanel();
		addProductPanel.setBounds(10, 39, 349, 204);
		addProductPanel.setBackground(new Color(189, 183, 107));
		addProductPanel.setBorder(blackline);
		addProductPanel.setLayout(null);
		getContentPane().add(addProductPanel);
		
		productComboBox = new JComboBox();
		productComboBox.setBounds(122, 84, 197, 26);
		productComboBox.addActionListener(this);
		addProductPanel.add(productComboBox);
		
		productLabel = new JLabel("Product:");
		productLabel.setBounds(56, 84, 59, 26);
		addProductPanel.add(productLabel);
		
		quantityLabel = new JLabel("Quantity:");
		quantityLabel.setBounds(56, 121, 100, 26);
		addProductPanel.add(quantityLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(122, 121, 136, 26);
		quantityTextField.setText("Positive Integer");
		quantityTextField.setColumns(10);
		quantityTextField.addFocusListener(this);
		quantityTextField.addKeyListener(this);
		addProductPanel.add(quantityTextField);
		
		piecesLabel = new JLabel("piece/s");
		piecesLabel.setBounds(268, 121, 66, 26);
		addProductPanel.add(piecesLabel);
		
		quantityWarningLabel = new JLabel("Please input positive integer");
		quantityWarningLabel.setBounds(122, 158, 222, 35);
		quantityWarningLabel.setVisible(false);
		addProductPanel.add(quantityWarningLabel);
		
		customerNameLabel = new JLabel("Customer Name:");
		customerNameLabel.setBounds(10, 24, 100, 26);
		addProductPanel.add(customerNameLabel);
		
		customerNameTextField = new JTextField();
		customerNameTextField.setBounds(122, 24, 197, 26);
		customerNameTextField.setText("Enter customer name here...");
		customerNameTextField.setColumns(10);
		customerNameTextField.addFocusListener(this);
		customerNameTextField.addKeyListener(this);
		addProductPanel.add(customerNameTextField);
		
		nameWarningLabel = new JLabel("Customer name not in the database.");
		nameWarningLabel.setBounds(122, 56, 212, 27);
		nameWarningLabel.setVisible(false);
		addProductPanel.add(nameWarningLabel);

//		lblNewProduct = new JLabel("+ New Product ");
//		productListScrollPane.setColumnHeaderView(lblNewProduct);
		
		finishButton = new JButton("Finish");
		finishButton.setForeground(Color.BLACK);
		finishButton.setBackground(new Color(0, 128, 0));
		finishButton.setBounds(497, 278, 96, 23);
		finishButton.addActionListener(this);
		finishButton.setEnabled(false);
		getContentPane().add(finishButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(391, 278, 96, 23);
		cancelButton.addActionListener(this);
		getContentPane().add(cancelButton);
		
		productCountLabel = new JLabel("Product Count: 0");
		productCountLabel.setBounds(372, 228, 138, 23);
		getContentPane().add(productCountLabel);
		
		totalPriceLabel = new JLabel("Total Price: P 0.0");
		totalPriceLabel.setBounds(372, 247, 138, 23);
		getContentPane().add(totalPriceLabel);
		
		productsAddedLabel = new JLabel("Products Added");
		productsAddedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		productsAddedLabel.setBounds(428, 11, 118, 23);
		getContentPane().add(productsAddedLabel);
		
		helpTextLabel = new JLabel("Help Text");
		helpTextLabel.setBounds(10, 306, 583, 23);
		getContentPane().add(helpTextLabel);
		
		addProductButton = new JButton("Add Product");
		addProductButton.setBounds(191, 278, 107, 23);
		addProductButton.addActionListener(this);
		addProductButton.setEnabled(false);
		getContentPane().add(addProductButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(69, 278, 112, 23);
		clearButton.addActionListener(this);
		getContentPane().add(clearButton);
		
		pricePerUnitLabel = new JLabel("Price (per unit):");
		pricePerUnitLabel.setBounds(104, 245, 133, 23);
		getContentPane().add(pricePerUnitLabel);
		
		subtotalLabel = new JLabel("Subtotal:");
		subtotalLabel.setBounds(247, 245, 112, 23);
		getContentPane().add(subtotalLabel);
		
		addProductSummaryPanel = new JPanel();
		addProductSummaryPanel.setBackground(new Color(189, 183, 107));
		addProductSummaryPanel.setBounds(89, 242, 270, 28);
		addProductSummaryPanel.setBorder(blackline);
		getContentPane().add(addProductSummaryPanel);
		
		productsAddedSummaryPanel = new JPanel();
		productsAddedSummaryPanel.setBounds(369, 226, 224, 44);
		productsAddedSummaryPanel.setBackground(new Color(189, 183, 107));
		productsAddedSummaryPanel.setBorder(blackline);
		getContentPane().add(productsAddedSummaryPanel);
		
		detailsLabel = new JLabel("Details");
		detailsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		detailsLabel.setBounds(155, 8, 84, 28);
		getContentPane().add(detailsLabel);
		
		productTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < productListColumn.length; i++ )
		{
			productTableModel.addColumn(productListColumn[i]);
		}
		
		productListTable = new JTable(productTableModel);
		productListScrollPane = new JScrollPane(productListTable);
		productListScrollPane.setBounds(369, 39, 224, 188);
		productListScrollPane.setBackground(new Color(189, 183, 107));
		productListScrollPane.setBorder(blackline);
		getContentPane().add(productListScrollPane);
		
		ListSelectionModel listSelectionModel = productListTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{ 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				if(!lsm.isSelectionEmpty())
				{
//					reviewServiceDetails();
				}
			};
		});
		
		this.setTitle("Add Products");
		this.setLocation(325, 100);
		this.setVisible(true);
		this.setResizable(false);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(607, 358);
	}
	
	//ACTION LISTENER FUNCTION
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == productComboBox )
		{
			updateLabels(true);
		}
		else if( e.getSource() == clearButton )
		{
			clearForm();
		}
		else if( e.getSource() == addProductButton )
		{
			int temp = 0;
			if( checkQuantityInput() == true )
			{
				temp = Integer.parseInt(quantityTextField.getText());
				if( checkProductQuantity(productOptions[productComboBox.getSelectedIndex()], temp) == true && bValidQuantityInput == true )
				{
					addToProductTable(productOptions[productComboBox.getSelectedIndex()], temp, productPrices[productComboBox.getSelectedIndex()]);
					
					productsBought.add(new Object[] {customerNameTextField.getText(), productOptions[productComboBox.getSelectedIndex()], temp, productPrices[productComboBox.getSelectedIndex()], temp * productPrices[productComboBox.getSelectedIndex()]});
					
					clearForm();
				}
				temp = 0;
			}
		}
		else if( e.getSource() == cancelButton )
		{
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else if( e.getSource() == finishButton )
		{
			this.addTransactionGUI.addOverTheCounter(productsBought);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
		if( productsBought.size() > 0 )
		{
			finishButton.setEnabled(true);
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
		
		if( bCustomerNameInDB == false || bValidQuantityInput == false )
		{
			addProductButton.setEnabled(false);
		}
		else if( bCustomerNameInDB == true && bValidQuantityInput == true )
		{
			addProductButton.setEnabled(true);
		}
	}
	
//	public void resetTables()
//	{
//		productTableModel = new DefaultTableModel()
//		{
//			public boolean isCellEditable(int row, int column)
//			{
//				return false;// This causes all cells to be not editable
//			}
//		};
//		
//		for( int i = 0; i < productListColumn.length; i++ )
//		{
//			productTableModel.addColumn(productListColumn[i]);
//		}
//		
//	}
	
	public void clearForm()
	{
		customerNameTextField.setText("Enter customer name here...");
		customerNameTextField.setBackground(Color.WHITE);
		productComboBox.setSelectedIndex(0);
		quantityTextField.setText("Positive Integer");
		quantityTextField.setBackground(Color.WHITE);
		bValidQuantityInput = false;
		addProductButton.setEnabled(false);
		updateLabels(false);
	}
	
	public void addToProductTable(String productName, int quantity, double price )
	{
		Object[] row = {productName, quantity, price, quantity * price};
		productTableModel.addRow(row);
	}

	public void updateLabels(boolean b)
	{
		int productCount = 0;
		double subtotal = 0;
		
		if( b == true )
		{
			if( bValidQuantityInput == true )
			{
				pricePerUnitLabel.setText("Price (per unit): P " + productPrices[productComboBox.getSelectedIndex()]);
				subtotalLabel.setText("Subtotal: P " + productPrices[productComboBox.getSelectedIndex()] * Integer.parseInt(quantityTextField.getText()));
			}
			
			for( int i = 0; i < productsBought.size(); i++ )
			{
				productCount += ((int) productsBought.get(i)[2]);
			}
			productCountLabel.setText("Product Count: " + productCount);
			
			for( int i = 0; i < productsBought.size(); i++ )
			{
				subtotal += getProductSubtotal((String) productsBought.get(i)[1], (int) productsBought.get(i)[2]);
			}
			totalPriceLabel.setText("Total Price: P " + subtotal);
		}
		else if( b == false )
		{
			pricePerUnitLabel.setText("Price (per unit): ");
			subtotalLabel.setText("Subtotal: ");
		}
	}
	
	public double getProductSubtotal(String productName, int quantity)
	{
		for( int i = 0; i < productOptions.length; i++ )
		{
			if( productOptions[i].equalsIgnoreCase(productName) == true )
				return productPrices[i] * quantity;
		}
		return 0;
	}
	
	//INPUT VALIDATION METHODS
	public void checkClient()
	{
		String s = customerNameTextField.getText();
		if( s.equalsIgnoreCase("Client one") || s.equalsIgnoreCase("Client two") ||
		    s.equalsIgnoreCase("Client three") || s.equalsIgnoreCase("Client four") )
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
			updateLabels(true);
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
	
	public boolean checkProductQuantity( String name, int temp )
	{
		Product p = addProductsController.getProduct(name);
		int quantity = 0;
		
		quantity += this.addTransactionGUI.getProductQuantity(name);
		
		for( int i = 0; i < productsBought.size(); i++ )
		{
			if( ((String) productsBought.get(i)[0]).equalsIgnoreCase(name) == true )
				quantity += ((int) productsBought.get(i)[1]);
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
	
	//GETTERS
	public void getData()
	{
		addProductsController.getProducts();
		
		this.repaint();
		this.revalidate();
	}
	
	public void getOverTheCounterList(Iterator i)
	{
		ArrayList<Object> p = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			p.add((OverTheCounter) i.next());
		}
		
		productOptions = new String[p.size()];
		productPrices = new double[p.size()];
		
		for( int x = 0; x < p.size(); x++ )
		{
			productOptions[x] = ((OverTheCounter) p.get(x)).getsName();
			productPrices[x] = ((OverTheCounter) p.get(x)).getdPrice();
		}
		
		productComboBoxModel = new DefaultComboBoxModel<>(productOptions);
		productComboBox.setModel(productComboBoxModel);
	}
}
