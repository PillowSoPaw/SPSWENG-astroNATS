package model;

import java.sql.Date;
import java.util.ArrayList;
public class Receipt
{

	private String sReceiptId;
	private Client client;
	private Date dateOfReceipt;
	private String sModeOfPayment;
	private ArrayList<Transaction> transactions;
	private double dTotalBill;
	
	public Receipt( String sReceiptId, Client client, Date dateOfReceipt, String sModeOfPayment, double dTotalBill )
	{
		this.sReceiptId = sReceiptId;
		this.client = client;
		this.dateOfReceipt = dateOfReceipt;
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

	public Date getDateOfReceipt()
	{
		return dateOfReceipt;
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

	public void setDateOfReceipt(Date dateOfReceipt)
	{
		this.dateOfReceipt = dateOfReceipt;
	}

	public void setsModeOfPayment(String sModeOfPayment)
	{
		this.sModeOfPayment = sModeOfPayment;
	}

	public void setdTotalBill(double dTotalBill)
	{
		this.dTotalBill = dTotalBill;
	}
	
	//Add transaction
	public void addTransaction(Transaction t)
	{
		this.transactions.add(t);
	}
	
}
