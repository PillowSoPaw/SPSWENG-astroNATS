package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.AddProductsController;

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

import model.Employee;
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

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

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
	private JTextField discountTextField;
	private JLabel discountLabel;
	private JLabel percentLabel;
	private JLabel discountWarningLabel;
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
	private DefaultComboBoxModel<String> employeeComboBoxModel;
	
	private String[] productListColumn = {"Customer", "Product", "Quantity", "Price(per unit)", "Subtotal"};
	private ArrayList<Object[]> productsBought;
	private String[] productOptions;
	private double[] productPrices;
	private String[] staff;
	private boolean bValidQuantityInput = false;
	private boolean bCustomerNameInDB = false;
	private boolean bHasDiscount = false;
	private int clientIndex;
	private JLabel soldByLabel;
	private JComboBox employeeComboBox;
	
	public AddProductsGUI( AddProductsController addProductsController, AddTransactionGUI addTransactionGUI ) 
	{
		this.addProductsController = addProductsController;
		this.addTransactionGUI = addTransactionGUI;
		
		productsBought = new ArrayList<>(0);
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(189, 183, 107));
		
		addProductPanel = new JPanel();
		addProductPanel.setBounds(10, 39, 349, 231);
		addProductPanel.setBackground(new Color(189, 183, 107));
		addProductPanel.setBorder(blackline);
		addProductPanel.setLayout(null);
		getContentPane().add(addProductPanel);
		
		productComboBox = new JComboBox();
		productComboBox.setBounds(122, 71, 197, 26);
		productComboBox.addActionListener(this);
		addProductPanel.add(productComboBox);
		
		productLabel = new JLabel("Product:");
		productLabel.setBounds(56, 71, 59, 26);
		addProductPanel.add(productLabel);
		
		quantityLabel = new JLabel("Quantity:");
		quantityLabel.setBounds(56, 108, 100, 26);
		addProductPanel.add(quantityLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(122, 108, 136, 26);
		quantityTextField.setText("Positive Integer");
		quantityTextField.setColumns(10);
		quantityTextField.addFocusListener(this);
		quantityTextField.addKeyListener(this);
		addProductPanel.add(quantityTextField);
		
		piecesLabel = new JLabel("piece/s");
		piecesLabel.setBounds(268, 108, 66, 26);
		addProductPanel.add(piecesLabel);
		
		quantityWarningLabel = new JLabel("Please input positive integer");
		quantityWarningLabel.setBounds(123, 134, 222, 19);
		quantityWarningLabel.setVisible(false);
		addProductPanel.add(quantityWarningLabel);
		
		customerNameLabel = new JLabel("Customer Name:");
		customerNameLabel.setBounds(10, 11, 100, 26);
		addProductPanel.add(customerNameLabel);
		
		customerNameTextField = new JTextField();
		customerNameTextField.setBounds(122, 11, 197, 26);
		customerNameTextField.setText("Enter customer name here...");
		customerNameTextField.setColumns(10);
		customerNameTextField.addFocusListener(this);
		customerNameTextField.addKeyListener(this);
		addProductPanel.add(customerNameTextField);
		
		nameWarningLabel = new JLabel("Customer name not in the database.");
		nameWarningLabel.setBounds(122, 43, 212, 27);
		nameWarningLabel.setVisible(false);
		addProductPanel.add(nameWarningLabel);
		
		discountTextField = new JTextField();
		discountTextField.setBounds(122, 187, 47, 20);
		discountTextField.setColumns(10);
		discountTextField.setText(Integer.toString(0));
		discountTextField.addKeyListener(this);
		discountTextField.addFocusListener(this);
		addProductPanel.add(discountTextField);
		
		discountLabel = new JLabel("Discount (in %):");
		discountLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		discountLabel.setBounds(10, 190, 97, 14);
		addProductPanel.add(discountLabel);
		
		percentLabel = new JLabel("%");
		percentLabel.setBounds(179, 190, 46, 14);
		addProductPanel.add(percentLabel);
		
		discountWarningLabel = new JLabel("Input 0 - 100 only");
		discountWarningLabel.setBounds(123, 209, 102, 14);
		discountWarningLabel.setVisible(false);
		addProductPanel.add(discountWarningLabel);
		
		soldByLabel = new JLabel("Sold By:");
		soldByLabel.setBounds(56, 161, 46, 14);
		addProductPanel.add(soldByLabel);
		
		employeeComboBox = new JComboBox();
		employeeComboBox.setBounds(122, 156, 197, 20);
		addProductPanel.add(employeeComboBox);

//		lblNewProduct = new JLabel("+ New Product ");
//		productListScrollPane.setColumnHeaderView(lblNewProduct);
		
		finishButton = new JButton("Finish");
		finishButton.setForeground(Color.BLACK);
		finishButton.setBackground(new Color(0, 128, 0));
		finishButton.setBounds(584, 305, 96, 23);
		finishButton.addActionListener(this);
		finishButton.setEnabled(false);
		getContentPane().add(finishButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(478, 305, 96, 23);
		cancelButton.addActionListener(this);
		getContentPane().add(cancelButton);
		
		productCountLabel = new JLabel("Product Count: 0");
		productCountLabel.setBounds(372, 255, 138, 23);
		getContentPane().add(productCountLabel);
		
		totalPriceLabel = new JLabel("Total Price: P 0.0");
		totalPriceLabel.setBounds(372, 274, 138, 23);
		getContentPane().add(totalPriceLabel);
		
		productsAddedLabel = new JLabel("Products Added");
		productsAddedLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		productsAddedLabel.setBounds(372, 11, 118, 23);
		getContentPane().add(productsAddedLabel);
		
		helpTextLabel = new JLabel("Help Text");
		helpTextLabel.setBounds(10, 333, 776, 23);
		getContentPane().add(helpTextLabel);
		
		addProductButton = new JButton("Add Product");
		addProductButton.setBounds(191, 305, 107, 23);
		addProductButton.addActionListener(this);
		addProductButton.setEnabled(false);
		getContentPane().add(addProductButton);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(69, 305, 112, 23);
		clearButton.addActionListener(this);
		getContentPane().add(clearButton);
		
		pricePerUnitLabel = new JLabel("Price (per unit):");
		pricePerUnitLabel.setBounds(104, 272, 133, 23);
		getContentPane().add(pricePerUnitLabel);
		
		subtotalLabel = new JLabel("Subtotal:");
		subtotalLabel.setBounds(247, 272, 112, 23);
		getContentPane().add(subtotalLabel);
		
		addProductSummaryPanel = new JPanel();
		addProductSummaryPanel.setBackground(new Color(189, 183, 107));
		addProductSummaryPanel.setBounds(89, 269, 270, 28);
		addProductSummaryPanel.setBorder(blackline);
		getContentPane().add(addProductSummaryPanel);
		
		productsAddedSummaryPanel = new JPanel();
		productsAddedSummaryPanel.setBounds(369, 253, 417, 44);
		productsAddedSummaryPanel.setBackground(new Color(189, 183, 107));
		productsAddedSummaryPanel.setBorder(blackline);
		getContentPane().add(productsAddedSummaryPanel);
		
		detailsLabel = new JLabel("Details");
		detailsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		detailsLabel.setBounds(10, 8, 84, 28);
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
		productListTable.getTableHeader().setReorderingAllowed(false);
		productListTable.getTableHeader().setResizingAllowed(false);
		productListScrollPane = new JScrollPane(productListTable);
		productListScrollPane.setBounds(369, 40, 417, 213);
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
		this.setSize(802, 385);
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
					double price = productPrices[productComboBox.getSelectedIndex()];
					double subtotalPrice = 0;
					
					subtotalPrice = price * temp;
					
					if( bHasDiscount == true )
						subtotalPrice *= ( 1 - (Double.parseDouble(discountTextField.getText()) / 100) );
					
					addToProductTable(addTransactionGUI.getClients().get(clientIndex), productOptions[productComboBox.getSelectedIndex()], temp, price, subtotalPrice);
					
					productsBought.add(new Object[] {addTransactionGUI.getClients().get(clientIndex), productOptions[productComboBox.getSelectedIndex()], temp, price, subtotalPrice,  staff[employeeComboBox.getSelectedIndex()]});
					
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
				updateLabels(bValidQuantityInput);
			}
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
		else if( e.getSource() == discountTextField )
			checkDiscountInput();
	}
	
	public void clearForm()
	{
		clientIndex = -1;
		customerNameTextField.setText("Enter customer name here...");
		customerNameTextField.setBackground(Color.WHITE);
		productComboBox.setSelectedIndex(0);
		employeeComboBox.setSelectedIndex(0);
		quantityTextField.setText("Positive Integer");
		quantityTextField.setBackground(Color.WHITE);
		discountTextField.setBackground(Color.WHITE);
		bValidQuantityInput = false;
		bHasDiscount = false;
		addProductButton.setEnabled(false);
		updateLabels(false);
	}
	
	public void addToProductTable(String customer, String productName, int quantity, double price, double subtotal )
	{
		Object[] row = {customer, productName, quantity, price, subtotal};
		productTableModel.addRow(row);
	}

	public void updateLabels(boolean b)
	{
		int productCount = 0;
		double subtotal = 0;
		double price = 0;
		
		if( b == true )
		{
			if( bValidQuantityInput == true )
			{
				price = productPrices[productComboBox.getSelectedIndex()];
				
				pricePerUnitLabel.setText("Price (per unit): P " + price);
				
				price *= Integer.parseInt(quantityTextField.getText());
				
				if( bHasDiscount == true )
					price *= 1 - (Double.parseDouble(discountTextField.getText()) / 100);
				
				subtotalLabel.setText("Subtotal: P " + price);
			}
			
			for( int i = 0; i < productsBought.size(); i++ )
			{
				productCount += ((int) productsBought.get(i)[2]);
			}
			productCountLabel.setText("Product Count: " + productCount);
			
			for( int i = 0; i < productsBought.size(); i++ )
			{
				subtotal += (double) productsBought.get(i)[4];
//				subtotal += getProductSubtotal((String) productsBought.get(i)[1], (int) productsBought.get(i)[2]);
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
			resetDiscountTextField(bValidQuantityInput);
			updateLabels(true);
			return true;
		} 
		catch (NumberFormatException ex)
		{
			quantityTextField.setBackground(Color.RED);
			quantityWarningLabel.setVisible(true);
			bValidQuantityInput = false;
			resetDiscountTextField(bValidQuantityInput);
			addProductButton.setEnabled(false);
		}
		catch (IllegalArgumentException ex)
		{
			quantityTextField.setBackground(Color.RED);
			quantityWarningLabel.setVisible(true);
			bValidQuantityInput = false;
			resetDiscountTextField(bValidQuantityInput);
			addProductButton.setEnabled(false);
		}
		return false;
	}
	
	public void resetDiscountTextField( boolean enable )
	{
		discountTextField.setEnabled(enable);
		if( enable == false )
		{
			discountTextField.setText(Integer.toString(0));
			discountTextField.setBackground(Color.WHITE);
		}
	}
	
	public boolean checkDiscountInput()
	{
		try
		{
			int temp = Integer.parseInt(discountTextField.getText());
			
			if( temp < 0 || temp >= 101 )
				throw new IllegalArgumentException();
			
			addProductButton.setEnabled(true);
			discountTextField.setBackground(Color.GREEN);
			discountWarningLabel.setVisible(false);
			if( temp != 0 )
				bHasDiscount = true;
			else
				discountTextField.setBackground(Color.WHITE);
			updateLabels(true);
			return true;
		} 
		catch (NumberFormatException ex)
		{
			discountTextField.setBackground(Color.RED);
			discountWarningLabel.setVisible(true);
			bHasDiscount = false;
			addProductButton.setEnabled(false);
		}
		catch (IllegalArgumentException ex)
		{
			discountTextField.setBackground(Color.RED);
			discountWarningLabel.setVisible(true);
			bValidQuantityInput = false;
			addProductButton.setEnabled(false);
		}
		return false;
	}
	
	public boolean checkProductQuantity( String name, int temp )
	{
		Product p = addProductsController.getProduct(name);
		int quantity = 0;
		
		quantity += this.addTransactionGUI.getConsumableQuantityUsed(name);
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
		addProductsController.getStaff();
		
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
	
	public void getStaffList(Iterator i)
	{
		ArrayList<Object> e = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			e.add((Employee) i.next());
		}
		
		staff = new String[e.size()+1];
		staff[0] = "";
		for( int x = 0; x < e.size(); x++ )
			staff[x+1] = ((Employee) e.get(x)).getsName();
		
		employeeComboBoxModel = new DefaultComboBoxModel<>(staff);
		employeeComboBox.setModel(employeeComboBoxModel);
	}
}
