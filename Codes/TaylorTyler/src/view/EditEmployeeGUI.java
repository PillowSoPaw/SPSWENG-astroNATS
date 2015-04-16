package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.Employee;

public class EditEmployeeGUI extends JFrame{
	
	private ViewClientsGUI mainFrame;
	public Border blackline;
	private JTextArea nameText;
	private JComboBox cmbType;
	private JComboBox cmbBranch;
	private Employee employee;
	
	private JButton confirm;
	
	public EditEmployeeGUI(Employee e){
		//this.mainFrame = mainFrame; //Pass the view clients GUI
		
		// Combo Box items
		String[] typeItems = {"Senior Employee", "Junior Employee"};
		String[] branchItems = {"Sample Branch 1", "Sample Branch 2", "Sample Branch 3"};
		
		this.employee = e;
		
		blackline = BorderFactory.createLineBorder(Color.black);
		this.setTitle("Edit Employee");
		
		getContentPane().setLayout(null);
		this.setSize(395, 255);
		
		
		JPanel mainPanel = new JPanel();
		//mainPanel.setBackground(new Color(240, 240, 240));
		//mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		//mainPanel.setBounds(10, 11, 374, 350);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 379, 216);
		mainPanel.setBorder(blackline);
		mainPanel.setBackground(new Color(128, 128, 0));
		setVisible(true);
		
		// JLabels
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(10, 39, 93, 14);
		mainPanel.add(nameLabel);
		
		JLabel separator0 = new JLabel("__________________________________________________");
		separator0.setBounds(10, 64, 359, 14);
		mainPanel.add(separator0);
		
		JLabel branchLabel = new JLabel("Salon Branch*:");
		branchLabel.setBounds(10, 89, 93, 14);
		mainPanel.add(branchLabel);
		
		JLabel typeLabel = new JLabel("Employee Type*:");
		typeLabel.setBounds(10, 122, 117, 14);
		mainPanel.add(typeLabel);
		
		//Text Areas
		
		nameText = new JTextArea();
		nameText.setEditable(false);
		nameText.setBounds(133, 34, 231, 19);
		mainPanel.add(nameText);
		
		cmbType = new JComboBox(typeItems);
		cmbType.setBounds(133, 118, 231, 22);
		mainPanel.add(cmbType);
				
		confirm = new JButton("Save");
		confirm.setBounds(50, 165, 294, 30);
		mainPanel.add(confirm);
				
		//Adding listeners for confirm and date Combo Boxes
		confirm.addActionListener(new ListenerAddEvent());
		getContentPane().add(mainPanel);
		
		cmbBranch = new JComboBox(branchItems);
		cmbBranch.setBounds(133, 85, 231, 22);
		mainPanel.add(cmbBranch);
	}
	
	class ListenerAddEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getSource().equals(confirm)) {
				if(!nameText.getText().equals("")){
					
					/*
						DEVS do the adding to the database here :)
					*/
					
					//dispose();
				}
		}
	}
	}
}
