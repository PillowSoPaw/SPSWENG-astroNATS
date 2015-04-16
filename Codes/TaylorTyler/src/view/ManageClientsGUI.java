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
	private JPanel imagePanel;
	private JLabel nameLabel;
	private JLabel contactDetailsLabel;
	private JLabel addressLabel;
	private JLabel dateLastVisitedLabel;
	private JLabel numOfVisitsLabel;
	
	private String[] clientTableColumn = {"Client ID", "Last Name", "First Name", "Contact Details", "Date Last Visited"};
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
		clientsScrollPane.setBounds(10, 48, 645, 424);
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
		sortByLabel.setBounds(480, 11, 46, 26);
		add(sortByLabel);
		
		categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(526, 14, 129, 20);
		add(categoryComboBox);
		
		addNewClientButton = new JButton("Add New Client");
		addNewClientButton.setBounds(312, 11, 129, 26);
		add(addNewClientButton);
		
		imagePanel = new JPanel();
		imagePanel.setBounds(665, 48, 146, 136);
		imagePanel.setBorder(blackline);
		add(imagePanel);
		
		nameLabel = new JLabel("Full Name");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(665, 185, 146, 33);
		add(nameLabel);
		
		contactDetailsLabel = new JLabel("Contact Details");
		contactDetailsLabel.setForeground(Color.WHITE);
		contactDetailsLabel.setBounds(665, 220, 146, 33);
		add(contactDetailsLabel);
		
		addressLabel = new JLabel("Address");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setBounds(665, 255, 146, 33);
		add(addressLabel);
		
		dateLastVisitedLabel = new JLabel("Date Last Visited");
		dateLastVisitedLabel.setForeground(Color.WHITE);
		dateLastVisitedLabel.setBounds(665, 290, 146, 33);
		add(dateLastVisitedLabel);
		
		numOfVisitsLabel = new JLabel("# of Visits");
		numOfVisitsLabel.setForeground(Color.WHITE);
		numOfVisitsLabel.setBounds(665, 325, 146, 33);
		add(numOfVisitsLabel);
		
		editDetailsButton = new JButton("Edit Details");
		editDetailsButton.setBounds(682, 382, 113, 33);
		editDetailsButton.addActionListener(this);
		add(editDetailsButton);

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
	
	public void getClient(Client client)
	{
		imagePanel.setBackground(getBackground());//this just gets the background i dunno how the picture works
		nameLabel.setText("Name: "+client.getsName());
		contactDetailsLabel.setText("Contact: "+client.getsContactNumber());
		addressLabel.setText("Address: "+client.getsAddress());
		dateLastVisitedLabel.setText("Last Visited: "+client.getDateLastVisited());
		numOfVisitsLabel.setText("#Visits: ewan");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (clientsTable.getSelectedRow()> -1)
		{
			System.out.println(clientsTable.getSelectedRow());
		}
		else if(e.getSource() == editDetailsButton )
		{
			new ViewClientDetailsGUI();
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
			String value = (String) clientsTable.getValueAt(clientsTable.getSelectedRow(), 0);
			int client_id = Integer.parseInt(value);
			viewClientsController.getClient(client_id);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
