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
	
	public Client getClient( int client_id )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE client_id = ?");
			ps.setInt(1, client_id);
			ResultSet rs = ps.executeQuery();
			
			rs.first();
			
			model.Client c = new Client(Integer.toString(rs.getInt("client_id")),
								   rs.getString("fname"),
								   rs.getString("mname"),
								   rs.getString("lname"),
								   rs.getString("address"), 
								   rs.getString("contactNumber"), 
								   rs.getString("email"), 
								   rs.getDate("dateJoined"), 
								   rs.getDate("dateLastVisited"),
								   rs.getDate("birthday"),
								   rs.getString("gender"));
			return c;
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
								   rs.getString("fname"),
								   rs.getString("mname"), 
								   rs.getString("lname"), 
								   rs.getString("address"), 
								   rs.getString("contactNumber"), 
								   rs.getString("email"), 
								   rs.getDate("dateJoined"), 
								   rs.getDate("dateLastVisited"),
								   rs.getDate("birthday"),
								   rs.getString("gender"));
			return c;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
        public Iterator getClientsByName( String name )
        {
                try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE name LIKE ?%");
                        
                        ps.setString(1, name);
                        
			ResultSet rs = ps.executeQuery();
			
			ArrayList<model.Client> clients = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Client c = new Client(Integer.toString(rs.getInt("client_id")),
									   rs.getString("fname"),
									   rs.getString("mname"),
									   rs.getString("lname"),
									   rs.getString("address"), 
									   rs.getString("contactNumber"), 
									   rs.getString("email"), 
									   rs.getDate("dateJoined"), 
									   rs.getDate("dateLastVisited"),
									   rs.getDate("birthday"),
									   rs.getString("gender"));
				clients.add(c);
			}
			
			return clients.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
        }
        
	public Iterator getAllClients()
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM client");
			ResultSet rs = ps.executeQuery();
			
			ArrayList<model.Client> clients = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Client c = new Client(Integer.toString(rs.getInt("client_id")),
									   rs.getString("fname"),
									   rs.getString("mname"), 
									   rs.getString("lname"), 
									   rs.getString("address"), 
									   rs.getString("contactNumber"), 
									   rs.getString("email"), 
									   rs.getDate("dateJoined"), 
									   rs.getDate("dateLastVisited"),
									   rs.getDate("birthday"),
									   rs.getString("gender"));
				clients.add(c);
			}
			
			return clients.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getProductTransactionsByClient( int client_id )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT P.name as productName, PLI.quantity as quantity, R.date " +
																"FROM taylortyler.client C, taylortyler.transaction T, " +
																"taylortyler.productlist PL, taylortyler.productlineitem PLI, " +
																"taylortyler.receipt R, taylortyler.transactionlist TL, " +
																"taylortyler.product P " +
																"WHERE C.client_id = T.client_id " +
																"AND T.transaction_id = PL.transaction_id " +
																"AND PL.productlineitem_id = PLI.productlineitem_id " +
																"AND R.receipt_id = TL.receipt_id " +
																"AND P.product_id = PLI.product_id " +
																"AND TL.transaction_id = T.transaction_id " +
																"AND C.client_id = ?;");
			ps.setInt(1, client_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<Object[]> ptList = new ArrayList();
			
			while(rs.next())
			{
				Object[] oArray = new Object[3];
				oArray[0] = rs.getString("productName");
				oArray[1] = rs.getInt("quantity");
				oArray[2] = rs.getDate("date");
				ptList.add(oArray);
			}
			return ptList.iterator();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getServiceTransactionByClient( int client_id )
	{
		try{
		PreparedStatement ps = connection.prepareStatement("SELECT S.name as serviceName, concat(E1.fname,\" \", E1.lname) as Senior, concat(E2.fname ,\" \", E2.lname) as Junior, R.date " +
															"FROM taylortyler.client C, taylortyler.transaction T, " +
															"taylortyler.servicelist SL, taylortyler.servicelineitem SLI, " +
															"taylortyler.receipt R, taylortyler.transactionlist TL, " +
															"taylortyler.service S, taylortyler.employee E1, taylortyler.employee E2 " +
															"WHERE C.client_id = T.client_id " +
															"AND T.transaction_id = SL.transaction_id " +
															"AND SL.servicelineitem_id = SLI.servicelineitem_id " +
															"AND R.receipt_id = TL.receipt_id " +
															"AND S.service_id = SLI.service_id " +
															"AND TL.transaction_id = T.transaction_id " +
															"AND E1.employee_id = SLI.employee_id1 " +
															"AND E2.employee_id = SLI.employee_id2 " +
															"AND C.client_id = ?;");
		
		ps.setInt(1, client_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<Object[]> stList = new ArrayList();
			
			while(rs.next())
			{
				Object[] oArray = new Object[4];
				oArray[0] = rs.getString("serviceName");
				oArray[1] = rs.getString("Senior");
				oArray[2] = rs.getString("Junior");
				oArray[3] = rs.getDate("date");
				stList.add(oArray);
			}
			return stList.iterator();
		}catch(Exception e)
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
									rs.getInt("quantity"),
									rs.getInt("threshold"));
			return p;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getProductInfo(int id)
	{
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM product WHERE product_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			rs.first();

			String[] s = { Integer.toString(rs.getInt("product_id")),
					rs.getString("name"), rs.getString("description"),
					Integer.toString(rs.getInt("quantity")),
					Integer.toString(rs.getInt("threshold")),
					Double.toString(rs.getDouble("price")),
					rs.getString("measurement") };
			return s;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Iterator getLackingProducts()
	{
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM product WHERE quantity <= threshold");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Product> products = new ArrayList<>(0);

			while (rs.next())
			{
				model.Product p = new Product(Integer.toString(rs
						.getInt("product_id")), rs.getString("name"),
						rs.getString("description"),
						rs.getInt("quantity"), rs.getInt("threshold"));
				products.add(p);
			}
			return products.iterator();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteProduct(String productID)
	{
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM product WHERE product_id = ?");
			ps.setString(1, productID);
			ps.executeUpdate();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addProduct(Consumable c)
	{
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO product (name, description, quantity, threshold, measurement) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, c.getsName());
			ps.setString(2, c.getsDescription());
			ps.setInt(3, 0);
			ps.setInt(4, c.getnThreshold());
			ps.setString(5, c.getsMeasurement());
			ps.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addProduct(OverTheCounter o)
	{
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO product (name, description, quantity, threshold, price) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, o.getsName());
			ps.setString(2, o.getsDescription());
			ps.setInt(3, 0);
			ps.setInt(4, o.getnThreshold());
			ps.setDouble(5, o.getdPrice());
			ps.executeUpdate();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addProduct(Product p, double price, String measurement)
	{
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO product (name, description, quantity, threshold, price, measurement) VALUES (?, ?, ?, ?, ?, ?);");
			ps.setString(1, p.getsName());
			ps.setString(2, p.getsDescription());
			ps.setInt(3, 0);
			ps.setInt(4, p.getnThreshold());
			ps.setDouble(5, price);
			ps.setString(6, measurement);
			ps.executeUpdate();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editProduct(Product p, double price, String measurement,
			String type)
	{
		System.out.println("Edit");
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("UPDATE product SET description = ?, threshold = ?, price = ?, measurement = ? WHERE product_id = ?");

			ps.setString(1, p.getsDescription());
			ps.setInt(2, p.getnThreshold());

			switch (type)
			{
				case "Consumable":
					ps.setString(4, measurement);
					ps.setNull(3, Types.NULL);
					break;
				case "Over-the-Counter":
					ps.setDouble(3, price);
					ps.setNull(4, Types.NULL);
					break;
				default:
					ps.setDouble(3, price);
					ps.setString(4, measurement);
			}
			ps.setString(5, p.getsProductId());

			ps.executeUpdate();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Iterator filterProducts(String parameter)
	{

		try
		{
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM product WHERE name LIKE ?");
			ps.setString(1, "%" + parameter + "%");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Product> products = new ArrayList<>(0);

			while (rs.next())
			{
				System.out.println("Hey");
				model.Product p = new Product(Integer.toString(rs
						.getInt("product_id")), rs.getString("name"),
						rs.getString("description"),
						rs.getInt("quantity"), rs.getInt("threshold"));
				products.add(p);
			}
			return products.iterator();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;

	}

	public Iterator getAllHybridProducts()
	{
		try
		{
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM product WHERE measurement IS NOT null AND price IS NOT null");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Product> products = new ArrayList<>(0);

			while (rs.next())
			{
				model.Product p = new Product(Integer.toString(rs
						.getInt("product_id")), rs.getString("name"),
						rs.getString("description"),
						rs.getInt("quantity"), rs.getInt("threshold"));
				products.add(p);
			}
			return products.iterator();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getAllStaff()
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE type LIKE 'senior' OR type LIKE 'junior' OR type LIKE 'salonmanager'");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Employee> employees = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Employee e = new Employee(Integer.toString(rs.getInt("employee_id")), 
										  getBranch(rs.getInt("branch_id")), 
										  rs.getString("fname"),
										  rs.getString("mname"),
										  rs.getString("lname"), 
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
					  rs.getString("fname"),
					  rs.getString("mname"),
					  rs.getString("lname"),
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
	
        public Iterator getAllReceipts()
        {
            try
            {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM receipt");
                ResultSet rs = ps.executeQuery();
                ArrayList<model.Receipt> rList = new ArrayList();
                while( rs.next() )
                {
                    model.Receipt r = new Receipt( Integer.toString(rs.getInt("receipt_id")),
                                                    getClient(rs.getInt("client_id")),
                                                    rs.getString("modeOfPayment"),
                                                    rs.getDouble("totalBill") );
                    r.setDateOfReceipt(rs.getDate("date"));
                    r.setTimeOfReceipt(rs.getTime("date"));
                    rList.add(r);
                }
                
                return rList.iterator();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        
        public Iterator getAllProductTransactionsOfReceipt( int receipt_id )
        {
            try
            {
                PreparedStatement ps = connection.prepareStatement("SELECT C.name as clientName, P.name as productName, PLI.quantity, p.price, (PLI.quantity*P.price)   " +
                                                                    "FROM taylortyler.transaction T, taylortyler.receipt R, " +
                                                                    "taylortyler.transactionlist TL, taylortyler.client C, " +
                                                                    "taylortyler.productlist PL, taylortyler.productlineitem PLI, " +
                                                                    "taylortyler.product P " +
                                                                    "where T.transaction_id = TL.transaction_id " +
                                                                    "AND r.receipt_id = TL.receipt_id " +
                                                                    "AND c.client_id = t.client_id " +
                                                                    "AND PL.transaction_id = T.transaction_id " +
                                                                    "AND P.product_id = PLI.product_id " +
                                                                    "AND PL.productlineitem_id = PLI.productlineitem_id " +
                                                                    "AND r.receipt_id = ?;");
                ps.setInt(1, receipt_id);
                ResultSet rs = ps.executeQuery();
                ArrayList<Object[]> ptList = new ArrayList();
                
                while(rs.next())
                {
                    Object[] oArray = new Object[5];
                    oArray[0] = rs.getString("clientName");
                    oArray[1] = rs.getString("productName");
                    oArray[2] = rs.getInt("quantity");
                    oArray[3] = rs.getDouble("price");
                    oArray[4] = rs.getDouble("total");
                    ptList.add(oArray);
                }
                
                return ptList.iterator();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        
        public Iterator getAllServiceTransactionsOfReceipt( int receipt_id )
        {
            try
            {
                PreparedStatement ps = connection.prepareStatement("SELECT C.name as clientName, S.name as serviceName, E1.name as Senior, E2.name as Junior, S.price\n" +
                                                                    "FROM taylortyler.transaction T, taylortyler.receipt R, " +
                                                                    "taylortyler.transactionlist TL, taylortyler.client C, " +
                                                                    "taylortyler.servicelist SL, taylortyler.servicelineitem SLI, " +
                                                                    "taylortyler.service S, taylortyler.employee E1, taylortyler.employee E2 " +
                                                                    "where T.transaction_id = TL.transaction_id " +
                                                                    "AND r.receipt_id = TL.receipt_id " +
                                                                    "AND c.client_id = t.client_id " +
                                                                    "AND SL.transaction_id = T.transaction_id " +
                                                                    "AND S.service_id = SLI.service_id " +
                                                                    "AND SL.servicelineitem_id = SLI.servicelineitem_id " +
                                                                    "AND SLI.employee_id1 = E1.employee_id " +
                                                                    "AND SLI.employee_id2 = E2.employee_id;" + 
                                                                    "AND r.receipt_id = ?;");
                ps.setInt(1, receipt_id);
                ResultSet rs = ps.executeQuery();
                ArrayList<Object[]> stList = new ArrayList();
                
                while(rs.next())
                {
                    Object[] oArray = new Object[5];
                    oArray[0] = rs.getString("clientName");
                    oArray[1] = rs.getString("serviceName");
                    oArray[2] = rs.getString("Senior");
                    oArray[3] = rs.getString("Junior");
                    oArray[4] = rs.getDouble("price");
                    stList.add(oArray);
                }
                
                return stList.iterator();
            }catch(Exception e)
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
										  rs.getString("fname"),
										  rs.getString("mname"),
										  rs.getString("lname"),
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
	
	public Iterator getWorkingEmployees()
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE type LIKE 'senior' OR type LIKE 'junior'");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Employee> wEmployees = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Employee e = new Employee(Integer.toString(rs.getInt("employee_id")), 
										  getBranch(rs.getInt("branch_id")), 
										  rs.getString("fname"),
										  rs.getString("mname"),
										  rs.getString("lname"),
										  rs.getDate("dateStartedWorking"), 
										  rs.getDouble("hoursRendered"),
										  rs.getString("type"));
				wEmployees.add(e);
			}
			return wEmployees.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getSeniorEmployees()
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE type LIKE 'senior'");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Employee> wEmployees = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Employee e = new Employee(Integer.toString(rs.getInt("employee_id")), 
										  getBranch(rs.getInt("branch_id")), 
										  rs.getString("fname"),
										  rs.getString("mname"),
										  rs.getString("lname"),
										  rs.getDate("dateStartedWorking"), 
										  rs.getDouble("hoursRendered"),
										  rs.getString("type"));
				wEmployees.add(e);
			}
			return wEmployees.iterator();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterator getJuniorEmployees()
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM employee WHERE type LIKE 'junior'");
			ResultSet rs = ps.executeQuery();
			ArrayList<model.Employee> wEmployees = new ArrayList<>(0);
			
			while( rs.next() )
			{
				model.Employee e = new Employee(Integer.toString(rs.getInt("employee_id")), 
										  getBranch(rs.getInt("branch_id")), 
										  rs.getString("fname"), 
										  rs.getString("mname"), 
										  rs.getString("lname"), 
										  rs.getDate("dateStartedWorking"), 
										  rs.getDouble("hoursRendered"),
										  rs.getString("type"));
				wEmployees.add(e);
			}
			return wEmployees.iterator();
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
										rs.getInt("quantity"),
										rs.getInt("threshold"));
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
										rs.getInt("threshold"),
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
										rs.getInt("threshold"),
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
			PreparedStatement ps = connection.prepareStatement("INSERT INTO client(fname,mname,lname, address, contactNumber, email, dateJoined, dateLastVisited, birthday, gender) "
																								  + "VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getsFname());
			ps.setString(2, c.getsMname());
			ps.setString(3, c.getsLname());
			ps.setString(4, c.getsAddress());
			ps.setString(5, c.getsContactNumber());
			ps.setString(6, c.getsEmail());
			ps.setDate(7, c.getDateJoined());
			ps.setDate(8, c.getDateLastVisited());
			ps.setDate(9, c.getBirthday());
			ps.setString(10, c.getsGender());
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
	
//        public int addCard(Card c)
//        {
//            try
//            {
//                PreparedStatement ps = connection.prepareStatement("INSERT INTO card(client_id, stamps, status) "
//                                                                + "VALUES (?,?,?)");
//                
//                ps.setInt(1, c.);
//            }catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
                
	public int addTransaction(Transaction t)
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
			return generatedKeys.getInt(1);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return 999;
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
				
				if(services.get(i).getEmployee(1) != null)
				{
					ps.setInt(3, Integer.parseInt(services.get(i).getEmployee(1).getsEmployeeId()));
				}
				else if(services.get(i).getEmployee(1) == null)
				{
					ps.setNull(3, java.sql.Types.INTEGER);
				}
				
				if(services.get(i).getEmployee(2) != null)
				{
					ps.setInt(4, Integer.parseInt(services.get(i).getEmployee(2).getsEmployeeId()));
				}
				else if(services.get(i).getEmployee(2) == null)
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
	
        public void addReceipt(Receipt r)
        {
            try
            {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO receipt(client_id, date, modeOfPayment, totalBill) "
					  + "VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, Integer.parseInt(r.getClient().getsClientId()));
            ps.setString(2, r.getDateOfReceipt().toString() + " " +r.getTimeOfReceipt().toString());
            ps.setString(3, r.getsModeOfPayment());
            ps.setDouble(4, r.getdTotalBill());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            
            addTransactionList(r.getTransactions(), generatedKeys.getInt(1));
            JOptionPane.showMessageDialog(null, "Receipt has been successfully saved!", "Add Receipt", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        public void addTransactionList(ArrayList<Transaction> tList, int nReceiptID)
        {
            try
            {
                for(int i = 0; i < tList.size(); i++)
                {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO transactionlist(receipt_id, transaction_id) "
					  + "VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
                    
                    ps.setInt(1, nReceiptID);
                    ps.setInt(2, Integer.parseInt(tList.get(i).getsTransactionId()));
                    ps.executeUpdate();
                }
            
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
	public void addEmployee( Employee e )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO employee(branch_id, fname, mname, lname, dateStartedWorking, hoursRendered, type)"
																+ "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, Integer.parseInt(e.getBranch().getsBranchId()));
			ps.setString(2, e.getsFname());
			ps.setString(3, e.getsMname());
			ps.setString(4, e.getsLname());
			ps.setDate(5, e.getDateStartedWorking());
			ps.setFloat(6, (float) 0.0);
			ps.setString(7, e.getsType());
			
			ps.executeUpdate();
		}catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	//UPDATE STATEMENTS
	public void updateProductQuantity( ArrayList<ProductLineItem> pLineItem )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("UPDATE product SET quantity = ? WHERE name = ?");
			
			for( int i = 0; i < pLineItem.size(); i++ )
			{
				ps.setInt(1, getProduct(pLineItem.get(i).getProduct().getsName()).getnQuantity() - pLineItem.get(i).getnQuantity());
				ps.setString(2, pLineItem.get(i).getProduct().getsName());

				ps.execute();
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateConsumableQuantity( ArrayList<Product> consumable )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("UPDATE product SET quantity = ? WHERE name = ?");
			
			for( int i = 0; i < consumable.size(); i++ )
			{
				ps.setInt(1, getProduct(consumable.get(i).getsName()).getnQuantity() - 1);
				ps.setString(2, getProduct(consumable.get(i).getsName()).getsName());

				ps.execute();
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
	public void updateEmployee( Employee e )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("UPDATE employee "
																+ "SET branch_id = ?, fname = ?,mname = ?,lname = ?, dateStartedWorking = ?, hoursRendered = ?, type = ? "
																+ "WHERE employee_id = ?");
			ps.setInt(1, Integer.parseInt(e.getBranch().getsBranchId()));
			ps.setString(2, e.getsFname());
			ps.setString(3, e.getsMname());
			ps.setString(4, e.getsLname());
			ps.setDate(5, e.getDateStartedWorking());
			ps.setFloat(6, (float) e.getdHoursRendered());
			ps.setString(7, e.getsType());
			ps.setInt(8, Integer.parseInt(e.getsEmployeeId()));
			
			ps.executeUpdate();
		}catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void updateClient( Client c )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("UPDATE client "
																+ "SET fname = ?, mname = ?, lname = ?, address = ?, contactNumber = ?, email = ?, "
																+ "birthday = ?, gender = ? "
																+ "WHERE client_id = ?");
			
			ps.setString(1, c.getsFname());
			ps.setString(2, c.getsMname());
			ps.setString(3, c.getsLname());
			ps.setString(4, c.getsAddress());
			ps.setString(5, c.getsContactNumber());
			ps.setString(6, c.getsEmail());
			ps.setDate(7, c.getBirthday());
			ps.setString(8, c.getsGender());
			ps.setInt(9, Integer.parseInt(c.getsClientId()));
			
			ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateClientLastVisit( Client c, Date date )
	{
		try
		{
			PreparedStatement ps = connection.prepareStatement("UPDATE client "
																+ "SET dateLastVisited = ? "
																+ "WHERE client_id = ?");
			ps.setDate(1, date);
			ps.setInt(2, Integer.parseInt(c.getsClientId()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}  
}