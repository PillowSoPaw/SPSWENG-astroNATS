package view;

import controller.ViewNotificationsController;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Product;

public class ViewNotificationsGUI extends JPanel{
	
	private JTable productsTable;
	private JScrollPane productsScroll;
        private ViewNotificationsController controller;
        private ArrayList<Object[]> productRows;
        private DefaultTableModel productTableModel;
        
	
	public ViewNotificationsGUI(ViewNotificationsController controller){
                this.controller = controller;
                this.controller.setView(this);
		setBackground(new Color(128, 128, 0));
		setSize(821, 483);
		setVisible(true);
		setLayout(null);
		
		JLabel title = new JLabel("Notifications");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		title.setBounds(10, 11, 801, 49);
		add(title);
		
		String[] column = {"Product", "Amount in Stock"};
		String[][] rows = {
							{"SAMPLE", "SAMPLE"},
							{"SAMPLE", "SAMPLE"}
						  };
		productTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};

                for( int i = 0; i < column.length; i++ )
		{
			productTableModel.addColumn(column[i]);
		}
                
		productsTable = new JTable(productTableModel);
		productsTable.setSize(400, 300);
		
		productsScroll = new JScrollPane(productsTable);
		productsScroll.setLocation(10, 71);
		productsScroll.setSize(801, 371);
		add(productsScroll);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 801, 51);
		add(panel);
                productRows = new ArrayList(0);
                resetTable();
                controller.updateNotifications();
                updateInventoryTable();
	}
        
        public void resetTable()
	{
                System.out.println("Resetting tables");
                
                productRows.clear();
                
                while (productTableModel.getRowCount() > 0)
                {
                        productTableModel.removeRow(0);
                }
	}
        public void updateInventoryTable()
        {
                
		for( int i = 0; i < productRows.size(); i++ )
		{
			for( int j = 0; j < productRows.get(i).length; j++ ){
                            System.out.println(productRows.get(i)[j]);
                        }
				
			productTableModel.addRow(productRows.get(i));
		}
		productsTable.setModel(productTableModel);
                
                this.revalidate();
                this.repaint();
                
	}

        public ArrayList<Object[]> getProductRows() 
        {
            return productRows;
        }
        
        
}
