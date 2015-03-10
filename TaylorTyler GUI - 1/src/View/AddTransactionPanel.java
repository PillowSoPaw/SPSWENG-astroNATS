package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class AddTransactionPanel extends JPanel {
    
    private int entries; //TURN THIS BLOCK INTO A CLASS called TransactionEntry
    private ArrayList<String> services;
    private ArrayList<String> customerNames;
    private ArrayList<String> prices;
    
    private JPanel leftPanel;
    private JScrollPane transScroll;
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
           
    private String[] testOptions = {"Shampoo", "Nail Polish", "Hair Dye Product"}; //Should get from DATABASE for DEVS
    private String[] testOptions2 = {"Manicure", "Pedicure", "Haircut", "Dye Hair"}; //Should get from DATABASE for DEVS
    
    private Service[] serviceReference;
    
    private double[] testPrice = {124.99, 99.99, 249.99};
    private double[] testPrice2 = {200.00, 200.00, 100.00, 300.00};
    
    private boolean isOpen;
    
    private AddTransactionPanel reference;
    
    public AddTransactionPanel() {
        setBounds(183, 120, 600, 440);
        setLayout(null);
//        setBounds()
        pTransactionDetail = new JPanel();
        
        entries = 0;
        
        isOpen = false;
        
        serviceReference = new Service[4]; // Should load from Database all the Services
        
        /* Instantiating Services, REMOVE AFTER TESTING */
            serviceReference[0] = new Service(testOptions2[0]);
            serviceReference[1] = new Service(testOptions2[1]);
            serviceReference[2] = new Service(testOptions2[2]);
            serviceReference[3] = new Service(testOptions2[3]);
            
            serviceReference[0].addProduct(new Product("Nail Polish", "Liter"));
            serviceReference[0].addProduct(new Product("Nail Color", "Liter"));
            
            serviceReference[1].addProduct(new Product("Nail Polish", "Liter"));
            serviceReference[1].addProduct(new Product("Nail Color", "Liter"));
            
            serviceReference[2].addProduct(new Product("Shampoo", "Liter"));
            serviceReference[2].addProduct(new Product("Hair Spray", "Liter"));
            
            serviceReference[3].addProduct(new Product("Hair Dye Product", "Liter"));
            serviceReference[3].addProduct(new Product("Bleach", "Liter"));
        /* Instantiating Services, REMOVE AFTER TESTING */
        
        
        Border blackline = BorderFactory.createLineBorder(Color.black);

        setBorder(blackline);
        
        services = new ArrayList();
        customerNames = new ArrayList();
        prices = new ArrayList();
        
        leftPanel = new JPanel();
        customerName = new JTextField("Customer Name"); // Retrieve Customer Name from Database
        customerName.setBounds(10, 10, 200, 20);
        bCancel = new JButton("Cancel");
        bSave = new JButton("Save Transaction");
        titleService = new JLabel("Service Rendered");
        titleLine = new JLabel("______________________________________");
        titleProduct = new JLabel("Product Availed");
        titleLine2 = new JLabel("______________________________________");
        
        DefaultTableModel tModel = new DefaultTableModel();
        tModel.addColumn("Service / Product");
        tModel.addColumn("Customer Name");
        tModel.addColumn("Price");
        
        transactionDetail = new JTable(tModel);
        transactionDetail.setBounds(10, 50, 300, 330);
        transactionDetail.setRowHeight(20);
        pTransactionDetail.add(transactionDetail);
        bCancel.setBounds(10, 400, 75, 30);
        bSave.setBounds(175, 400, 135, 30);
        titleService.setBounds(480, 10, 120, 20);
        titleProduct.setBounds(490, 240, 100, 20);
        titleLine.setBounds(325, 20, 300, 20);
        titleLine2.setBounds(325, 250, 300, 20);
        transScroll = new JScrollPane(transactionDetail);
        transScroll.setBounds(10, 50, 300, 330);
        
        servicePanel = new JPanel();
        lChoose = new JLabel("Choose Service:");
        chooseService = new JComboBox(testOptions2);
        
        //initialize the add employee button
        buttonListener ButtonListener = new buttonListener();
        
        reference = this;
        
        employee = new JButton("Add Employee");
        employee.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		employeeListFrame empList = new employeeListFrame(reference);
                        empList.addWindowListener(new WindowCloser());
        	}
        });
        
        //initialize add Products button
        products = new JButton("Products Used");
        products.addActionListener(ButtonListener);
        
        //initialize the add service button
        addService = new JButton("Add Service");
        addService.addActionListener(ButtonListener);

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
        
        productsPanel = new JPanel();
        lChooseProduct = new JLabel("Choose Product:");
        chooseProduct = new JComboBox(testOptions);
        lQuantity = new JLabel("Quantity:");
        quantity = new JTextArea("Enter quantity here");
        bAddProduct = new JButton("Add Product"); 
        
        bAddProduct.addActionListener(ButtonListener);
        
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
        add(transScroll);
    }
    
    private void updateTable(){
        DefaultTableModel tModel = new DefaultTableModel(){
        public boolean isCellEditable(int row, int column)
        {
            return false;//This causes all cells to be not editable
        }
        };
        
        tModel.addColumn("Service / Product");
        tModel.addColumn("Customer Name");
        tModel.addColumn("Price");
        
        int i = 0;
        while(i < entries){
            String[] entry = {services.get(i), customerNames.get(i), prices.get(i)};
            tModel.addRow(entry);
            
            i++;
        }
        
        transactionDetail.setModel(tModel);
    }
    
    public void toggleOpen(){
        isOpen = false;
    }
    
    public class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bAddProduct){
                try {
                    int temp = Integer.parseInt(quantity.getText());
                    
                    services.add(testOptions[chooseProduct.getSelectedIndex()] + " (" + temp + ")");
                    prices.add("" + (testPrice[chooseProduct.getSelectedIndex()] * temp));
                    customerNames.add(customerName.getText());
                    
                    entries++;
                    
                    updateTable();
                } catch (NumberFormatException ex) {
                    System.out.println("Quantity has to be double!");
                    
                    quantity.setText("This has to be a number.");
                }
            }else if(e.getSource() == addService){
                    services.add(testOptions2[chooseService.getSelectedIndex()]);
                    prices.add("" + testPrice2[chooseService.getSelectedIndex()]);
                    customerNames.add(customerName.getText());
                    
                    entries++;
                    
                    updateTable();
            }else if(e.getSource() == products){
                    if(isOpen == false){
                        productListFrame temp = new productListFrame(reference, serviceReference[chooseService.getSelectedIndex()]);
                        temp.addWindowListener(new WindowCloser());
                        isOpen = true;
                    }
            }
        }
    }
    
    public class WindowCloser extends WindowAdapter{
        public void windowClosing(WindowEvent e)
        {
            toggleOpen();
        }
    }
}

