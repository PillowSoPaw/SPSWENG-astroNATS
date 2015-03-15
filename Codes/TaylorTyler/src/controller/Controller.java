package controller;

import java.util.ArrayList;
import java.util.Iterator;

import view.AddTransactionPanel;
import view.EmployeeListFrame;
import view.ProductListFrame;
import view.TransactionGUI;
import model.Client;
import model.DatabaseManager;
import model.Employee;
import model.Product;
import model.Service;
import model.Transaction;
import model.ProductLineItem;
import model.ServiceLineItem;

public class Controller
{
	private static DatabaseManager	DBManager;
	private TransactionGUI transactionGUI;
	//private AddTransactionPanel transactionPanel;

	public Controller(TransactionGUI transactionGUI)// AddTransactionPanel transactionPanel )
	{
		DBManager = DBManager.getInstance();
		transactionGUI.setController(this);
		this.transactionGUI = transactionGUI;
		transactionGUI.getAddTransactionPanel().getData();
	}

	//get client based on name
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
	// get employees from model
	public void getEmployees()
	{
		transactionGUI.getAddTransactionPanel().getEmployeeList(DBManager.getAllEmployees());
	}

	// create transaction with information from view
	public void createTransaction(ArrayList<String> servicesAvailed, ArrayList<String> productsBought, ArrayList<Integer> productsQuantity, String clientName)
	{
		ArrayList<Service> s = new ArrayList<>(0);
		ArrayList<Product> p = new ArrayList<>(0);
		
		Client c = getClient(clientName);
		Transaction t = new Transaction("", c, "");
		
		for( int i = 0; i < servicesAvailed.size(); i++ )
		{
			s.add(getService(servicesAvailed.get(i)));
		}
		
		for( int i = 0; i < productsBought.size(); i++ )
		{
			p.add(getProduct(productsBought.get(i)));
		}
		
		ArrayList<ServiceLineItem> sli = new ArrayList<>(0);
		ArrayList<ProductLineItem> pli = new ArrayList<>(0);
		
		for( int i = 0; i < p.size(); i++ )
		{
			t.addProductLineItem(new ProductLineItem("", p.get(i), productsQuantity.get(i)));
		}
		
		for( int i = 0; i < s.size(); i++ )
		{
			t.addServiceLineItem(new ServiceLineItem("", s.get(i), 1, getEmployee("Sen One"), null));
		}
		
		
		DBManager.addTransaction(t);
	}

	//get all consumable products from model
	public void getConsumables()
	{
		transactionGUI.getAddTransactionPanel().getConsumableList(DBManager.getAllConsumableProducts());
	}
	
	public void getOverTheCounters()
	{
		transactionGUI.getAddTransactionPanel().getOverTheCounterList(DBManager.getAllOverTheCounterProducts());
	}
	// get services from model then give data to view
	public void getServices()
	{
		transactionGUI.getAddTransactionPanel().getServiceList(DBManager.getAllServices());
	}
	
	// save transaction to database
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

	// A function that returns the generated transaction (to be displayed in
	// the table)
	public Transaction getTransaction(Transaction transaction)
	{
		return transaction;
	}
}
