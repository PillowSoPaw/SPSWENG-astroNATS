package controller;

import view.AddServicesGUI;
import model.DatabaseManager;
import model.Product;

public class AddServicesController
{
	private static DatabaseManager DBManager;
	private AddServicesGUI addServiceGUI;
	
	public AddServicesController()
	{
		DBManager = DBManager.getInstance();
	}
		
	public void setView( AddServicesGUI addServicesGUI )
	{
		this.addServiceGUI = addServicesGUI;
		addServicesGUI.getData();
	}
	
	//get product from model
	public Product getProduct(String name)
	{
		return DBManager.getProduct(name);
	}
	
	// get employees from model
	public void getEmployees()
	{
		addServiceGUI.getSeniorEmployeeList(DBManager.getSeniorEmployees());
		addServiceGUI.getJuniorEmployeeList(DBManager.getJuniorEmployees());
	}

	// get all consumable products from model
	public void getConsumables()
	{
		addServiceGUI.getConsumableList(DBManager.getAllConsumableProducts());
	}

	// get servies from model then give data to service view
	public void getServices()
	{
		addServiceGUI.getServiceList(DBManager.getAllServices());
	}
}
