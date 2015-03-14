package model;
import java.sql.*;

public class DatabaseConnector
{
	private Connection	con;
	private static DatabaseConnector dbConn;

	private DatabaseConnector()
	{
		String dbname = "mydb";
		String connurl = "jdbc:mysql://localhost:3306/" + dbname;
		String username = "root";
		String password = "I@mtheEXECUTOR";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(connurl, username, password);
			System.out.println("It fucking works!");
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
		return con;
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
		DatabaseConnector con = new DatabaseConnector();
	}
}
