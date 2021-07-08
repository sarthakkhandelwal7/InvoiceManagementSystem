package com.higradius;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higradius.web.dao.InvoicesDao;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
/**
 * Servlet implementation class AddInvoice
 */
@SuppressWarnings("serial")
@WebServlet("/addInvoice")
public class AddInvoice extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InvoicesDao dao = new InvoicesDao();
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader =  request.getReader();
		while ((line = reader.readLine()) != null)
		      jb.append(line);
		
		String jsonString = jb.toString(); 
		Object obj;
		
		try {
			obj = new JSONParser().parse(jsonString);
			JSONObject json = (JSONObject) obj;
			String name = "'" + (String) json.get("customer_name") + "'";
			String cust_no = "'" + (String) json.get("customer_number")+ "'";
			String invoice_id = "'" + (String) json.get("invoice_id")+ "'";
			float amount = (float) ((long) json.get("invoice_amount"));
			float due_date = (float) ((long) json.get("due_date"));
			String notes = "'" + (String) json.get("notes")+ "'";
			dao.addInvoice(name, cust_no, invoice_id, amount, due_date, "'none'", notes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
