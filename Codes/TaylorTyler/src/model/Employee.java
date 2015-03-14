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
	private Date dateStartedWorking;
	private Time hoursRendered;
	private String sType;
	
	public Employee(String sEmployeeId, Branch branch, String sName, Date dateStartedWorking, Time hoursRendered, String sType )
	{
		this.sEmployeeId = sEmployeeId;
		this.timelog = new ArrayList<TimeLog>(0);
		this.branch = branch;
		this.sName = sName;
		this.dateStartedWorking = dateStartedWorking;
		this.hoursRendered = hoursRendered;
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

	public Time getHoursRendered()
	{
		return hoursRendered;
	}
	
	public String getsType()
	{
		return sType;
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

	public void setHoursRendered(Time hoursRendered)
	{
		this.hoursRendered = hoursRendered;
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
}
