package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Employee 
{

	private String sEmployeeId;
	private String sName;
	private Date dateStartedWorking;
	private String sType;
	
	public Employee(String sEmployeeId, String sName, Date dateStartedWorking, String sType )
	{
		this.sEmployeeId = sEmployeeId;
		this.sName = sName;
		this.dateStartedWorking = dateStartedWorking;
		this.sType = sType;
	}
	
	//getters
	public String getsEmployeeId()
	{
		return sEmployeeId;
	}

	public String getsName()
	{
		return sName;
	}

	public Date getDateStartedWorking()
	{
		return dateStartedWorking;
	}

	public String getsType()
	{
		return sType;
	}

//	public String getsFname()
//	{
//		return sFname;
//	}
//
//	public String getsMname() 
//	{
//		return sMname;
//	}
//	
//	public String getsLname() 
//	{
//		return sLname;
//	}
	//setters
	public void setsEmployeeId(String sEmployeeId)
	{
		this.sEmployeeId = sEmployeeId;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public void setDateStartedWorking(Date dateStartedWorking)
	{
		this.dateStartedWorking = dateStartedWorking;
	}

	public void setsType( String sType )
	{
		this.sType = sType;
	}

//	public void setsFname(String sFname)
//	{
//		this.sFname = sFname;
//	}
//
//	public void setsMname(String sMname) 
//	{
//		this.sMname = sMname;
//	}
//	
//	public void setsLname(String sLname) 
//	{
//		this.sLname = sLname;
//	}


}
