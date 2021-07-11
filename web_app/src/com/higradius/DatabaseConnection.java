package com.higradius;
import java.sql.*;  
class MysqlCon
{  
	public static void main(String args[])
	{  
		System.out.println("Started");
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/h2hbabba2697","root","7189");   
			Statement stmt=con.createStatement();  
			String query = "SELECT name_customer, cust_number, invoice_id, total_open_amount, due_in_date, predicted_payment_date, notes FROM h2hbabba2697";
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			
//			inv.setCustomer_number(rs.getString("cust_number"));
			System.out.println(rs.getString("name_customer"));
			System.out.println(rs.getString("cust_number"));
			System.out.println(rs.getString("invoice_id"));
			System.out.println(rs.getFloat("total_open_amount"));
			System.out.println(rs.getString("due_in_date"));
			System.out.println(rs.getString("predicted_payment_date"));
			System.out.println(rs.getString("notes"));

//		}
			stmt.close();
			con.close();  
		}
		
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
	}  
}  