/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javax.swing.*;

import controller.Controller;

/**
 *
 * @author Joseph
 */
public class TransactionGUI extends JFrame
{
	//declaration of variable for panels
	private JPanel mainPanel;  //main panel for background
	private JPanel menuPanel;  //panel for options - in the left side
	
	//components for mainPanel
		//left side of the panel
	private JLabel transactionLabel;
	private JLabel inventoryLabel;
	private JLabel clientLabel;
	private JLabel employeeLabel;
	private JLabel reportLabel;
		//upper right side of the panel
	private JLabel manageAccountLabel;
	private JLabel logOutLabel;
	private JLabel lineLabel;
		//logo
	private JLabel logoLabel;
    
	private AddTransactionPanel yes;
	private Controller controller;
    
    	public TransactionGUI()
    	{
    		mainPanel = new JPanel();
    		
    		logoLabel = new JLabel("LOGO");
		mainPanel.add(logoLabel);

		getContentPane().add(mainPanel);
		
		manageAccountLabel = new JLabel("Manage Account");
		manageAccountLabel.setBounds(610, 6, 92, 20);
		getContentPane().add(manageAccountLabel);

		lineLabel = new JLabel("|");
		lineLabel.setBounds(706, 6, 13, 20);
		this.getContentPane().add(lineLabel);
		
		logOutLabel = new JLabel("Logout");
		logOutLabel.setBounds(730, 6, 200, 20);
		this.getContentPane().add(logOutLabel);
		
		transactionLabel = new JLabel("Transactions");
		transactionLabel.setBounds(54, 163, 200, 20);
		this.getContentPane().add(transactionLabel);
		
		inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setBounds(54, 253, 200, 20);
		this.getContentPane().add(inventoryLabel);
		
		clientLabel = new JLabel("Client");
		clientLabel.setBounds(54, 333, 200, 20);
		this.getContentPane().add(clientLabel);
		
		employeeLabel = new JLabel("Employees");
		employeeLabel.setBounds(54, 413, 200, 20);
		this.getContentPane().add(employeeLabel);
		
		reportLabel = new JLabel("Daily Report");
		reportLabel.setBounds(54, 490, 200, 20);
		this.getContentPane().add(reportLabel);

		yes = new AddTransactionPanel();
		this.getContentPane().add(yes);
		
		menuPanel = new JPanel();

		this.getContentPane().setLayout(null);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
		yes.setController(controller);
	}

	public AddTransactionPanel getAddTransactionPanel()
	{
		return yes;
	}

	public static void main(String[] args)
	{

		new TransactionGUI();
	}
}