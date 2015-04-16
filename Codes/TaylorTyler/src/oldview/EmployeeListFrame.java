package oldview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Employee;

public class EmployeeListFrame extends JFrame
{
	AddTransactionPanel	mainFrame;
	private JTable		employeesTable;
	private JScrollPane	scroll;
	private JButton	addButton;

	public EmployeeListFrame(AddTransactionPanel mainFrame)
	{
		ActListener actListener = new ActListener();
		this.mainFrame = mainFrame;
		this.getContentPane().setLayout(null);
		this.setSize(300, 400);
		
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.addColumn("Name");
		tModel.addColumn("Type");

		employeesTable = new JTable(tModel);
		employeesTable.setBounds(10, 10, 275, 300);
		scroll = new JScrollPane(employeesTable);
		scroll.setBounds(10, 10, 275, 300);
		this.getContentPane().add(scroll);
		
		addButton = new JButton("Add");
		addButton.setBounds(10, 320, 275, 30);
		addButton.setEnabled(false);
		addButton.addActionListener(actListener);
		this.getContentPane().add(addButton);

		ListSelectionModel listSelectionModel = employeesTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{ 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				addButton.setEnabled(!lsm.isSelectionEmpty());
			};
		});
		
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

		loadEmployees(mainFrame.getWorkingEmployees());
	}

	public void loadEmployees(Iterator entries)
	{
		DefaultTableModel tModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};
		
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
			if( e.getSource() == addButton )
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