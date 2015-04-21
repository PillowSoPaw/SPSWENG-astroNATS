package controller;

import java.util.Iterator;
import model.DatabaseManager;
import model.Product;
import view.ViewNotificationsGUI;

public class ViewNotificationsController
{
	private DatabaseManager DBManager;
	private ViewNotificationsGUI viewNotificationsGUI;

	public ViewNotificationsController()
	{
		DBManager = DBManager.getInstance();
	}

	public void setView(ViewNotificationsGUI viewNotificationsGUI)
	{
		this.viewNotificationsGUI = viewNotificationsGUI;
	}

	public void updateNotifications()
	{
		Iterator products = DBManager.getLackingProducts();

		while (products.hasNext())
		{
			Product p = (Product) products.next();
			Object[] x = { p.getsName(), p.getnQuantity() };
			viewNotificationsGUI.getProductRows().add(x);
		}
	}

}
