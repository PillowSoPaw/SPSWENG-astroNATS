package model;

import java.sql.Date;
import java.sql.Time;

public class TimeLog 
{

	private String sTimelogId;
	private Date date;
	private Time timein;
	private Time timeout;
	
	public TimeLog(String sTimelogId, Date date, Time timein, Time timeout )
	{
		this.sTimelogId = sTimelogId;
		this.date = date;
		this.timein = timein;
		this.timeout = timeout;
	}

	//getters
	public String getsTimelogId()
	{
		return sTimelogId;
	}

	public Date getDate()
	{
		return date;
	}

	public Time getTimein()
	{
		return timein;
	}

	public Time getTimeout()
	{
		return timeout;
	}

	//setters
	public void setsTimelogId(String sTimelogId)
	{
		this.sTimelogId = sTimelogId;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void setTimein(Time timein)
	{
		this.timein = timein;
	}

	public void setTimeout(Time timeout)
	{
		this.timeout = timeout;
	}
	
}
