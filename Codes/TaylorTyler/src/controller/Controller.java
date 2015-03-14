package controller;

import java.util.ArrayList;
import java.util.Iterator;

import view.AddTransactionPanel;
import view.EmployeeListFrame;
import view.ProductListFrame;
import view.TransactionGUI;
import model.DatabaseManager;
import model.Employee;
import model.Product;
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
		//transactionPanel.setController(this);
		//this.transactionPanel = transactionPanel;
		//transactionPanel.getData();
	}

	// A function that displays all the employees
	public void getEmployees()
	{
		transactionGUI.getAddTransactionPanel().getEmployeeList(DBManager.getAllEmployees());
	}

	// get product from model
	public void getProducts()
	{
		transactionGUI.getAddTransactionPanel().getProductList(DBManager.getAllProducts());
	}

	//get all consumable products from model
	public void getConsumables()
	{
		transactionGUI.getAddTransactionPanel().getConsumableList(DBManager.getAllConsumableProducts());
	}
	
	// get servies from model then give data to view
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
