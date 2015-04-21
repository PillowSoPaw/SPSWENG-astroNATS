package controller;

import java.util.Iterator;

import view.LogInGUI;
import model.DatabaseManager;

public class LogInController 
{	
	private static DatabaseManager DBManager;
	private LogInGUI LogInGUI;
	
	public LogInController()
	{
		DBManager = DBManager.getInstance();
	}
	
	public void setView( LogInGUI LogInGUI )
	{
		this.LogInGUI = LogInGUI;
	}
	
	public boolean checkLoginDetails( String username, String password )
	{	
		
		Iterator<Object[]> oList = DBManager.getAllAccounts();
		Object[] o = new Object[2];
		//boolean boolFlag = false;
		
		while(oList.hasNext())
		{
			o = oList.next();
			String uname = new String(o[0].toString());
			String pw    = new String(o[1].toString());
			if(username.equals(uname) && password.equals(pw))
			{	
				
				return true;
				//return boolFlag;
			}
			
		}
	
		return false;
	}

}
