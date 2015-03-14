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

	public ProductListFrame(AddTransactionPanel mainFrame)
	{

		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Product");
		tModel.addColumn("Quantity");
		
		productsTable = new JTable(tModel);
		productsTable.setBounds(10, 10, 275, 300);
		scroll = new JScrollPane(productsTable);
		scroll.setBounds(10, 10, 275, 300);
		addButton = new JButton("Add");
		addButton.setBounds(10, 320, 275, 30);

		getContentPane().setLayout(null);

		getContentPane().add(scroll);
		getContentPane().add(addButton);

		loadProducts(mainFrame.getProducts());
		
		this.setResizable(false);

		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public void loadProducts( Iterator products )
	{
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Product");
		tModel.addColumn("Quantity");

		while ( products.hasNext() == true )
		{
			Object o = new Object();
			o = ((Product) products.next());
			
			String[] entry = { ((Product) o).getsName(), Integer.toString(((Product) o).getnQuantity()) };
			tModel.addRow(entry);
		}

		productsTable.setModel(tModel);
		this.repaint();
		this.revalidate();
	}

}
