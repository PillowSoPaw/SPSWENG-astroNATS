package model;

import java.util.ArrayList;

public class Transaction
{

	private String sTransactionId;
	private Client client;
	private ArrayList<ProductLineItem> products;
	private ArrayList<ServiceLineItem> services;
	private String sFeedback;
	
	public Transaction( String sTransactionId, Client client, String sFeedback )
	{
		this.sTransactionId = sTransactionId;
		this.client = client;
		this.products = new ArrayList<ProductLineItem>(0);//Product List in Database
		this.services = new ArrayList<ServiceLineItem>(0);//Service List in Database
		this.sFeedback = sFeedback;
	}
	
	//getters
	public String getsTransactionId()
	{
		return sTransactionId;
	}

	public Client getClient()
	{
		return client;
	}

	public ArrayList<ProductLineItem> getProducts()
	{
		return products;
	}

	public ArrayList<ServiceLineItem> getServices()
	{
		return services;
	}

	public String getsFeedback()
	{
		return sFeedback;
	}

	//setters
	public void setsTransactionId(String sTransactionId)
	{
		this.sTransactionId = sTransactionId;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public void setProducts(ArrayList<ProductLineItem> products)
	{
		this.products = products;
	}

	public void setServices(ArrayList<ServiceLineItem> services)
	{
		this.services = services;
	}

	public void setsFeedback(String sFeedback)
	{
		this.sFeedback = sFeedback;
	}

	//add product line items
	public void addProductLineItem( ProductLineItem product )
	{
		this.products.add(product);
	}
	
	//add service line items
	public void addServiceLineItem( ServiceLineItem service )
	{
		this.services.add(service);
	}
}
