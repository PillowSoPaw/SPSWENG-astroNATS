package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Client;
import model.DatabaseManager;
import view.AddClientGUI;
import view.ViewClientsGUI;

public class AddClientController 
{
	private static DatabaseManager	DBManager;
	private AddClientGUI addClientGUI;
	
	public AddClientController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( AddClientGUI addClientGUI )
	{
		this.addClientGUI = addClientGUI;
	}	

	public void addClient(String sName, String sAddress, String sContactNumber, String sEmail, String sDateJoined, String sDateLastVisited, String sBirthday, String sGender) throws ParseException 
	{
		// TODO Auto-generated method stub
		//public Client(String sName, String sAddress, String sContactNumber, String sEmail, Date dateJoined, Date dateLastVisited, Date birthday )

		//Date dateJoined = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sDateJoined);
		//Date dateLastVisited = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sDateLastVisited);
		//Date birthday = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sBirthday);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDateJoined = sdf.parse(sDateJoined);
		java.util.Date uDateLastVisited = sdf.parse(sDateLastVisited);
		java.util.Date uBirthday = sdf.parse(sBirthday);
		Date dateJoined = new java.sql.Date(uDateJoined.getTime());
		Date dateLastVisited = new java.sql.Date(uDateLastVisited.getTime());
		Date birthday = new java.sql.Date(uBirthday.getTime());
		Client c = new Client(sName, sAddress, sContactNumber ,sEmail, dateJoined, dateLastVisited, birthday, sGender);
		try
		{
			DBManager.addClient(c);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
