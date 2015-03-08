package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class AddTransactionPanel extends JPanel {

    private JPanel leftPanel;
    private JPanel pTransactionDetail;
    private JTextField customerName;
    private JTable transactionDetail;
    private JButton bCancel;
    private JButton bSave;

    private JPanel rightPanel;
    private JLabel titleService;
    private JLabel titleLine;
    private JLabel titleLine2;
    private JLabel titleProduct;

    private JPanel servicePanel;
    private JLabel lChoose;
    private JComboBox chooseService;
    private JButton employee;
    private JButton products;
    private JButton addService;

    private JPanel productsPanel;
    private JLabel lChooseProduct;
    private JComboBox chooseProduct;
    private JLabel lQuantity;
    private JTextArea quantity;
    private JButton bAddProduct;

    public AddTransactionPanel() {
        setBounds(183, 120, 600, 440);
        setLayout(null);
//        setBounds()
        String[] temp = {"Service", "Customer Name", "Price"};
        String[][] temp2 = {{"Blah", "Blah", "Blah"},
        {"Blah", "Blah", "Blah"}
        };

        pTransactionDetail = new JPanel();

        Border blackline = BorderFactory.createLineBorder(Color.black);

        setBorder(blackline);

        leftPanel = new JPanel();
        customerName = new JTextField("Customer Name"); // Retrieve Customer Name from Database
        customerName.setBounds(10, 10, 200, 20);
        bCancel = new JButton("Cancel");
        bSave = new JButton("Save Transaction");
        titleService = new JLabel("Service Rendered");
        titleLine = new JLabel("______________________________________");
        titleProduct = new JLabel("Product Availed");
        titleLine2 = new JLabel("______________________________________");
        
        transactionDetail = new JTable(temp2, temp);
        transactionDetail.setBounds(10, 50, 300, 330);
        pTransactionDetail.add(transactionDetail);
        bCancel.setBounds(10, 400, 75, 30);
        bSave.setBounds(175, 400, 135, 30);
        titleService.setBounds(480, 10, 120, 20);
        titleProduct.setBounds(490, 240, 100, 20);
        titleLine.setBounds(325, 20, 300, 20);
        titleLine2.setBounds(325, 250, 300, 20);
        
        servicePanel = new JPanel();
        lChoose = new JLabel("Choose Service:");
        chooseService = new JComboBox();
        
        //initialize the add employee button
        employee = new JButton("Add Employee");
        employee.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		employeeListFrame empList = new employeeListFrame();
        		System.out.print(empList.getText());
        	}
        });
        
        //initialize add Products button
        products = new JButton("Products Used");
        products.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//insert add products JFrame here
        	}
        });
        
        //initialize the add service button
        addService = new JButton("Add Service");
        addService.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//insert add service code
        	}
        });

        servicePanel.setBorder(blackline);
        servicePanel.setLayout(null);
        servicePanel.setBounds(325, 50, 266, 180);
        lChoose.setBounds(87, 10, 100, 25);
        chooseService.setBounds(59, 40, 150, 25);
        employee.setBounds(59, 70, 150, 25);
        products.setBounds(59, 100, 150, 25);
        addService.setBounds(59, 140, 150, 25);
        
        servicePanel.add(lChoose);
        servicePanel.add(chooseService);
        servicePanel.add(employee);
        servicePanel.add(products);
        servicePanel.add(addService);
        add(servicePanel);
        
        JPanel productsPanel = new JPanel();
        JLabel lChooseProduct = new JLabel("Choose Product:");
        JComboBox chooseProduct = new JComboBox();
        JLabel lQuantity = new JLabel("Quantity:");
        JTextArea quantity = new JTextArea("Enter quantity here");
        JButton bAddProduct = new JButton("Add Product"); 
        
        productsPanel.setBorder(blackline);
        productsPanel.setLayout(null);
        productsPanel.setBounds(325, 280, 266, 150);
        lChooseProduct.setBounds(87, 10, 100, 25);
        chooseProduct.setBounds(59, 40, 150, 25);
        lQuantity.setBounds(106, 65, 150, 25);
        quantity.setBounds(59, 85, 150, 18);
        quantity.setBorder(blackline);
        bAddProduct.setBounds(59, 110, 150, 25);
        
        
        productsPanel.add(lChooseProduct);
        productsPanel.add(chooseProduct);
        productsPanel.add(lQuantity);
        productsPanel.add(quantity);
        productsPanel.add(bAddProduct);
        
        add(productsPanel);
        
        add(titleLine2);
        add(titleProduct);
        add(titleLine);
        add(titleService);
        add(bSave);
        add(bCancel);
        add(customerName);
        add(transactionDetail);
    }
}
