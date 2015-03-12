package model;

public class ProductLineItem
{
	
	private String sProductLineItemId;
	private Product product;
	
	public ProductLineItem( String sProductLineItemId, Product product )
	{
		this.sProductLineItemId = sProductLineItemId;
		this.product = product;
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

	//setters
	public void setsProductLineItemId(String sProductLineItemId)
	{
		this.sProductLineItemId = sProductLineItemId;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}
	
}
