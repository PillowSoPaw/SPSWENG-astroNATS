package model;

import java.sql.Date;
import java.sql.Time;

public class TimeLog 
{

	private String sTimelogId;
	private Employee employee;
	private Date dateOfTimelog;
	private Time timein;
	private Time timeout;
	
	public TimeLog(String sTimelogId, Employee employee, Date dateOfTimelog, Time timein, Time timeout )
	{
		this.sTimelogId = sTimelogId;
		this.employee = employee;
		this.dateOfTimelog = dateOfTimelog;
		this.timein = timein;
		this.timeout = timeout;
	}

	//getters
	public String getsTimelogId()
	{
		return sTimelogId;
	}

	public Date getDateOfTimelog()
	{
		return dateOfTimelog;
	}

	public Time getTimein()
	{
		return timein;
	}

	public Time getTimeout()
	{
		return timeout;
	}
	
	public Employee getEmployee()
	{
		return this.employee;
	}
	//setters
	public void setsTimelogId(String sTimelogId)
	{
		this.sTimelogId = sTimelogId;
	}

	public void setDateOfTimelog(Date dateOfTimelog)
	{
		this.dateOfTimelog = dateOfTimelog;
	}

	public void setTimein(Time timein)
	{
		this.timein = timein;
	}

	public void setTimeout(Time timeout)
	{
		this.timeout = timeout;
	}
	
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	
}
