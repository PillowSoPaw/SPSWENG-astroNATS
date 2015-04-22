package controller;

import javax.swing.JOptionPane;
import model.Consumable;
import model.DatabaseManager;
import model.OverTheCounter;
import model.Product;
import view.EditProductGUI;

public class EditProductController 
{
        private static DatabaseManager DBManager;
        private char cMode;
        private EditProductGUI editProductGUI;

        public EditProductController(char cMode) 
        {
                this.cMode = cMode;
                DBManager = DBManager.getInstance();
        }

        public char getMode()
        {
                return this.cMode;
        }

        public void setView( EditProductGUI editProductGUI )
        {
                this.editProductGUI = editProductGUI;;
        }

        public void confirm()
        {
                int threshold = 0;
                boolean valid = true;
                String type = editProductGUI.getProductType().getSelectedItem().toString();
                String description = editProductGUI.getDescriptionField().getText();
                float price =0;
                String errormessage = "";
                try
                {
                    threshold = Integer.parseInt(editProductGUI.getThresholdField().getText());
                    if (threshold < 0)
                    {
                        throw new NumberFormatException();
                    }
                }
                catch(NumberFormatException ex)
                {
                    errormessage += "Please enter a positive integer for the threshold.\n";
                    valid = false;
                }
                
                switch(cMode)
                {
                    case 'a':
                        String productName = editProductGUI.getNameField().getText();
                        
                        if (productName.equals(""))
                        {
                                valid = false;
                                errormessage += "Please enter the productname.\n";
                                
                        }
                        String measurement = editProductGUI.getMeasurementField().getText();
                        if (measurement.equals(""))
                        {
                                valid = false;
                                errormessage += "Please enter the unit of measurement.\n";
                                
                        }
                        
                        switch (type)
                        {
                            case "Consumable":
                                Consumable c = new Consumable("", productName, description, 0, threshold, measurement);
                                if (valid)
                                {
                                    DBManager.addProduct(c);
                                    int index = editProductGUI.getInventoryGUI().getCategoryComboBox().getSelectedIndex();
                                    editProductGUI.getInventoryGUI().getController().updateProducts(index);
                                    editProductGUI.dispose();
                                    
                                }
                                break;
                            case "Over-the-Counter":
                                try
                                {
                                    price = Float.parseFloat(editProductGUI.getPriceField().getText());
                                    if (price < 0)
                                    {
                                        throw new NumberFormatException();
                                    }
                                }
                                catch(NumberFormatException ex)
                                {
                                   errormessage += "Please enter a positive number for the price.\n";
                                   valid = false;
                                }
                                OverTheCounter o = new OverTheCounter("", productName, description, 0, threshold, price);
                                if (valid)
                                {
                                    DBManager.addProduct(o);
                                    int index = editProductGUI.getInventoryGUI().getCategoryComboBox().getSelectedIndex();
                                    editProductGUI.getInventoryGUI().getController().updateProducts(index);
                                    editProductGUI.dispose();
                                }
                                
                                break;
                            case "Hybrid":
                                Product p = new Product("", productName, description, 0, threshold);
                                try{
                                    price = Float.parseFloat(editProductGUI.getPriceField().getText());
                                    if (price < 0)
                                    {
                                        throw new NumberFormatException();
                                    }
                                }
                                catch(NumberFormatException ex)
                                {
                                   errormessage += "Please enter a positive number for the price.\n";
                                   valid = false;
                                }
                                
                                if (valid)
                                {
                                    DBManager.addProduct(p, price, measurement);
                                    int index = editProductGUI.getInventoryGUI().getCategoryComboBox().getSelectedIndex();
                                    editProductGUI.getInventoryGUI().getController().updateProducts(index);
                                    editProductGUI.dispose();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(editProductGUI, errormessage, "Input error", JOptionPane.ERROR_MESSAGE);
                                }
                                
                                break;
                        }
                        break;
                    case 'e':
                         String ID = editProductGUI.getInventoryGUI().getInventoryTable().getValueAt(editProductGUI.getInventoryGUI().getInventoryTable().getSelectedRow(), 0).toString();
                         Product p = new Product(ID, "", description, 0, threshold);
                         if (!type.equals("Consumable"))
                         {
                             
                             try
                             {
                                 price = Float.parseFloat(editProductGUI.getPriceField().getText());
                                 if (price < 0)
                                 {
                                        throw new NumberFormatException();
                                 }
                             }
                             catch(NumberFormatException ex)
                             {
                                errormessage += "Please enter a positive number for the price.\n";
                                valid = false;
                             }
                             
                         }
                         else
                         {
                             price = 0;
                         }
                         measurement = editProductGUI.getMeasurementField().getText();
                         if (valid)
                         {
                            DBManager.editProduct(p, price, measurement, type);
                            int index = editProductGUI.getInventoryGUI().getCategoryComboBox().getSelectedIndex();
                            editProductGUI.getInventoryGUI().getController().updateProducts(index);
                            editProductGUI.dispose();
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(editProductGUI, errormessage, "Input error", JOptionPane.ERROR_MESSAGE);
                         }
                }
        }

    public String[] getProductInfo(int id) 
    {
        
            String[] s = DBManager.getProductInfo(id);
            return s;
    }
        
        
}


