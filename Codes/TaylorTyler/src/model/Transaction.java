package model;

import java.util.ArrayList;

public class Transaction
{

	private String sTransactionId;
	private Client client;
	private Employee employee;
	private ArrayList<ProductLineItem> products;
	private ArrayList<ServiceLineItem> services;
	private Receipt receipt;
	private String sType;
	private String sFeedback;
	
	public Transaction( String sTransactionId, Client client, Employee employee, Receipt receipt, String sType, String sFeedback )
	{
		this.sTransactionId = sTransactionId;
		this.client = client;
		this.employee = employee;
		this.products = new ArrayList<ProductLineItem>(0);
		this.services = new ArrayList<ServiceLineItem>(0);
		this.receipt = receipt;
		this.sType = sType;
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

	public Employee getEmployee()
	{
		return employee;
	}

	public ArrayList<ProductLineItem> getProducts()
	{
		return products;
	}

	public ArrayList<ServiceLineItem> getServices()
	{
		return services;
	}

	public Receipt getReceipt()
	{
		return receipt;
	}

	public String getsType()
	{
		return sType;
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

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public void setProducts(ArrayList<ProductLineItem> products)
	{
		this.products = products;
	}

	public void setServices(ArrayList<ServiceLineItem> services)
	{
		this.services = services;
	}

	public void setReceipt(Receipt receipt)
	{
		this.receipt = receipt;
	}

	public void setsType(String sType)
	{
		this.sType = sType;
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
