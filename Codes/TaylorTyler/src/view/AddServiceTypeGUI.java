package view;

import java.awt.Color;

import javax.swing.*;

public class AddServiceTypeGUI extends JFrame{
	private JTextField nameField;
	private JTextField priceField;
	private JButton addButton;
	
	public AddServiceTypeGUI(){
		setSize(350, 170);
		setVisible(true);
		setResizable(false);
		setTitle("Add Service Type");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(128, 128, 0));
		this.setLocationRelativeTo(null);
		
		JLabel lblServiceName = new JLabel("Service Name:");
		lblServiceName.setBounds(10, 23, 85, 14);
		getContentPane().add(lblServiceName);
		
		nameField = new JTextField();
		nameField.setBounds(104, 17, 230, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(10, 54, 46, 14);
		getContentPane().add(lblPrice);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(104, 48, 230, 20);
		getContentPane().add(priceField);
		
		addButton = new JButton("ADD");
		addButton.setBounds(10, 84, 324, 47);
		getContentPane().add(addButton);
	}
}
