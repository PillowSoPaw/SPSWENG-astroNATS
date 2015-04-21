package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Client;
import model.DatabaseManager;
import view.EditClientDetailsGUI;

public class EditClientDetailsController
{
	private static DatabaseManager	DBManager;
	private EditClientDetailsGUI editClientDetailsGUI;
	private AddClientController addClientController = new AddClientController();
	
	public EditClientDetailsController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( EditClientDetailsGUI editClientDetailsGUI )
	{
		this.editClientDetailsGUI = editClientDetailsGUI;
	}	

	public void editClient(String sClientId, String sFname, String sMname, String sLname, String sAddress, String sContactNumber, String sEmail, String sBirthday, String sGender) throws ParseException 
	{
		// TODO Auto-generated method stub
		//public Client(String sName, String sAddress, String sContactNumber, String sEmail, Date dateJoined, Date dateLastVisited, Date birthday )

		//Date dateJoined = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sDateJoined);
		//Date dateLastVisited = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sDateLastVisited);
		//Date birthday = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sBirthday);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDateJoined = sdf.parse("0000-00-00"); 
		java.util.Date uDateLastVisited = sdf.parse("0000-00-00"); 
		java.util.Date uBirthday = sdf.parse(sBirthday);
		Date dateJoined = new java.sql.Date(uDateJoined.getTime());
		Date dateLastVisited = new java.sql.Date(uDateLastVisited.getTime());
		Date birthday = new java.sql.Date(uBirthday.getTime());
		Client c = new Client(sClientId, sFname, sMname, sLname, sAddress, sContactNumber ,sEmail, dateJoined, dateLastVisited, birthday, sGender);
		try
		{
			DBManager.updateClient(c);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
