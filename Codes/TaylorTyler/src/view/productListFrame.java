package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Consumable;

public class ProductListFrame extends JFrame
{
	private AddTransactionPanel	mainFrame;
	private JTable				productsTable;
	private JScrollPane			scroll;
	private JButton			addButton;
	private DefaultTableModel tModel;
	
	public ProductListFrame(AddTransactionPanel mainFrame)
	{
		this.mainFrame = mainFrame;
		tModel = new DefaultTableModel();
		tModel.addColumn("Product");
		tModel.addColumn("Quantity");
		
		ActListener actListener = new ActListener();
	
		productsTable = new JTable(tModel);
		productsTable.setBounds(10, 10, 275, 300);
		scroll = new JScrollPane(productsTable);
		scroll.setBounds(10, 10, 275, 300);
		addButton = new JButton("Add");
		addButton.setBounds(10, 320, 275, 30);
		addButton.setEnabled(false);
		addButton.addActionListener(actListener);
		ListSelectionModel listSelectionModel = productsTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{ 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				addButton.setEnabled(!lsm.isSelectionEmpty());
			};
			});
		
		
		getContentPane().setLayout(null);

		getContentPane().add(scroll);
		getContentPane().add(addButton);

		loadProducts(mainFrame.getConsumables());
		
		this.setResizable(false);

		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void loadProducts( Iterator products )
	{
		tModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		tModel.addColumn("Product");
		tModel.addColumn("Quantity");

		while ( products.hasNext() == true )
		{
			Object o = new Object();
			o = ((Consumable) products.next());
			
			String[] entry = { ((Consumable) o).getsName(), ((Consumable) o).getsMeasurement() };
			tModel.addRow(entry);
		}

		productsTable.setModel(tModel);
		this.repaint();
		this.revalidate();
	}
	
	private class ActListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if( e.getSource() == addButton )
			{
				int x = productsTable.getSelectedRow();
				String consumable = (String) productsTable.getModel().getValueAt(x, 0);
				mainFrame.setSelectedProduct(consumable);
				JOptionPane.showMessageDialog(null, consumable + " assigned", "Add Consumable", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		}
		
	}
}
