package controller;

import model.DatabaseManager;
import model.Product;
import view.ManageClientsGUI;

public class ViewClientsController 
{	
	private static DatabaseManager	DBManager;
	private ManageClientsGUI manageClientsGUI;
	
	public ViewClientsController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( ManageClientsGUI viewClientsGUI )
	{
		this.manageClientsGUI = viewClientsGUI;
		viewClientsGUI.getData();
	}	
	// get employees from model
	public void getClients()
	{
		manageClientsGUI.getClients(DBManager.getAllClients());
	}
	
//	public void getClient(int client_id)
//	{
//		manageClientsGUI.getClient(DBManager.getClient(client_id));
//	}
}
