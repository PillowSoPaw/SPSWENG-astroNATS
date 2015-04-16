package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ViewClientsGUI extends JPanel
{	
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
	
	public ViewClientsGUI()
	{	
		blackline = BorderFactory.createLineBorder(Color.black);
		
		this.title = "View Clients";
		this.setBackground(new Color(128, 128, 0));
		this.setLayout(null);
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		
		clientsScrollPane = new JScrollPane();
		clientsScrollPane.setBounds(10, 48, 645, 424);
		clientsScrollPane.setLayout(null);
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
		nameLabel.setBounds(706, 195, 58, 14);
		add(nameLabel);
		
		contactDetailsLabel = new JLabel("Contact Details");
		contactDetailsLabel.setForeground(Color.WHITE);
		contactDetailsLabel.setBounds(693, 220, 89, 14);
		add(contactDetailsLabel);
		
		addressLabel = new JLabel("Address");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setBounds(708, 261, 51, 26);
		add(addressLabel);
		
		dateLastVisitedLabel = new JLabel("Date Last Visited");
		dateLastVisitedLabel.setForeground(Color.WHITE);
		dateLastVisitedLabel.setBounds(693, 298, 101, 14);
		add(dateLastVisitedLabel);
		
		numOfVisitsLabel = new JLabel("# of Visits");
		numOfVisitsLabel.setForeground(Color.WHITE);
		numOfVisitsLabel.setBounds(706, 323, 71, 14);
		add(numOfVisitsLabel);
		
		editDetailsButton = new JButton("Edit Details");
		editDetailsButton.setBounds(681, 371, 113, 33);
		add(editDetailsButton);
		
		String sample = "sampledata";
		//column names for notificationsTable
		String[] columnNamesClientsTable = {"Client ID", "Last Name", "First Name", "Contact Details", "Date Last Visited"};
		Object[][] rowDataClientsTable = 
			{	columnNamesClientsTable,
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample},
				{sample, sample, sample, sample, sample}
			};
		clientsTable = new JTable(rowDataClientsTable, columnNamesClientsTable);
		clientsTable.setSize(645, 424);
		clientsTable.setBorder(blackline);
		clientsScrollPane.add(clientsTable);
		
	}
}
