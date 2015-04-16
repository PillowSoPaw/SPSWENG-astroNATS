package model;

public class Product
{
	
	private String sProductId;
	private String sName;
	private String sDescription;
	private int nQuantity;
	private int nThreshold;
	
	public Product( String sProductId, String sName, String sDescription, int nQuantity, int nThreshold )
	{
		this.sProductId = sProductId;
		this.sName = sName;
		this.sDescription = sDescription;
		this.nQuantity = nQuantity;
		this.nThreshold = nThreshold;
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

	public int getnThreshold()
	{
		return nThreshold;
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
	
	public void setnThreshold(int nThreshold)
	{
		this.nThreshold = nThreshold;
	}
	
}
