package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import controller.Controller;

public class EmployeeListFrame extends JFrame
{
	AddTransactionPanel mainFrame;
	private JTable		employeesTable;
	private JScrollPane	scroll;
	private JButton	add;

	public EmployeeListFrame(AddTransactionPanel mainFrame)
	{
		
		ActListener actListener = new ActListener();
		this.mainFrame = mainFrame;
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Name");
		tModel.addColumn("Type");

		employeesTable = new JTable(tModel);
		employeesTable.setBounds(10, 10, 275, 300);
		scroll = new JScrollPane(employeesTable);
		scroll.setBounds(10, 10, 275, 300);
		add = new JButton("Add");
		add.setBounds(10, 320, 275, 30);
		add.addActionListener(actListener);
		
		getContentPane().setLayout(null);

		getContentPane().add(scroll);
		getContentPane().add(add);

		this.setResizable(false);

		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

		add.setEnabled(false);
		
		ListSelectionModel listSelectionModel = employeesTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{ 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				add.setEnabled(!lsm.isSelectionEmpty());
			};
			});
		
		loadEmployees(mainFrame.getWorkingEmployees()); // Should be called by controller by getting
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
		this.repaint();
		this.revalidate();
	}
	
	private class ActListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if( e.getSource() == add )
			{
				int x = employeesTable.getSelectedRow();
				String selectedType = (String) employeesTable.getModel().getValueAt(x, 1);
				String[] sEmployee = new String[2];
				
				
				if( selectedType.equalsIgnoreCase("senior") == true )
				{
					sEmployee[0] = (String) employeesTable.getModel().getValueAt(x, 0);
					mainFrame.setSelectedEmployee1(sEmployee[0]);
					JOptionPane.showMessageDialog(null, sEmployee[0] + " assigned", "Add Employee", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else if( selectedType.equalsIgnoreCase("junior") == true )
				{
					sEmployee[1] = (String) employeesTable.getModel().getValueAt(x, 0);
					mainFrame.setSelectedEmployee2(sEmployee[1]);
					JOptionPane.showMessageDialog(null, sEmployee[1] + " assigned", "Add Employee", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		}	
	}
}