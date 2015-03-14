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

	public Controller( AddTransactionPanel transactionPanel, EmployeeListFrame employeeListFrame )
	{
		DBManager = DBManager.getInstance();
		transactionPanel.setController(this);
		this.transactionPanel = transactionPanel;
		employeeListFrame.setController(this);
		this.employeeListPanel = employeeListFrame;
	}

	// A function that displays all the employees
	public void getEmployees()
	{
		employeeListPanel.loadEmployees(DBManager.getAllEmployees());
	}

	// get product from model
	public void getProducts()
	{
		transactionPanel.getProductList(DBManager.getAllProducts());
	}

	//get all consumable products from model
	public void getConsumables()
	{
		transactionPanel.getConsumableList(DBManager.getAllConsumableProducts());
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
