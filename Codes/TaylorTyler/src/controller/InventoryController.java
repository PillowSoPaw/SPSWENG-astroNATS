
package controller;

import java.util.ArrayList;
import java.util.Iterator;
import model.DatabaseManager;
import model.Product;
import view.EditProductGUI;
import view.InventoryGUI;

/**
 *
 * @author Mariell
 */
public class InventoryController 
{
        private static DatabaseManager DBManager;
	private InventoryGUI inventoryGUI;
	
	public InventoryController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( InventoryGUI inventoryGUI )
	{
		this.inventoryGUI = inventoryGUI;
	}
        
        public void search()
        {
            System.out.println("start");
                inventoryGUI.resetTable();
                
                String searchparam = this.inventoryGUI.getSearchProductTextField().getText();
                Iterator x = DBManager.filterProducts(searchparam);
                
                 while (x.hasNext()){
                 
                 Product p = (Product)x.next();
                 Object[] y = {p.getsProductId(), p.getsName(), p.getnQuantity(),"", ""};
                 inventoryGUI.getInventoryTableRows().add(y);
                 
                 
             }  
                 
                 inventoryGUI.updateInventoryTable();
                
        }
	
        //public  void 

        public void addProduct() 
        {
                EditProductGUI editProductGUI = new EditProductGUI(new EditProductController('a'), this.inventoryGUI);
        }

        public void editProduct() 
        {
            
                EditProductGUI editProductGUI = new EditProductGUI(new EditProductController('e'), this.inventoryGUI);
                
        }

        public void deleteProduct() 
        {
            int row = inventoryGUI.getInventoryTable().getSelectedRow();
            DBManager.deleteProduct((String)inventoryGUI.getInventoryTable().getValueAt(row, 0));
            inventoryGUI.resetTable();
            updateProducts();
            inventoryGUI.updateInventoryTable();
            
         
            //update the UI
            
        }
        
        public void updateProducts()
        {
             Iterator products = DBManager.getAllProducts();
             
             while (products.hasNext())
             {
                 Product p = (Product)products.next();
                 Object[] x = {p.getsProductId(), p.getsName(), p.getnQuantity()};
                 inventoryGUI.getInventoryTableRows().add(x);
             }
        }

        

    public void updateProducts(int index) 
    {
        inventoryGUI.resetTable();
        Iterator products;
        switch (index)
        {
            case 0:
                this.updateProducts();
                
                break;
            case 1:
                products = DBManager.getAllConsumableProducts();
             
                while (products.hasNext())
                {
                    Product p = (Product)products.next();
                    Object[] x = {p.getsProductId(), p.getsName(), p.getnQuantity()};
                    inventoryGUI.getInventoryTableRows().add(x);
                }
                break;
            case 2:
                products = DBManager.getAllOverTheCounterProducts();
             
                while (products.hasNext())
                {
                    Product p = (Product)products.next();
                    Object[] x = {p.getsProductId(), p.getsName(), p.getnQuantity()};
                    inventoryGUI.getInventoryTableRows().add(x);
                }
                break;
            case 3:
                products = DBManager.getAllHybridProducts();
             
                while (products.hasNext())
                {
                    Product p = (Product)products.next();
                    Object[] x = {p.getsProductId(), p.getsName(), p.getnQuantity(),"", ""};
                    inventoryGUI.getInventoryTableRows().add(x);
                }
                break;
        }
        inventoryGUI.updateInventoryTable();
    }
	
}
