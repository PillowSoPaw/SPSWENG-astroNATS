package model;

public class OverTheCounter extends Product
{
	
	private double dPrice;
	
	public OverTheCounter( String sProductId, String sName, String sDescription, int nQuantity, int nThreshold, double dPrice )
	{
		super( sProductId, sName, sDescription, nQuantity, nThreshold );
		this.dPrice = dPrice;
	}
	
	//getters
	public double getdPrice()
	{
		return dPrice;
	}

	//setters
	public void setdPrice(double dPrice)
	{
		this.dPrice = dPrice;
	}
	
}
