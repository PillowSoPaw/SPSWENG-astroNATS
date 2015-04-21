package model;

import java.sql.Date;

public class Client
{
	
	private String sClientId;
	private String sName;
	private String sFname; //first name
	private String sMname; //middle name
	private String sLname; //last name
	private String sAddress;
	private String sContactNumber;
	private String sEmail;
	private Date dateJoined;
	private Date dateLastVisited;
	private Date birthday;
	private String sGender;
	public Client( String sClientId, String sFname,String sMname,String sLname, String sAddress, String sContactNumber, String sEmail, Date dateJoined, Date dateLastVisited , Date birthday, String sGender)
	{
		this.sClientId = sClientId;
		this.sFname = sFname;
		this.sMname = sMname;
		this.sLname = sLname;
		String sMiddleName = sMname;
		if(!sMname.equals(""))
			sMiddleName = sMiddleName + " ";
		this.sName = sFname +" "+ sMiddleName + sLname;
		this.sAddress = sAddress;
		this.sContactNumber = sContactNumber;
		this.sEmail = sEmail;
		this.dateJoined = dateJoined;
		this.dateLastVisited = dateLastVisited;
		this.birthday = birthday;
		this.sGender = sGender;
	}
	public Client(String sFname,String sMname,String sLname, String sAddress, String sContactNumber, String sEmail, Date dateJoined, Date dateLastVisited, Date birthday, String sGender )
	{
		this.sFname = sFname;
		this.sMname = sMname;
		this.sLname = sLname;
		String sMiddleName = sMname;
		if(!sMname.equals(""))
			sMiddleName = sMiddleName + " ";
		this.sName = sFname +" "+ sMiddleName + sLname;
		this.sAddress = sAddress;
		this.sContactNumber = sContactNumber;
		this.sEmail = sEmail;
		this.dateJoined = dateJoined;
		this.dateLastVisited = dateLastVisited;
		this.birthday = birthday;
		this.sGender = sGender;
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
	
	public String getsGender() 
	{
		return sGender;
	}
	
	public String getsFname() {
		return sFname;
	}
	
	public String getsMname() 
	{
		return sMname;
	}
	
	public String getsLname() 
	{
		return sLname;
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

	public void setsGender(String sGender) 
	{
		this.sGender = sGender;
	}

	public void setsFname(String sFname) 
	{
		this.sFname = sFname;
	}

	public void setsMname(String sMname) 
	{
		this.sMname = sMname;
	}

	public void setsLname(String sLname)
	{
		this.sLname = sLname;
	}
	
}
