package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import model.Client;
import controller.ViewClientsController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ManageClientsGUI extends JPanel implements ActionListener, MouseListener
{	
	private ViewClientsController viewClientsController;
	
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
	
	private String[] clientTableColumn = {"Client ID", "Last Name", "First Name", "Contact Details", "Date Last Visited"};
	private ArrayList<Object[]> clientTableRows;
	private DefaultTableModel clientTableModel;
	private JButton viewDetailsButton;
	
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
		add(editDetailsButton);
		
		viewDetailsButton = new JButton("View Details");
		viewDetailsButton.addActionListener(this);
		viewDetailsButton.setBounds(568, 439, 113, 33);
		add(viewDetailsButton);

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

		ArrayList<Client> clients = new ArrayList<>(0);
		while (c.hasNext() == true)
		{
			clients.add((Client) c.next());
		}
		if(clients.size()>0)
			
		for( int i = 0; i < clients.size(); i++ )
		{
			//"Client ID", "Last Name", "First Name", "Contact Details", "Date Last Visited"
			Object[] row = {clients.get(i).getsClientId(),clients.get(i).getsName(),clients.get(i).getsName(),clients.get(i).getsContactNumber(),clients.get(i).getDateLastVisited()};
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
	
//	public void getClient(Client client)
//	{
//		imagePanel.setBackground(getBackground());//this just gets the background i dunno how the picture works
//		nameLabel.setText("Name: "+client.getsName());
//		contactDetailsLabel.setText("Contact: "+client.getsContactNumber());
//		addressLabel.setText("Address: "+client.getsAddress());
//		dateLastVisitedLabel.setText("Last Visited: "+client.getDateLastVisited());
//		numOfVisitsLabel.setText("#Visits: ewan");
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (clientsTable.getSelectedRow()> -1)
		{
			System.out.println(clientsTable.getSelectedRow());
		}
		else if(e.getSource().equals(editDetailsButton))
		{
			new EditClientDetailsGUI();
		}
		else if(e.getSource().equals(viewDetailsButton))
		{
			new ViewClientDetailsGUI();
		}else if(e.getSource().equals(addNewClientButton)){
			new AddClient();
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == clientsTable)
		{
			//System.out.println(clientsTable.getSelectedRow());
//			String value = (String) clientsTable.getValueAt(clientsTable.getSelectedRow(), 0);
//			int client_id = Integer.parseInt(value);
//			viewClientsController.getClient(client_id);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
