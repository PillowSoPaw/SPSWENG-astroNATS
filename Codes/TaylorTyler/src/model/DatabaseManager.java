package model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class DatabaseManager
{
	private static DatabaseManager db = new DatabaseManager();
	private DatabaseConnector dbConn;
	private Connection con;
	
	private DatabaseManager()
	{
		dbConn = DatabaseConnector.getInstance();
		con = dbConn.getConnection();
	}
	
	public static DatabaseManager getInstance()
	{
		return db;
	}
	
	//SELECT STATEMENTS
	private Branch getBranch( int id ) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM branch WHERE branch_id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		rs.first();
		
		model.Branch b = new Branch(Integer.toString(rs.getInt("branch_id")), 
							   rs.getString("name"), 
							   rs.getDouble("pettycash"));
		return b;
	}
	
	private Iterator getAllEmployees() throws SQLException
	{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM employee");
		ResultSet rs = ps.executeQuery();
		ArrayList<model.Employee> employees = new ArrayList<>(0);
		
		while( rs.next() )
		{
			model.Employee e = new Employee(Integer.toString(rs.getInt("employee_id")), 
									  getBranch(rs.getInt("branch_id")), 
									  rs.getString("name"), 
									  rs.getDate("dateStartedWorking"), 
									  rs.getTime("hoursRendered"));
			employees.add(e);
		}
		return employees.iterator();
	}
	
	private Iterator getAllPoducts() throws SQLException
	{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM product");
		ResultSet rs = ps.executeQuery();
		ArrayList<model.Product> products = new ArrayList<>(0);
		
		while( rs.next() )
		{
			model.Product p = new Product(Integer.toString(rs.getInt("product_id")), 
									rs.getString("name"), 
									rs.getString("description"), 
									rs.getInt("quantity"));
			products.add(p);
		}
		return products.iterator();
	}
	
	//INSERT STATEMENTS
	private int addClient( Client c ) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement("INSERT INTO client(name, address, contactNumber, picture, dateJoined, dateLastVisited) "
										  + "VALUES (?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getsName());
		ps.setString(2, c.getsAddress());
		ps.setString(3, c.getsContactNumber());
		ps.setString(4, c.getsPictureDirectory());
		ps.setDate(5, c.getDateJoined());
		ps.setDate(6, c.getDateLastVisited());
		
		int affectedRows = ps.executeUpdate();
		int genId;
		
		if (affectedRows == 0) 
			throw new SQLException("Creating user failed, no rows affected.");

		try (ResultSet generatedKeys = ps.getGeneratedKeys()) 
		{
			if (generatedKeys.next()) 
			{
				genId = generatedKeys.getInt(1);
				return genId;
			}
			else 
				throw new SQLException("Creating user failed, no ID obtained.");
		}
	}
	
	private void addTransaction(Transaction t) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement("INSERT INTO transaction(client_id, employee_id, receipt_id, servicelineitem_id, productlineitem_id, feedback) "
										    + "VALUES (?, ?, ?, ?, ?, ?)");
		
		for(int i = 0; i < t.getProducts().size(); i++)
		{
			for(int j = 0; j < t.getServices().size();j++)
			{
				ps.setInt(1, Integer.parseInt(t.getClient().getsClientId()));
				ps.setInt(2, Integer.parseInt(t.getEmployee().getsEmployeeId()));
				ps.setInt(3, Integer.parseInt(t.getReceipt().getsReceiptId()));
				ps.setInt(4, Integer.parseInt(t.getProducts().get(i).getsProductLineItemId()));
				ps.setInt(4, Integer.parseInt(t.getServices().get(j).getsServiceLineItemId()));
				ps.setString(5, t.getsFeedback());
			ps.executeUpdate();
			}
		}
	}
}
