package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Employee 
{

	private String sEmployeeId;
	private ArrayList<TimeLog> timelog;
	private Branch branch;
	private String sName;
	private String sFname;
	private String sMname;
	private String sLname;
	private Date dateStartedWorking;
	private double dHoursRendered;
	private String sType;
	
	public Employee(String sEmployeeId, Branch branch, String sFname, String sMname, String sLname, Date dateStartedWorking, double dHoursRendered, String sType )
	{
		this.sEmployeeId = sEmployeeId;
		this.timelog = new ArrayList<TimeLog>(0);
		this.branch = branch;
		this.setsFname(sFname);
		this.setsMname(sMname);
		this.setsLname(sLname);
		String sMiddleName = sMname;
		if(!sMname.equals(""))
			sMiddleName = sMiddleName + " ";
		this.sName = sFname +" "+ sMiddleName + sLname;
		this.dateStartedWorking = dateStartedWorking;
		this.dHoursRendered = dHoursRendered;
		this.sType = sType;
	}
	
	//getters
	public String getsEmployeeId()
	{
		return sEmployeeId;
	}

	public ArrayList<TimeLog> getTimelog()
	{
		return timelog;
	}

	public Branch getBranch()
	{
		return branch;
	}

	public String getsName()
	{
		return sName;
	}

	public Date getDateStartedWorking()
	{
		return dateStartedWorking;
	}

	public double getdHoursRendered()
	{
		return dHoursRendered;
	}
	
	public String getsType()
	{
		return sType;
	}

	public String getsFname()
	{
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
	public void setsEmployeeId(String sEmployeeId)
	{
		this.sEmployeeId = sEmployeeId;
	}

	public void setTimelog(ArrayList<TimeLog> timelog)
	{
		this.timelog = timelog;
	}

	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public void setDateStartedWorking(Date dateStartedWorking)
	{
		this.dateStartedWorking = dateStartedWorking;
	}

	public void setdHoursRendered(double hoursRendered)
	{
		this.dHoursRendered = hoursRendered;
	}

	public void setsType( String sType )
	{
		this.sType = sType;
	}
	
	//add timelogs
	public void addTimeLog( TimeLog timelog )
	{
		this.timelog.add(timelog);
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
