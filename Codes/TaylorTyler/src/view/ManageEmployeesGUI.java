package view;
import model.Employee;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;

import controller.ManageEmployeesController;
import controller.ViewClientsController;
import model.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageEmployeesGUI extends JPanel implements ActionListener
{
	private ManageEmployeesController manageEmployeesController;
	private ArrayList<Employee> employees;
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
	
	private int nSenior = 0;
	private int nJunior = 0;
	private int nManager = 0;
	private int nEmployee = 0;
	
	private String[] sortByListOwner = {"Name", "Hours Rendered", "Branch"};
	private String[] sortByList = {"Name", "Hours Rendered"};
	
	private DefaultTableModel employeeTableModel;
	private JTable employeeListTable;
	private String[] employeeListColumn = {"Employee Id", "Name", "Type", "Date Started Working"}; //for Salon Manager
	//private String[] employeeListColumnOwner = {"Name", "Type", "Hours Rendered", "Address", "Branch"}; //for Owner
	private JCheckBox ShowSalonManagerCheckBox;
	
	public ManageEmployeesGUI(ManageEmployeesController manageEmployeesController ) {
		this.manageEmployeesController = manageEmployeesController;
		blackline = BorderFactory.createLineBorder(Color.black);
		
		this.title = "Manage Employees";
		this.setBackground(new Color(128, 128, 0));
		//this.setBackground(new Color(189, 183, 107));
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		this.setLayout(null);
		this.repaint();
		
		
		employeeTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < employeeListColumn.length; i++ )
		{
			employeeTableModel.addColumn(employeeListColumn[i]);
		}
		
		employeeListTable = new JTable(employeeTableModel);
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
		addEmployeeButton.setBounds(312, 11, 130, 26);
		addEmployeeButton.addActionListener(this);
		add(addEmployeeButton);
		
		editDetailsButton = new JButton("Edit Details");
		editDetailsButton.setBounds(682, 442, 129, 26);
		editDetailsButton.setEnabled(false);
		editDetailsButton.addActionListener(this);
		add(editDetailsButton);
		
		viewDetailsButton = new JButton("View Details");
		viewDetailsButton.setBounds(543, 442, 129, 26);
		viewDetailsButton.setEnabled(false);
		//add(viewDetailsButton);
		
		totalCountEmployeesLabel = new JLabel("Total no. of employees: "+ nEmployee);
		totalCountEmployeesLabel.setForeground(Color.WHITE);
		totalCountEmployeesLabel.setBounds(276, 440, 166, 27);
		add(totalCountEmployeesLabel);
		
		juniorStaffCountLabel = new JLabel("Junior Staff count: " + nJunior);
		juniorStaffCountLabel.setForeground(Color.WHITE);
		juniorStaffCountLabel.setBounds(141, 440, 137, 27);
		add(juniorStaffCountLabel);
		
		seniorStaffCountLabel = new JLabel("Senior Staff count: " + nSenior);
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
		
		ListSelectionModel listSelectionModel = employeeListTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
	        public void valueChanged(ListSelectionEvent e)
	        { 
	            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
	            viewDetailsButton.setEnabled(!lsm.isSelectionEmpty());
	            editDetailsButton.setEnabled(!lsm.isSelectionEmpty());
	        }
		});
	
	}
	//GET DATA FROM THE DATABASE
	public void getData()
	{
		manageEmployeesController.getEmployees();
		this.repaint();
		this.revalidate();
		
	}
	
	public void resetAll()
	{
		employees.clear();
		resetTables();
		//updateEmployeeTable();
	}
	
	public void resetTables()
	{
		employeeTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
		for( int i = 0; i < employeeListColumn.length; i++ )
		{
			employeeTableModel.addColumn(employeeListColumn[i]);
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
	
//	public void addEmployees(ArrayList<ArrayList<String>> employees, ArrayList<ArrayList<Integer>> employeesQuantity)
//	{
//		for( int i = 0; i < employees.size(); i++ )
//		{
//			for( int j = 0; j < employees.get(i).size(); j++ )
//			{
//				Object[] row = {employees.get(i).get(j), employeesQuantity.get(i).get(j), "N/A", "N/A"};
//				employeeListRows.add(row);
//			}
//			employees.add(employees.get(i));
//			this.employeesQuantity.add(employeesQuantity.get(i));
//		}
//		
//		updateEmployeeTable();
//	}
	
//	public void updateEmployeeTable()
//	{	
//		resetTables();
//		
//		for( int i = 0; i < employeeListRows.size(); i++ )
//		{
//			employeeTableModel.addRow(employeeListRows.get(i));
//		}
//		employeeListTable.setModel(employeeTableModel);
//	}
	
	public void getEmployees(Iterator e)
	{
	    employees = new ArrayList<>(0);
	    nSenior = 0;
		nJunior = 0;
		nManager = 0;
		nEmployee = 0;
		while (e.hasNext() == true)
		{
			employees.add((Employee) e.next());
		}
		if(employees.size()>0)
		
		for( int i = 0; i < employees.size(); i++ )
		{
			if(employees.get(i).getsType().equals("junior"))
				nJunior++;
			else if (employees.get(i).getsType().equals("senior"))
				nSenior++;
			else if (employees.get(i).getsType().equals("salonmanager"))
				nManager++;
			//"Name", "Type", "Date Started Working"
			Object[] row = {employees.get(i).getsEmployeeId(), employees.get(i).getsName(), employees.get(i).getsType(), employees.get(i).getDateStartedWorking()};
			employeeTableModel.addRow(row);
			System.out.println(employees.get(i).getsName());
			nEmployee++;
		}
		System.out.println("total"+ nEmployee);
		totalCountEmployeesLabel.setText("Total no. of employees: "+ nEmployee);
		juniorStaffCountLabel.setText("Junior Staff count: " + nJunior);
		seniorStaffCountLabel.setText("Senior Staff count: " + nSenior);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource().equals(addEmployeeButton))
		{
			new AddEmployeeGUI(this);
		}
		else if(e.getSource().equals(editDetailsButton))
		{
			for( int i = 0; i < employees.size() ; i++ )
			{
				if(employees.get(i).getsEmployeeId().equals((String) employeeTableModel.getValueAt(employeeListTable.getSelectedRow(), 0)))
				new EditEmployeeGUI(employees.get(i), this);
			}
		}
		
	}
	public void addEmployee() 
	{
		// TODO Auto-generated method stub
		for( int i = 0; i < employees.size(); i++ )
		{
			employeeTableModel.removeRow(0);
		}
		this.repaint();
		this.revalidate();
	}
	
}
