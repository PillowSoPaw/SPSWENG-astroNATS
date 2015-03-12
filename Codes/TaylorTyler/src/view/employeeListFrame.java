package view;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class employeeListFrame extends JFrame{
	
	private JTable employees;
        private JScrollPane scroll;
	private JButton add;
        
	
	public employeeListFrame(AddTransactionPanel mainFrame){
        
        DefaultTableModel tModel = new DefaultTableModel();
        tModel.addColumn("Name");
        tModel.addColumn("Type");
            
        
        employees = new JTable(tModel);    
        employees.setBounds(10, 10, 275, 300);    
        scroll = new JScrollPane(employees);
        scroll.setBounds(10, 10, 275, 300);  
        add = new JButton("Add");
        add.setBounds(10, 320, 275, 30);
        
        this.setLayout(null);
		
        add(scroll);
        add(add);
        
        this.setResizable(false);
        
	this.setSize(300, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
            String[][] test = {{"John Smith", "Senior"}, {"Jim Moriarty", "Senior"},
                               {"Michael Bay", "Junior"}
                                };// For testing
            
            loadEmployees(test); // Should be called by controller by getting
                                // Results from database
	}
        
        public void loadEmployees(String[][] entries){
            DefaultTableModel tModel = new DefaultTableModel();
            tModel.addColumn("Name");
            tModel.addColumn("Type");
            
            int i = 0;
            
            while(i < entries.length){
                String[] entry = {entries[i][0], entries[i][1]};
                tModel.addRow(entry);
            
                i++;
            }
        
            employees.setModel(tModel);
        }
}