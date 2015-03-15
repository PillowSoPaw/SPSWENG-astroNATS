package model;
import java.sql.*;

public class DatabaseConnector
{
	private Connection	connection;
	private static DatabaseConnector dbConn;

	private DatabaseConnector()
	{
		String dbname = "taylortyler";
		String connurl = "jdbc:mysql://localhost:3306/" + dbname;
		String username = "root";
		String password = "I@mtheEXECUTOR";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connurl, username, password);
			System.out.println("It works!");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Exception: " + e.getMessage());
		} 
//		finally
//		{
//			try
//			{
//				if (con != null)
//				{
//					//con.close();
//					System.out.println("Connection Closed");
//				}
//			} 
//			catch (SQLException e)
//			{
//			}
//		}
	}

	public Connection getConnection()
	{
		return connection;
	}
	
	public static DatabaseConnector getInstance()
	{
		if(dbConn == null)
		{
			dbConn = new DatabaseConnector();
		}
		return dbConn;
	}
	
	public static void main(String[] args)
	{
		DatabaseConnector connection = new DatabaseConnector();
	}
}
