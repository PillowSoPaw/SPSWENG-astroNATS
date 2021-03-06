package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewClientDetailsGUI extends JFrame{
	private JLabel emailLabel;
	private JLabel birthdayLabel;
	private JLabel addressLabel;
	private JLabel contactLabel;
	private JLabel genderLabel;
	
	private JTable servicesTable;
	private JTable productsTable;
	
	private JLabel lblServices;
	private JLabel lblProducts;
	
	private JScrollPane servicesScroll;
	private JScrollPane productsScroll;
	
	private JButton doneButton;
	
	private DefaultTableModel serviceTableModel;
	private DefaultTableModel productTableModel;
	private String[] serviceTableColumn = {"Service", "Employees", "Date (y-m-d)"};
	private String[] productTableColumn = {"Product", "Quantity", "Date (y-m-d)"};
	public ViewClientDetailsGUI(Client client, ArrayList<Object[]> services, ArrayList<Object[]> products)
	{
		serviceTableModel = new DefaultTableModel(){
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		for( int i = 0; i < serviceTableColumn.length; i++ )
		{
			serviceTableModel.addColumn(serviceTableColumn[i]);
		}
		servicesTable = new JTable(serviceTableModel);
		
		productTableModel = new DefaultTableModel(){
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		for( int i = 0; i < productTableColumn.length; i++ )
		{
			productTableModel.addColumn(productTableColumn[i]);
		}
		productsTable = new JTable(productTableModel);
		for(int i = 0 ; i< services.size(); i ++)
		{
			System.out.println("view client details services");
			System.out.println(services.get(i)[0]);
			System.out.println(services.get(i)[1]);
			System.out.println(services.get(i)[2]);
			System.out.println(services.get(i)[3]);
			Object[] row = {services.get(i)[0],services.get(i)[1] + ", "+ services.get(i)[2],services.get(i)[3]};
			serviceTableModel.addRow(row);
		}
		for(int i = 0 ; i< products.size(); i ++)
		{
			System.out.println("view client details products");
			System.out.println(products.get(i)[0]);
			System.out.println(products.get(i)[1]);
			System.out.println(products.get(i)[2]);
			Object[] row = {products.get(i)[0],products.get(i)[1],products.get(i)[2]};
			productTableModel.addRow(row);
		}
		getContentPane().setBackground(new Color(128, 128, 0)); // If DEVs have a CLIENT class and if you'll use it pass it here.
		setSize(650, 535);
		setVisible(true);
		setTitle("Client Details");
		setResizable(false);
		getContentPane().setLayout(null);
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBackground(new Color(128, 128, 0));
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.WHITE);
		JLabel lblBirthday = new JLabel("Birthday(y-m-d):");
		lblBirthday.setForeground(Color.WHITE);
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.WHITE);
		JLabel lblContactNo = new JLabel("Contact No.:");
		lblContactNo.setForeground(Color.WHITE);
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setForeground(Color.WHITE);
		emailLabel = new JLabel(client.getsEmail());
		emailLabel.setForeground(Color.WHITE);
		birthdayLabel = new JLabel(client.getBirthday().toString());
		birthdayLabel.setForeground(Color.WHITE);
		addressLabel = new JLabel(client.getsAddress());
		addressLabel.setForeground(Color.WHITE);
		contactLabel = new JLabel(client.getsContactNumber());
		contactLabel.setForeground(Color.WHITE);
		genderLabel = new JLabel(client.getsGender());
		genderLabel.setForeground(Color.WHITE);
		lblServices = new JLabel("AVAILED SERVICES");
		lblProducts = new JLabel("PRODUCTS BOUGHT");
		doneButton = new JButton("Done");
		
		detailsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		detailsPanel.setBounds(10, 51, 624, 141);
		detailsPanel.setLayout(null);
		
		getContentPane().add(detailsPanel);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 11, 70, 14);
		detailsPanel.add(lblEmail);
		
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthday.setBounds(10, 36, 96, 14);
		detailsPanel.add(lblBirthday);
		
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(10, 61, 70, 14);
		detailsPanel.add(lblAddress);
		
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContactNo.setBounds(10, 86, 70, 14);
		detailsPanel.add(lblContactNo);
		
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGender.setBounds(10, 111, 70, 14);
		detailsPanel.add(lblGender);
		
		//DETAILS go here
		
		emailLabel.setBounds(111, 11, 274, 14);
		detailsPanel.add(emailLabel);
		
		birthdayLabel.setBounds(111, 36, 274, 14);
		detailsPanel.add(birthdayLabel);
		
		addressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressLabel.setBounds(111, 61, 274, 14);
		detailsPanel.add(addressLabel);
		
		contactLabel.setBounds(111, 86, 274, 14);
		detailsPanel.add(contactLabel);
		
		genderLabel.setBounds(111, 111, 274, 14);
		detailsPanel.add(genderLabel);
		
		//TABLES
		
		Border blackline = BorderFactory.createLineBorder(Color.black);

		String[] servicesColumn = {"Service", "Employees", "Date(y-m-d)"};
		String[][] servicesRow = 
			{
				{"sample", "sample", "sample"},
				{"sample", "sample", "sample"},
				{"sample", "sample", "sample"}
			};
		//servicesTable = new JTable(servicesRow, servicesColumn);
		servicesTable.setSize(307, 223);
		servicesTable.getTableHeader().setReorderingAllowed(false);
		servicesTable.getTableHeader().setResizingAllowed(false);
		servicesScroll = new JScrollPane(servicesTable);
		servicesScroll.setBounds(10, 220, 307, 241);
		servicesScroll.setBorder(blackline);
		getContentPane().add(servicesScroll);
		
		String[] productsColumn = {"Product", "Quantity", "Date(y-m-d)"};
		
		//productsTable = new JTable(servicesRow, productsColumn);
		productsTable.setSize(307, 223);
		productsTable.getTableHeader().setReorderingAllowed(false);
		productsTable.getTableHeader().setResizingAllowed(false);
		productsScroll = new JScrollPane(productsTable);
		productsScroll.setBounds(327, 220, 307, 241);
		productsScroll.setBorder(blackline);
		getContentPane().add(productsScroll);
		
		lblServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblServices.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblServices.setForeground(Color.WHITE);
		lblServices.setBounds(10, 192, 307, 27);
		getContentPane().add(lblServices);
		
		lblProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducts.setForeground(Color.WHITE);
		lblProducts.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProducts.setBounds(327, 192, 307, 27);
		getContentPane().add(lblProducts);
		
		doneButton.setBounds(538, 472, 96, 23);
		doneButton.addActionListener(new DoneButtonListener());
		getContentPane().add(doneButton);
		
		JLabel nameLabel = new JLabel(client.getsName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		nameLabel.setBounds(141, 11, 351, 29);
		getContentPane().add(nameLabel);
	}
	
	private class DoneButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent a) 
		{
			if(a.getSource().equals(doneButton)){
				dispose();
			}
		}
	}
}
