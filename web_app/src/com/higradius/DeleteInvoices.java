package com.higradius;

import java.io.BufferedReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import java.util.Iterator;
import com.higradius.web.dao.InvoicesDao;

/**
 * Servlet implementation class DeleteInvoices
 */
@SuppressWarnings("serial")
@WebServlet("/deleteInvoices")
public class DeleteInvoices extends HttpServlet {
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
	{
		StringBuffer jb = new StringBuffer();
		String line = null;
		InvoicesDao dao = new InvoicesDao();
		BufferedReader reader =  request.getReader();
		while ((line = reader.readLine()) != null)
		      jb.append(line);
		Object obj;
		try {
			obj = new JSONParser().parse(jb.toString());
			JSONArray serial_no = (JSONArray) obj;
			
			@SuppressWarnings("rawtypes")
			Iterator iter = serial_no.iterator();
			String toDelete = "(";
			while(iter.hasNext())
			{
				toDelete = toDelete+iter.next()+", ";
			}
			toDelete = toDelete.substring(0, toDelete.length()-2)+")";
			dao.deleteInvoices(toDelete);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
