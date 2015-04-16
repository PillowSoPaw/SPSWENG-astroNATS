package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewClientDetailsGUI extends JFrame{
	
	private JLabel nameLabel;
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
	
	public ViewClientDetailsGUI(){
		getContentPane().setBackground(new Color(128, 128, 0)); // If DEVs have a CLIENT class and if you'll use it pass it here.
		setSize(650, 535);
		setVisible(true);
		setTitle("Client Details");
		setResizable(false);
		JPanel namePanel = new JPanel();
		getContentPane().setLayout(null);
		JPanel detailsPanel = new JPanel();
		JLabel lblEmail = new JLabel("E-mail:");
		nameLabel = new JLabel("NAME OF CLIENT"); //Replace this
		JLabel lblBirthday = new JLabel("Birthday:");
		JLabel lblAddress = new JLabel("Address:");
		JLabel lblContactNo = new JLabel("Contact No.:");
		JLabel lblGender = new JLabel("Gender:");
		emailLabel = new JLabel("E-MAIL");
		birthdayLabel = new JLabel("MM/DD/YY");
		addressLabel = new JLabel("ADDRESS");
		contactLabel = new JLabel("CONTACT NUMBER");
		genderLabel = new JLabel("GENDER");
		lblServices = new JLabel("AVAILED SERVICES");
		lblProducts = new JLabel("PRODUCTS BOUGHT");
		doneButton = new JButton("Done");
		
		namePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		namePanel.setBounds(10, 11, 624, 34);
		getContentPane().add(namePanel);
		
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(10, 10, 300, 20);
		namePanel.add(nameLabel);
		
		detailsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		detailsPanel.setBounds(10, 56, 624, 141);
		detailsPanel.setLayout(null);
		
		getContentPane().add(detailsPanel);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 11, 70, 14);
		detailsPanel.add(lblEmail);
		
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthday.setBounds(10, 36, 70, 14);
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
		
		emailLabel.setBounds(90, 11, 274, 14);
		detailsPanel.add(emailLabel);
		
		birthdayLabel.setBounds(90, 36, 274, 14);
		detailsPanel.add(birthdayLabel);
		
		addressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressLabel.setBounds(90, 61, 274, 14);
		detailsPanel.add(addressLabel);
		
		contactLabel.setBounds(90, 86, 274, 14);
		detailsPanel.add(contactLabel);
		
		genderLabel.setBounds(90, 111, 274, 14);
		detailsPanel.add(genderLabel);
		
		//TABLES
		
		Border blackline = BorderFactory.createLineBorder(Color.black);

		String[] servicesColumn = {"Service", "Employee", "Date"};
		String[][] servicesRow = 
			{
				{"sample", "sample", "sample"},
				{"sample", "sample", "sample"},
				{"sample", "sample", "sample"}
			};
		servicesTable = new JTable(servicesRow, servicesColumn);
		servicesTable.setSize(307, 223);
		
		servicesScroll = new JScrollPane(servicesTable);
		servicesScroll.setBounds(10, 238, 307, 223);
		servicesScroll.setBorder(blackline);
		getContentPane().add(servicesScroll);
		
		String[] productsColumn = {"Product", "Quantity", "Date"};
		
		productsTable = new JTable(servicesRow, productsColumn);
		productsTable.setSize(307, 223);
		
		productsScroll = new JScrollPane(productsTable);
		productsScroll.setBounds(327, 238, 307, 223);
		productsScroll.setBorder(blackline);
		getContentPane().add(productsScroll);
		
		lblServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblServices.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblServices.setForeground(Color.WHITE);
		lblServices.setBounds(10, 208, 307, 27);
		getContentPane().add(lblServices);
		
		lblProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducts.setForeground(Color.WHITE);
		lblProducts.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProducts.setBounds(327, 208, 307, 27);
		getContentPane().add(lblProducts);
		
		doneButton.setBounds(538, 472, 96, 23);
		doneButton.addActionListener(new DoneButtonListener());
		getContentPane().add(doneButton);
	}
	
	private class DoneButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getSource().equals(doneButton)){
				dispose();
			}
		}
	}
}
