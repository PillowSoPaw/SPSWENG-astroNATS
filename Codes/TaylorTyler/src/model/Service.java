package model;

public class Service
{

	private String sServiceId;
	private String sName;
	private String sDescription;
	private double dPrice;
	
	public Service( String sServiceId, String sName, String sDescription, double dPrice )
	{
		this.sServiceId = sServiceId;
		this.sName = sName;
		this.sDescription = sDescription;
		this.dPrice = dPrice;
	}

	//getters
	public String getsServiceId()
	{
		return sServiceId;
	}

	public String getsName()
	{
		return sName;
	}

	public String getsDescription()
	{
		return sDescription;
	}

	public double getdPrice()
	{
		return dPrice;
	}

	//setters
	public void setsServiceId(String sServiceId)
	{
		this.sServiceId = sServiceId;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public void setsDescription(String sDescription)
	{
		this.sDescription = sDescription;
	}

	public void setdPrice(double dPrice)
	{
		this.dPrice = dPrice;
	}
	
}
