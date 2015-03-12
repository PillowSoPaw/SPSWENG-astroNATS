package model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Worker extends Employee
{
	
	private String sType;
	
	public Worker(String sType, String sEmployeeId, Branch branch, String sName, Date dateStartedWorking, Time hoursRendered )
	{
		super(sEmployeeId, branch, sName, dateStartedWorking, hoursRendered);
		this.sType = sType;
	}
	
	//getters
	public String getsType() 
	{
		return sType;
	}
	
	//setters
	public void setsType(String sType)
	{
		this.sType = sType;
	}
	
	
}
