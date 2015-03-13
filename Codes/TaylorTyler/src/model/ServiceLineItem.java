package model;

public class ServiceLineItem
{

	private String sServiceLineItemId;
	private Service service;
	private int nQuantity;
	private Employee employee1;
	private Employee employee2;
	
	public ServiceLineItem( String sServiceLineItemId, Service service, int nQuantity, Employee employee1, Employee employee2)
	{
		this.sServiceLineItemId = sServiceLineItemId;
		this.service = service;
		this.nQuantity = nQuantity;
		this.employee1 = employee1;
		this.employee2 = employee2;
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
	
	public Employee getEmployee(int index)
	{
		if(index == 1)
		{
			return employee1;
		}else if(index == 2)
		{
			return employee2;
		}else
		{
			return null;
		}
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
	
	public void setEmployee(int index, Employee employee)
	{
		if(index == 1)
		{
			this.employee1 = employee;
		}else if(index == 2)
		{
			this.employee2 = employee;
		}
	}
}
