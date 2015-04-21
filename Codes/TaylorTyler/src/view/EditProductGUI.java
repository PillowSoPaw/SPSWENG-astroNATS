package view;

import controller.EditProductController;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class EditProductGUI extends JFrame implements ActionListener{
	
        private char cMode;
        
        private JComboBox productType;
	private JLabel nameLabel;
        private JTextField nameField;
	private JLabel actualNameLabel; //Name of the product to be changed when loaded
							//from database. (Not editable)
	private JLabel priceLabel;
	
	private JTextField priceField;
	private JTextField thresholdField;
	
	private JPanel namePanel;
	
	private JButton confirm;
	private JTextField measurementField;
	//private JTextField textField_1;
	
        private EditProductController controller;
        private JTextArea descriptionField;
        private InventoryGUI inventoryGUI;
	
        
	public EditProductGUI(EditProductController controller, InventoryGUI inventoryGUI)
        {
                this.inventoryGUI = inventoryGUI;
                this.controller = controller;
                this.controller.setView(this);
                
		init();
	}
        
        public void init(){
                setSize(492, 300);
                setResizable(false);
                getContentPane().setLayout(null);
                getContentPane().setBackground(new Color(128, 128, 0));
                
                cMode = controller.getMode();
                
                switch (cMode)
                {
                    case 'a': setTitle("Add Product"); break;
                    case 'e': setTitle("Edit Product"); break;
                }
		
		
		namePanel = new JPanel();
		namePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		namePanel.setBackground(new Color(240, 240, 240));
		namePanel.setBounds(10, 11, 466, 136);
		namePanel.setLayout(null);
		getContentPane().add(namePanel);
		
		nameLabel = new JLabel("Name of Product: ");
		nameLabel.setBounds(10, 14, 110, 17);
		namePanel.add(nameLabel);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 42, 68, 14);
		namePanel.add(lblDescription);
		
		descriptionField = new JTextArea();
		descriptionField.setBounds(85, 42, 370, 50);
		namePanel.add(descriptionField);
		
		JLabel label_1 = new JLabel("Threshold Amount:");
		label_1.setBounds(230, 104, 120, 15);
		namePanel.add(label_1);
		
		thresholdField = new JTextField();
		thresholdField.setBounds(369, 103, 87, 15);
		namePanel.add(thresholdField);
                
                switch(cMode)
                {
                    case 'a': 
                        nameField = new JTextField();
                        nameField.setBounds(130, 12, 220, 20);
                        namePanel.add(nameField);
                        
                        confirm = new JButton("Add");
                        break;
                    case 'e': 
                        actualNameLabel = new JLabel((String)inventoryGUI.getInventoryTable().getValueAt(inventoryGUI.getInventoryTable().getSelectedRow(), 1));
                        actualNameLabel.setBounds(130, 12, 258, 20);
                        namePanel.add(actualNameLabel);
                        
                        confirm = new JButton("Save");
                        break;
                }
                
                JLabel lblProductCategory = new JLabel("Category");
                lblProductCategory.setBounds(10, 104, 92, 14);
                namePanel.add(lblProductCategory);

                productType = new JComboBox();
                productType.setModel(new DefaultComboBoxModel(new String[] {"Hybrid", "Consumable", "Over-the-Counter"}));
                productType.setBounds(80, 101, 134, 20);
                productType.addActionListener(this);
                namePanel.add(productType);

                
                confirm.setBounds(192, 213, 100, 34);
                confirm.addActionListener(this);
                getContentPane().add(confirm);

                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.setBorder(new LineBorder(new Color(0, 0, 0)));
                panel.setBounds(10, 163, 466, 39);
                getContentPane().add(panel);

                priceField = new JTextField();
                priceField.setBounds(56, 11, 71, 16);
                panel.add(priceField);

                priceLabel = new JLabel("Price:");
                priceLabel.setBounds(10, 12, 36, 15);
                panel.add(priceLabel);

                measurementField = new JTextField();
                measurementField.setBounds(347, 11, 109, 16);
                panel.add(measurementField);

                JLabel lblMeasurement = new JLabel("Unit of Measurement:");
                lblMeasurement.setBounds(200, 12, 150, 15);
                panel.add(lblMeasurement);
                
                if (cMode == 'e')
                {
                    String[] info = controller.getProductInfo(Integer.parseInt("" + inventoryGUI.getInventoryTable().getValueAt(inventoryGUI.getInventoryTable().getSelectedRow(), 0)));
                    
                    descriptionField.setText(info[2]);
                    thresholdField.setText(info[4]);
                    priceField.setText(info[5]);
                    measurementField.setText(info[6]);
                    
                    if (info[5].equals("0.0"))
                    {
                        productType.setSelectedIndex(1);
                        priceField.setText("");
                        priceField.setEditable(false);
                    }
                    else if (info[6] == null || info[6].equals(""))
                    {
                        productType.setSelectedIndex(2);
                        measurementField.setEditable(false);
                    }
                
                
                }
                        
		setVisible(true);
        }

    public char getcMode() {
        return cMode;
    }

    public void setcMode(char cMode) {
        this.cMode = cMode;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JLabel getActualNameLabel() {
        return actualNameLabel;
    }

    public void setActualNameLabel(JLabel actualNameLabel) {
        this.actualNameLabel = actualNameLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(JLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public void setPriceField(JTextField priceField) {
        this.priceField = priceField;
    }

    public JTextField getThresholdField() {
        return thresholdField;
    }

    public void setThresholdField(JTextField thresholdField) {
        this.thresholdField = thresholdField;
    }

    public JPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(JPanel namePanel) {
        this.namePanel = namePanel;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public void setConfirm(JButton confirm) {
        this.confirm = confirm;
    }

   public EditProductController getController() {
        return controller;
    }

    public void setController(EditProductController controller) {
        this.controller = controller;
    }

    public InventoryGUI getInventoryGUI() {
        return inventoryGUI;
    }

    public void setInventoryGUI(InventoryGUI inventoryGUI) {
        this.inventoryGUI = inventoryGUI;
    }

    public JTextField getMeasurementField() {
        return measurementField;
    }

    public JComboBox getProductType() {
        return productType;
    }

    public JTextArea getDescriptionField() {
        return descriptionField;
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == productType)
        {
            switch (productType.getSelectedIndex())
            {
                case 0:
                    priceField.setEditable(true);
                    measurementField.setEditable(true);
                    break;
                case 1:
                    priceField.setEditable(false);
                    measurementField.setEditable(true);
                    break;
                case 2:
                    priceField.setEditable(true);
                    measurementField.setEditable(false);
                    break;
            }
        }
        else if (e.getSource() == confirm)
        {
            controller.confirm();
        }
    }
}
