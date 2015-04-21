package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import controller.EditClientDetailsController;
import controller.EditEmployeeController;
import model.Employee;

public class EditEmployeeGUI extends JFrame implements ActionListener
{
	private EditEmployeeController editEmployeesController = new EditEmployeeController();
	private ManageEmployeesGUI manageEmployeesGUI;
	private ViewClientsGUI mainFrame;
	public Border blackline;
	private JTextArea nameText;
	private JComboBox cmbType;
	private JComboBox cmbBranch;
	private Employee employee;
	
	private JButton saveButton;
	
	public EditEmployeeGUI(Employee employee, ManageEmployeesGUI manageEmployeesGUI){
		//this.mainFrame = mainFrame; //Pass the view clients GUI
		this.manageEmployeesGUI = manageEmployeesGUI;
		// Combo Box items
		String[] typeItems = {"senior", "junior", "salonmanager"};
		String[] branchItems = {"Sample Branch 1", "Sample Branch 2", "Sample Branch 3"};
		
		this.employee = employee;
		
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
		
		JLabel nameLabel = new JLabel("Name*:");
		nameLabel.setBounds(10, 39, 93, 14);
		mainPanel.add(nameLabel);
		
		JLabel separator0 = new JLabel("__________________________________________________");
		separator0.setBounds(10, 64, 359, 14);
		mainPanel.add(separator0);
		
		JLabel branchLabel = new JLabel("Salon Branch*:");
		branchLabel.setBounds(10, 122, 93, 14);
		//mainPanel.add(branchLabel);
		
		JLabel typeLabel = new JLabel("Employee Type*:");
		typeLabel.setBounds(10, 89, 117, 14);
		mainPanel.add(typeLabel);
		
		//Text Areas
		
		nameText = new JTextArea(employee.getsName());
		nameText.setBounds(133, 34, 231, 19);
		mainPanel.add(nameText);
		
		cmbType = new JComboBox(typeItems);
		cmbType.setBounds(133, 85, 231, 22);
		mainPanel.add(cmbType);
				
		saveButton = new JButton("Save");
		saveButton.setBounds(50, 151, 294, 30);
		saveButton.addActionListener(this);
		mainPanel.add(saveButton);
				

		getContentPane().add(mainPanel);
		
		cmbBranch = new JComboBox(branchItems);
		cmbBranch.setBounds(133, 118, 231, 22);
		//mainPanel.add(cmbBranch);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == saveButton)
		{
			if(nameText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a name.");
			else
			{
				//String sClientId, String sName, String sType
				try 
				{
					editEmployeesController.editEmployee(employee.getsEmployeeId(), nameText.getText(), cmbType.getSelectedItem().toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Successfully updated employee!");
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			manageEmployeesGUI.addEmployee();
			manageEmployeesGUI.getData();
		}
	}
	
}
