package controller;

import model.DatabaseManager;
import view.ManageEmployeesGUI;

public class ManageEmployeesController 
{
	private static DatabaseManager	DBManager;
	private ManageEmployeesGUI manageEmployeesGUI;
	
	public ManageEmployeesController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( ManageEmployeesGUI manageEmployeesGUI )
	{
		this.manageEmployeesGUI = manageEmployeesGUI;
		manageEmployeesGUI.getData();
	}	
	// get employees from model
	public void getEmployees()
	{
		manageEmployeesGUI.getEmployees(DBManager.getAllEmployees());
	}
	
	public void getEmployee(int client_id)
	{	
	//manageClientsGUI.getClient(DBManager.getClient(client_id), DBManager.getServiceTransactionByClient(client_id),DBManager.getProductTransactionsByClient(client_id));
	}
	public void getEmployeesByName(String name)
	{
		manageEmployeesGUI.getEmployeesByName(DBManager.getEmployeesByName(name));
	}
	
	public void deactivateEmployee(String employee_id)
	{
		DBManager.deactivateEmployee(employee_id);
	}
}
