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
			String query = "SELECT * FROM h2hbabba2697 WHERE business_code = 'CA02'";
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			String name = rs.getString("name_customer");
//			while(rs.next())  
			System.out.println(name); 
			stmt.close();
			con.close();  
		}
		
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
	}  
}  