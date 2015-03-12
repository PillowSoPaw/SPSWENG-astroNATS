package model;

public class ProductLineItem
{
	
	private String sProductLineItemId;
	private Product product;
	private int nQuantity;
	
	public ProductLineItem( String sProductLineItemId, Product product, int nQuantity )
	{
		this.sProductLineItemId = sProductLineItemId;
		this.product = product;
		this.nQuantity = nQuantity;
	}

	//getters
	public String getsProductLineItemId()
	{
		return sProductLineItemId;
	}

	public Product getProduct()
	{
		return product;
	}

	public int getnQuantity()
	{
		return nQuantity;
	}
	
	//setters
	public void setsProductLineItemId(String sProductLineItemId)
	{
		this.sProductLineItemId = sProductLineItemId;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}
	
	public void setnQuantity( int nQuantity )
	{
		this.nQuantity = nQuantity;
	}
	
}
