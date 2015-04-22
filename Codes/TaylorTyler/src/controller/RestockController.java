package controller;

import javax.swing.JOptionPane;
import model.DatabaseManager;
import view.RestockGUI;

public class RestockController 
{
        private static DatabaseManager DBManager;
	private RestockGUI restockGUI;
        private char cMode;
        
        public RestockController(char cMode)
	{
                this.cMode = cMode;
		DBManager = DBManager.getInstance();
	}
	
	public void setView(  RestockGUI restockGUI )
	{
		this.restockGUI = restockGUI;
	}

        public char getcMode() 
        {
                return cMode;
        }

        public void confirm()
        {
                int id = Integer.parseInt(restockGUI.getInventoryGUI().getInventoryTable().getValueAt(restockGUI.getInventoryGUI().getInventoryTable().getSelectedRow(), 0).toString());
                int currQuantity = Integer.parseInt(restockGUI.getInventoryGUI().getInventoryTable().getValueAt(restockGUI.getInventoryGUI().getInventoryTable().getSelectedRow(), 2).toString());
                try
                {
                        
                        int quantity = Integer.parseInt(restockGUI.getQuantityTextField().getText());
                        
                        
                        if (quantity <= 0)
                        {
                                throw new NumberFormatException();
                        }
                        
                        if (cMode=='p' && currQuantity < quantity)
                        {
                                JOptionPane.showMessageDialog(restockGUI, "You cannot pull out that many items!", "Input error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                                switch (cMode)
                                {
                                        case ('p'):
                                                DBManager.updateProductQuantity(id, currQuantity - quantity);
                                                int index = restockGUI.getInventoryGUI().getCategoryComboBox().getSelectedIndex();
                                                restockGUI.getInventoryGUI().getController().updateProducts(index);
                                                restockGUI.dispose();
                                        break;

                                        case ('r'):
                                                DBManager.updateProductQuantity(id, currQuantity + quantity);
                                                index = restockGUI.getInventoryGUI().getCategoryComboBox().getSelectedIndex();
                                                restockGUI.getInventoryGUI().getController().updateProducts(index);
                                                restockGUI.dispose();
                                        break;
                                }
                        }
                    
                }
                catch(NumberFormatException ex)
                {
                        JOptionPane.showMessageDialog(restockGUI, "Please enter a positive integer", "Input error", JOptionPane.ERROR_MESSAGE);
                }
            
            
        }
    
}
