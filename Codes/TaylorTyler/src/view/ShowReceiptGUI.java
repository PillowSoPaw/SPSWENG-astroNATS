package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

public class ShowReceiptGUI extends JFrame
{
	
	private String title;
	private Border blackline;
	
	private JScrollPane servicesTransactionScrollPane;
	private JScrollPane productsTransactionScrollPane;
	private JTable servicesTransactionTable;
	private JTable productsTransactionTable;
	private DefaultTableModel serviceModel;
	private DefaultTableModel productModel;
	private String[] serviceTableColumn = {"Customer Name", "Service", "Senior E.", "Junior E.", "Price"};
	private String[] productTableColumn = {"Customer Name", "Product", "Quantity", "Price(per unit)", "Subtotal"};
	private ArrayList<Object[]> serviceTableRows;
	private ArrayList<Object[]> productTableRows;
	private JLabel servicesLabel;
	private JLabel productsLabel;
	private JLabel customerNameLabel;
	private JLabel dateLabel;
	private JLabel separatorLabel;
	private JLabel serviceCountLabel;
	private JLabel serviceSubtotalLabel;
	private JLabel productCountLabel;
	private JLabel productsSubTotal;
	private JLabel totalPriceLabel;
	private JPanel receiptPanel;
	
	public ShowReceiptGUI( String clientName, String date, ArrayList<Object[]> services, ArrayList<Object[]> products ) 
	{
		this.serviceTableRows = services;
		this.productTableRows = products;
		
		this.setTitle("Receipt");
		getContentPane().setBackground(new Color(128, 128, 0));
		getContentPane().setLayout(null);
		this.setSize(700, 580);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
		receiptPanel = new JPanel();
		receiptPanel.setBounds(0, 0, 711, 580);
		receiptPanel.setBackground(new Color(128, 128, 0));
		receiptPanel.setLayout(null);
		getContentPane().add(receiptPanel);
		
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
		servicesTransactionScrollPane.setBounds(10, 74, 338, 395);
		servicesTransactionScrollPane.setBorder(blackline);
		servicesTransactionScrollPane.setVisible(true);
		receiptPanel.add(servicesTransactionScrollPane);
		
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
		productsTransactionScrollPane.setBounds(363, 74, 322, 395);
		productsTransactionScrollPane.setBorder(blackline);
		productsTransactionScrollPane.setVisible(true);
		receiptPanel.add(productsTransactionScrollPane);
		
		servicesLabel = new JLabel("Services");
		servicesLabel.setForeground(Color.WHITE);
		servicesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		servicesLabel.setBounds(10, 46, 200, 27);
		servicesLabel.setVisible(true);
		receiptPanel.add(servicesLabel);
		
		productsLabel = new JLabel("Products");
		productsLabel.setForeground(Color.WHITE);
		productsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		productsLabel.setBounds(363, 46, 200, 27);
		productsLabel.setVisible(true);
		receiptPanel.add(productsLabel);
		
		customerNameLabel = new JLabel("Customer Name: " + clientName);
		customerNameLabel.setForeground(Color.WHITE);
		customerNameLabel.setBounds(10, 11, 371, 27);
		customerNameLabel.setVisible(true);
		receiptPanel.add(customerNameLabel);
		
		dateLabel = new JLabel("Date/Time: " + date);
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setBounds(391, 10, 294, 28);
		dateLabel.setVisible(true);
		receiptPanel.add(dateLabel);
		
		separatorLabel = new JLabel("________________________________________________________________________________________________");
		separatorLabel.setBounds(10, 23, 675, 22);
		separatorLabel.setVisible(true);
		receiptPanel.add(separatorLabel);
		
		
		serviceCountLabel = new JLabel("Service Count: " + services.size());
		serviceCountLabel.setForeground(Color.WHITE);
		serviceCountLabel.setBounds(10, 475, 200, 22);
		serviceCountLabel.setVisible(true);
		receiptPanel.add(serviceCountLabel);
		
		double serviceSubtotal = 0;
		
		for( int i = 0; i < services.size(); i++ )
			serviceSubtotal += (double) services.get(i)[4];
		
		serviceSubtotalLabel = new JLabel("Services Subtotal: P " + serviceSubtotal);
		serviceSubtotalLabel.setForeground(Color.WHITE);
		serviceSubtotalLabel.setBounds(10, 495, 200, 22);
		serviceSubtotalLabel.setVisible(true);
		receiptPanel.add(serviceSubtotalLabel);
		
		productCountLabel = new JLabel("Product Count: " + products.size());
		productCountLabel.setForeground(Color.WHITE);
		productCountLabel.setBounds(391, 475, 200, 22);
		productCountLabel.setVisible(true);
		receiptPanel.add(productCountLabel);
		
		double productSubtotal = 0;
		for( int i = 0; i < products.size(); i++ )
			productSubtotal += (double) products.get(i)[4];
		
		productsSubTotal = new JLabel("Products Subtotal: P " + productSubtotal);
		productsSubTotal.setForeground(Color.WHITE);
		productsSubTotal.setBounds(391, 495, 200, 22);
		productsSubTotal.setVisible(true);
		receiptPanel.add(productsSubTotal);
		
		totalPriceLabel = new JLabel("Total Price: P " + (serviceSubtotal + productSubtotal));
		totalPriceLabel.setForeground(Color.WHITE);
		totalPriceLabel.setBounds(485, 519, 200, 22);
		totalPriceLabel.setVisible(true);
		receiptPanel.add(totalPriceLabel);
		
		this.repaint();
		receiptPanel.repaint();
		
		updateServiceTable();
		updateProductTable();
	}
	
	public void updateServiceTable()
	{	
		for( int i = 0; i < serviceTableRows.size(); i++ )
		{
			serviceModel.addRow(serviceTableRows.get(i));
		}
		servicesTransactionTable.setModel(serviceModel);
	}
	
	public void updateProductTable()
	{	
		for( int i = 0; i < productTableRows.size(); i++ )
		{
			productModel.addRow(productTableRows.get(i));
		}
		productsTransactionTable.setModel(productModel);
	}
}
