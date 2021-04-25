package main.java.model;

import java.sql.*; 

public class Payment {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 }	
	
	public String insertPayment(String paymentType) 
	
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into payment 
	 (`paymentID`,`paymentType`,`paymentAmount`)"+ " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, paymentType); 
	 preparedStmt.setFloat(4, Float.parseFloat(amount)); 
	  
	// execute the statement3
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the payment."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	public String readpayments() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment Type</th><th>Payment Amount</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from payments"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String paymentID = Integer.toString(rs.getInt("paymentID")); 
	 String paymentType = rs.getString("paymentType"); 
	 String paymentAmount = Float.toString(rs.getFloat("paymentAmount")); 
	  
	 // Add into the html table
	 output += "<tr><td>" + paymentType + "</td>"; 
	 output += "<td>" + paymentAmount + "</td>"; 
	 
	 // buttons
	 output += "<td><input name='btnUpdate'  type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='payment.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
	 + "<input name='paymentID' type='hidden' value='" + paymentID + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the payments."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	public String updatePayment(String ID, String paymentType, String Amount)
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE payments SET paymentType=?,paymentAmount=? WHERE paymentID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, paymentType); 
	 preparedStmt.setFloat(2, Float.parseFloat(Amount)); 
	  preparedStmt.setInt(3, Integer.parseInt(ID)); 
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
	
	public String deletePayment(String paymentID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from payments where paymentID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the payment."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

	public String insertPayment(String paymentType, String paymentAmount) {
		// TODO Auto-generated method stub
		return null;
	} 	

}
