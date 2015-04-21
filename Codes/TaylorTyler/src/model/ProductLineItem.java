package model;

public class ProductLineItem
{
	
	private String sProductLineItemId;
	private Product product;
	private int nQuantity;
	private Employee employee;
	
	public ProductLineItem( String sProductLineItemId, Product product, int nQuantity, Employee employee )
	{
		this.sProductLineItemId = sProductLineItemId;
		this.product = product;
		this.nQuantity = nQuantity;
		this.employee = employee;
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
	
	public Employee getEmployee()
	{
		return employee;
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
	
	public void setEmployeeID( Employee employee )
	{
		this.employee = employee;
	}
}
