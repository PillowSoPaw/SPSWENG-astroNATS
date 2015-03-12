package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.Employee;
import model.Product;
import model.Transaction;

public class Controller 
{
	private ArrayList<Employee> employees;
	private ArrayList<Product> products;
	private ArrayList<Transaction> transactions;
	
	public Controller()
	{
		employees= new ArrayList<Employee>();
		products= new ArrayList<Product>();
		transactions= new ArrayList<Transaction>();
	}
	// A function that displays all the employees
	public Iterator getEmployees()
	{
        return employees.iterator();
    }
	//get product from model
	public Iterator getProducts()
	{
        return products.iterator();
    }
	//save transaction to database
	public void addTransaction( String sTransactionId, String sClientId, String sServiceLineItemId, String sProductLineId )//not sure parameter
	{
		try
		{
			//from dbm
			//DBManager.addTransaction( sTransactionId, sClientId, sServiceLineItemId, sProductLineId);
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
