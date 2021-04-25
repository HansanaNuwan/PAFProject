package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Item {

	//A common method to connect to the DB
		private Connection connect() 
		{ 
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
		 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/item", "root", ""); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			return con; 
		 } 
		
		
		public String insertItem(String code, String name, String price, String desc,int quantity) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for inserting."; 
				} 
				// create a prepared statement
				String query = " insert into item_tb (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`,`quantity`)"+ " values (?, ?, ?, ?, ? , ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, code); 
				preparedStmt.setString(3, name); 
				preparedStmt.setDouble(4, Double.parseDouble(price)); 
				preparedStmt.setString(5, desc); 
				preparedStmt.setInt(6, quantity);
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while inserting the item."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		 } 
		
		public Map<String,String> getItems() {
			String output = ""; 
			Map<String,String> results = new HashMap<String, String>();
			try
			{ 
				Connection con = connect(); 

				// Prepare the html table to be displayed
				
		 
				String query = "select * from item_tb"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String itemID = Integer.toString(rs.getInt("itemID")); 
					String itemCode = rs.getString("itemCode"); 
					String itemName = rs.getString("itemName"); 
					String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
					String itemDesc = rs.getString("itemDesc"); 
					String quantity = Integer.toString(rs.getInt("quantity"));
					
					results.put("itemID", itemID);
					results.put("itemCode", itemCode);
					results.put("itemName", itemName);
					results.put("itemPrice", itemPrice);
					results.put("itemDesc", itemDesc);
					results.put("quantity", quantity);				
							
				} 
				con.close(); 
				
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the items."; 
				System.err.println(e.getMessage()); 
			} 
			return results;
		}
		
		public String readItems() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for reading."; 
				} 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>ItemID</th>"
						+ "<th>ItemCode</th>"
						+ "<th>Item Name</th>" +
						"<th>Item Price</th>" + 
						"<th>Item Description</th>" +
						"<th>Quantity</th>"+
						"<th>Update</th><th>Remove</th></tr>"; 
		 
				String query = "select * from item_tb"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String itemID = Integer.toString(rs.getInt("itemID")); 
					String itemCode = rs.getString("itemCode"); 
					String itemName = rs.getString("itemName"); 
					String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
					String itemDesc = rs.getString("itemDesc"); 
					String quantity = Integer.toString(rs.getInt("quantity"));
					// Add into the html table
					output += "<tr><td>" + itemID + "</td>"; 
					output += "<tr><td>" + itemCode + "</td>"; 
					output += "<td>" + itemName + "</td>"; 
					output += "<td>" + itemPrice + "</td>"; 
					output += "<td>" + itemDesc + "</td>";
					output += "<td>" + quantity + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						   + "<td><form method='delete' action='item.jsp'>"
						   + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						   + "<input name='itemID' type='hidden' value='" + itemID + "'>" + "</form></td></tr>"; 
				} 
				con.close(); 
				// Complete the html table
				output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the items."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		 } 
		
		
		public String updateItem(String ID, String code, String name, String price, String desc, String quantity)
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 // create a prepared statement
				 String query = "UPDATE item_tb SET itemCode=?,itemName=?,itemPrice=?,itemDesc=? ,quantity=? WHERE itemID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setString(1, code); 
				 preparedStmt.setString(2, name); 
				 preparedStmt.setDouble(3, Double.parseDouble(price)); 
				 preparedStmt.setString(4, desc); 
				 preparedStmt.setInt(5, Integer.parseInt(quantity)); 
				 preparedStmt.setInt(6, Integer.parseInt(ID)); 
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
		
		public String deleteItem(String itemID) 
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for deleting.";
				 } 
				 // create a prepared statement
				 String query = "delete from item_tb where itemID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
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
