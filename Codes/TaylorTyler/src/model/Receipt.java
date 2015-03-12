package model;

import java.sql.Date;

public class Receipt
{

	private String sReceiptId;
	private Client client;
	private Date date;
	private String sModeOfPayment;
	private double dTotalBill;
	
	public Receipt( String sReceiptId, Client client, Date date, String sModeOfPayment, double dTotalBill )
	{
		this.sReceiptId = sReceiptId;
		this.client = client;
		this.date = date;
		this.sModeOfPayment = sModeOfPayment;
		this.dTotalBill = dTotalBill;
	}

	//getters
	public String getsReceiptId()
	{
		return sReceiptId;
	}

	public Client getClient()
	{
		return client;
	}

	public Date getDate()
	{
		return date;
	}

	public String getsModeOfPayment()
	{
		return sModeOfPayment;
	}

	public double getdTotalBill()
	{
		return dTotalBill;
	}

	//setters
	public void setsReceiptId(String sReceiptId)
	{
		this.sReceiptId = sReceiptId;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void setsModeOfPayment(String sModeOfPayment)
	{
		this.sModeOfPayment = sModeOfPayment;
	}

	public void setdTotalBill(double dTotalBill)
	{
		this.dTotalBill = dTotalBill;
	}
	
}
