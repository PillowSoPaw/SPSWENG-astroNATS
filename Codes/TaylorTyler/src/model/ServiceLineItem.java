package model;

public class ServiceLineItem
{

	private String sServiceLineItemId;
	private Service service;
	private int nQuantity;
	
	public ServiceLineItem( String sServiceLineItemId, Service service, int nQuantity )
	{
		this.sServiceLineItemId = sServiceLineItemId;
		this.service = service;
		this.nQuantity = nQuantity;
	}

	//getters
	public String getsServiceLineItemId()
	{
		return sServiceLineItemId;
	}

	public Service getService()
	{
		return service;
	}
	
	public int getnQuantity()
	{
		return nQuantity;
	}

	//setters
	public void setsServiceLineItemId(String sServiceLineItemId)
	{
		this.sServiceLineItemId = sServiceLineItemId;
	}

	public void setService(Service service)
	{
		this.service = service;
	}
	
	public void setnQuantity( int nQuantity )
	{
		this.nQuantity = nQuantity;
	}
}
