package view;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

import model.Employee;

public class ViewEmployeeGUI extends JFrame{
	
	private JLabel lblEmployeename;
	private JLabel lblSalonbranch;
	private JLabel lblEmployeetype;
	
	public ViewEmployeeGUI(Employee e){
		setSize(300, 140);
		setVisible(true);
		setResizable(false);
		setTitle("View Employee Details");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(128, 128, 0));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 27, 84, 14);
		getContentPane().add(lblName);
		
		lblEmployeename = new JLabel("employeeName");
		lblEmployeename.setBounds(104, 27, 180, 14);
		getContentPane().add(lblEmployeename);
		
		JLabel lblSalonBranchV = new JLabel("Salon Branch:");
		lblSalonBranchV.setBounds(10, 52, 84, 14);
		getContentPane().add(lblSalonBranchV);
		
		JLabel lblEmployeeTypeV = new JLabel("Employee Type:");
		lblEmployeeTypeV.setBounds(10, 77, 84, 14);
		getContentPane().add(lblEmployeeTypeV);
		
		lblSalonbranch = new JLabel("salonBranch");
		lblSalonbranch.setBounds(104, 52, 95, 14);
		getContentPane().add(lblSalonbranch);
		
		lblEmployeetype = new JLabel("employeeType");
		lblEmployeetype.setBounds(104, 77, 180, 14);
		getContentPane().add(lblEmployeetype);
	}
}
