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
	public Branch getBranch( int id )
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM branch WHERE branch_id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		rs.first();
		
		model.Branch b = new Branch(Integer.toString(rs.getInt("branch_id")), 
							   rs.getString("name"), 
							   rs.getDouble("pettycash"));
		return b;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getAllEmployees()
	{
		try
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
									  rs.getDouble("hoursRendered"),
									  rs.getString("type"));
			employees.add(e);
		}
		return employees.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getAllProducts()
	{
		try
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
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getAllConsumableProducts()
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE price IS null");
		ResultSet rs = ps.executeQuery();
		ArrayList<model.Product> products = new ArrayList<>(0);
		
		while( rs.next() )
		{
			model.Product p = new Consumable(Integer.toString(rs.getInt("product_id")), 
									rs.getString("name"), 
									rs.getString("description"), 
									rs.getInt("quantity"),
									rs.getString("measurement"));
			products.add(p);
		}
		return products.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getAllOverTheCounterProducts()
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE price IS NOT null");
		ResultSet rs = ps.executeQuery();
		ArrayList<model.Product> products = new ArrayList<>(0);
		
		while( rs.next() )
		{
			model.Product p = new OverTheCounter(Integer.toString(rs.getInt("product_id")), 
									rs.getString("name"), 
									rs.getString("description"), 
									rs.getInt("quantity"),
									rs.getDouble("price"));
			products.add(p);
		}
		return products.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getAllServices()
	{
		try
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM service");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Service> services = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Service p = new Service(Integer.toString(rs.getInt("service_id")), 
										rs.getString("name"), 
										rs.getString("description"), 
										rs.getInt("price"));
				services.add(p);
			}
			return services.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public Iterator getAllTransactions()
	{
		ArrayList<Transaction> transactions;
		
		return transactions.iterator();
	}
	*/
	
	//INSERT STATEMENTS
	public int addClient( Client c )
	{
		try
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
				genId = generatedKeys.getInt("client_id");
				return genId;
			}
			else 
				throw new SQLException("Creating user failed, no ID obtained.");
		}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return -999;
	}
	
	public void addTransaction(Transaction t)
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("INSERT INTO transaction(client_id, productlist_id, servicelist_id, feedback) "
										    + "VALUES (?, ?, ?, ?)");

				ps.setInt(1, Integer.parseInt(t.getClient().getsClientId()));
				ps.setInt(2, addProductList(t.getProducts()));
				ps.setInt(3, addServiceList(t.getServices()));
				ps.setString(4, t.getsFeedback());
				ps.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private int addProductList(ArrayList<ProductLineItem> products)
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("INSERT INTO productlist(productlineitem_id) "
				  + "VALUES (?)",Statement.RETURN_GENERATED_KEYS);
		
		ArrayList<Integer> lineItemIDs;
		lineItemIDs = addProductLineItem(products);
		int genID = 1000;
		
		ps.setInt(1, lineItemIDs.get(0));
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if(generatedKeys.next())
		{
		genID = generatedKeys.getInt("productlist_id");
		}
		
		if(lineItemIDs.size() > 1)
		{
			ps = con.prepareStatement("INSERT INTO productlist(productlist_id, productlineitem_id) "
					  + "VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
			for(int i = 1; i < lineItemIDs.size(); i++)
			{
			ps.setInt(1, genID);
			ps.setInt(2, lineItemIDs.get(i));
			ps.executeUpdate();
			}
		}
		
		return genID;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return -999;
	}
	
	private ArrayList<Integer> addProductLineItem(ArrayList<ProductLineItem> products)
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("INSERT INTO product line item(product_id, quantity) "
				  + "VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
		
		for(int i = 0; i < products.size(); i ++)
		{
			ps.setInt(1, Integer.parseInt(products.get(i).getProduct().getsProductId()));
			ps.setInt(2, products.get(i).getnQuantity());
			ps.executeUpdate();
		}
		ResultSet generatedKeys = ps.getGeneratedKeys();
		ArrayList<Integer> lineItemIDs = new ArrayList<Integer>();
		
		while(generatedKeys.next())
		{
			lineItemIDs.add(generatedKeys.getInt("productlineitem_id"));
		}
		
		return lineItemIDs;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private int addServiceList(ArrayList<ServiceLineItem> services)
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("INSERT INTO servicelist(servicelineitem_id) "
				  + "VALUES (?)",Statement.RETURN_GENERATED_KEYS);
		
		ArrayList<Integer> lineItemIDs;
		lineItemIDs = addServiceLineItem(services);
		int genID = 1000;
		
		ps.setInt(1, lineItemIDs.get(0));
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if(generatedKeys.next())
		{
		genID = generatedKeys.getInt("servicelist_id");
		}
		
		if(lineItemIDs.size() > 1)
		{
			ps = con.prepareStatement("INSERT INTO servicelist(servicelist_id, servicelineitem_id) "
					  + "VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
			for(int i = 1; i < lineItemIDs.size(); i++)
			{
			ps.setInt(1, genID);
			ps.setInt(2, lineItemIDs.get(i));
			ps.executeUpdate();
			}
		}
		
		return genID;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return -999;
	}
	
	private ArrayList<Integer> addServiceLineItem(ArrayList<ServiceLineItem> services)
	{
		try
		{
		PreparedStatement ps = con.prepareStatement("INSERT INTO service line item(service_id, quantity, employee_id1, employee_id2) "
				  + "VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
		
		for(int i = 0; i < services.size(); i ++)
		{
			ps.setInt(1, Integer.parseInt(services.get(i).getService().getsServiceId()));
			ps.setInt(2, services.get(i).getnQuantity());
			ps.setInt(3, Integer.parseInt(services.get(i).getEmployee(0).getsEmployeeId()));
			if(services.get(i).getEmployee(1) != null)
			{
				ps.setInt(4, Integer.parseInt(services.get(i).getEmployee(1).getsEmployeeId()));
			}
			ps.executeUpdate();
		}
		ResultSet generatedKeys = ps.getGeneratedKeys();
		ArrayList<Integer> lineItemIDs = new ArrayList<Integer>();
		
		while(generatedKeys.next())
		{
			lineItemIDs.add(generatedKeys.getInt("productlineitem_id"));
		}
		
		return lineItemIDs;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}