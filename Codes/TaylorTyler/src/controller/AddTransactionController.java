package controller;

import java.util.ArrayList;
import java.util.Iterator;

import view.AddTransactionGUI;
import model.Client;
import model.DatabaseManager;
import model.Employee;
import model.Product;
import model.ProductLineItem;
import model.Service;
import model.ServiceLineItem;
import model.Transaction;

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
		addTransactionGUI.getData();
	}
	
	public Client getClient(String name)
	{
		return DBManager.getClient(name);
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
	public void createTransaction(ArrayList<Object[]> servicesAvailed, ArrayList<Object[]> productsBought,
							ArrayList<ArrayList<Object[]>> consumable, String clientName)
	{
		boolean success;
		ArrayList<Service> s = new ArrayList<>(0);
		ArrayList<Product> p = new ArrayList<>(0);
		ArrayList<Product> cs = new ArrayList<>(0);
                ArrayList<Integer> csAmount = new ArrayList<>(0);
		Client c = getClient(clientName);
		Transaction t = new Transaction("", c, "");
		
		for( int i = 0; i < servicesAvailed.size(); i++ )
		{
			s.add(getService((String) servicesAvailed.get(i)[1]));
		}
		
		for( int i = 0; i < productsBought.size(); i++ )
		{
			p.add(getProduct((String) productsBought.get(i)[0]));
		}
		
                for(int i = 0; i < consumable.size(); i++)
                {
                    for(int k = 0; k < consumable.get(i).size(); k++)
                    {
                        cs.add(getProduct( (String) consumable.get(i).get(k)[0]));
                        csAmount.add((int) consumable.get(i).get(k)[1]);
                    }
                }
                
		ArrayList<ServiceLineItem> sli = new ArrayList<>(0);
		ArrayList<ProductLineItem> pli = new ArrayList<>(0);
		
		for( int i = 0; i < p.size(); i++ )
		{
			t.addProductLineItem(new ProductLineItem("", p.get(i), (int) productsBought.get(i)[1]));
		}
                
                for(int i = 0; i < cs.size(); i++)
                {
                    t.addProductLineItem(new ProductLineItem("", cs.get(i), (int) csAmount.get(i)));
                }
		
		for( int i = 0; i < s.size(); i++ )
		{	
			if( "None".equalsIgnoreCase((String) servicesAvailed.get(i)[2]) && "None".equalsIgnoreCase((String) servicesAvailed.get(i)[3])){
				t.addServiceLineItem(new ServiceLineItem("", s.get(i), 1, getEmployee((String) servicesAvailed.get(i)[2]), getEmployee((String) servicesAvailed.get(i)[3])));
                        }
                        else if( "None".equalsIgnoreCase((String) servicesAvailed.get(i)[3]) )
				t.addServiceLineItem(new ServiceLineItem("", s.get(i), 1, null, getEmployee((String) servicesAvailed.get(i)[2])));
			else if( "None".equalsIgnoreCase((String) servicesAvailed.get(i)[2]) )
				t.addServiceLineItem(new ServiceLineItem("", s.get(i), 1, getEmployee((String) servicesAvailed.get(i)[3]), null));
		}
		
		success = DBManager.addTransaction(t);
		if( success == true )
		{
			DBManager.updateProductQuantity( t.getProducts() );
			DBManager.updateConsumableQuantity(cs);
		}
	}
}
