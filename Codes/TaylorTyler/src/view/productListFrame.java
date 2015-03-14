package view;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Product;

public class ProductListFrame extends JFrame
{
	private AddTransactionPanel	mainFrame;
	private JTable				products;
	private JScrollPane			scroll;
	private JButton			add;
	private Service			service;
	private Iterator<Product>	productsUsed;

	public ProductListFrame(AddTransactionPanel mainFrame, Service service)
	{
		this.mainFrame = mainFrame;
		productsUsed = mainFrame.getConsumables();
		
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Product");
		tModel.addColumn("Quantity");

		this.service = service;

		products = new JTable(tModel);
		products.setBounds(10, 10, 275, 300);
		scroll = new JScrollPane(products);
		scroll.setBounds(10, 10, 275, 300);
		add = new JButton("Add");
		add.setBounds(10, 320, 275, 30);

		this.setLayout(null);

		add(scroll);
		add(add);

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

		int i = 0;
		String[] names = service.getProductNames();

		while (i < names.length)
		{
			Object[] entry = { names[i], "Input Quantity" };
			tModel.addRow(entry);

			i++;
		}

		products.setModel(tModel);
	}

}
