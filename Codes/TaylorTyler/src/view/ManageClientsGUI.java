package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import model.Client;
import controller.ViewClientsController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ManageClientsGUI extends JPanel implements ActionListener
{	
	private ViewClientsController viewClientsController;
	private ArrayList<Client> clients;
	public Border blackline;
	public String title;
	private JTextField searchClientTextField;
	private JScrollPane clientsScrollPane;
	private JButton searchClientButton;
	private JLabel sortByLabel;
	private JComboBox categoryComboBox;
	private JButton addNewClientButton;
	private JTable clientsTable;
	private JButton editDetailsButton;
	private JButton viewDetailsButton;
	private String[] clientTableColumn = {"Client ID", "Name", "Contact Details", "Date Last Visited"};
	private ArrayList<Object[]> clientTableRows;
	private DefaultTableModel clientTableModel;
	
	public ManageClientsGUI( ViewClientsController viewClientsController )
	{	
		this.viewClientsController = viewClientsController;
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		this.title = "View Clients";
		this.setBackground(new Color(128, 128, 0));
		this.setLayout(null);
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		
		clientTableModel = new DefaultTableModel(){
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		for( int i = 0; i < clientTableColumn.length; i++ )
		{
			clientTableModel.addColumn(clientTableColumn[i]);
		}
		clientsTable = new JTable(clientTableModel);
		
		clientsScrollPane = new JScrollPane(clientsTable);
		clientsScrollPane.setBounds(10, 48, 801, 385);
		clientsScrollPane.setBorder(blackline);
		add(clientsScrollPane);
		
		searchClientTextField = new JTextField();
		searchClientTextField.setText("Enter Client Name here...");
		searchClientTextField.setBounds(10, 11, 193, 26);
		add(searchClientTextField);
		searchClientTextField.setColumns(10);
		
		searchClientButton = new JButton("Search");
		searchClientButton.setBounds(213, 11, 89, 26);
		add(searchClientButton);
		
		sortByLabel = new JLabel("Sort by:");
		sortByLabel.setForeground(Color.WHITE);
		sortByLabel.setBounds(636, 11, 46, 26);
		add(sortByLabel);
		
		categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(682, 14, 129, 20);
		add(categoryComboBox);
		
		addNewClientButton = new JButton("Add New Client");
		addNewClientButton.setBounds(312, 11, 129, 26);
		addNewClientButton.addActionListener(this);
		add(addNewClientButton);
		
		editDetailsButton = new JButton("Edit Details");
		editDetailsButton.setBounds(698, 439, 113, 33);
		editDetailsButton.addActionListener(this);
		editDetailsButton.setEnabled(false);
		add(editDetailsButton);
		
		viewDetailsButton = new JButton("View Details");
		viewDetailsButton.addActionListener(this);
		viewDetailsButton.setBounds(568, 439, 113, 33);
		viewDetailsButton.setEnabled(false);
		add(viewDetailsButton);
		
		ListSelectionModel listSelectionModel = clientsTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
	        public void valueChanged(ListSelectionEvent e)
	        { 
	            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
	            viewDetailsButton.setEnabled(!lsm.isSelectionEmpty());
	            editDetailsButton.setEnabled(!lsm.isSelectionEmpty());
	        }
		});

	}
	
	//GET DATA FROM THE DATABASE
	public void getData()
	{
		viewClientsController.getClients();
		this.repaint();
		this.revalidate();
		
	}
	
	public void getClients(Iterator c)
	{
	    clients = new ArrayList<>(0);
		while (c.hasNext() == true)
		{
			clients.add((Client) c.next());
		}
		if(clients.size()>0)
			
		for( int i = 0; i < clients.size(); i++ )
		{
			//"Client ID", "Name", "Contact Details", "Date Last Visited"
			Object[] row = {clients.get(i).getsClientId(),clients.get(i).getsName(),clients.get(i).getsContactNumber(),clients.get(i).getDateLastVisited()};
			clientTableModel.addRow(row);
			System.out.println(clients.get(i).getsName());
		}
		
	}
	public void resetTables()
	{
		clientTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < clientTableColumn.length; i++ )
		{
			clientTableModel.addColumn(clientTableColumn[i]);
		}
		

	}
	public void updateClientTable()
	{	
		resetTables();
		for( int i = 0; i < clientTableColumn.length; i++ )
		{
			clientTableModel.addColumn(clientTableColumn[i]);
		}
		if(clientTableRows.size()>0)
		for( int i = 0; i < clientTableRows.size(); i++ )
		{
			clientTableModel.addRow(clientTableRows.get(i));
		}
		clientsTable.setModel(clientTableModel);
	}
	
	
	
	public void getClient(Client client, Iterator iServices, Iterator iProducts)
	{
		 ArrayList<Object[]> services = new ArrayList<>(0);
		 ArrayList<Object[]> products = new ArrayList<>(0);
			while (iServices.hasNext() == true)
			{
				services.add((Object[]) iServices.next());
			}
			while (iProducts.hasNext() == true)
			{
				products.add((Object[]) iProducts.next());
			}
				
		new ViewClientDetailsGUI(client, services, products);
		
//		imagePanel.setBackground(getBackground());//this just gets the background i dunno how the picture works
//		nameLabel.setText("Name: "+client.getsName());
//		contactDetailsLabel.setText("Contact: "+client.getsContactNumber());
//		addressLabel.setText("Address: "+client.getsAddress());
//		dateLastVisitedLabel.setText("Last Visited: "+client.getDateLastVisited());
//		numOfVisitsLabel.setText("#Visits: ewan");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(editDetailsButton))
		{
			new EditClientDetailsGUI();
		}
		else if(e.getSource().equals(viewDetailsButton))
		{
			//System.out.print(clientTableModel.getValueAt(clientsTable.getSelectedRow(), 0));
			viewClientsController.getClient(Integer.parseInt((String) clientTableModel.getValueAt(clientsTable.getSelectedRow(), 0)));
		}
		else if(e.getSource().equals(addNewClientButton)){
			new AddClientGUI(this);
		}
		
		
	}
	
	public void addClient() 
	{
		// TODO Auto-generated method stub
		for( int i = 0; i < clients.size(); i++ )
		{
			clientTableModel.removeRow(0);
		}
		this.repaint();
		this.revalidate();
	}
}
