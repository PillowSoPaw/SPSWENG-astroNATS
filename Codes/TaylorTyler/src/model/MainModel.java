package model;

import java.sql.SQLException;

public class MainModel
{
	private DatabaseManager database;
	
	public MainModel()
	{
		database = DatabaseManager.getInstance();
	}
}
