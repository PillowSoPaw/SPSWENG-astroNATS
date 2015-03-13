package model;

public class Consumable extends Product
{
	private String sMeasurement;
	
	public Consumable( String sProductId, String sName, String sDescription, int nQuantity, String sMeasurement )
	{
		super( sProductId, sName, sDescription, nQuantity );
		this.sMeasurement = sMeasurement;
	}
	
	//GETTERS
	public String getsMeasurement()
	{
		return sMeasurement;
	}
	
	//SETTERS
	public void setsMeasurement( String sMeasurement )
	{
		this.sMeasurement = sMeasurement;
	}
}
