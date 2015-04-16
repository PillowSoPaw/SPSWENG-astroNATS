package view;

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

public class InventoryGUI extends JPanel
{	
	public Border blackline;
	private String title;
	
	private JTabbedPane mainTabbedPane;
	private JPanel notificationsTab;
	private JPanel productsTab;
	private JScrollPane productsScrollPane;
	private JTextField searchProductTextField;
	private JLabel sortByLabel;
	private JLabel productsLabel;
	private JScrollPane notificationScrollPane;
	private JLabel sortByLabel2;
	private JComboBox<String> categoryComboBox2;
	private JButton markDoneButton;
	private JButton deleteNotificationButton;
	private JLabel searchLabel2;
	private JTextField searchTextField2;
	private JButton goButton;
	private JButton searchButton;
	private JComboBox<String> categoryComboBox;
	private JTable inventoryTable;
	private JButton addNotificationButton;
	private JButton addNotesButton;
	private JTable notificationsTable;
	private JButton deleteProductButton;
	private JButton editProductButton;
	private JButton addProductButton;
	
	public InventoryGUI()
	{	
		blackline = BorderFactory.createLineBorder(Color.black);
		
		this.title = "Inventory";
		this.setBackground(new Color(189, 183, 107));
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		this.setLayout(null);
		
		
		
//		mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		mainTabbedPane.setBackground(new Color(128, 128, 0));
//		mainTabbedPane.setBounds(10, 10, 801, 462);
//		mainTabbedPane.setBorder(blackline);
		//mainTabbedPane.setLayout(null);
//		add(mainTabbedPane);
		
		
		//notification moved - new panel
//		notificationsTab = new JPanel();
//		notificationsTab.setBackground(new Color(128, 128, 0));
//		notificationsTab.setLayout(null);
		//mainTabbedPane.addTab("Notifications", notificationsTab);
		
//		notificationScrollPane = new JScrollPane();
//		notificationScrollPane.setBounds(166, 10, 620, 413);
//		notificationScrollPane.setLayout(null);
//		notificationScrollPane.setBorder(blackline);
		//notificationsTab.add(notificationScrollPane);
		
//		sortByLabel2 = new JLabel("Sort by:");
//		sortByLabel2.setForeground(new Color(255, 255, 255));
//		sortByLabel2.setBounds(60, 55, 46, 14);
//		notificationsTab.add(sortByLabel2);
//		
//		categoryComboBox2 = new JComboBox<String>();
//		categoryComboBox2.setBounds(10, 72, 146, 20);
//		notificationsTab.add(categoryComboBox2);
//		
//		markDoneButton = new JButton("Mark Done");
//		markDoneButton.setBounds(10, 298, 146, 38);
//		notificationsTab.add(markDoneButton);
//		
//		deleteNotificationButton = new JButton("Delete Notification");
//		deleteNotificationButton.setBounds(10, 347, 146, 38);
//		notificationsTab.add(deleteNotificationButton);
//		
//		searchLabel2 = new JLabel("Search");
//		searchLabel2.setForeground(new Color(255, 255, 255));
//		searchLabel2.setBounds(60, 133, 46, 14);
//		notificationsTab.add(searchLabel2);
//		
//		searchTextField2 = new JTextField();
//		searchTextField2.setBounds(10, 149, 146, 20);
//		notificationsTab.add(searchTextField2);
//		searchTextField2.setColumns(10);
//		
//		goButton = new JButton("Go");
//		goButton.setBounds(50, 180, 62, 23);
//		notificationsTab.add(goButton);
//		
//		addNotesButton = new JButton("Add Notes");
//		addNotesButton.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent e) 
//			{
//				if(e.getSource() == addNotesButton)
//				{
//					new AddNotesGUI();
//				}
//			}
//		});
//		addNotesButton.setBounds(10, 249, 146, 38);
//		notificationsTab.add(addNotesButton);
	
		productsTab = new JPanel();
		productsTab.setBackground(new Color(128, 128, 0));
		productsTab.setBounds(0, 0, 821, 483);
		productsTab.setBorder(blackline);
		productsTab.setLayout(null);
		add(productsTab);
		
		productsScrollPane = new JScrollPane();
		productsScrollPane.setBounds(10, 71, 801, 367);
		productsScrollPane.setLayout(null);
		productsScrollPane.setBorder(blackline);
		productsTab.add(productsScrollPane);
		
		searchProductTextField = new JTextField("Enter Product Name here...");
		searchProductTextField.setBounds(10, 37, 168, 23);
		productsTab.add(searchProductTextField);
		searchProductTextField.setColumns(10);
		
		searchButton = new JButton("Search");
		searchButton.setBounds(188, 37, 87, 23);
		productsTab.add(searchButton);
		
		sortByLabel = new JLabel("Sort by:");
		sortByLabel.setForeground(new Color(255, 255, 255));
		sortByLabel.setBounds(628, 40, 46, 14);
		productsTab.add(sortByLabel);
		
		categoryComboBox = new JComboBox<String>();
		categoryComboBox.setBounds(672, 37, 139, 20);
		productsTab.add(categoryComboBox);
		
		productsLabel = new JLabel("Products");
		productsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		productsLabel.setForeground(new Color(255, 255, 255));
		productsLabel.setBounds(10, 11, 78, 21);
		productsTab.add(productsLabel);
		
		//add Notification feature - jframe to follow
		addNotificationButton = new JButton("Add Notification");
		addNotificationButton.setToolTipText("Add a notification for a chosen product");
		addNotificationButton.setBounds(672, 449, 139, 23);
		productsTab.add(addNotificationButton);
		
		//delete - edit - add only available to owner. salon manager may request delete-edit-add(to follow)
		deleteProductButton = new JButton("Delete Product");
		deleteProductButton.setToolTipText("Delete the selected product");
		deleteProductButton.setBounds(523, 449, 139, 23);
		productsTab.add(deleteProductButton);
		
		editProductButton = new JButton("Edit Product");
		editProductButton.setToolTipText("Edit the selected product details");
		editProductButton.setBounds(374, 449, 139, 23);
		productsTab.add(editProductButton);
		
		addProductButton = new JButton("Add Product");
		addProductButton.setToolTipText("Add a product to inventory");
		addProductButton.setBounds(285, 37, 108, 23);
		productsTab.add(addProductButton);
		
		String sample = "sampledata";
		//column names for notificationsTable
//				String[] columnNamesNotificationsTable = {"Notification Date", "Priority Level", "Product/Services Affected", "Description"};
//				Object[][] rowDataNotificationsTable = 
//					{	columnNamesNotificationsTable,
//						{sample, sample, sample, sample, sample},
//						{sample, sample, sample, sample, sample},
//						{sample, sample, sample, sample, sample}
//					};
//		notificationsTable = new JTable(rowDataNotificationsTable, columnNamesNotificationsTable);
//		notificationsTable.setSize(620, 413);
//		notificationsTable.setBorder(blackline);
//		notificationScrollPane.add(notificationsTable);
		
		//column names for inventoryTable
		String[] columnNamesInventoryTable = {"Product ID", "Product Name", "Quantity", "Date Last Restocked", "Services catered"}; 
		
		Object[][] rowDataInventoryTable = 
			{	columnNamesInventoryTable,
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample}
			};
		inventoryTable = new JTable(rowDataInventoryTable, columnNamesInventoryTable);
		inventoryTable.setSize(801, 367);
		inventoryTable.setBorder(blackline);
		productsScrollPane.add(inventoryTable);
		
		
	}
}
