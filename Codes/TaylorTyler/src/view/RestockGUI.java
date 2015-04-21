package view;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RestockGUI extends JFrame implements ActionListener
{	
	private JPanel restockPanel;
	private Border blackline;
	private JTextField quantityTextField;
	private JLabel productLabel;
	private JLabel quantityLabel;
	private JLabel pcsLabel;
	private JButton restockButton;
	
	public RestockGUI()
	{	
		blackline = BorderFactory.createLineBorder(Color.black);
		
		getContentPane().setBackground(new Color(128, 128, 0));
		this.setTitle("Restock Product");
		getContentPane().setLayout(null);
		//this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLocation(550, 250);
		this.setSize(287, 201);
		
		
		restockPanel = new JPanel();
		restockPanel.setBackground(new Color(128, 128, 0));
		restockPanel.setBounds(10, 10, 253, 100);
		restockPanel.setLayout(null);
		restockPanel.setBorder(blackline);
		getContentPane().add(restockPanel);
		
		
		productLabel = new JLabel("Product Name: " /* + String productName*/);
		productLabel.setForeground(Color.WHITE);
		productLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		productLabel.setBounds(10, 10, 233, 29);
		restockPanel.add(productLabel);
		
		quantityLabel = new JLabel("Quantity: ");
		quantityLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		quantityLabel.setForeground(Color.WHITE);
		quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		quantityLabel.setBounds(0, 46, 101, 50);
		restockPanel.add(quantityLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(115, 58, 50, 29);
		restockPanel.add(quantityTextField);
		quantityTextField.setColumns(10);
		
		pcsLabel = new JLabel("pc/s");
		pcsLabel.setForeground(Color.WHITE);
		pcsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pcsLabel.setBounds(175, 45, 41, 50);
		restockPanel.add(pcsLabel);
		
		restockButton = new JButton("Restock product");
		restockButton.setBounds(10, 121, 253, 30);
		getContentPane().add(restockButton);
		
		restockPanel.repaint();
		getContentPane().repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
