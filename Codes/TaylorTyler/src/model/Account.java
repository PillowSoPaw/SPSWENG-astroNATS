package model;

public class Account 
{

	private String sAccountId;
	private Employee employee;
	private String sUsername;
	private String sPassword;
	
	public Account (String sAccountId, Employee employee, String sUsername, String sPassword)
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
	
}
