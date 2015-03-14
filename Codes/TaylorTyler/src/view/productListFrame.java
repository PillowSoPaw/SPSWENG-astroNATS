package view;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Product;

public class ProductListFrame extends JFrame
{
	private AddTransactionPanel	mainFrame;
	private JTable				productsTable;
	private JScrollPane			scroll;
	private JButton			addButton;
//	private Service			service;
	private Iterator<Product>	productsUsed;

	public ProductListFrame(Iterator<Product> products)
	{
		this.productsUsed = products;
		
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Product");
		tModel.addColumn("Quantity");

		//this.service = service;

		productsTable = new JTable(tModel);
		productsTable.setBounds(10, 10, 275, 300);
		scroll = new JScrollPane(productsTable);
		scroll.setBounds(10, 10, 275, 300);
		addButton = new JButton("Add");
		addButton.setBounds(10, 320, 275, 30);

		getContentPane().setLayout(null);

		getContentPane().add(scroll);
		getContentPane().add(addButton);

		loadProducts();

		this.setResizable(false);

		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public void loadProducts()
	{
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Product");
		tModel.addColumn("Quantity");

		while ( productsUsed.hasNext() == true )
		{
			Object o = new Object();
			o = ((Product) productsUsed.next());
			
			String[] entry = { ((Product) o).getsName(), Integer.toString(((Product) o).getnQuantity()) };
			tModel.addRow(entry);
		}

		productsTable.setModel(tModel);
	}

}
