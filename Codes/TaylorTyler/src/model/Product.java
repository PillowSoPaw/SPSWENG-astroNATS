package model;

public class Product
{
	
	private String sProductId;
	private String sName;
	private String sDescription;
	private int nQuantity;
	
	public Product( String sProductId, String sName, String sDescription, int nQuantity )
	{
		this.sProductId = sProductId;
		this.sName = sName;
		this.sDescription = sDescription;
		this.nQuantity = nQuantity;
	}
	
	//getters
	public String getsProductId()
	{
		return sProductId;
	}

	public String getsName()
	{
		return sName;
	}

	public String getsDescription()
	{
		return sDescription;
	}

	public int getnQuantity()
	{
		return nQuantity;
	}

	//setters
	public void setsProductId(String sProductId)
	{
		this.sProductId = sProductId;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public void setsDescription(String sDescription)
	{
		this.sDescription = sDescription;
	}

	public void setnQuantity(int nQuantity)
	{
		this.nQuantity = nQuantity;
	}
	
}
