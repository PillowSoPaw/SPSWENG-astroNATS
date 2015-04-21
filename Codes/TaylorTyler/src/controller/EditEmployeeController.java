package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Client;
import model.DatabaseManager;
import model.Employee;
import view.EditClientDetailsGUI;
import view.EditEmployeeGUI;

public class EditEmployeeController
{
	private static DatabaseManager	DBManager;
	private EditEmployeeGUI editEmployeeGUI;
	
	public EditEmployeeController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( EditEmployeeGUI editEmployeeGUI )
	{
		this.editEmployeeGUI = editEmployeeGUI;
	}	

	public void editEmployee(String sClientId, String sName, String sType) throws ParseException 
	{
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDefault = sdf.parse("0000-00-00"); 
		Date dateDefault = new java.sql.Date(uDefault.getTime());
		Employee e = new Employee(sClientId, sName, dateDefault, sType);
		try
		{
			DBManager.updateEmployee(e);
		} catch (Exception x)
		{
			x.printStackTrace();
		}
	}

}
