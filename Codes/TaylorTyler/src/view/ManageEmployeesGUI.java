package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageEmployeesGUI extends JPanel{
	
	private Border blackline;
	private String title;
	
	private JScrollPane employeeListScrollPane;
	
	
	private JTextField employeeNameTextField;
	private JLabel juniorStaffCountLabel;
	private JButton searchButton;
	private JButton addEmployeeButton;
	private JButton editDetailsButton;
	private JButton viewDetailsButton;
	private JLabel totalCountEmployeesLabel;
	private JLabel seniorStaffCountLabel;
	private JCheckBox ShowSeniorStaffCheckBox;
	private JCheckBox ShowJuniorStaffCheckBox;
	
	
	private String[] sortByListOwner = {"Name", "Hours Rendered", "Branch"};
	private String[] sortByList = {"Name", "Hours Rendered"};
	
	private DefaultTableModel employeeListModel;
	private JTable employeeListTable;
	private String[] employeeListColumn = {"Name", "Type", "Hours Rendered", "Address"}; //for Salon Manager
	private String[] employeeListColumnOwner = {"Name", "Type", "Hours Rendered", "Address", "Branch"}; //for Owner
	private ArrayList<Object[]> employeeListRows;
	private ArrayList<ArrayList<String>> employees;
	private ArrayList<ArrayList<Integer>> employeesQuantity;
	private JCheckBox ShowSalonManagerCheckBox;
	
	public ManageEmployeesGUI() {
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		this.title = "Manage Employees";
		this.setBackground(new Color(128, 128, 0));
		//this.setBackground(new Color(189, 183, 107));
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		this.setLayout(null);
		this.repaint();
		
		
		employeeListModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < employeeListColumn.length; i++ )
		{
			employeeListModel.addColumn(employeeListColumn[i]);
		}
		
		employeeListTable = new JTable(employeeListModel);
		employeeListTable.getTableHeader().setReorderingAllowed(false);
		employeeListTable.getTableHeader().setResizingAllowed(false);
		employeeListScrollPane = new JScrollPane(employeeListTable);
		employeeListScrollPane.setBounds(10, 58, 801, 373);
		employeeListScrollPane.setBorder(blackline);
		add(employeeListScrollPane);
		
		employeeNameTextField = new JTextField();
		employeeNameTextField.setText("Enter name here...");
		employeeNameTextField.setBounds(10, 11, 193, 26);
		add(employeeNameTextField);
		employeeNameTextField.setColumns(10);
		
		searchButton = new JButton("Search");
		searchButton.setBounds(213, 11, 89, 26);
		add(searchButton);
		
		addEmployeeButton = new JButton("Add Employee");
		addEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeGUI();
			}
		});
		addEmployeeButton.setBounds(312, 11, 130, 26);
		add(addEmployeeButton);
		
		editDetailsButton = new JButton("Edit Details");
		editDetailsButton.setBounds(682, 442, 129, 26);
		editDetailsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//new EditEmployeeGUI(); pass here the employee selected
			}
			
		});
		add(editDetailsButton);
		
		viewDetailsButton = new JButton("View Details");
		viewDetailsButton.setBounds(543, 442, 129, 26);
		add(viewDetailsButton);
		
		totalCountEmployeesLabel = new JLabel("Total no. of employees:");
		totalCountEmployeesLabel.setForeground(Color.WHITE);
		totalCountEmployeesLabel.setBounds(276, 440, 166, 27);
		add(totalCountEmployeesLabel);
		
		juniorStaffCountLabel = new JLabel("Junior Staff count:");
		juniorStaffCountLabel.setForeground(Color.WHITE);
		juniorStaffCountLabel.setBounds(141, 440, 137, 27);
		add(juniorStaffCountLabel);
		
		seniorStaffCountLabel = new JLabel("Senior Staff count:");
		seniorStaffCountLabel.setForeground(Color.WHITE);
		seniorStaffCountLabel.setBounds(10, 440, 137, 27);
		add(seniorStaffCountLabel);
		
		ShowSeniorStaffCheckBox = new JCheckBox("Show Senior Staff");
		ShowSeniorStaffCheckBox.setSelected(true);
		ShowSeniorStaffCheckBox.setForeground(Color.WHITE);
		ShowSeniorStaffCheckBox.setBackground(new Color(128, 128, 0));
		ShowSeniorStaffCheckBox.setBounds(660, 7, 151, 23);
		add(ShowSeniorStaffCheckBox);
		
		ShowJuniorStaffCheckBox = new JCheckBox("Show Junior Staff");
		ShowJuniorStaffCheckBox.setSelected(true);
		ShowJuniorStaffCheckBox.setForeground(Color.WHITE);
		ShowJuniorStaffCheckBox.setBackground(new Color(128, 128, 0));
		ShowJuniorStaffCheckBox.setBounds(660, 28, 151, 23);
		add(ShowJuniorStaffCheckBox);	
		
		ShowSalonManagerCheckBox = new JCheckBox("Show Salon Manager");
		ShowSalonManagerCheckBox.setSelected(true);
		ShowSalonManagerCheckBox.setForeground(Color.WHITE);
		ShowSalonManagerCheckBox.setBackground(new Color(128, 128, 0));
		ShowSalonManagerCheckBox.setBounds(475, 15, 154, 26);
		add(ShowSalonManagerCheckBox);
	
	}
	
	public void getData()
	{
		updateEmployeeTable();
		//updateProductTable();
		
		this.repaint();
		this.revalidate();
	}
	
	public void resetAll()
	{
		employeeListRows.clear();
		employees.clear();
		employeesQuantity.clear();
		resetTables();
		updateEmployeeTable();
		//updateProductTable();
	}
	
	public void resetTables()
	{
		employeeListModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < employeeListColumn.length; i++ )
		{
			employeeListModel.addColumn(employeeListColumn[i]);
		}
		
	}
	
//	public void addProducts(ArrayList<Object[]> employees)
//	{
//		for( int i = 0; i < employee.size(); i++ )
//			clientListRows.add(clients.get(i));
//		
//		updateServiceTable();
//	}
//	
//	public void updateServiceTable()
//	{	
//		resetTables();
//		
//		for( int i = 0; i < clientListRows.size(); i++ )
//		{
//			for( int j = 0; j < clientListRows.get(i).length; j++ )
//				System.out.println(clientListRows.get(i)[j]);
//			System.out.println();
//			clientListModel.addRow(clientListRows.get(i));
//		}
//		clientListTable.setModel(clientListModel);
//	}
	
	public void addEmployees(ArrayList<ArrayList<String>> employees, ArrayList<ArrayList<Integer>> employeesQuantity)
	{
		for( int i = 0; i < employees.size(); i++ )
		{
			for( int j = 0; j < employees.get(i).size(); j++ )
			{
				Object[] row = {employees.get(i).get(j), employeesQuantity.get(i).get(j), "N/A", "N/A"};
				employeeListRows.add(row);
			}
			this.employees.add(employees.get(i));
			this.employeesQuantity.add(employeesQuantity.get(i));
		}
		
		updateEmployeeTable();
	}
	
	public void updateEmployeeTable()
	{	
		resetTables();
		
		for( int i = 0; i < employeeListRows.size(); i++ )
		{
			employeeListModel.addRow(employeeListRows.get(i));
		}
		employeeListTable.setModel(employeeListModel);
	}
	
}
