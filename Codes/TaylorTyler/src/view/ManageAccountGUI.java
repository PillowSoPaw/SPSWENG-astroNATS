package view;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ManageAccountGUI extends JFrame{
	
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField textField;
	
	public ManageAccountGUI(){
		getContentPane().setBackground(new Color(128, 128, 0));
		setSize(440, 247);
		setResizable(false);
		setVisible(true);
		setTitle("Manage Account");
		getContentPane().setLayout(null);
		
		JPanel editPanel = new JPanel();
		editPanel.setBackground(new Color(128, 128, 0));
		editPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		editPanel.setBounds(10, 11, 416, 135);
		editPanel.setLayout(null);
		getContentPane().add(editPanel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(10, 20, 107, 14);
		editPanel.add(lblName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(10, 45, 107, 14);
		editPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(10, 76, 107, 14);
		editPanel.add(lblPassword);
		
		nameField = new JTextField();
		nameField.setBounds(127, 11, 278, 20);
		nameField.setColumns(10);
		editPanel.add(nameField);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(127, 42, 278, 20);
		editPanel.add(usernameField);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(127, 70, 278, 20);
		editPanel.add(passwordField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(127, 101, 278, 20);
		editPanel.add(textField);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmPassword.setBounds(10, 104, 107, 14);
		editPanel.add(lblConfirmPassword);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(12, 174, 414, 35);
		getContentPane().add(btnConfirm);
		
		JLabel lblErrorlabel = new JLabel("errorLabel");
		lblErrorlabel.setBounds(10, 149, 416, 14);
		getContentPane().add(lblErrorlabel);
		
	}
}
