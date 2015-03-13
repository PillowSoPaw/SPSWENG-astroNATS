package controller;

import java.util.ArrayList;
import java.util.Iterator;

import view.AddTransactionPanel;
import view.EmployeeListFrame;
import view.ProductListFrame;
import model.DatabaseManager;
import model.Employee;
import model.Product;
import model.Transaction;
import model.ProductLineItem;
import model.ServiceLineItem;

public class Controller
{
	private static DatabaseManager	DBManager;
	private AddTransactionPanel transactionPanel;
	private EmployeeListFrame	employeeListPanel;

	public Controller( AddTransactionPanel transactionPanel, EmployeeListFrame employeeLIstFrame )
	{
		DBManager = DBManager.getInstance();
		transactionPanel.setController(this);
		this.transactionPanel = transactionPanel;;
	}

	// A function that displays all the employees
	public Iterator getEmployees()
	{
		return DBManager.getAllEmployees();
	}

	// get product from model
	public Iterator getProducts()
	{
		return DBManager.getAllProducts();
	}

	//get all consumable products from model
	public Iterator getConsumables()
	{
		return DBManager.getAllConsumableProducts();
	}
	
	// get servies from model then give data to view
	public void getServices()
	{
		transactionPanel.getServiceList(DBManager.getAllServices());
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
