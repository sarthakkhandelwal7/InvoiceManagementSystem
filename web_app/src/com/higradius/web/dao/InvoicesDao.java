package com.higradius.web.dao;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.higradius.web.model.Invoice;
import java.sql.*;
import java.util.ArrayList;  

public class InvoicesDao 
{
	public Connection getConnection()
	{
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			String URL = "jdbc:mysql://localhost:3306/h2hbabba2697";
			String username = "root";
			String password = "7189";
			return DriverManager.getConnection(URL, username, password);     
		}
		
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		return null;  
	}
	
	public String getInvoices(int start, int limit) throws SQLException 
	{
		ArrayList<Invoice> invoices_list = new ArrayList<Invoice>();
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		
		String query = "SELECT serial_no, name_customer, cust_number, invoice_id, total_open_amount, due_in_date, predicted_payment_date, notes FROM h2hbabba2697 ORDER BY serial_no LIMIT "+start+", "+limit;
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) 
		{
			Invoice inv = new Invoice();
			inv.setSerial_no(rs.getInt("serial_no"));
			inv.setCustomer_name(rs.getString("name_customer"));
			inv.setCustomer_number(rs.getString("cust_number"));
			inv.setInvoice_id(rs.getString("invoice_id"));
			inv.setInvoice_amount(rs.getFloat("total_open_amount"));
			inv.setDue_date(rs.getString("due_in_date"));
			inv.setPredicted_payment_date(rs.getString("predicted_payment_date"));
			inv.setNotes(rs.getString("notes"));
			invoices_list.add(inv);
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		// converting list to json string
		String jsonInvoiceList = gson.toJson(invoices_list);
		
		stmt.close();
		con.close();
		return jsonInvoiceList;
	}
	
	public void addInvoice(String name, String cust_no, String invoice_id, float amount, float due_date, String predicted_date, String notes) throws SQLException 
	{
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		
		String values = "VALUES("+name+", "+cust_no+", "+invoice_id+", "+amount+", "+due_date+", "+predicted_date+", "+notes+")";
		String query = "INSERT INTO h2hbabba2697(name_customer, cust_number, invoice_id, total_open_amount, due_in_date, predicted_payment_date, notes) " + values;
		stmt.executeUpdate(query);
		stmt.close();
		con.close();
	}
	
	public void editInvoice(int serial_no, float amount, String notes) throws SQLException {		
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		String query = "UPDATE h2hbabba2697 SET total_open_amount = "+amount+", notes = "+notes+" WHERE serial_no = "+serial_no;
		stmt.executeUpdate(query);
	}
	
	public void deleteInvoices(String toDelete) throws SQLException {
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		String query = "DELETE FROM h2hbabba2697 WHERE serial_no IN "+toDelete;
		stmt.executeUpdate(query);
		
	}
}

