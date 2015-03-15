package model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.swing.JOptionPane;

public class DatabaseManager
{
	private static DatabaseManager db = new DatabaseManager();
	private DatabaseConnector dbConn;
	private Connection connection;
	
	private DatabaseManager()
	{
		dbConn = DatabaseConnector.getInstance();
		connection = dbConn.getConnection();
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM branch WHERE branch_id = ?");
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
	
	public Client getClient( String name )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			rs.first();
			
			model.Client c = new Client(Integer.toString(rs.getInt("client_id")),
								   rs.getString("name"), 
								   rs.getString("address"), 
								   rs.getString("contactNumber"), 
								   rs.getString("picture"), 
								   rs.getDate("dateJoined"), 
								   rs.getDate("dateLastVisited"));
			return c;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Service getService( String name )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM service WHERE name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			rs.first();
			
			model.Service s = new Service(Integer.toString(rs.getInt("service_id")), 
									rs.getString("name"), 
									rs.getString("description"), 
									rs.getDouble("price"));
			return s;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Product getProduct( String name )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			rs.first();
			
			model.Product p = new Product(Integer.toString(rs.getInt("product_id")), 
									rs.getString("name"), 
									rs.getString("description"), 
									rs.getInt("quantity"));
			return p;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee getEmployee( String name )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			rs.first();
			
			model.Employee e = new Employee(Integer.toString(rs.getInt("employee_id")), 
					  getBranch(rs.getInt("branch_id")), 
					  rs.getString("name"), 
					  rs.getDate("dateStartedWorking"), 
					  rs.getDouble("hoursRendered"),
					  rs.getString("type"));
			return e;
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee");
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM product");
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE measurement IS NOT null");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Consumable> products = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Consumable p = new Consumable(Integer.toString(rs.getInt("product_id")), 
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE price IS NOT null");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.OverTheCounter> products = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.OverTheCounter p = new OverTheCounter(Integer.toString(rs.getInt("product_id")), 
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM service");
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
			PreparedStatement ps = connection.prepareStatement("INSERT INTO client(name, address, contactNumber, picture, dateJoined, dateLastVisited) "
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
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return -999;
	}
	
	public boolean addTransaction(Transaction t)
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO transaction(client_id, feedback) "
										    + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, Integer.parseInt(t.getClient().getsClientId()));
			ps.setString(2, t.getsFeedback());
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			generatedKeys.next();
			addProductList(t.getProducts(), generatedKeys.getInt(1));
			addServiceList(t.getServices(), generatedKeys.getInt(1));
			
			JOptionPane.showMessageDialog(null, "Transaction has been successfully saved!", "Save Transaction", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private int addProductList(ArrayList<ProductLineItem> products, int nTransactionID)
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO productlist(productlineitem_id, transaction_id) "
					  + "VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
			
			ArrayList<Integer> lineItemIDs;
			lineItemIDs = addProductLineItem(products);
			int genID = -999;
			
			for(int i = 0; i < lineItemIDs.size(); i++)
			{
				ps.setInt(1, lineItemIDs.get(i));
				ps.setInt(2, nTransactionID);
				ps.executeUpdate();
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
			PreparedStatement ps = connection.prepareStatement("INSERT INTO productlineitem(product_id, quantity) "
					  + "VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
			
			ArrayList<Integer> lineItemIDs = new ArrayList<Integer>();
			
			for(int i = 0; i < products.size(); i ++)
			{
				ps.setInt(1, Integer.parseInt(products.get(i).getProduct().getsProductId()));
				ps.setInt(2, products.get(i).getnQuantity());
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				generatedKeys.next();
				lineItemIDs.add(generatedKeys.getInt(1));
			}
			
			return lineItemIDs;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private int addServiceList(ArrayList<ServiceLineItem> services, int nTransactionID)
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO servicelist(servicelineitem_id, transaction_id) "
					  + "VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
			
			ArrayList<Integer> lineItemIDs;
			lineItemIDs = addServiceLineItem(services);
			int genID = -999;
			
			for(int i = 0; i < lineItemIDs.size(); i++)
			{
				ps.setInt(1, lineItemIDs.get(i));
				ps.setInt(2, nTransactionID);
				ps.executeUpdate();
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
			PreparedStatement ps = connection.prepareStatement("INSERT INTO servicelineitem(service_id, quantity, employee_id1, employee_id2) "
					  + "VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			
			ArrayList<Integer> lineItemIDs = new ArrayList<Integer>();
			
			for(int i = 0; i < services.size(); i ++)
			{
				ps.setInt(1, Integer.parseInt(services.get(i).getService().getsServiceId()));
				ps.setInt(2, services.get(i).getnQuantity());
				ps.setInt(3, Integer.parseInt(services.get(i).getEmployee(1).getsEmployeeId()));
				if(services.get(i).getEmployee(2) != null)
				{
					ps.setInt(4, Integer.parseInt(services.get(i).getEmployee(2).getsEmployeeId()));
				}else 
				{
					ps.setNull(4, java.sql.Types.INTEGER);
				}
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				generatedKeys.next();
				lineItemIDs.add(generatedKeys.getInt(1));
			}
			
			return lineItemIDs;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//UPDATE STATEMENS
	public void updateProductQuantity( ArrayList<ProductLineItem> pLineItem )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("UPDATE product SET quantity = ? WHERE name = ?");
			
			for( int i = 0; i < pLineItem.size(); i++ )
			{
				ps.setInt(1, pLineItem.get(i).getProduct().getnQuantity() - pLineItem.get(i).getnQuantity());
				ps.setString(2, pLineItem.get(i).getProduct().getsName());

				ps.execute();
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}