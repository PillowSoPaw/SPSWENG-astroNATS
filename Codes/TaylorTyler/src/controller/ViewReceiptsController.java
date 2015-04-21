package controller;

import view.ViewReceiptsGUI;
import model.DatabaseManager;

public class ViewReceiptsController
{
	private static DatabaseManager DBManager;
	private ViewReceiptsGUI viewReceiptsGUI;
	
	public  ViewReceiptsController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( ViewReceiptsGUI viewReceiptsGUI )
	{
		this.viewReceiptsGUI = viewReceiptsGUI;
	}
	
	public void getReceipts()
	{
		viewReceiptsGUI.getReceiptList(DBManager.getAllReceipts());
	}
	
	public void getLineItemsOfReceipt( int receipt_id )
	{
		viewReceiptsGUI.getServiceList(DBManager.getAllServiceTransactionsOfReceipt(receipt_id));
		viewReceiptsGUI.getProductList(DBManager.getAllProductTransactionsOfReceipt(receipt_id));
	}
}
