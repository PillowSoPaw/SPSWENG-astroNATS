package view;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class EditProductGUI extends JFrame{
	
	private JLabel nameLabel;
	private JLabel actualNameLabel; //Name of the product to be changed when loaded
									//from database. (Not editable)
	private JLabel priceLabel;
	private JLabel thresholdLabel;
	
	private JTextField priceField;
	private JTextField thresholdField;
	
	private JPanel namePanel;
	private JPanel detailsPanel;
	
	private JButton confirm;
	
	
	public EditProductGUI(){
		setSize(400, 190);
		setResizable(false);
		getContentPane().setLayout(null);
		
		getContentPane().setBackground(new Color(128, 128, 0));
		
		setTitle("Edit Product");
		
		namePanel = new JPanel();
		namePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		namePanel.setBackground(new Color(240, 240, 240));
		namePanel.setBounds(10, 11, 374, 42);
		namePanel.setLayout(null);
		getContentPane().add(namePanel);
		
		nameLabel = new JLabel("Name of Product: ");
		nameLabel.setBounds(10, 14, 120, 15);
		namePanel.add(nameLabel);

		actualNameLabel = new JLabel("NAME");
		actualNameLabel.setBounds(106, 11, 258, 20);
		namePanel.add(actualNameLabel);
		
		detailsPanel = new JPanel();
		detailsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		detailsPanel.setBounds(10, 64, 374, 42);
		detailsPanel.setLayout(null);
		getContentPane().add(detailsPanel);
		
		priceLabel = new JLabel("Price:");
		priceLabel.setBounds(33, 12, 36, 15);
		detailsPanel.add(priceLabel);
		
		priceField = new JTextField();
		priceField.setBounds(79, 11, 71, 16);
		detailsPanel.add(priceField);
		
		thresholdLabel = new JLabel("Threshold Amount:");
		thresholdLabel.setBounds(160, 12, 100, 15);
		detailsPanel.add(thresholdLabel);
		
		thresholdField = new JTextField();
		thresholdField.setBounds(270, 11, 71, 15);
		detailsPanel.add(thresholdField);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(150, 117, 100, 34);
		getContentPane().add(confirm);
		
		thresholdField = new JTextField();
		
		setVisible(true);
	}
}
