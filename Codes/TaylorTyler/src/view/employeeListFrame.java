package view;

import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import controller.Controller;

public class EmployeeListFrame extends JFrame
{
	private Controller controller;
	private JTable		employeesTable;
	private JScrollPane	scroll;
	private JButton	add;

	public EmployeeListFrame(AddTransactionPanel mainFrame)
	{

		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Name");
		tModel.addColumn("Type");

		employeesTable = new JTable(tModel);
		employeesTable.setBounds(10, 10, 275, 300);
		scroll = new JScrollPane(employeesTable);
		scroll.setBounds(10, 10, 275, 300);
		add = new JButton("Add");
		add.setBounds(10, 320, 275, 30);

		getContentPane().setLayout(null);

		getContentPane().add(scroll);
		getContentPane().add(add);

		this.setResizable(false);

		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

		String[][] test = { { "John Smith", "Senior" },
				{ "Jim Moriarty", "Senior" }, { "Michael Bay", "Junior" } };// For
																// testing

		controller.getEmployees();
		//loadEmployees(test); // Should be called by controller by getting
							// Results from database
	}

	public void loadEmployees(Iterator entries)
	{
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Name");
		tModel.addColumn("Type");

		while ( entries.hasNext() == true )
		{
			Object o = new Object();
			o = ((Employee) entries.next());
			
			String[] entry = { ((Employee) o).getsName(), ((Employee) o).getsType() };
			tModel.addRow(entry);
		}

		employeesTable.setModel(tModel);
	}
	
	//SETTERS
	public void setController(Controller c)
	{
		this.controller = c;
	}
}