package view;

import controller.InventoryController;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class InventoryGUI extends JPanel implements ActionListener,
		ListSelectionListener
{
	public Border blackline;
	private String title;

	private JTabbedPane mainTabbedPane;
	private JPanel notificationsTab;
	private JPanel productsTab;
	private JScrollPane productsScrollPane;
	private JTextField searchProductTextField;
	private JLabel filterByLabel;
	private JLabel productsLabel;
	private JScrollPane notificationScrollPane;
	private JLabel sortByLabel2;
	// private JComboBox<String> categoryComboBox2;
	private JButton markDoneButton;
	private JLabel searchLabel2;
	private JTextField searchTextField2;
	private JButton goButton;
	private JButton searchButton;
	private JComboBox<String> categoryComboBox;
	private JTable inventoryTable;
	private JButton deleteProductButton;
	private JButton editProductButton;
	private JButton addProductButton;
	private JButton restockButton;

	private DefaultTableModel inventoryModel;
	private String[] inventoryTableColumn = { "Product ID", "Product Name", "Quantity" };
	private ArrayList<Object[]> inventoryTableRows;

	private InventoryController controller;

	public InventoryGUI(InventoryController controller)
	{
		this.controller = controller;
		this.controller.setView(this);
		blackline = BorderFactory.createLineBorder(Color.black);

		this.title = "Inventory";
		this.setBackground(new Color(189, 183, 107));
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		this.setLayout(null);

		productsTab = new JPanel();
		productsTab.setBackground(new Color(128, 128, 0));
		productsTab.setBounds(0, 0, 821, 483);
		productsTab.setBorder(blackline);
		productsTab.setLayout(null);
		add(productsTab);

		searchProductTextField = new JTextField("Enter Product Name here...");
		searchProductTextField.setBounds(10, 37, 168, 23);
		productsTab.add(searchProductTextField);
		searchProductTextField.setColumns(10);

		searchButton = new JButton("Search");
		searchButton.setBounds(188, 37, 87, 23);
		searchButton.addActionListener(this);
		productsTab.add(searchButton);

		filterByLabel = new JLabel("Filter:");
		filterByLabel.setForeground(new Color(255, 255, 255));
		filterByLabel.setBounds(628, 40, 46, 14);
		productsTab.add(filterByLabel);

		categoryComboBox = new JComboBox<String>(new String[] { "All", "Consumables", "Over-the-Counter", "Hybrid" });
		categoryComboBox.setBounds(672, 37, 139, 20);
		categoryComboBox.addActionListener(this);
		productsTab.add(categoryComboBox);

		productsLabel = new JLabel("Products");
		productsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		productsLabel.setForeground(new Color(255, 255, 255));
		productsLabel.setBounds(10, 11, 78, 21);
		productsTab.add(productsLabel);

		// delete - edit - add only available to owner. salon manager may
		// request delete-edit-add(to follow)
		deleteProductButton = new JButton("Delete Product");
		deleteProductButton.setToolTipText("Delete the selected product");
		deleteProductButton.setBounds(523, 449, 139, 23);
		deleteProductButton.setEnabled(false);
		deleteProductButton.addActionListener(this);
		productsTab.add(deleteProductButton);

		editProductButton = new JButton("Edit Product");
		editProductButton.setToolTipText("Edit the selected product details");
		editProductButton.setBounds(374, 449, 139, 23);
		editProductButton.setEnabled(false);
		editProductButton.addActionListener(this);
		productsTab.add(editProductButton);

		addProductButton = new JButton("Add Product");
		addProductButton.setToolTipText("Add a product to inventory");
		addProductButton.setBounds(285, 37, 108, 23);
		addProductButton.addActionListener(this);
		productsTab.add(addProductButton);

		restockButton = new JButton("Restock");
		restockButton.setToolTipText("Restock a product in the inventory");
		restockButton.setBounds(225, 449, 139, 23);
		restockButton.setEnabled(false);
		restockButton.addActionListener(this);
		productsTab.add(restockButton);

		inventoryModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};

		for (int i = 0; i < inventoryTableColumn.length; i++)
		{
			inventoryModel.addColumn(inventoryTableColumn[i]);
		}

		inventoryTable = new JTable(inventoryModel);
		inventoryTable.getTableHeader().setReorderingAllowed(false);
		inventoryTable.getTableHeader().setResizingAllowed(false);
		inventoryTable.getSelectionModel().addListSelectionListener(this);

		productsScrollPane = new JScrollPane(inventoryTable);
		productsScrollPane.setBounds(10, 71, 801, 367);
		productsScrollPane.setBorder(blackline);
		productsTab.add(productsScrollPane);

		inventoryTableRows = new ArrayList(0);

		resetTable();
		controller.updateProducts();
		updateInventoryTable();

	}

	public void resetTable()
	{
		System.out.println("Resetting tables");

		inventoryTableRows.clear();

		while (inventoryModel.getRowCount() > 0)
		{
			inventoryModel.removeRow(0);
		}
	}

	public void updateInventoryTable()
	{

		for (int i = 0; i < inventoryTableRows.size(); i++)
		{
			for (int j = 0; j < inventoryTableRows.get(i).length; j++)
			{
				System.out.println(inventoryTableRows.get(i)[j]);
			}

			inventoryModel.addRow(inventoryTableRows.get(i));
		}
		inventoryTable.setModel(inventoryModel);

		this.revalidate();
		this.repaint();

	}

	public Border getBlackline()
	{
		return blackline;
	}

	public String getTitle()
	{
		return title;
	}

	public JTabbedPane getMainTabbedPane()
	{
		return mainTabbedPane;
	}

	public JPanel getNotificationsTab()
	{
		return notificationsTab;
	}

	public JPanel getProductsTab()
	{
		return productsTab;
	}

	public JScrollPane getProductsScrollPane()
	{
		return productsScrollPane;
	}

	public JTextField getSearchProductTextField()
	{
		return searchProductTextField;
	}

	public JLabel getSortByLabel()
	{
		return filterByLabel;
	}

	public JLabel getProductsLabel()
	{
		return productsLabel;
	}

	public JScrollPane getNotificationScrollPane()
	{
		return notificationScrollPane;
	}

	public JLabel getSortByLabel2()
	{
		return sortByLabel2;
	}

	public JButton getMarkDoneButton()
	{
		return markDoneButton;
	}

	public JLabel getSearchLabel2()
	{
		return searchLabel2;
	}

	public JTextField getSearchTextField2()
	{
		return searchTextField2;
	}

	public JButton getGoButton()
	{
		return goButton;
	}

	public JButton getSearchButton()
	{
		return searchButton;
	}

	public JComboBox<String> getCategoryComboBox()
	{
		return categoryComboBox;
	}

	public JTable getInventoryTable()
	{
		return inventoryTable;
	}

	public JButton getDeleteProductButton()
	{
		return deleteProductButton;
	}

	public JButton getEditProductButton()
	{
		return editProductButton;
	}

	public JButton getAddProductButton()
	{
		return addProductButton;
	}

	public InventoryController getController()
	{
		return controller;
	}

	public DefaultTableModel getInventoryModel()
	{
		return inventoryModel;
	}

	public String[] getInventoryTableColumn()
	{
		return inventoryTableColumn;
	}

	public ArrayList<Object[]> getInventoryTableRows()
	{
		return inventoryTableRows;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == searchButton)
		{
			controller.search();
		} 
		else if (e.getSource() == addProductButton)
		{
			controller.addProduct();
		} 
		else if (e.getSource() == editProductButton)
		{
			controller.editProduct();
		} 
		else if (e.getSource() == deleteProductButton)
		{
			controller.deleteProduct();
		} 
		else if( e.getSource() == restockButton )
		{
			
		}
		else if (e.getSource() == categoryComboBox)
		{
			controller.updateProducts(categoryComboBox.getSelectedIndex());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		int row = inventoryTable.getSelectedRow();
		if (row < 0)
		{
			editProductButton.setEnabled(false);
			deleteProductButton.setEnabled(false);
			restockButton.setEnabled(false);
		} else
		{
			editProductButton.setEnabled(true);
			deleteProductButton.setEnabled(true);
			restockButton.setEnabled(true);
		}
	}
}

//

