package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeGUI extends JFrame{
	
	private ViewClientsGUI mainFrame;
	public Border blackline;
	private JTextArea firstNameText;
	private JTextArea middleNameText;
	private JTextArea lastNameText;
	private JComboBox cmbType;
	private JComboBox cmbBranch;
	
	private JButton confirm;
	
	public AddEmployeeGUI(){
		//this.mainFrame = mainFrame; //Pass the view clients GUI
		
		// Combo Box items
		String[] typeItems = {"Senior Employee", "Junior Employee"};
		String[] branchItems = {"Sample Branch 1", "Sample Branch 2", "Sample Branch 3"};
		
		blackline = BorderFactory.createLineBorder(Color.black);
		this.setTitle("Add Employee");
		
		getContentPane().setLayout(null);
		this.setSize(395, 325);
		
		
		JPanel mainPanel = new JPanel();
		//mainPanel.setBackground(new Color(240, 240, 240));
		//mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		//mainPanel.setBounds(10, 11, 374, 350);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 379, 311);
		mainPanel.setBorder(blackline);
		mainPanel.setBackground(new Color(128, 128, 0));
		setVisible(true);
		
		// JLabels
		
		JLabel fnameLabel = new JLabel("First Name*:");
		fnameLabel.setBounds(10, 39, 93, 14);
		mainPanel.add(fnameLabel);
		
		JLabel mnameLabel = new JLabel("Middle Name*:");
		mnameLabel.setBounds(10, 69, 93, 14);
		mainPanel.add(mnameLabel);
		
		JLabel lnameLabel = new JLabel("Last Name*:");
		lnameLabel.setBounds(10, 99, 93, 14);
		mainPanel.add(lnameLabel);
		
		JLabel separator0 = new JLabel("__________________________________________________");
		separator0.setBounds(10, 116, 359, 14);
		mainPanel.add(separator0);
		
		JLabel branchLabel = new JLabel("Salon Branch*:");
		branchLabel.setBounds(10, 141, 93, 14);
		mainPanel.add(branchLabel);
		
		JLabel typeLabel = new JLabel("Employee Type*:");
		typeLabel.setBounds(10, 171, 117, 14);
		mainPanel.add(typeLabel);
		
		//Text Areas
		
		firstNameText = new JTextArea();
		firstNameText.setBounds(133, 34, 231, 19);
		mainPanel.add(firstNameText);
		
		middleNameText = new JTextArea();
		middleNameText.setBounds(133, 64, 231, 19);
		mainPanel.add(middleNameText);
		
		lastNameText = new JTextArea();
		lastNameText.setBounds(133, 94, 231, 19);
		mainPanel.add(lastNameText);
		
		cmbType = new JComboBox(typeItems);
		cmbType.setBounds(133, 166, 231, 22);
		mainPanel.add(cmbType);
		
		cmbBranch = new JComboBox(branchItems);
		cmbBranch.setBounds(133, 138, 231, 22);
		getContentPane().add(cmbBranch);
				
		confirm = new JButton("Confirm");
		confirm.setBounds(50, 234, 294, 30);
		mainPanel.add(confirm);
				
		//Adding listeners for confirm and date Combo Boxes
		confirm.addActionListener(new ListenerAddEvent());
		getContentPane().add(mainPanel);
	}
	
	class ListenerAddEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getSource().equals(confirm)) {
				if(!firstNameText.getText().equals("") && !middleNameText.getText().equals("")
				   && !lastNameText.getText().equals("")){
					
					/*
						DEVS do the adding to the database here :)
					*/
					
					//dispose();
				}
		}
	}
	}
}
