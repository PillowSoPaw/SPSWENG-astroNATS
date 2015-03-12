package model;

public class Branch 
{
	
	private String sBranchId;
	private String sName;
	private double dPettyCash;
	
	public Branch(String sBranchId, String sName, double dPettyCash)
	{
		this.sBranchId = sBranchId;
		this.sName = sName;
		this.dPettyCash = dPettyCash;
	}

	//getters
	public String getsBranchId()
	{
		return sBranchId;
	}

	public String getsName()
	{
		return sName;
	}

	public double getdPettyCash()
	{
		return dPettyCash;
	}

	//setters
	public void setsBranchId(String sBranchId)
	{
		this.sBranchId = sBranchId;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public void setdPettyCash(double dPettyCash)
	{
		this.dPettyCash = dPettyCash;
	}
	
}
