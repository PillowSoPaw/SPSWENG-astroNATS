package model;

import java.sql.Date;

public class Client
{
	
	private String sClientId;
	private String sName;
	private String sAddress;
	private String sContactNumber;
	private String sPictureDirectory;
	private Date dateJoined;
	private Date dateLastVisited;
	
	public Client( String sClientId, String sName, String sAddress, String sContactNumber, String sPictureDirectory, Date dateJoined, Date dateLastVisited )
	{
		this.sClientId = sClientId;
		this.sName = sName;
		this.sAddress = sAddress;
		this.sContactNumber = sContactNumber;
		this.sPictureDirectory = sPictureDirectory;
		this.dateJoined = dateJoined;
		this.dateLastVisited = dateLastVisited;
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

	public String getsPictureDirectory()
	{
		return sPictureDirectory;
	}

	public Date getDateJoined()
	{
		return dateJoined;
	}

	public Date getDateLastVisited()
	{
		return dateLastVisited;
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

	public void setsPictureDirectory(String sPictureDirectory)
	{
		this.sPictureDirectory = sPictureDirectory;
	}

	public void setDateJoined(Date dateJoined)
	{
		this.dateJoined = dateJoined;
	}

	public void setDateLastVisited(Date dateLastVisited)
	{
		this.dateLastVisited = dateLastVisited;
	}
	
}
