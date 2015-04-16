package controller;

import view.AddProductsGUI;
import model.DatabaseManager;
import model.Product;

public class AddProductsController
{
	private static DatabaseManager DBManager;
	private AddProductsGUI addProductsGUI;
	
	public AddProductsController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( AddProductsGUI addProductsGUI )
	{
		this.addProductsGUI = addProductsGUI;
		addProductsGUI.getData();
	}
	
	public void getProducts()
	{
		addProductsGUI.getOverTheCounterList(DBManager.getAllOverTheCounterProducts());
	}
	
	//get product from model
	public Product getProduct(String name)
	{
		return DBManager.getProduct(name);
	}
}
