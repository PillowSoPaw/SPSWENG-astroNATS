package controller;

import java.util.ArrayList;
import java.util.Iterator;
import model.Client;
import model.DatabaseManager;
import model.Employee;
import model.Product;
import model.ProductLineItem;
import model.Receipt;
import model.Service;
import model.ServiceLineItem;
import model.Transaction;
import view.AddTransactionGUI;

public class AddTransactionController
{
	private static DatabaseManager DBManager;
	private AddTransactionGUI addTransactionGUI;
	
	public AddTransactionController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( AddTransactionGUI addTransactionGUI )
	{
		this.addTransactionGUI = addTransactionGUI;
	}
	
	public Client getClient(String name)
	{
		return DBManager.getClient(name);
	}
	
	public Client getClient(int client_id)
	{
		return DBManager.getClient(client_id);
	}
	
	public Service getService(String name)
	{
		return DBManager.getService(name);
	}
	
	public Product getProduct(String name)
	{
		return DBManager.getProduct(name);
	}
	
	public Employee getEmployee(String name)
	{
		if(name.equals(""))
		{
			return DBManager.getEmployee("Taylor Tyler");
		}
		return DBManager.getEmployee(name);
	}
	
	public Iterator getAllClients()
	{
		return DBManager.getAllClients();
	}
	
	public void addTransaction(Transaction t)
	{
		try
		{
			DBManager.addTransaction(t);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// create transaction with information from view
	public Transaction createTransaction(ArrayList<Object[]> servicesAvailed, ArrayList<Object[]> productsBought,
							ArrayList<ArrayList<Object[]>> consumable, String clientName)
	{
		boolean success;
                
		ArrayList<Product> cs = new ArrayList<>(0);
		ArrayList<Integer> csAmount = new ArrayList<>(0);
		Client c = getClient(clientName);
		Transaction t = new Transaction("", c, "");
		
//                for(int i = 0; i < consumable.size(); i++)
//                {
//                    if(clientName.equals((String) servicesAvailed.get(i)[0]))
//                        {
//                            for(int k = 0; k < consumable.get(i).size(); k++)
//                            {
//                                cs.add(getProduct( (String) consumable.get(i).get(k)[0]));
//                                csAmount.add((int) consumable.get(i).get(k)[1]);
//                            }
//                        }
//                }
                
		for( int i = 0; i < productsBought.size(); i++ )
		{
                    if(clientName.equals((String) productsBought.get(i)[0]))
			t.addProductLineItem(new ProductLineItem("", getProduct((String) productsBought.get(i)[1]),
                                (int) productsBought.get(i)[2], getEmployee((String) productsBought.get(i)[5])));
		}
                
//                for(int i = 0; i < cs.size(); i++)
//                {
//                    t.addProductLineItem(new ProductLineItem("", cs.get(i), csAmount.get(i)));
//                }
		
		for( int i = 0; i < servicesAvailed.size(); i++ )
		{	
                    if(clientName.equals((String) servicesAvailed.get(i)[0]))
                    {
			if( !"None".equalsIgnoreCase((String) servicesAvailed.get(i)[2]) && !"None".equalsIgnoreCase((String) servicesAvailed.get(i)[3]))
                        {
				t.addServiceLineItem(new ServiceLineItem("", getService((String) servicesAvailed.get(i)[1]),
                                        1, getEmployee((String) servicesAvailed.get(i)[2]), getEmployee((String) servicesAvailed.get(i)[3])));
                        }
                        else if( "None".equalsIgnoreCase((String) servicesAvailed.get(i)[2]) )
                        {
				t.addServiceLineItem(new ServiceLineItem("", getService((String) servicesAvailed.get(i)[1]),
                                        1, null, getEmployee((String) servicesAvailed.get(i)[3])));
                        }
			else if( "None".equalsIgnoreCase((String) servicesAvailed.get(i)[3]) )
                        {
				t.addServiceLineItem(new ServiceLineItem("", getService((String) servicesAvailed.get(i)[1]),
                                        1, getEmployee((String) servicesAvailed.get(i)[2]), null));
                        }
                    }
		}
		
		int nTransactionID = DBManager.addTransaction(t);
		if( nTransactionID != 999 )
		{
			DBManager.updateProductQuantity( t.getProducts() );
			DBManager.updateConsumableQuantity(cs);
                        t.setsTransactionId(Integer.toString(nTransactionID));
                        return t;
		}else{
                    return null;
                }
	}
        
        public void createReceipt(ArrayList<Transaction> tList, String clientName, double price)
        {
            
            Receipt r = new Receipt("", getClient(clientName), "Cash", price);
            
            for(int i = 0; i < tList.size(); i++)
            {
                r.addTransaction(tList.get(i));
                tList.get(i).getClient().getsName();
            }
                
            
            DBManager.addReceipt(r);
        }
}
