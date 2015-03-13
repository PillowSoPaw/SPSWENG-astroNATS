/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javax.swing.*;

/**
 *
 * @author Joseph
 */
public class TransactionGUI extends JFrame
{
    
    //declaration of variable for panels
    private JPanel mainPanel;  //main panel for background
    private JPanel menuPanel;  //panel for options - in the left side
    private JPanel transactionPanel;  //main transaction panel - display of details
    private JPanel addTransPanel;  //panel for adding details to transaction
    
    //private JFrame addProduct;
    //private JFrame addEmployee;
    
    //components for mainPanel
        //left side of the panel
    private JLabel transactionLabel;
    private JLabel addTransactionLabel;
    private JLabel viewTransactionLabel;
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
    
    //components for addTransPanel
        //components for Add Service
    private JLabel serviceLabel;
    private JComboBox servicesComboBox;
    private JLabel chooseServiceLabel;
    private JButton addEmployee;
    private JButton addProduct1;
    private JButton addService;
        //components for Add Product
    private JLabel productLabel;
    private JLabel chooseProductLabel;
    private JComboBox productComboBox;
    private JLabel quantityLabel;
    private JTextField quantityTextField;
    private JButton addProduct2;
    
    //components for transactionPanel
    private JTextField customerNameTextField;
    private JTextArea transactionDetailsTextArea;
    private JButton cancelTransactionButton;
    private JButton saveTransactionButton;
    
    public TransactionGUI()
    {
        mainPanel = new JPanel();
        menuPanel = new JPanel();
        transactionPanel = new JPanel();
        addTransPanel = new JPanel();
        
        transactionLabel = new JLabel("Transactions");
        addTransactionLabel = new JLabel("Add Transactions");
        viewTransactionLabel = new JLabel("View Transactions");
        inventoryLabel = new JLabel("Inventory");
        clientLabel = new JLabel("Client");
        employeeLabel = new JLabel("Employees");
        reportLabel = new JLabel("Daily Report");
        
        manageAccountLabel = new JLabel("Manage Account");
        logOutLabel = new JLabel("Logout");
        
        logoLabel = new JLabel("LOGO");
        
        serviceLabel = new JLabel();
        servicesComboBox = new JComboBox();
        chooseServiceLabel = new JLabel();
        
        addEmployee = new JButton();
        
        
        addProduct1 = new JButton();
        addService = new JButton();
        
        productLabel = new JLabel();
        chooseProductLabel = new JLabel();
        productComboBox = new JComboBox();
        quantityLabel = new JLabel();
        quantityTextField = new JTextField();
        addProduct2 = new JButton();
        
        customerNameTextField = new JTextField();
        transactionDetailsTextArea = new JTextArea();
        cancelTransactionButton = new JButton();
        saveTransactionButton = new JButton();
        
        add(mainPanel);
        mainPanel.add(logoLabel);
        
        add(transactionPanel);
        JPanel derp = new JPanel();
        JPanel yes = new AddTransactionPanel();
        derp = yes;
        add(derp);
        
        lineLabel = new JLabel("|");
        lineLabel.setBounds(716, 6, 200, 20);
        manageAccountLabel.setBounds(610,6,200,20);
        logOutLabel.setBounds(730, 6, 200, 20);
        add(lineLabel);
        add(logOutLabel);
        add(manageAccountLabel);
        
        transactionLabel.setBounds(54,163,200,20);
        add(transactionLabel);
        inventoryLabel.setBounds(54,253,200,20);
        add(inventoryLabel);
        clientLabel.setBounds(54,333,200,20);
        employeeLabel.setBounds(54,413,200,20);
        reportLabel.setBounds(54,490,200,20);
        add(clientLabel);
        add(employeeLabel);
        add(reportLabel);

        setLayout(null);
        
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        
        new TransactionGUI();
    }
}