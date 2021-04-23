 package Model;
import java.sql.*;
public class Item

{ //A common method to connect to the DB
	private Connection connect()
		 {
			 Connection con = null;
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");
				
				 //Provide the correct details: DBServer/DBName, username, password
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "hansana");
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
			 return con;
		 }
	
	public String insertItem(String ProductName, String ProductDesc, String ProductReg , String ProductPrice , Integer InventorID)
		 {
			String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into products(`ProductName`,`ProductDesc`,`ProductReg`,`ProductPrice`,InventorID)"
			 + " values (?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1,ProductName);
			 preparedStmt.setString(2, ProductDesc);
			 preparedStmt.setString(3, ProductReg);
			 preparedStmt.setString(4, ProductPrice);
			 preparedStmt.setInt(5, InventorID);
			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Item Inserted successfully";
		 }
		 catch (Exception e)
		 	{
			 output = "Error while inserting the item.";
			 System.err.println(e.getMessage());
		 	}
		 	return output;
		 }
	public String readItems()
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Product Code</th><th>ProductName</th>" +
		 "<th>ProductDesc</th>" +
		 "<th>ProductReg</th>" +"<th>ProductPrice</th>" +"<th>InventorID</th>" +
		 "<th>Update</th><th>Remove</th></tr>";
		
		 String query = "select * from products";
		 
		 Statement stmt = con.createStatement();
		 
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 
		 while (rs.next())
		 {
		 String ProductID = Integer.toString(rs.getInt("ProductID"));
		 String ProductName = rs.getString("ProductName");
		 String ProductDesc = rs.getString("ProductDesc");
		 String ProductReg = rs.getString("ProductReg");
		 String ProductPrice = rs.getString("ProductPrice");
		 String InventorID = Integer.toString(rs.getInt("InventorID"));
		 
		 // Add into the html table
		 output += "<tr><td>" + ProductID + "</td>";
		 output += "<td>" + ProductName + "</td>";
		 output += "<td>" + ProductDesc + "</td>";
		 output += "<td>" + ProductReg + "</td>";
		 output += "<td>" + ProductPrice + "</td>";
		 output += "<td>" + InventorID + "</td>";
		 // buttons
		 
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='item.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + ProductID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
	}
	 catch (Exception e)
	 {
		 output = "Error while reading the products.";
		 System.err.println(e.getMessage());
	 }
	 	return output;
	 }
public String updateItem( Integer ProductID, String ProductName, String ProductDesc, String ProductReg, String ProductPrice, Integer InventorID)

	 {
	 String output = "";
	 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 
		 // create a prepared statement
		 String query = "UPDATE products SET ProductName=?,ProductDesc=?,ProductReg=?,ProductPrice=?,InventorID=? WHERE ProductID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, ProductName);
		 preparedStmt.setString(2, ProductDesc);
		 preparedStmt.setString(3, ProductReg);
		 preparedStmt.setString(4, ProductPrice);
		 preparedStmt.setInt(5, InventorID);
		 preparedStmt.setInt(6, ProductID);
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 
		 }
	 catch (Exception e)
	 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
public String deleteItem(Integer ProductID)
	 {
	 String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from products where ProductID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1,ProductID);
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 