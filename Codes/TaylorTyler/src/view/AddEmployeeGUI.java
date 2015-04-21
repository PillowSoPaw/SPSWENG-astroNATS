package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.AddClientController;
import controller.AddEmployeeController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Calendar;

public class AddEmployeeGUI extends JFrame implements ActionListener
{
	private AddEmployeeController addEmployeeController = new AddEmployeeController();
	private ManageEmployeesGUI manageEmployeesGUI;
	private ViewClientsGUI mainFrame;
	public Border blackline;
	private JTextArea firstNameText;
	private JTextArea middleNameText;
	private JTextArea lastNameText;
	private JComboBox cmbType;
	private JComboBox cmbBranch;	
	private JButton confirm;
	private String dateToday;
	public AddEmployeeGUI(ManageEmployeesGUI manageEmployeesGUI){
		//this.mainFrame = mainFrame; //Pass the view clients GUI
		this.manageEmployeesGUI = manageEmployeesGUI;
		// Combo Box items
		String[] typeItems = {"senior", "junior", "salonmanager"};
		String[] branchItems = {"Sample Branch 1", "Sample Branch 2", "Sample Branch 3"};
		//Calendar stuff
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int currMonth = Calendar.getInstance().get(Calendar.MONTH);
		dateToday = currYear+"-" + (currMonth +1) + "-" + currDay;
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
		
		JLabel mnameLabel = new JLabel("Middle Name:");
		mnameLabel.setBounds(10, 69, 93, 14);
		mainPanel.add(mnameLabel);
		
		JLabel lnameLabel = new JLabel("Last Name*:");
		lnameLabel.setBounds(10, 99, 93, 14);
		mainPanel.add(lnameLabel);
		
		JLabel separator0 = new JLabel("__________________________________________________");
		separator0.setBounds(10, 116, 359, 14);
		mainPanel.add(separator0);
		
		JLabel branchLabel = new JLabel("Salon Branch*:");
		branchLabel.setBounds(10, 181, 93, 14);
		//mainPanel.add(branchLabel);
		
		JLabel typeLabel = new JLabel("Employee Type*:");
		typeLabel.setBounds(10, 141, 117, 14);
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
		cmbType.setBounds(133, 141, 231, 22);
		mainPanel.add(cmbType);
				
		confirm = new JButton("Confirm");
		confirm.setBounds(50, 210, 294, 30);
		confirm.addActionListener(this);
		mainPanel.add(confirm);
		getContentPane().add(mainPanel);
		
		cmbBranch = new JComboBox(branchItems);
		cmbBranch.setBounds(133, 177, 231, 22);
		//mainPanel.add(cmbBranch);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(firstNameText.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Please enter a first name.");
		else if(lastNameText.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Please enter a last name.");
		else
		{
			String sFname = firstNameText.getText();
			String sMname = middleNameText.getText();
			String sLname = lastNameText.getText();
			if(!sMname.equals(""))
				sMname = sMname + " ";
			String sName = sFname +" "+ sMname + sLname;
			try 
			{
				String sType = cmbType.getSelectedItem().toString();
				addEmployeeController.addEmployee(sName, dateToday, sType);
				JOptionPane.showMessageDialog(null, "Successfully added employee!");
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			} catch (ParseException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			manageEmployeesGUI.addEmployee();
			manageEmployeesGUI.getData();
		}
	}
}
