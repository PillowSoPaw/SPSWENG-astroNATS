package view;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class ViewNotificationsGUI extends JPanel{
	
	private JTable productsTable;
	private JScrollPane productsScroll;
	
	public ViewNotificationsGUI(){
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
		
		productsTable = new JTable(rows, column);
		productsTable.setSize(400, 300);
		
		productsScroll = new JScrollPane(productsTable);
		productsScroll.setLocation(10, 71);
		productsScroll.setSize(801, 371);
		add(productsScroll);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 801, 51);
		add(panel);
	}
}
