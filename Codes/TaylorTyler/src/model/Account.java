package model;

public class Account 
{

	private String sAccountId;
	private Employee employee;
	private String sUsername;
	private String sPassword;
	private String sType;
	
	public Account (String sAccountId, Employee employee, String sUsername, String sPassword, String sType)
	{
		this.sAccountId = sAccountId;
		this.employee = employee;
		this.sUsername = sUsername;
		this.sPassword = sPassword;
	}

	//getters
	public String getsAccountId()
	{
		return sAccountId;
	}

	public Employee getEmployee()
	{
		return employee;
	}

	public String getsUsername()
	{
		return sUsername;
	}

	public String getsPassword()
	{
		return sPassword;
	}
	
	public String getsType()
	{
		return this.sType;
	}
	
	//setters
	public void setsAccountId(String sAccountId)
	{
		this.sAccountId = sAccountId;
	}

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public void setsUsername(String sUsername)
	{
		this.sUsername = sUsername;
	}

	public void setsPassword(String sPassword)
	{
		this.sPassword = sPassword;
	}
	public void setsType(String sType)
	{
		this.sType = sType;
	}
	
}
