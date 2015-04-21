package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import view.AddEmployeeGUI;
import model.Employee;
import model.DatabaseManager;


public class AddEmployeeController 
{
	private static DatabaseManager	DBManager;
	private AddEmployeeGUI addEmployeeGUI;
	
	public AddEmployeeController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( AddEmployeeGUI addEmployeeGUI )
	{
		this.addEmployeeGUI = addEmployeeGUI;
	}	
	
	//Employee(String sName, Date dateStartedWorking, String sType )
	public void addEmployee(String sName, String sDateStartedWorking, String sType) throws ParseException 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDateStartedWorking = sdf.parse(sDateStartedWorking);
		Date DateStartedWorking = new java.sql.Date(uDateStartedWorking.getTime());
		//Employee(String sName, Date dateStartedWorking, String sType )
		Employee e = new Employee(sName, DateStartedWorking, sType);
		try
		{
			DBManager.addEmployee(e);
		} catch (Exception x)
		{
			x.printStackTrace();
		}
	}

}
