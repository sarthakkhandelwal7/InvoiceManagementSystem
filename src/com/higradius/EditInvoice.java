package com.higradius;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.higradius.web.dao.InvoicesDao;

/**
 * Servlet implementation class EditInvoice
 */
@SuppressWarnings("serial")
@WebServlet("/editInvoice")
public class EditInvoice extends HttpServlet {
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*System.out.println(request.getParameter("serial_no"));
		int serial_no = Integer.parseInt(request.getParameter("serial_no"));
		float amount = Float.parseFloat(request.getParameter("invoice_amount"));
		String notes = request.getParameter("notes");
		System.out.println(serial_no + " " + amount + " " + notes);*/
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
			float amount = (float) ((long) json.get("invoice_amount"));
			int serial_no = (int) ((long) json.get("serial_no"));
			String notes = "'" + (String) json.get("notes")+ "'";
			dao.editInvoice(serial_no, amount, notes);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
