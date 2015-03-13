package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.DatabaseManager;
import model.Employee;
import model.Product;
import model.Transaction;
import model.ProductLineItem;
import model.ServiceLineItem;

public class Controller 
{
	private static DatabaseManager DBManager;
	
	public Controller()
	{
		DBManager = DBManager.getInstance();
	}
	
	// A function that displays all the employees
	public Iterator getEmployees()
	{
        return DBManager.getAllEmployees();
    }
	//get product from model
	public Iterator getProducts()
	{
        return DBManager.getAllProducts();
    }
	//get servies from model
	public Iterator getServices(){
		return DBManager.getAllServices();
	}
	
	//save transaction to database
	public void addTransaction(Transaction t)
	{
		try
		{
			DBManager.addTransaction(t);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	// A function that returns the generated transaction (to be displayed in the table)
	public Transaction getTransaction (Transaction transaction)
	{
		return transaction;
	}
}
