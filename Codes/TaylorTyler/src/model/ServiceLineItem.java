package model;

public class ServiceLineItem
{

	private String sServiceLineItemId;
	private Service service;
	
	public ServiceLineItem( String sServiceLineItemId, Service service )
	{
		this.sServiceLineItemId = sServiceLineItemId;
		this.service = service;
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

	//setters
	public void setsServiceLineItemId(String sServiceLineItemId)
	{
		this.sServiceLineItemId = sServiceLineItemId;
	}

	public void setService(Service service)
	{
		this.service = service;
	}
	
}
