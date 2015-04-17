package model;

import java.sql.Date;

public class Client
{
	
	private String sClientId;
	private String sName;
	private String sAddress;
	private String sContactNumber;
	private String sEmail;
	private Date dateJoined;
	private Date dateLastVisited;
	private Date birthday;
	
	public Client( String sClientId, String sName, String sAddress, String sContactNumber, String sEmail, Date dateJoined, Date dateLastVisited , Date birthday)
	{
		this.sClientId = sClientId;
		this.sName = sName;
		this.sAddress = sAddress;
		this.sContactNumber = sContactNumber;
		this.sEmail = sEmail;
		this.dateJoined = dateJoined;
		this.dateLastVisited = dateLastVisited;
		this.birthday = birthday;
	}
	public Client(String sName, String sAddress, String sContactNumber, String sEmail, Date dateJoined, Date dateLastVisited, Date birthday )
	{
		this.sName = sName;
		this.sAddress = sAddress;
		this.sContactNumber = sContactNumber;
		this.sEmail = sEmail;
		this.dateJoined = dateJoined;
		this.dateLastVisited = dateLastVisited;
		this.birthday = birthday;
	}
	
	//getters
	public String getsClientId()
	{
		return sClientId;
	}

	public String getsName()
	{
		return sName;
	}

	public String getsAddress()
	{
		return sAddress;
	}

	public String getsContactNumber()
	{
		return sContactNumber;
	}

	public String getsEmail()
	{
		return sEmail;
	}

	public Date getDateJoined()
	{
		return dateJoined;
	}

	public Date getDateLastVisited()
	{
		return dateLastVisited;
	}
	
	public Date getBirthday() 
	{
		return birthday;
	}

	//setters
	public void setsClientId(String sClientId)
	{
		this.sClientId = sClientId;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public void setsAddress(String sAddress)
	{
		this.sAddress = sAddress;
	}

	public void setsContactNumber(String sContactNumber)
	{
		this.sContactNumber = sContactNumber;
	}

	public void setsEmail(String sEmail)
	{
		this.sEmail = sEmail;
	}

	public void setDateJoined(Date dateJoined)
	{
		this.dateJoined = dateJoined;
	}

	public void setDateLastVisited(Date dateLastVisited)
	{
		this.dateLastVisited = dateLastVisited;
	}
	
	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}
	
}
