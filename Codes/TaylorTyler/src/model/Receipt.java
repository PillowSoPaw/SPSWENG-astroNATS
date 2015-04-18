package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Receipt
{

	private String sReceiptId;
	private Client client;
	private Date dateOfReceipt;
        private Time timeOfReceipt;
	private String sModeOfPayment;
	private ArrayList<Transaction> transactions;
	private double dTotalBill;
        
	public Receipt( String sReceiptId, Client client, String sModeOfPayment, double dTotalBill )
	{    
		this.sReceiptId = sReceiptId;
		this.client = client;
                this.dateOfReceipt = Date.valueOf(LocalDate.now());
                this.timeOfReceipt = Time.valueOf(LocalTime.now());
		this.sModeOfPayment = sModeOfPayment;
		this.dTotalBill = dTotalBill;
                this.transactions = new ArrayList<>();
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
        
        public Time getTimeOfReceipt()
        {
                return timeOfReceipt;
        }
        
	public String getsModeOfPayment()
	{
		return sModeOfPayment;
	}

	public double getdTotalBill()
	{
		return dTotalBill;
	}
        
        public ArrayList<Transaction> getTransactions()
        {
            return this.transactions;
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

        public void setTimeOfReceipt(Time timeOfReceipt)
        {
                this.timeOfReceipt = timeOfReceipt;
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
        
        public void addTransactions(ArrayList<Transaction> tList)
        {
                this.transactions.addAll(tList);
        }
	
}
