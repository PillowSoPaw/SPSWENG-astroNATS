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
                    JOptionPane.showMessageDialog(editProductGUI, "Please enter a positive integer for the threshold.", "Input error", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                }
                
                String type = editProductGUI.getProductType().getSelectedItem().toString();
                String description = editProductGUI.getDescriptionField().getText();
                float price =0;
                switch(cMode)
                {
                    case 'a':
                        String productName = editProductGUI.getNameField().getText();
                        String measurement = editProductGUI.getMeasurementField().getText();
                        switch (type)
                        {
                            case "Consumable":
                                Consumable c = new Consumable("", productName, description, 0, threshold, measurement);
                                if (valid)
                                {
                                    DBManager.addProduct(c);
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
                                   JOptionPane.showMessageDialog(editProductGUI, "Please enter a positive number for the price.", "Input error", JOptionPane.ERROR_MESSAGE);
                                   valid = false;
                                }
                                OverTheCounter o = new OverTheCounter("", productName, description, 0, threshold, price);
                                if (valid)
                                {
                                    DBManager.addProduct(o);
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
                                   JOptionPane.showMessageDialog(editProductGUI, "Please enter a positive number for the price.", "Input error", JOptionPane.ERROR_MESSAGE);
                                   valid = false;
                                }
                                
                                if (valid)
                                {
                                    DBManager.addProduct(p, price, measurement);
                                    editProductGUI.dispose();
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
                                JOptionPane.showMessageDialog(editProductGUI, "Please enter a positive number for the price.", "Input error", JOptionPane.ERROR_MESSAGE);
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
                            editProductGUI.dispose();
                         }
                }
                
                
            
            
        
        }

    public String[] getProductInfo(int id) 
    {
        
            String[] s = DBManager.getProductInfo(id);
            return s;
    }
        
        
}


